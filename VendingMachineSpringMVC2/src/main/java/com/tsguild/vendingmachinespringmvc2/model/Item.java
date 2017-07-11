/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.model;

/**
 *
 * @author apprentice
 */
public class Item {

    private long inventoryId;
    private String cost;
    private String itemType;
    private String itemQuantity;

    public Item(long inventoryId) {
        this.inventoryId = inventoryId;
    }
    
    public Item() {
        
    }

    public long getInventoryId() {
        return inventoryId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    
    public String getItemQuantity() {
        return itemQuantity;
    }
    
    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "|InventoryID: " + inventoryId + "|ItemType: " + itemType
                + "|ItemCost: " + cost + "|";
    }
}
