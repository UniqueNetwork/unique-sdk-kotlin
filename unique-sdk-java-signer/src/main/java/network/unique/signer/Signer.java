package network.unique.signer;

public class Signer {
    static {
        System.loadLibrary("signer");
    }

    static native long jPairInitSuri(int scheme, String suri, String password);
    static native void jPairFree(long nativeHandle);
    static native byte[] jPairSign(long nativeHandle, byte[] message);
}
