/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.service;

import com.tsguild.vendingmachinespringmvc2.dao.VendingMachinePersistenceException;
import com.tsguild.vendingmachinespringmvc2.model.Change;
import com.tsguild.vendingmachinespringmvc2.model.Item;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

   
    public VendingMachineServiceLayerTest() {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("serviceTest", VendingMachineServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws VendingMachinePersistenceException, URISyntaxException {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void listTotalInventory() throws VendingMachineNullInventoryException {
        List<Item> itemList = service.listTotalInventory();
        assertEquals(2, itemList.size());
    }
    
    @Test
    public void testGetRemoveItem() throws VendingMachineNullInventoryException, VendingMachineDoesNotExistException {
        Item item = service.getItemBySelection(1);
        assertEquals("Llama", item.getItemType());
        assertEquals("5", item.getItemQuantity());
        service.removeItem(1);
        assertEquals("4", item.getItemQuantity());
    }
    
    @Test 
    public void testValidateCanBuy() throws VendingMachineNullInventoryException, VendingMachineInsufficientFundsException, VendingMachineDoesNotExistException {
        Item item = service.getItemBySelection(1);
        boolean canBuy = service.validateCanBuy(5.50, item);
        assertEquals(true, canBuy);
        
    }
    
    @Test
    public void testMakeSale() throws VendingMachineNullInventoryException, VendingMachineDoesNotExistException {
        Item item = service.getItemBySelection(1);
        Change change = service.makeSale(5.00, item);
       
        assertEquals("2", change.getDollars().toString());
    }

}
