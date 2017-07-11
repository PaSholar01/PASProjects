/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.dao;

import com.tsguild.vendingmachinespringmvc2.model.Item;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineDao {
    
     public List<Item> listTotalInventory();

    public Item getItemBySelection(long itemId);

    public void removeItem(Item item);

    public void loadInventory() throws VendingMachinePersistenceException, URISyntaxException;

   
}
