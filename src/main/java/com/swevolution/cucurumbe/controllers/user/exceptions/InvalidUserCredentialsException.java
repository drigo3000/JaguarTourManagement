/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.user.exceptions;

/**
 *
 * @author rxkx
 */
public class InvalidUserCredentialsException extends Exception {

    public InvalidUserCredentialsException(String message) {
        super(message);
    }

}
