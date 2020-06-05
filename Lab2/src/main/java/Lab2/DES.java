package Lab2;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.nio.charset.StandardCharsets;

public class DES {
    private final int sizeOfChar = 8; //use ascii(8bit)
    private final int sizeOfBlock = sizeOfChar * 8;

    private final int shiftKey = 2;

    private final int quantityOfRounds = 16;

    String[] blocks;

    private String strToRightLength(String input) {
        StringBuilder inputBuilder = new StringBuilder(input);

        while (((inputBuilder.length() * sizeOfChar) % sizeOfBlock) != 0)
            inputBuilder.append("#");

        input = inputBuilder.toString();

        return input;
    }

    private void cutStringIntoBlocks(String input) {
        blocks = new String[(input.length() * sizeOfChar) / sizeOfBlock];

        int lengthOfBlock = input.length() / blocks.length;

        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = input.substring(i * lengthOfBlock, (i + 1) * lengthOfBlock);
            blocks[i] = stringToBinaryFormat(blocks[i]);
        }
    }

    private void cutBinaryStringIntoBlocks(String input) {
        blocks = new String[input.length() / sizeOfBlock];

        int lengthOfBlock = input.length() / blocks.length;

        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = input.substring(i * lengthOfBlock, (i + 1) * lengthOfBlock);
        }
    }

    private String stringToBinaryFormat(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            StringBuilder char_binary = new StringBuilder(Integer.toBinaryString(input.charAt(i)));

            while (char_binary.length() < sizeOfChar)
                char_binary.insert(0, "0");

            output.append(char_binary);
        }

        return output.toString();
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

    private String encodeDESOneRound(String input, String key) {
        String L = input.substring(0, input.length() / 2);
        String R = input.substring(input.length() / 2);

        return (R + XOR(L, f(R, key)));
    }

    private String decodeDESOneRound(String input, String key) {
        String L = input.substring(0, input.length() / 2);
        String R = input.substring(input.length() / 2);

        return (XOR(f(L, key), R) + L);
    }

    private String XOR(String s1, String s2) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s1.length(); i++) {
            boolean a = "1".equals(s1.substring(i, i+1));
            boolean b = "1".equals(s2.substring(i, i+1));

            if (a ^ b)
                result.append("1");
            else
                result.append("0");
        }

        return result.toString();
    }

    private String f(String s1, String s2) {
        return XOR(s1, s2);
    }

    private String keyToNextRound(String key) {
        StringBuilder keyBuilder = new StringBuilder(key);

        for (int i = 0; i < shiftKey; i++) {
            keyBuilder.insert(0, keyBuilder.substring(keyBuilder.length() - 1));
            keyBuilder = new StringBuilder(keyBuilder.substring(0, keyBuilder.length() - 1));
        }
        key = keyBuilder.toString();

        return key;
    }

    private String keyToPrevRound(String key) {
        StringBuilder keyBuilder = new StringBuilder(key);

        for (int i = 0; i < shiftKey; i++) {
            keyBuilder.append(keyBuilder.charAt(0));
            keyBuilder = new StringBuilder(keyBuilder.substring(1));
        }

        key = keyBuilder.toString();

        return key;
    }

    private String stringFromBinaryToNormalFormat(String input) {
        StringBuilder output = new StringBuilder();

        while (input.length() > 0) {
            String char_binary = input.substring(0, sizeOfChar);

            input = input.substring(sizeOfChar);

            int a = 0;
            int degree = char_binary.length() - 1;

            for (Character c : char_binary.toCharArray()) {
                a += Integer.parseInt(c.toString()) * Math.pow(2, degree--);
            }

            output.append((char) a);
        }

        return output.toString();
    }

    String encrypt(String plaintext, String key, boolean debug) {
        plaintext = strToRightLength(plaintext);
        cutStringIntoBlocks(plaintext);

        key = correctKeyWord(key, plaintext.length() / (2 * blocks.length));
        if (debug)
            System.out.println("Corrected key: " + key);
        key = stringToBinaryFormat(key);

        for (int j = 0; j < quantityOfRounds; j++) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = encodeDESOneRound(blocks[i], key);
            }

            key = keyToNextRound(key);
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < blocks.length; i++)
            result.append(blocks[i]);

        String hex = HexBin.encode(stringFromBinaryToNormalFormat(result.toString()).getBytes());

        if (debug)
            System.out.println("Encrypted string: " + hex);

        return hex;
    }

    String decrypt(String hex, String key, boolean debug) {
        String ciphertext = new String(HexBin.decode(hex), StandardCharsets.UTF_8);

        cutStringIntoBlocks(ciphertext);

        key = correctKeyWord(key, ciphertext.length() / (2 * blocks.length));
        if (debug)
            System.out.println("Corrected key: " + key);

        key = stringToBinaryFormat(key);

        key = keyToPrevRound(key);

        for (int j = 0; j < quantityOfRounds; j++) {
            for (int i = 0; i < blocks.length; i++)
                blocks[i] = decodeDESOneRound(blocks[i], key);

            key = keyToPrevRound(key);
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < blocks.length; i++)
            result.append(blocks[i]);

        if (debug)
            System.out.println("Decoded string: " + stringFromBinaryToNormalFormat(result.toString()));

        return stringFromBinaryToNormalFormat(result.toString());
    }
}
