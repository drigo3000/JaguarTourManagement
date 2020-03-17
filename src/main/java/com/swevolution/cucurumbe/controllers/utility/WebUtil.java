/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 * @author Rxkx
 */
public class WebUtil {

    public static String encode(String input) {
        try {
            return URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    public static String decode(String input) {
        try {
            return URLDecoder.decode(input, "UTF-8");
        } catch (Exception e) {
            
            return null;
        }
    }
    
    
}
