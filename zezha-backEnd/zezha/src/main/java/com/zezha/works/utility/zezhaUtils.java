package com.zezha.works.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class zezhaUtils {
    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    public static boolean checkPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        String hashedInputPassword = encryptPassword(password);
        return hashedInputPassword.equals(hashedPassword);
    }
    
    public static String decryptPassword(String encryptedPass) throws NoSuchAlgorithmException {
    	MessageDigest digest = MessageDigest.getInstance("SHA-256");
    	byte[] hash = digest.digest(encryptedPass.getBytes(StandardCharsets.UTF_8));
    	String hex = bytesToHex(hash);
		return hex;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
    	//String encryptedpass = encryptPassword("Avinash");
    	//System.out.println("encryptedpass--"+encryptedpass);
    	//System.out.println("decryptPassword--"+decryptPassword("b6bc7b58510319a151d168ba3d5aecb3ac0a9708d06dd930f37fbc89b6cdc697"));
    	System.out.println(encryptPassword("Avinash"));
    	System.out.println(checkPassword("Avinash", "864ca47546d52e2bdf50be382c85ef79dc87c291942df772f2047adc978255eb"));
	}
}
