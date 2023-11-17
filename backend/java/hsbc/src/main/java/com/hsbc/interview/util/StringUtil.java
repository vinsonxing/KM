package com.hsbc.interview.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public static boolean isBlank(String value) {
        return value == null || value.length() == 0 || value.trim().length() == 0;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
