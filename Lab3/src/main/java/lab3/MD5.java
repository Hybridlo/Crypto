package lab3;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MD5 {
    private static final int INIT_A = 0x67452301;
    private static final int INIT_B = (int)0xEFCDAB89L;
    private static final int INIT_C = (int)0x98BADCFEL;
    private static final int INIT_D = 0x10325476;

    private int A;
    private int B;
    private int C;
    private int D;

    String hash(String input) {
        BitArray array = step1(input);
        step2(input, array);
        step3();
        step4(array);
        return step5();
    }

    private BitArray step1(String input) {
        BitArray bits = BitArray.fromBytes(input.getBytes(StandardCharsets.UTF_8));

        for (int i = 0; i < 7; i++)
            bits.add(false);
        bits.add(true);
        while (bits.size() % 512 != 448)
            bits.add(false);

        return bits;
    }

    private void step2(String input, BitArray step1res) {
        long[] length = {(long) ((input.length() * 8) % Math.pow(2, 64))};

        BitArray bits = BitArray.fromLongs(length);
        int initSize = bits.size();
        for (int i = 0; i < 64 - initSize; i++)
            bits.add(false);

        step1res.extend(bits);
    }

    private void step3() {
        A = INIT_A;
        B = INIT_B;
        C = INIT_C;
        D = INIT_D;
    }

    private int funcF(int x, int y, int z) {
        return (x & y) | (~x & z);
    }

    private int funcG(int x, int y, int z) {
        return (x & z) | (y & ~z);
    }

    private int funcH(int x, int y, int z) {
        return x ^ y ^ z;
    }

    private int funcI(int x, int y, int z) {
        return y ^ (x | ~z);
    }

    private int modAdd(int a, int b) {
        return (int) ((a + b) % Math.pow(2, 32));
    }

    private int[] sineTable() {
        int size = 64;
        int[] res = new int[size];

        for (int i = 0; i < size; i++) {
            res[i] = (int)(long) Math.floor(Math.pow(2, 32) * Math.abs(Math.sin(i + 1)));
        }

        return res;
    }

    private void step4(BitArray step2res) {
        int[] table = sineTable();

        int n = step2res.size() / 32;

        for (int chunk_index = 0; chunk_index < n / 16; chunk_index++) {
            int start = chunk_index * 512;

            int size = 16;
            List<BitArray> x = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                x.add(step2res.subArray(start + (i * 32), start + (i * 32) + 32));
            }

            List<Integer> x2 = new ArrayList<>();

            for (BitArray array : x)
                x2.add(array.toInt());

            int tmpA = A;
            int tmpB = B;
            int tmpC = C;
            int tmpD = D;

            int k;
            int[] s;

            int temp;

            for (int i = 0; i < 4 * 16; i++) {
                if (i <= 15) {
                    k = i;
                    s = new int[]{7, 12, 17, 22};
                    temp = funcF(tmpB, tmpC, tmpD);
                } else if (i <= 31) {
                    k = ((5 * i) + 1) % 16;
                    s = new int[]{5, 9, 14, 20};
                    temp = funcG(tmpB, tmpC, tmpD);
                } else if (i <= 47) {
                    k = ((3 * i) + 5) % 16;
                    s = new int[]{4, 11, 16, 23};
                    temp = funcH(tmpB, tmpC, tmpD);
                } else {
                    k = (7 * i) % 16;
                    s = new int[]{6, 10, 15, 21};
                    temp = funcI(tmpB, tmpC, tmpD);
                }

                temp = modAdd(temp, x2.get(k));
                temp = modAdd(temp, table[i]);
                temp = modAdd(temp, tmpA);
                temp = Integer.rotateLeft(temp, s[i % 4]);
                temp = modAdd(temp, tmpB);

                tmpA = tmpD;
                tmpD = tmpC;
                tmpC = tmpB;
                tmpB = temp;
            }

            A = modAdd(A, tmpA);
            B = modAdd(B, tmpB);
            C = modAdd(C, tmpC);
            D = modAdd(D, tmpD);
        }
    }

    private String step5() {
        BitArray tmpA = BitArray.fromLongs(new long[]{A});
        BitArray tmpB = BitArray.fromLongs(new long[]{B});
        BitArray tmpC = BitArray.fromLongs(new long[]{C});
        BitArray tmpD = BitArray.fromLongs(new long[]{D});

        return tmpA.toHex() +
                tmpB.toHex() +
                tmpC.toHex() +
                tmpD.toHex();
    }
}
