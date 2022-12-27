# Unique SKD Kotlin
[![Maven Central JVM](https://img.shields.io/maven-central/v/network.unique/unique-sdk-jvm.svg?label=Maven%20Central%20JVM)](https://search.maven.org/search?q=g:%22network.unique%22%20AND%20a:%22unique-sdk-jvm%22)
[![Maven Central Signer](https://img.shields.io/maven-central/v/network.unique/unique-sdk-jvm.svg?label=Maven%20Central%20Signer)](https://search.maven.org/search?q=g:%22network.unique%22%20AND%20a:%22unique-sdk-java-signer%22)
[![Maven Central Android](https://img.shields.io/maven-central/v/network.unique/unique-sdk-jvm.svg?label=Maven%20Central%20Android)](https://search.maven.org/search?q=g:%22network.unique%22%20AND%20a:%22unique-sdk-android%22)
![Publishing](https://github.com/UniqueNetwork/unique-sdk-kotlin/actions/workflows/gradle-publishing.yml/badge.svg)


Unique SDK Kotlin is a Kotlin library for dealing with polkadot blockchains.

## Installation

Use the Maven Central to download unique-sdk-kotlin.

```maven
<dependency>
  <groupId>network.unique</groupId>
  <artifactId>unique-sdk-jvm</artifactId>
  <version>0.0.1</version>
</dependency>
```

And also you can download our library for key management on Android.

```maven
<dependency>
  <groupId>network.unique</groupId>
  <artifactId>unique-sdk-android</artifactId>
  <version>0.0.1</version>
</dependency>
```

## Usage

### unique-sdk-kotlin
First of all, create Signer and SDK Object

```kotlin
// Create signer from suri and password
val signer = Sr25519SignerWrapper(seed, seedPassword, false)
// Or generate signer with password
val signer = Sr25519SignerWrapper(null, seedPassword, true)
// Or generate signer without password
val signer = Sr25519SignerWrapper(null, null, true)

// Assign signer to SDK static field
UniqueSdk.signerWrapper = signerWrapper

// create SDK god object. First parameter - signer, second - base url of backend of blockchain
val sdk = UniqueSdk("https://rest.opal.uniquenetwork.dev")
```

Signer has ```sign``` function for payload signing. When you using ```UniqueSdk``` methods they automatically use inner signer passed by parameter.

After we can use inner services of SDK

```kotlin
// Getting balance service for balance reading or manipulation
val balanceService: BalanceService = sdk.balanceService;
```

Some methods of service can be used with many parameters based on template, which we named ```Mutation```.
One of these mutations is balance transfering.

```kotlin
/// Getting balance transfering mutation
val transferService = balanceService.getTransfer();
```

```Mutation``` has three main methods for executing:
- Build
- Sign
- Submit

Let's see how it's working
```kotlin
// Creating payload for balance transfering
val transferBody = TransferMutationRequest(
//from
    "5DnUE1uV7iW25bUriWVPHY67KMm2t6g5v23GzVbZCUc8fyBD",
//to
    "unjKJQJrRd238pkUZZvzDQrfKuM39zBSnQ5zjAGAGcdRhaJTx",
//amount
    BigDecimal("0.01")
)

// Build transaction
val transferResponse = transferMutationService.build(transferBody)

val signBody = UnsignedTxPayloadResponse(
    transferResponse.signerPayloadJSON,
    transferResponse.signerPayloadRaw,
    transferResponse.signerPayloadHex
)

// Sign transaction via inner signer
val signResponse = transferService.sign(signBody)

val submitBody = SubmitTxBody(signResponse.signerPayloadJSON, signResponse.signature)

// Submit transaction and track result with web server
val submitResponse = transferService.submitWatch(submitBody)
```

After all executions we can see transaction hash

```kotlin
println(submitResponse.hash)
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)