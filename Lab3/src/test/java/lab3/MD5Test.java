package lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5Test {
    @Test
    void testHash() throws Exception {
        MD5 md5 = new MD5();

        assertEquals("8b1a9953c4611296a827abf8c47804d7", md5.hash("Hello"));
        assertEquals("e509465ef513154988e088d6ad3c21bf", md5.hash("World!"));
        assertEquals("ed076287532e86365e841e92bfc50d8c", md5.hash("Hello World!"));
    }
}