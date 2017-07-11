/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.service;

import com.tsguild.vendingmachinespringmvc2.dao.VendingMachinePersistenceException;
import com.tsguild.vendingmachinespringmvc2.model.Change;
import com.tsguild.vendingmachinespringmvc2.model.Item;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface VendingMachineServiceLayer {
    
    
    public Map<String, List<Item>> getInventoryByItem();

    public int getListSize();

    public List<Item> listTotalInventory();

    public Item getItemBySelection(long itemId) throws VendingMachineNullInventoryException, VendingMachineDoesNotExistException;

    public void removeItem(long itemId);

    public Change calculateUserChange(BigInteger pennies);

    public boolean validateCanBuy(double userMoney, Item itemSelection) throws VendingMachineInsufficientFundsException;

    public Change makeSale(double userMoney, Item itemSelection);
    
    public BigInteger convertMoneyToPennies(double userMoney);
    
    public BigInteger calcRemainingUserPennies(BigInteger userMoney, BigInteger itemCost);

    public void loadInventory() throws VendingMachinePersistenceException, URISyntaxException;

}
