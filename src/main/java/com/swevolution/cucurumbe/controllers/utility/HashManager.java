/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Rxkx
 */
public class HashManager {

    public static String getEncoded(String password) {
        try {
            return Base64.getEncoder().encodeToString(encriptar(password));
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }

    public static byte[] encriptar(String password)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        return md.digest();
    }
}
