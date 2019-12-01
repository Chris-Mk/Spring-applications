package com.mkolongo.exodia.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public final class PasswordHash {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String SALT = "ljhabksdKHBAJKSDsjlhfSFDblesfbLKSJDasdfBF";
    private static final int ITERATION_COUNT = 65565;
    private static final int KEY_LENGTH = 518;

    public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec keySpec = new PBEKeySpec(
                password.toCharArray(),
                SALT.getBytes(),
                ITERATION_COUNT,
                KEY_LENGTH);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        final byte[] encoded = keyFactory.generateSecret(keySpec).getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }
}
