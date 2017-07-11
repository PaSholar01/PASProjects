/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.model;

import java.math.BigInteger;

/**
 *
 * @author apprentice
 */
public class Change {
    
    private BigInteger dollars;
    private BigInteger quarters;
    private BigInteger dimes;
    private BigInteger nickels;
    private BigInteger pennies;

    public BigInteger getDollars() {
        return dollars;
    }

    public void setDollars(BigInteger dollars) {
        this.dollars = dollars;
    }

    public BigInteger getQuarters() {
        return quarters;
    }

    public void setQuarters(BigInteger quarters) {
        this.quarters = quarters;
    }

    public BigInteger getDimes() {
        return dimes;
    }

    public void setDimes(BigInteger dimes) {
        this.dimes = dimes;
    }

    public BigInteger getNickels() {
        return nickels;
    }

    public void setNickels(BigInteger nickels) {
        this.nickels = nickels;
    }

    public BigInteger getPennies() {
        return pennies;
    }

    public void setPennies(BigInteger pennies) {
        this.pennies = pennies;
    }
}
