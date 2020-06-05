package Lab1;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class MongometryTest {
    @Test
    void testMontgomeryMultiply() {
        int iter = 100;
        String min = "100089916566505271515074956512346621616506419335395603058370234568048968116757963626142078482019214442523165529354341964331819634499012391082484546469060970593635274797";
        String max = "200089916566505271515074956512346621616506419335395603058370234568048968116757963626142078482019214442523165529354341964331819634499012391082484546469060970593635274797";
        BigInteger lim = new BigInteger(max).shiftLeft(10).subtract(BigInteger.ONE);
        Montgomery m = new Montgomery();
        m.setM(lim);
        for (int i = 0; i < iter; i++) {
            BigInteger a = Util.randBigInt(new BigInteger(min), new BigInteger(max));
            BigInteger b = Util.randBigInt(new BigInteger(min), new BigInteger(max));

            assertEquals(a.multiply(b).mod(lim), m.multiply(a, b));
        }
    }

    @Test
    void testMontgomeryPow() {
        int iter = 100;
        String min = "100089916566505271515074956512346621616506419335395603058370234568048968116757963626142078482019214442523165529354341964331819634499012391082484546469060970593635274797";
        String max = "200089916566505271515074956512346621616506419335395603058370234568048968116757963626142078482019214442523165529354341964331819634499012391082484546469060970593635274797";
        BigInteger lim = new BigInteger(max).shiftLeft(10).subtract(BigInteger.ONE);
        Montgomery m = new Montgomery();
        m.setM(lim);
        for (int i = 0; i < iter; i++) {
            BigInteger a = Util.randBigInt(new BigInteger(min), new BigInteger(max));
            BigInteger b = BigInteger.valueOf(200);

            assertEquals(a.modPow(b, lim), m.modPow(a, b));
        }
    }
}