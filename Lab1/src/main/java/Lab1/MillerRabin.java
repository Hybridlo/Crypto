package Lab1;

import java.math.BigInteger;

public class MillerRabin {
    private MillerRabin(){}

    static boolean test(BigInteger n, int iter) {
        if (n.equals(BigInteger.valueOf(2)) || n.equals(BigInteger.valueOf(3)))
            return true;

        if (n.compareTo(BigInteger.valueOf(2)) < 0 || n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO))
            return false;

        BigInteger t = n.subtract(BigInteger.ONE);

        int s = 0;

        while (t.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            t = t.divide(BigInteger.valueOf(2));
            s++;
        }

        for (int i = 0; i < iter; i++) {
            BigInteger a = Util.randBigInt(BigInteger.valueOf(2), n.subtract(BigInteger.valueOf(2)));

            BigInteger x = a.modPow(t, n);

            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.valueOf(1))))
                continue;

            for (int r = 1; r < s; r++) {
                x = x.modPow(BigInteger.valueOf(2), n);

                if (x.equals(BigInteger.ONE))
                    return false;

                if (x.equals(n.subtract(BigInteger.valueOf(1))))
                    break;
            }

            if (!x.equals(n.subtract(BigInteger.valueOf(1))))
                return false;
        }

        return true;
    }
}
