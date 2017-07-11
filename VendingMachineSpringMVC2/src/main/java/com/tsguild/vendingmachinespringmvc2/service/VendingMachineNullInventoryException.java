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
public class VendingMachineNullInventoryException extends Exception{
    
     public VendingMachineNullInventoryException(String message){
        super(message);
    }
    
    public VendingMachineNullInventoryException(String message, Throwable cause){
        super(message,cause);
    }
}
