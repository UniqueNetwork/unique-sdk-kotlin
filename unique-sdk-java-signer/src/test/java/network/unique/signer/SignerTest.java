package network.unique.signer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignerTest {
    @Test
    public void sr25519Basic() {
        Pair pair = Pair.fromSuri(CryptoScheme.Sr25519, "//Alice", null);

        byte[] signature = pair.sign(new byte[] {1, 2, 3});

        assertEquals(signature.length, 64);
    }

    @Test
    public void ed25519Basic() {
        Pair pair = Pair.fromSuri(CryptoScheme.Ed25519, "//Alice", null);

        byte[] signature = pair.sign(new byte[] {1, 2, 3});

        assertEquals(signature.length, 64);
    }

    @Test
    public void ecdsaBasic() {
        Pair pair = Pair.fromSuri(CryptoScheme.EcDSA, "//Alice", null);

        byte[] signature = pair.sign(new byte[] {1, 2, 3});

        assertEquals(signature.length, 65);
    }

    @Test
    public void generate() {
        String pairJson = Pair.generate(CryptoScheme.Sr25519, null);

        Pair pair = Pair.fromSuri(CryptoScheme.Sr25519, pairJson.substring(219, pairJson.indexOf('"', 219)), null);

        byte[] signature = pair.sign(new byte[] {1, 2, 3});

        assertEquals(signature.length, 64);
    }
}
