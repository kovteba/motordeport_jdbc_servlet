package ua.nure.kovteba.finaltask.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Encryption {

    public static String SHA256(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }

    public static boolean testOriginal(String password, String passwordDB){
        return Encryption.SHA256(password).equals(passwordDB);
    }

    public static void main(String[] args) {
        System.out.println(Encryption.SHA256("kovteba"));
    }

}
