/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.dao;

import com.tsguild.vendingmachinespringmvc2.model.Item;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class VendingMachineDaoTest {
     VendingMachineDao dao = new VendingMachineDaoInMemImpl();
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws VendingMachinePersistenceException, URISyntaxException {
        Map<String, Item> inventoryList = new HashMap<>();
        dao.loadInventory();
        
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of getListSize method, of class VendingMachineDao.
     * This test shows that the methods used in the dao can both get the correct
     * item by inventoryId, as well as get the size of a list
     */
    @Test
    public void testGetListSizeAndGetItemBySelection() throws VendingMachinePersistenceException {
        
        Item item = dao.getItemBySelection(1);
        assertEquals(1, item.getInventoryId());
            
    }

    /**
     * Test of listInventoryTotal method, of class VendingMachineDao.
     * This test is used to showcase that the method used can return the correct size of a list.
     */
    @Test
    public void testListTotalInventory() throws VendingMachinePersistenceException {
        
        List<Item> itemList = dao.listTotalInventory();
        assertEquals(6, itemList.size());
        
        }
    

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveItem() throws VendingMachinePersistenceException {
       Item shark = dao.getItemBySelection(5);
        
      assertEquals("1", shark.getItemQuantity());
      shark.setItemQuantity("0");
      dao.removeItem(shark);
      assertEquals("0", shark.getItemQuantity());
    }

    
}
    

