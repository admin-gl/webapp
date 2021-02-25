package com.voidapp.ApplicationWeb.bdd;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Hasher {

    public static String encode(String password){

        String hash = "";

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes(StandardCharsets.UTF_8));
            hash = byteToHex(crypt.digest());

        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }

        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static void main(String[] args) {
        System.out.println(encode("salutation"));
    }
}
