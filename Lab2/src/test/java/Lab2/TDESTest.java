package Lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TDESTest {
    @Test
    void testEncrypt() {
        TDES tdes = new TDES();

        String res = tdes.encrypt("Hello World", "StrongPassword", false);
        String exp = "C3ABC2B45758C288006E6FC2A7C2B72314C3BE0A126C";

        assertEquals(exp, res);
    }

    @Test
    void testDecrypt() {
        TDES tdes = new TDES();

        String res = tdes.decrypt("C3ABC2B45758C288006E6FC2A7C2B72314C3BE0A126C",
                "StrongPassword", false);

        assertEquals("Hello World#####", res);
    }
}