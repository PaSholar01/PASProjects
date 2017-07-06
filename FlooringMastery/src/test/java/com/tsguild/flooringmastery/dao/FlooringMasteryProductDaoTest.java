/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryProductDaoTest {
    
    FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoImpl();
    
    public FlooringMasteryProductDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FlooringMasteryFilePersistenceException {
        List<Product> productList = productDao.listAllProducts();
        for(Product currentProduct: productList){
            productDao.removeProduct(currentProduct.getProductName());
        }
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of addProduct method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testAddProduct() throws FlooringMasteryFilePersistenceException {
        
        Product product = new Product();
        product.setProductName("Ivory");
        product.setMatCostSqFt(new BigDecimal("4.50"));
        product.setLaborCostSqFt(new BigDecimal("7.50"));
        
        productDao.addProduct(product.getProductName(), product);
        
        assertEquals ("Ivory", productDao.getProduct("Ivory").getProductName());
    }

    /**
     * Test of getProduct method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testGetProduct() throws FlooringMasteryFilePersistenceException {
        
        Product product = new Product();
        product.setProductName("Ivory");
        product.setMatCostSqFt(new BigDecimal("4.50"));
        product.setLaborCostSqFt(new BigDecimal("7.50"));
        
        productDao.addProduct(product.getProductName(), product);
        
        assertEquals ("Ivory", productDao.getProduct("Ivory").getProductName());
    }

    /**
     * Test of removeProduct method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testRemoveProduct() throws FlooringMasteryFilePersistenceException {
        
        Product product = new Product();
        product.setProductName("Ivory");
        product.setMatCostSqFt(new BigDecimal("4.50"));
        product.setLaborCostSqFt(new BigDecimal("7.50"));
        
        productDao.addProduct(product.getProductName(), product);
        
        assertEquals (1, productDao.listAllProducts().size());
        
        productDao.removeProduct(product.getProductName());
        
        assertEquals(0, productDao.listAllProducts().size());
    }

    /**
     * Test of editProduct method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testEditProduct() throws FlooringMasteryFilePersistenceException {
        Product product = new Product();
        product.setProductName("Ivory");
        product.setMatCostSqFt(new BigDecimal("4.50"));
        product.setLaborCostSqFt(new BigDecimal("7.50"));
        
        productDao.addProduct(product.getProductName(), product);
        
        assertEquals ("4.50", productDao.getProduct("Ivory").getMatCostSqFt());
        
        Product product2 = new Product();
        product.setMatCostSqFt(new BigDecimal("5.50"));
        product.setLaborCostSqFt(new BigDecimal("8.50"));
        productDao.editProduct(product.getProductName(), product2);
        
        assertEquals("5.50", productDao.getProduct("Ivory").getMatCostSqFt());
    }
    
}
