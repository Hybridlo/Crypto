package lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitArrayTest {
    @Test
    void testAdd() {
        BitArray array = new BitArray();
        array.add(true);

        assertTrue(array.get(0));

        array.add(false);

        assertFalse(array.get(1));
    }

    @Test
    void testAddToStart() {
        BitArray array = new BitArray();
        array.add(true);
        array.add(true);

        array.addToStart(false);

        assertFalse(array.get(0));
    }

    @Test
    void testSize() {
        BitArray array = new BitArray();
        array.add(true);
        array.add(true);
        array.add(true);

        assertEquals(3, array.size());
    }

    @Test
    void testSubArray() {
        BitArray array = new BitArray();
        array.add(true);
        array.add(false);
        array.add(true);
        array.add(false);
        array.add(true);
        array.add(false);

        BitArray res = array.subArray(2, 4);

        assertTrue(res.get(0));
        assertFalse(res.get(1));
    }

    @Test
    void testToInt() throws Exception {
        BitArray array = new BitArray();
        array.add(true);
        array.add(false);
        array.add(true);
        array.add(false);
        array.add(true);
        array.add(false);

        assertEquals(21, array.toInt());
    }

    @Test
    void testToHex() throws Exception {
        BitArray array = new BitArray();
        array.add(true);
        array.add(false);
        array.add(true);
        array.add(false);
        array.add(true);
        array.add(false);

        assertEquals("15", array.toHex());
    }

    @Test
    void testFromBytes() {
        BitArray array = BitArray.fromBytes("abcd".getBytes());

        String binary = "10000110010001101100011000100110";

        for (int i = 0; i < array.size(); i++)
            assertEquals(binary.charAt(i), array.get(i) ? '1' : '0');
    }

    @Test
    void testFromLongs() {
        BitArray array = BitArray.fromLongs(new long[]{8000});

        String binary = "0000001011111";

        for (int i = 0; i < array.size(); i++)
            assertEquals(binary.charAt(i), array.get(i) ? '1' : '0');
    }
}