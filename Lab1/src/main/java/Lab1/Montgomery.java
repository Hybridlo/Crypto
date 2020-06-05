package Lab1;

import java.math.BigInteger;

public class Montgomery {

    private BigInteger m;

    private int bitLengthR;
    private BigInteger multRev;
    private BigInteger factor;

    private BigInteger toMontgomery(BigInteger x) {
        return x.shiftLeft(bitLengthR).mod(m);
    }

    private BigInteger toNormal(BigInteger x) {
        return x.multiply(multRev).mod(m);
    }

    private BigInteger oneMult(BigInteger x, BigInteger y) {
        BigInteger xy = x.multiply(y);
        BigInteger factored = xy.multiply(factor);
        BigInteger res = xy.add(factored.multiply(m)).shiftRight(bitLengthR);

        return res;
    }

    public void setM(BigInteger m) {
        if (!m.testBit(0) || m.compareTo(BigInteger.ONE) <= 0)
            throw new IllegalArgumentException("Modulus must be an odd number at least 3");
        this.m = m;

        bitLengthR = (m.bitLength() / 8 + 1) * 8;
        BigInteger r = BigInteger.ONE.shiftLeft(bitLengthR);

        multRev = r.modInverse(m);
        factor = r.multiply(multRev).subtract(BigInteger.ONE).divide(m);
    }

    public BigInteger multiply(BigInteger x, BigInteger y) {
        x = toMontgomery(x);
        y = toMontgomery(y);

        return toNormal(oneMult(x, y));
    }

    public BigInteger modPow(BigInteger x, BigInteger y) {
        if (y.signum() == -1)
            throw new IllegalArgumentException("Negative exponent");

        x = toMontgomery(x);
        BigInteger z = toMontgomery(BigInteger.ONE);

        for (int i = 0, len = y.bitLength(); i < len; i++) {
            if (y.testBit(i))
                z = oneMult(z, x);
            x = oneMult(x, x);
        }
        return toNormal(z);
    }

}