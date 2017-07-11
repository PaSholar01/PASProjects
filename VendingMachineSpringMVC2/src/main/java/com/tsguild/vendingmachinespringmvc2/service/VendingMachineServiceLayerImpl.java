/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachinespringmvc2.service;

import com.tsguild.vendingmachinespringmvc2.dao.VendingMachineDao;
import com.tsguild.vendingmachinespringmvc2.dao.VendingMachinePersistenceException;
import com.tsguild.vendingmachinespringmvc2.model.Change;
import com.tsguild.vendingmachinespringmvc2.model.Item;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerImpl  implements VendingMachineServiceLayer{
    
    private final BigInteger DOLLARS = new BigInteger("100");
    private final BigInteger QUARTERS = new BigInteger("25");
    private final BigInteger DIMES = new BigInteger("10");
    private final BigInteger NICKELS = new BigInteger("5");

    private VendingMachineDao dao;
 
    private BigDecimalChangeCalc myBigD = new BigDecimalChangeCalc();
    
    @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDao dao){

        this.dao = dao;
      
    }

    @Override
    public int getListSize() {

        int size = dao.listTotalInventory().size();
        return size;

    }

    @Override
    public List<Item> listTotalInventory() {
        return dao.listTotalInventory();
    }

    @Override
    public Item getItemBySelection(long itemId) throws VendingMachineNullInventoryException, VendingMachineDoesNotExistException {
        
        Item item;
        if(dao.getItemBySelection(itemId) != null){
            item = dao.getItemBySelection(itemId);
        } else {
            throw new VendingMachineDoesNotExistException(" Item " + itemId + " does not exist!");
        }
        if (Integer.parseInt(item.getItemQuantity()) != 0) {
            return dao.getItemBySelection(itemId);
        } else {
            throw new VendingMachineNullInventoryException(" Item " + itemId + " is sold out!");        
        }
    }

    @Override
    public void removeItem(long itemId) {
        if (dao.getItemBySelection(itemId) != null) {
            Item removedItem = dao.getItemBySelection(itemId);
            int itemQuantity = Integer.parseInt(removedItem.getItemQuantity());
            Integer updatedQuantity = itemQuantity - 1;
            removedItem.setItemQuantity(updatedQuantity.toString());
            dao.removeItem(removedItem);
            //auditDao.writeToAudit("Item " + removedItem.getItemType() + " " + removedItem.getInventoryId() + " has been SOLD");

        }
    }

    @Override
    public Map<String, List<Item>> getInventoryByItem() {
        List<Item> itemList = dao.listTotalInventory();
        return itemList.stream()
                .collect(Collectors.groupingBy(Item::getItemType));
    }

    /*@Override
    public void addZeroInventoryItem(String itemTypeUserSelection) throws VendingMachinePersistenceException {
        Item zeroInvItem = new Item("OUT OF STOCK");
        zeroInvItem.setItemType(itemTypeUserSelection);
        zeroInvItem.setCost("OUT OF STOCK");
        dao.addZeroInvItem(zeroInvItem.getInventoryId(), zeroInvItem);
    }*/
    @Override
    public Change calculateUserChange(BigInteger pennies) {

        BigInteger numDollars = myBigD.changeCalc(ChangeEnum.DOLLAR_DIVIDE, pennies, DOLLARS);
        BigInteger remPennies1 = myBigD.changeCalc(ChangeEnum.DOLLAR_MOD, pennies, DOLLARS);

        BigInteger numQuarters = myBigD.changeCalc(ChangeEnum.QUARTER_DIVIDE, remPennies1, QUARTERS);
        BigInteger remPennies2 = myBigD.changeCalc(ChangeEnum.QUARTER_MOD, remPennies1, QUARTERS);

        BigInteger numDimes = myBigD.changeCalc(ChangeEnum.DIME_DIVIDE, remPennies2, DIMES);
        BigInteger remPennies3 = myBigD.changeCalc(ChangeEnum.DIME_MOD, remPennies2, DIMES);

        BigInteger numNickels = myBigD.changeCalc(ChangeEnum.NICKEL_DIVIDE, remPennies3, NICKELS);
        BigInteger remPennies4 = myBigD.changeCalc(ChangeEnum.NICKEL_MOD, remPennies3, NICKELS);
        
        Change userChange = new Change();
        userChange.setDollars(numDollars);
        userChange.setQuarters(numQuarters);
        userChange.setDimes(numDimes);
        userChange.setNickels(numNickels);
        userChange.setPennies(remPennies4);
        
       // String userChange = "|| Change due: \n" + "|| Dollars: " + numDollars + "\n" + "|| Quarters: " + numQuarters
       //        + "\n" + "|| Dimes: " + numDimes + "\n" + "|| Nickels: " + numNickels + "\n" + "|| Pennies: " + remPennies4 + "\n";

        return userChange;
    }
    
    @Override
    public Change makeSale(double userMoney, Item itemSelection) {
        double itemCost = Double.parseDouble(itemSelection.getCost());
        Change userChange = null;
        BigInteger itemCostPennies = convertMoneyToPennies(itemCost);
        BigInteger userMoneyPennies = convertMoneyToPennies(userMoney);
        BigInteger remainingPennies = calcRemainingUserPennies(userMoneyPennies, itemCostPennies);
        return userChange = calculateUserChange(remainingPennies);
    }

    @Override
    public boolean validateCanBuy(double userMoney, Item itemSelection) throws VendingMachineInsufficientFundsException {
        boolean canBuy = false;
        double itemCost = Double.parseDouble(itemSelection.getCost());
        if (userMoney >= itemCost) {
            canBuy = true;
        } else {
            double amntNeeded = itemCost - userMoney;
            BigDecimal bAmntNeeded = new BigDecimal(amntNeeded).setScale(2, RoundingMode.HALF_UP);
            throw new VendingMachineInsufficientFundsException("You have insufficient funds, please add $ " + bAmntNeeded);
        }

        return canBuy;
    }

    @Override
    public BigInteger convertMoneyToPennies(double money) {

        BigDecimal bigDPennies = new BigDecimal(money);
        BigDecimal penniesConversion = new BigDecimal("100");
        bigDPennies = bigDPennies.multiply(penniesConversion);
        BigInteger bigIPennies = bigDPennies.toBigInteger();
        return bigIPennies;

    }

    @Override
    public BigInteger calcRemainingUserPennies(BigInteger userMoney, BigInteger itemCost) {

        return userMoney.subtract(itemCost);
    }

    @Override
    public void loadInventory() throws VendingMachinePersistenceException, URISyntaxException{
        dao.loadInventory();
    }

}
