/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.service;

/**
 *
 * @author apprentice
 */
public class VendingMachineDoesNotExistException extends Exception{
    
    public VendingMachineDoesNotExistException(String message){
        super(message);
    }
    
    public VendingMachineDoesNotExistException(String message, Throwable cause){
        super(message, cause);
    }
}
