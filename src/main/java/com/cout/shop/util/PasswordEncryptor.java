package com.cout.shop.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class PasswordEncryptor {
    private static final Logger logger = LogManager.getLogger();
    private static final String ENCRYPTOR_ALGORITHM = "SHA-256";

    private PasswordEncryptor(){}

    public static Optional<String> encryptPassword(String password){
        Optional<String> passwordEncr = Optional.empty();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytesEncoded = messageDigest.digest();

            BigInteger bigInteger = new BigInteger(1,bytesEncoded);
            passwordEncr = Optional.of(bigInteger.toString(16));
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error while encrypting password",e);
        }
        return passwordEncr;
    }
}
