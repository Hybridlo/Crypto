package Lab1;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Util {
    private Util(){}

    static Random rand = new Random();

    static BigInteger randBigInt(BigInteger minLimit, BigInteger maxLimit) {
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        int len = maxLimit.bitLength();
        BigInteger res = new BigInteger(len, rand);

        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);

        return res;
    }
}
