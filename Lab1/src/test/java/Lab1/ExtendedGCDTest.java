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
        assertEquals(x.get(), BigInteger.ONE);
        assertEquals(y.get(), BigInteger.valueOf(-1));
        assertEquals(result, BigInteger.valueOf(30));
    }
}