package br.edu.unicesumar.ads.prog2.mapa.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author max.andriani
 */
public class AuthenticateRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public AuthenticateRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticateRequest setPassword(String password) {
        try {
            var md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            var hexStringBuilder = new StringBuilder();
            for (byte b : digest) {
                String hex = String.format("%02x", b);
                hexStringBuilder.append(hex);
            }
            this.password = hexStringBuilder.toString();
            return this;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return this;
    }
}
