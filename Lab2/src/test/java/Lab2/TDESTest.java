package Lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TDESTest {
    @Test
    void testEncrypt() {
        TDES tdes = new TDES();

        String res = tdes.encrypt("Hello World", "StrongPassword", false);
        String exp = "\u00eb\u00b4\u0057\u0058\u0088\u0000\u006e\u006f\u00a7\u00b7\u0023\u0014\u00fe\n\u0012\u006c";

        assertEquals(exp, res);
    }

    @Test
    void testDecrypt() {
        TDES tdes = new TDES();

        String res = tdes.decrypt("\u00eb\u00b4\u0057\u0058\u0088\u0000\u006e\u006f\u00a7\u00b7\u0023\u0014\u00fe\n\u0012\u006c",
                "StrongPassword", false);

        assertEquals("Hello World#####", res);
    }
}