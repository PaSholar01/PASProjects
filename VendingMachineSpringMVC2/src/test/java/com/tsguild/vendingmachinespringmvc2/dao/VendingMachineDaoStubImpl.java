/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.dao;

import com.tsguild.vendingmachinespringmvc2.dao.VendingMachineDao;
import com.tsguild.vendingmachinespringmvc2.dao.VendingMachinePersistenceException;
import com.tsguild.vendingmachinespringmvc2.model.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    private Item oneItem;
    private Item twoItem;
    private Map<Long, Item> itemList = new HashMap<>();

    public VendingMachineDaoStubImpl() {
        oneItem = new Item(1);
        oneItem.setItemType("Llama");
        oneItem.setCost("2.50");
        oneItem.setItemQuantity("5");

        twoItem = new Item(2);
        twoItem.setItemType("Tiger");
        twoItem.setCost("5.00");
        twoItem.setItemQuantity("5");

        itemList.put(oneItem.getInventoryId(), oneItem);
        itemList.put(twoItem.getInventoryId(), twoItem);
    }

    @Override
    public List<Item> listTotalInventory() {
        return new ArrayList<>(itemList.values());
    }

    @Override
    public Item getItemBySelection(long itemId) {
        if (itemList.get(itemId) != null) {
            return itemList.get(itemId);
        } else {
            return null;
        }
    }

    @Override
    public void removeItem(Item item) {
        itemList.replace(item.getInventoryId(), item);

    }

    @Override
    public void loadInventory() throws VendingMachinePersistenceException {

    }
}
