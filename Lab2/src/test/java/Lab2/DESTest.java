package Lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DESTest {
    @Test
    void testEncrypt() {
        DES des = new DES();

        String res = des.encrypt("Hello", "World", false);

        assertEquals("14C3957F19C3AEC394C2AA2D", res);
    }

    @Test
    void testDecrypt() {
        DES des = new DES();

        String res = des.decrypt("14C3957F19C3AEC394C2AA2D", "World", false);

        assertEquals("Hello###", res);
    }
}