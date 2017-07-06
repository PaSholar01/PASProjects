/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Tax;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasteryTaxDao {
    
    public void load() throws FlooringMasteryFilePersistenceException;
    
    public void save() throws FlooringMasteryFilePersistenceException;
    
    public Tax getTax(String state);
    
    public Tax addTax(String state, Tax tax);
    
    public Tax removeTax(String state);
    
    public Tax editTax(String state, Tax tax);
    
    public List<Tax> listAllTaxes();
}
