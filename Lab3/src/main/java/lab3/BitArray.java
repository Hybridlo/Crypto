package lab3;

import java.util.ArrayList;
import java.util.List;

public class BitArray {
    private List<Boolean> array;

    BitArray() {
        this.array = new ArrayList<>();
    }

    void add(boolean value) {
        array.add(value);
    }

    void extend(BitArray extendant) {
        for (int i = 0; i < extendant.size(); i++)
            array.add(extendant.get(i));
    }

    void addToStart(boolean value) {
        array.add(0, value);
    }

    boolean get(int i) {
        return array.get(i);
    }

    int size() {
        return array.size();
    }

    BitArray subArray(int start, int finish) {
        BitArray res = new BitArray();

        for (int i = start; i < finish; i++)
            res.add(array.get(i));

        return res;
    }

    int toInt() {
        int res = 0;

        for (int i = 0; i < array.size(); i++)
            if (Boolean.TRUE.equals(array.get(i)))
                res += Math.pow(2, i);

        return res;
    }

    String toHex() {

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < array.size(); i+=8) {
            StringBuilder binaryString = new StringBuilder();

            for (int j = 0; j < 8; j++) {
                if (i + j == array.size())
                    break;

                binaryString.append(Boolean.TRUE.equals(array.get(i + j)) ? "1" : "0");
            }
            binaryString.reverse();

            int decimal = Integer.parseInt(binaryString.toString(), 2);
            String add = "";
            if (decimal < 16)
                add = "0";
            res.append(add).append(Integer.toString(decimal, 16));
        }

        if (res.length() % 2 == 1)
            res.append("0");

        return res.toString();
    }

    static BitArray fromBytes(byte[] bytes) {
        BitArray res = new BitArray();
        for (int i = 0; i < bytes.length; i++) {
            byte aByte = bytes[i];

            for (int j = 0; j < 8; j++) {
                res.add(aByte % 2 == 1);

                aByte /= 2;
            }
        }

        return res;
    }

    static BitArray fromLongs(long[] longs) {
        BitArray res = new BitArray();
        for (int i = 0; i < longs.length; i++) {
            long aLong = longs[i];

            if (aLong < 0) {
                aLong += Integer.MAX_VALUE;
                aLong += Integer.MAX_VALUE;
                aLong += 2;
            }

            for (int j = 0; j < 64; j++) {
                res.add(aLong % 2 == 1);

                aLong /= 2;

                if (aLong == 0 && i == longs.length - 1)
                    break;
            }
        }

        return res;
    }
}
