package Lab1;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ExtendedGCD {
    private ExtendedGCD(){}

    public static BigInteger extendedGCD(BigInteger a, BigInteger b, AtomicReference<BigInteger> x, AtomicReference<BigInteger> y)
    {
        // Base Case
        if (a.equals(BigInteger.ZERO)) {
            x.set(BigInteger.ZERO);
            y.set(BigInteger.ONE);
            return b;
        }

        AtomicReference<BigInteger> x1 = new AtomicReference<>();
        AtomicReference<BigInteger> y1 = new AtomicReference<>(); // To store results of recursive call
        x1.set(BigInteger.ONE);
        y1.set(BigInteger.ONE);
        BigInteger gcd = extendedGCD(b.mod(a), a, x1, y1);

        // Update x and y using results of recursive
        // call
        x.set(y1.get().subtract((b.divide(a)).multiply(x1.get())));
        y.set(x1.get());

        return gcd;
    }
}
