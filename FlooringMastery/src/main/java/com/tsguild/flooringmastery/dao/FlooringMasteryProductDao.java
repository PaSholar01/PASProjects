/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Product;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasteryProductDao {
    
    public void load()throws FlooringMasteryFilePersistenceException;
    
    public void save() throws FlooringMasteryFilePersistenceException;
    
    public Product addProduct(String productName, Product product);
    
    public Product getProduct(String productName);
    
    public Product removeProduct(String productName);
    
    public Product editProduct(String productName, Product product);
    
    public List<Product> listAllProducts();
    
    
}
