package edu.epam.project.util;

import java.util.Base64;

public class PasswordEncryptor {

    public static String encryptPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
