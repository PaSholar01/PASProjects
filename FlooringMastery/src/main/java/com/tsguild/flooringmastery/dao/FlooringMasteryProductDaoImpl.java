/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryProductDaoImpl implements FlooringMasteryProductDao {

    private Map<String, Product> productMap = new HashMap<>();

    private String DELIMITER = ",";

    private String PRODUCT_FILE;

    public FlooringMasteryProductDaoImpl(String fileName) {
        this.PRODUCT_FILE = fileName;
    }

    public FlooringMasteryProductDaoImpl() {

    }

    @Override
    public void load() throws FlooringMasteryFilePersistenceException {

        Scanner console = null;

        try {

            console = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));

        } catch (FileNotFoundException e) {

            throw new FlooringMasteryFilePersistenceException("Product File Unavailable", e);
        }

        String currentLine;
        String[] currentTokens;

        while (console.hasNextLine()) {

            currentLine = console.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product();

            currentProduct.setProductName(currentTokens[0].toLowerCase());
            currentProduct.setMatCostSqFt(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostSqFt(new BigDecimal(currentTokens[2]));

            productMap.put(currentProduct.getProductName(), currentProduct);
        }

        console.close();
    }

    public void save() throws FlooringMasteryFilePersistenceException {

        PrintWriter save;

        try {
            save = new PrintWriter(new FileWriter(PRODUCT_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryFilePersistenceException("Failed to save file", e);
        }

        List<Product> productList = this.listAllProducts();

        for (Product currentProduct : productList) {

            save.println(currentProduct.getProductName() + "," + currentProduct.getMatCostSqFt() + ","
                    + currentProduct.getLaborCostSqFt());
            save.flush();
        }

        save.close();

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

}
