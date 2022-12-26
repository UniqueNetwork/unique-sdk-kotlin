use std::{mem::transmute, ptr::null_mut, result, str::Utf8Error};

use jni::{
    errors::Error as JniError,
    objects::{JClass, JString},
    strings::JavaStr,
    sys::{jbyteArray, jlong, jstring},
    JNIEnv,
};
use serde_json::json;
use sp_core::{crypto::{SecretStringError, default_ss58_version, Ss58Codec}, Pair, hexdisplay::HexDisplay};
use sp_runtime::{traits::IdentifyAccount, MultiSigner};

type Result<T, E = Error> = result::Result<T, E>;

#[derive(thiserror::Error, Debug)]
pub enum Error {
    #[error("invalid argument: {0}")]
    InvalidArgument(&'static str),
    #[error("invalid utf-8: {0}: {1}")]
    InvalidUtf8(&'static str, Utf8Error),
    #[error("secret string: {0:?}")]
    SecretString(SecretStringError),
    #[error("jni error: {0}")]
    Jni(#[from] JniError),
}
impl From<SecretStringError> for Error {
    fn from(value: SecretStringError) -> Self {
        Self::SecretString(value)
    }
}
impl Error {
    fn into_env(self, env: JNIEnv) {
        let _ = match self {
            Error::InvalidArgument(s) => env.throw_new("java/lang/InvalidArgumentException", s),
            Error::SecretString(s) => env.throw_new(
                "network/unique/signer/exception/SecretStringException",
                format!("{s:?}"),
            ),
            Error::Jni(e) => env.throw_new("java/lang/RuntimeException", e.to_string()),
            Error::InvalidUtf8(location, message) => env.throw_new(
                "network/unique/signer/exception/InvalidUtf8Exception",
                format!("{location}: {message}"),
            ),
        };
    }
}

fn get_string<'s>(env: &'s JNIEnv, str: JString<'s>) -> Result<Option<JavaStr<'s, 's>>> {
    match env.get_string(str) {
        Ok(v) => Ok(Some(v)),
        Err(JniError::NullPtr(_)) => Ok(None),
        Err(e) => Err(e.into()),
    }
}
unsafe fn get_jpair(_env: JNIEnv, signer: jlong) -> &&dyn JPair {
    assert!(signer != 0, "pair is already freed");
    transmute(signer as usize)
}

fn i2u(v: &[i8]) -> &[u8] {
    // Safety: &[u8] has the same layout as &[i8]
    unsafe { transmute(v) }
}
fn u2i(v: &[u8]) -> &[i8] {
    // Safety: &[u8] has the same layout as &[i8]
    unsafe { transmute(v) }
}

trait JPair {
    fn sign(&self, env: &JNIEnv, input: &[u8]) -> Result<jbyteArray>;
}
impl<T: Pair> JPair for T {
    fn sign(&self, env: &JNIEnv, input: &[u8]) -> Result<jbyteArray> {
        let signature = self.sign(input);
        let signature = signature.as_ref();
        let array = env.new_byte_array(signature.len() as i32)?;
        env.set_byte_array_region(array, 0, u2i(signature))?;
        Ok(array)
    }
}

fn init_suri<P: Pair>(env: JNIEnv, suri: JString, password: JString) -> Result<Box<dyn JPair>> {
    let suri =
        get_string(&env, suri)?.ok_or_else(|| Error::InvalidArgument("suri should not be null"))?;
    let suri = suri.to_str().map_err(|e| Error::InvalidUtf8("suri", e))?;
    let password = get_string(&env, password)?;
    let password = password
        .as_ref()
        .map(|s| s.to_str())
        .transpose()
        .map_err(|e| Error::InvalidUtf8("password", e))?;
    let pair = P::from_string(suri, password)?;
    Ok(Box::new(pair))
}
fn sign(env: JNIEnv, signer: &dyn JPair, input: jbyteArray) -> Result<jbyteArray> {
    let size = env.get_array_length(input)?;
    let mut input_buf = vec![0; size as usize];
    env.get_byte_array_region(input, 0, &mut input_buf)?;

    signer.sign(&env, i2u(input_buf.as_slice()))
}

#[repr(u32)]
#[derive(Debug)]
pub enum CryptoScheme {
    Ed25519 = 0,
    Sr25519,
    EcDSA,
}

#[no_mangle]
pub extern "system" fn Java_network_unique_signer_Signer_jPairInitSuri(
    env: JNIEnv,
    _class: JClass,
    pair_type: CryptoScheme,
    suri: JString,
    password: JString,
) -> jlong {
    let result = match pair_type {
        CryptoScheme::Ed25519 => init_suri::<sp_core::ed25519::Pair>(env, suri, password),
        CryptoScheme::Sr25519 => init_suri::<sp_core::sr25519::Pair>(env, suri, password),
        CryptoScheme::EcDSA => init_suri::<sp_core::ecdsa::Pair>(env, suri, password),
    };
    let signer = match result {
        Ok(v) => v,
        Err(e) => {
            e.into_env(env);
            return 0;
        }
    };
    Box::into_raw(Box::new(signer)) as usize as i64
}

/// # Safety
/// JPair should be obtained from jPairInit* methods, and passed to this method only once
#[no_mangle]
pub unsafe extern "system" fn Java_network_unique_signer_Signer_jPairFree(
    _env: JNIEnv,
    _class: JClass,
    jpair: jlong,
) {
    let _ = <Box<Box<dyn JPair>>>::from_raw(jpair as usize as *mut _);
}

/// # Safety
/// JPair should be obtained from jPairInit* methods
#[no_mangle]
pub unsafe extern "system" fn Java_network_unique_signer_Signer_jPairSign(
    env: JNIEnv,
    _class: JClass,
    jpair: jlong,
    data: jbyteArray,
) -> jbyteArray {
    match sign(env, unsafe { *get_jpair(env, jpair) }, data) {
        Ok(v) => v,
        Err(e) => {
            e.into_env(env);
            null_mut()
        }
    }
}

/// Seed type for Runtime
pub type SeedFor<P> = <P as sp_core::Pair>::Seed;

/// Public key type for Runtime
pub type PublicFor<P> = <P as sp_core::Pair>::Public;

/// formats seed as hex
pub fn format_seed<P: sp_core::Pair>(seed: SeedFor<P>) -> String {
	format!("0x{}", HexDisplay::from(&seed.as_ref()))
}

/// formats public key as hex
fn format_public_key<P: sp_core::Pair>(public_key: PublicFor<P>) -> String {
	format!("0x{}", HexDisplay::from(&public_key.as_ref()))
}

/// formats public key as accountId as hex
fn format_account_id<P: sp_core::Pair>(public_key: PublicFor<P>) -> String
where
	PublicFor<P>: Into<MultiSigner>,
{
	format!("0x{}", HexDisplay::from(&public_key.into().into_account().as_ref()))
}

fn generate<P: Pair>(env: JNIEnv, password: JString) -> Result<String>
where
    P: sp_core::Pair,
    P::Public: Into<MultiSigner>,
{
    let password = get_string(&env, password)?;
    let password = password
        .as_ref()
        .map(|s| s.to_str())
        .transpose()
        .map_err(|e| Error::InvalidUtf8("password", e))?;

    let (pair, phrase, seed) = P::generate_with_phrase(password);
    let public_key = pair.public();
    let network_override = default_ss58_version();
    
    let json = json!({
        "secretPhrase": phrase,
        "networkId": String::from(network_override),
        "secretSeed": format_seed::<P>(seed),
        "publicKey": format_public_key::<P>(public_key.clone()),
        "ss58PublicKey": public_key.to_ss58check_with_version(network_override),
        "accountId": format_account_id::<P>(public_key),
        "ss58Address": pair.public().into().into_account().to_ss58check_with_version(network_override),
    });
    Ok(serde_json::to_string_pretty(&json).expect("Json pretty print failed"))
}

/// # Safety
/// JPair should be obtained from jPairInit* methods
#[no_mangle]
pub unsafe extern "system" fn Java_network_unique_signer_Signer_jPairGenerate(
    env: JNIEnv,
    _class: JClass,
    pair_type: CryptoScheme,
    password: JString
) -> jstring {
    let result = match pair_type {
        CryptoScheme::Ed25519 => generate::<sp_core::ed25519::Pair>(env, password),
        CryptoScheme::Sr25519 => generate::<sp_core::sr25519::Pair>(env, password),
        CryptoScheme::EcDSA => generate::<sp_core::ecdsa::Pair>(env, password),
    };

    match result {
        Ok(json) => {
            let output = env.new_string(format!("{}", json))
                .expect("Couldn't create java string!");

            return output.into_raw()
        },
        Err(e) => {
            e.into_env(env);
            let output = env.new_string(format!("{}", "Error"))
                .expect("Couldn't create java string!");

            return output.into_raw()
        }
    };
}
