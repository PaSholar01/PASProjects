/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryTaxDaoStub implements FlooringMasteryTaxDao {
    
    private Map<String, Tax> taxMap = new HashMap<>();
    
    Tax tax1 = new Tax();
    Tax tax2 = new Tax();
    
    public FlooringMasteryTaxDaoStub() {
        
        tax1.setState("KY");
        tax1.setTaxRate(new BigDecimal("6.75"));
        
        tax2.setState("WI");
        tax2.setTaxRate(new BigDecimal("5.50"));
        
        taxMap.put(tax1.getState(), tax1);
        taxMap.put(tax2.getState(), tax2);
        
    }
    
   
    @Override
    public List<Tax> listAllTaxes() {

        return new ArrayList<>(taxMap.values());
    }


    @Override
    public Tax getTax(String state) {
        return taxMap.get(state);
    }
    
    
    @Override
    public Tax addTax(String state, Tax tax) {
        Tax newTax = taxMap.put(state, tax);
        return newTax;
        
    }

  
    @Override
    public Tax removeTax(String state) {
        Tax removedTax = taxMap.remove(state);
        return removedTax;
    }

   
    @Override
    public Tax editTax(String state, Tax tax) {
        Tax editedTax = taxMap.replace(state, tax);
        return editedTax;
    }

    @Override
    public void load() throws FlooringMasteryFilePersistenceException {
        
    }

    @Override
    public void save() throws FlooringMasteryFilePersistenceException {
        
    }
}
