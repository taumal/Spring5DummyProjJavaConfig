package com.dummies.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * User: nayeem
 * Date: Apr 8, 2010
 */
public class PasswordUtils {
    private static Random random = new Random();
    private static int saltLength = 10;
    private static String hashAlgorithm = "SHA1";
    private static final int randCharCount = 64;

    private static final char[] randChars = {
            '+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static final char[] hexChars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static boolean verifyPassword(String plainPassword, String encryptedPassword, String salt) {
        return encryptedPassword.equals(hashData(salt + plainPassword));
    }

    public static EncryptionPair getEncryptionPair(String plainPassword) {
        EncryptionPair encryptionPair = new EncryptionPair();
        String salt = getRandomStr();
        encryptionPair.setSalt(salt);
        encryptionPair.setHashValue(hashData(salt + plainPassword));
        return encryptionPair;
    }

    private static String getRandomStr() {
        StringBuilder sbRand = new StringBuilder();
        for (int i = 0; i < saltLength; i++) {
            sbRand.append(randChars[random.nextInt(randCharCount)]);
        }
        return sbRand.toString();
    }

    private static String hashData(String s) {
        if (s == null){
            throw new RuntimeException("null value");
        }

        byte[] dataToHash = s.getBytes();
        StringBuilder sbHex = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance(hashAlgorithm);
            digest.update(dataToHash, 0, dataToHash.length);
            byte[] bytes = digest.digest();
            int msb, lsb;

            for (byte bt : bytes) {
                msb = ((int) bt & 0x000000FF) / 16;
                lsb = ((int) bt & 0x000000FF) % 16;
                sbHex.append(hexChars[msb]).append(hexChars[lsb]);
            }
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return sbHex.toString();
    }
}
