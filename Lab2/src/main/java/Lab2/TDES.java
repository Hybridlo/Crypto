package Lab2;

public class TDES {
    private final int sizeOfChar = 8; //use ascii(8bit)
    private final int sizeOfBlock = sizeOfChar * 8;

    private String strToRightLength(String input) {
        StringBuilder inputBuilder = new StringBuilder(input);

        while (((inputBuilder.length() * sizeOfChar) % sizeOfBlock) != 0)
            inputBuilder.append("#");

        input = inputBuilder.toString();

        return input;
    }

    private String correctKeyWord(String input, int lengthKey) {
        if (input.length() > lengthKey)
            input = input.substring(0, lengthKey);
        else {
            StringBuilder inputBuilder = new StringBuilder(input);

            while (inputBuilder.length() < lengthKey)
                inputBuilder.insert(0, "0");

            input = inputBuilder.toString();
        }

        return input;
    }

    String encrypt(String plaintext, String key, boolean debug) {
        DES des = new DES();

        plaintext = strToRightLength(plaintext);
        key = correctKeyWord(key, 3 * plaintext.length() / (2 * plaintext.length() / sizeOfChar));

        String[] keys = {key.substring(0, key.length() / 3),
                key.substring(key.length() / 3, (2 * key.length()) / 3),
                key.substring((2 * key.length()) / 3)
        };

        String ciphertext = des.encrypt(
                des.decrypt(
                        des.encrypt(plaintext, keys[0], debug),
                        keys[1], debug),
                keys[2], debug);

        return ciphertext;
    }

    String decrypt(String ciphertext, String key, boolean debug) {
        DES des = new DES();

        key = correctKeyWord(key, 3 * ciphertext.length() / (2 * ciphertext.length() / sizeOfChar));

        String[] keys = {key.substring(0, key.length() / 3),
                key.substring(key.length() / 3, (2 * key.length()) / 3),
                key.substring((2 * key.length()) / 3)
        };

        String plaintext = des.decrypt(
                des.encrypt(
                        des.decrypt(ciphertext, keys[0], debug),
                        keys[1], debug),
                keys[2], debug);

        return plaintext;
    }
}
