/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Order;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface FlooringMasteryOrderDao {

    public void load() throws FlooringMasteryFilePersistenceException, FlooringMasteryFileValidationException;

    public void save() throws FlooringMasteryFilePersistenceException;
    
    public String loadAndSaveOrderID() throws FlooringMasteryFilePersistenceException;

    public Order getOrder(String orderID);

    public List<Order> getAllOrders();

    public Order addOrder(String orderID, Order order);

    public Order editOrder(String orderID, Order order);

    public Order removeOrder(String orderID);

    public List<String> getListAllDates();

    public File[] getAllPaths();

}
