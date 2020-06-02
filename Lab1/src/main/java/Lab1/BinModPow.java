package Lab1;

import java.math.BigInteger;

public class BinModPow {
    private BinModPow(){}

    static BigInteger binModPow(BigInteger a, BigInteger b, BigInteger n) {
        a = a.mod(n);
        BigInteger res = BigInteger.ONE;

        while (b.compareTo(BigInteger.ZERO) > 0) {
            if (b.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE))
                res = res.multiply(a).mod(n);
            a = a.multiply(a).mod(n);
            b = b.shiftRight(1);
        }
        return res;
    }
}
