package Lab1;

import java.math.BigInteger;

public class Ferma {
    private Ferma(){}

    static boolean test(BigInteger n, int iter) {
        BigInteger a = Util.randBigInt(BigInteger.ONE, n);

        for (int i = 0; i < iter; i++) {
            if (!a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE)) {
                System.out.println(a);
                return false;
            }
        }
        return true;
    }
}
