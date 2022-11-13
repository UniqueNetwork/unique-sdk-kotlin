package network.unique.signer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignerTest {
    @Test
    public void sr25519Basic() throws Exception {
        Pair pair = Pair.fromSuri(CryptoScheme.Sr25519, "//Alice", null);

        byte[] signature = pair.sign(new byte[] {1, 2, 3});

        assertEquals(signature.length, 64);
    }

    @Test
    public void ed25519Basic() throws Exception {
        Pair pair = Pair.fromSuri(CryptoScheme.Ed25519, "//Alice", null);

        byte[] signature = pair.sign(new byte[] {1, 2, 3});

        assertEquals(signature.length, 64);
    }

    @Test
    public void ecdsaBasic() throws Exception {
        Pair pair = Pair.fromSuri(CryptoScheme.EcDSA, "//Alice", null);

        byte[] signature = pair.sign(new byte[] {1, 2, 3});

        assertEquals(signature.length, 65);
    }
}
