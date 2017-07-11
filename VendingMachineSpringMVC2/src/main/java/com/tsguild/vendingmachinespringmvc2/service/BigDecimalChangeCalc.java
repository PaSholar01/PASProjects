/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.service;

import java.math.BigInteger;

/**
 *
 * @author apprentice
 */
public class BigDecimalChangeCalc {
    
    public BigInteger changeCalc(ChangeEnum changeType, BigInteger op1, BigInteger op2){
        
        switch (changeType){
            
            case DOLLAR_DIVIDE:
                return op1.divide(op2);
                
            case DOLLAR_MOD:
                return op1.remainder(op2);
                
            case QUARTER_DIVIDE:
                return op1.divide(op2);
                
            case QUARTER_MOD:
                return op1.remainder(op2);
                
            case DIME_DIVIDE:
                return op1.divide(op2);
                
            case DIME_MOD:
                return op1.remainder(op2);
                
            case NICKEL_DIVIDE:
                return op1.divide(op2);
                
            case NICKEL_MOD:
                return op1.remainder(op2);
                
            default:
                throw new UnsupportedOperationException("The command is unknown");
        }
        
    }
}
