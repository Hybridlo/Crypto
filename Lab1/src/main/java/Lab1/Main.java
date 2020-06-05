package Lab1;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        //555 bit prime number
        String prime555 = "100089916566505271515074956512346621616506419335395603058370234568048968116757963626142078482019214442523165529354341964331819634499012391082484546469060970593635274797";
        if (Ferma.test(new BigInteger(prime555), 100))
            System.out.println("Ferma test pass");

        if (MillerRabin.test(new BigInteger(prime555), 100))
            System.out.println("Miller-Rabin test pass");

        System.out.println("Prime 555 bit to pow 200 mod 5001 with binmodpow:");
        System.out.println(BinModPow.binModPow(new BigInteger(prime555), new BigInteger("200"), new BigInteger("5001")));

        System.out.println("34566543234567 x 987657896543 with karatsuba");
        System.out.println(Karatsuba.multiply(new BigInteger("34566543234567"), new BigInteger("987657896543")).toString());

        System.out.println("34566543234567 x 987657896543 mod 10000000000000000000000001 with montgomery");
        Montgomery m = new Montgomery();
        m.setM(new BigInteger("10000000000000000000000001"));
        System.out.println(m.multiply(new BigInteger("34566543234567"), new BigInteger("987657896543")));

        System.out.println("Prime 555 bit to pow 200 mod 5001 with montgomery:");
        m = new Montgomery();
        m.setM(new BigInteger("5001"));
        System.out.println(m.modPow(new BigInteger(prime555), BigInteger.valueOf(200)));

        AtomicReference<BigInteger> x = new AtomicReference<>();
        AtomicReference<BigInteger> y = new AtomicReference<>();
        BigInteger result = ExtendedGCD.extendedGCD(BigInteger.valueOf(180), BigInteger.valueOf(150), x, y);

        System.out.println("Extended GCD:");
        System.out.println("180 * " + x.get() + " + 150 * " + y.get() + " = " + result);
    }
}
