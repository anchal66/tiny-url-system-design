package com.anchal.tinyurlsystemdesign;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String hashString(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(input.getBytes());
            return bytesToHex(digest.digest());
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No Such Algo Found", e);
        }
    }

    private static String bytesToHex(byte[] digest) {
        StringBuilder br = new StringBuilder();
        for (byte b : digest){
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1){
                br.append('O');
            }
            br.append(hex);
        }
        return br.toString();
    }
}
