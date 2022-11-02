package network.unique.signer;

public class Pair {
    long nativeHandle;

    private Pair(long nativeHandle) {
        this.nativeHandle = nativeHandle;
    }

    public byte[] sign(byte[] message) {
        byte[] result = Signer.jPairSign(nativeHandle, message);
        if (result == null) throw new IllegalStateException("unknown native failure");
        return result;
    }

    public static Pair fromSuri(CryptoScheme scheme, String suri, String password) {
        long nativeHandle = Signer.jPairInitSuri(scheme.ordinal(), suri, password);
        if (nativeHandle == 0) throw new IllegalStateException("unknown native failure");
        return new Pair(nativeHandle);
    }

    public void close() {
        Signer.jPairFree(nativeHandle);
    }
}
