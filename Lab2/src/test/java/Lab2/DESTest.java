package Lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DESTest {
    @Test
    void testEncrypt() {
        DES des = new DES();

        String res = des.encrypt("Hello", "World", false);

        assertEquals("\u0014\u00d5\u007F\u0019\u00ee\u00d4\u00aa\u002d",    //utf-16 string, because using actual result
                res);                                                                //can break stuff
    }

    @Test
    void testDecrypt() {
        DES des = new DES();

        String res = des.decrypt("\u0014\u00d5\u007F\u0019\u00ee\u00d4\u00aa\u002d", "World", false);

        assertEquals("Hello###", res);
    }
}