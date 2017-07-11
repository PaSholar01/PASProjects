/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.dao;

import com.tsguild.vendingmachinespringmvc2.model.Item;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {

    //THIS IS THE MAP WHICH WILL HOLD THE INVENTORY LIST WHEN PULLED FROM FILE
    private Map<Long, Item> inventoryList = new HashMap<>();

    //THIS IS THE DELIMITER WITH WHICH TO SPLIT FIELDS OF EACH OBJECT
    //DURING FILE LOADING
    private final String DELIMITER = "::";

    //THIS IS THE FILE FROM WHICH TO LOAD YOUR INVENTORY....
    //private final String INVENTORY_FILE;
    //THIS CONSTRUCTOR ENSURES THAT TEST/REAL FILES MAY BE CHOSEN UPON INSTANCING
    public VendingMachineDaoInMemImpl() {

    }

    @Override
    public List<Item> listTotalInventory() {
        return new ArrayList<>(inventoryList.values());
    }

    @Override
    public Item getItemBySelection(long itemId) {
        return inventoryList.get(itemId);
    }

    @Override
    public void removeItem(Item item) {
        inventoryList.replace(item.getInventoryId(), item);
    }

    @Override
    public void loadInventory() throws VendingMachinePersistenceException, URISyntaxException {

        File toSearch = new File(this.getClass().getResource("/items.txt").toURI());
        Scanner console = null;
        try {
            console = new Scanner(new BufferedReader(new FileReader(toSearch.getAbsolutePath())));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("The file could not be loaded.");
        }
        String currentLine = "";
        String[] currentTokens;

        while (console.hasNextLine()) {

            currentLine = console.nextLine();
            currentTokens = currentLine.split("::");

            Item newItem = new Item(Long.parseLong(currentTokens[0]));
            newItem.setItemType(currentTokens[1]);
            newItem.setCost(currentTokens[2]);
            newItem.setItemQuantity(currentTokens[3]);

            inventoryList.put(newItem.getInventoryId(), newItem);
        }

    }
}
