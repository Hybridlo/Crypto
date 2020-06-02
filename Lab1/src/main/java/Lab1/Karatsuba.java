package Lab1;

import java.math.BigInteger;

public class Karatsuba {
    private Karatsuba(){}

    static BigInteger ten = BigInteger.TEN;
    static BigInteger two = BigInteger.valueOf(2);
    static BigInteger one = BigInteger.ONE;

    static BigInteger multiply(BigInteger i, BigInteger j) {
        if (i.compareTo(ten) < 0 || j.compareTo(ten) < 0)
            return i.multiply(j);
        int length = i.max(j).toString().length();
        BigInteger n = BigInteger.valueOf(length);
        if (n.mod(two).equals(one))
            n = n.add(one);

        BigInteger a = i.divide(ten.pow(n.divide(two).intValue()));
        BigInteger b = i.mod(ten.pow(n.divide(two).intValue()));
        BigInteger c = j.divide(ten.pow(n.divide(two).intValue()));
        BigInteger d = j.mod(ten.pow(n.divide(two).intValue()));

        BigInteger first = multiply(a,c);
        BigInteger second = multiply(b,d);
        BigInteger third = multiply(a.add(b),c.add(d));

        return ((first.multiply(ten.pow(n.intValue()))).add ((((third.subtract(first)).subtract( second))).multiply(ten.pow(n.divide((two)).intValue()))).add(second));
    }
}
