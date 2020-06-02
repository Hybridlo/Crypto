package Lab1;

import java.math.BigInteger;
import java.util.Random;

public class Ferma {
    private Ferma(){}

    static boolean test(BigInteger n, int iter) {
        Random random = new Random();
        BigInteger a = Util.randBigInt(new BigInteger("1"), n);
        for (int i = 0; i < iter; i++) {
            if (!a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE)) {
                System.out.println(a);
                return false;
            }
        }
        return true;
    }
}
