/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryFilePersistenceException extends Exception{
    
    public FlooringMasteryFilePersistenceException(String message){
        
        super(message);
    }
    
    public FlooringMasteryFilePersistenceException(String message, Throwable cause){
        
        super(message, cause);
    }
    
}
