/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.service;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryInvalidStateException extends Exception {

    public FlooringMasteryInvalidStateException(String message) {
        super(message);
    }
    
    public FlooringMasteryInvalidStateException(String message, Throwable cause){
        super(message, cause);
    }
    
}
