package edu.epam.project.util;

import java.util.Base64;

public class PasswordEncryptor {

    public String encryptPassword(String password) {
        String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        return encryptedPassword;
    }
}
