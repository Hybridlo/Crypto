package Lab1;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedGCDTest {
    @Test
    void textExtendedGCD() {
        AtomicReference<BigInteger> x = new AtomicReference<>();
        AtomicReference<BigInteger> y = new AtomicReference<>();
        BigInteger result = ExtendedGCD.extendedGCD(BigInteger.valueOf(180), BigInteger.valueOf(150), x, y);
        assertEquals(BigInteger.ONE, x.get());
        assertEquals(BigInteger.valueOf(-1), y.get());
        assertEquals(BigInteger.valueOf(30), result);
    }
}