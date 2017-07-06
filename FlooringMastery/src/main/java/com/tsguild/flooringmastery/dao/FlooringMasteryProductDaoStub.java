/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryProductDaoStub implements FlooringMasteryProductDao {

    private Map<String, Product> productMap = new HashMap<>();

    Product product1 = new Product();
    Product product2 = new Product();

    public FlooringMasteryProductDaoStub() {

        product1.setProductName("tile");
        product1.setLaborCostSqFt(new BigDecimal("50000"));
        product1.setMatCostSqFt(new BigDecimal("1000"));

        product2.setProductName("AshWood");
        product2.setLaborCostSqFt(new BigDecimal("10000"));
        product2.setMatCostSqFt(new BigDecimal("1000"));

        productMap.put(product1.getProductName(), product1);
        productMap.put(product2.getProductName(), product2);

    }

    @Override
    public Product addProduct(String productName, Product product) {

        Product newProduct = productMap.put(productName, product);
        return newProduct;
    }

    @Override
    public Product getProduct(String productName) {

        return productMap.get(productName);
    }

    @Override
    public Product removeProduct(String productName) {

        Product removedProduct = productMap.remove(productName);
        return removedProduct;
    }

    @Override
    public Product editProduct(String productName, Product product) {

        Product editedProduct = productMap.replace(productName, product);
        return editedProduct;
    }

    @Override
    public List<Product> listAllProducts() {

        return new ArrayList<>(productMap.values());
    }

    @Override
    public void load() throws FlooringMasteryFilePersistenceException {
        
    }

    @Override
    public void save() throws FlooringMasteryFilePersistenceException {
        
    }
}
