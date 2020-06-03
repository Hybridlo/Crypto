package Lab2;

public class Main {
    public static void main(String[] args) {
        DES des = new DES();

        String plaintext = "Testing";
        String key = "algorithm";

        System.out.println("Text: \"" + plaintext + "\" Key: \"" + key + "\"");

        String ciphertext = des.encrypt(plaintext, key, false);

        System.out.println("(DES) Ciphertext: " + ciphertext);

        String plaintext2 = des.decrypt(ciphertext, key, false);

        System.out.println("(DES) Deciphered back: " + plaintext2);

        TDES tdes = new TDES();

        ciphertext = tdes.encrypt(plaintext, key, false);

        System.out.println("(TDES) Ciphertext: " + ciphertext);

        plaintext2 = tdes.decrypt(ciphertext, key, false);

        System.out.println("(TDES) Deciphered back: " + plaintext2);
    }
}
