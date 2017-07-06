/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.service;

import com.tsguild.flooringmastery.dao.FlooringMasteryFileValidationException;
import com.tsguild.flooringmastery.dao.FlooringMasteryFilePersistenceException;
import com.tsguild.flooringmastery.model.Order;
import com.tsguild.flooringmastery.model.Product;
import com.tsguild.flooringmastery.model.Tax;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface FlooringMasteryServiceLayer {

    public void load() throws FlooringMasteryFilePersistenceException, FlooringMasteryFileValidationException;
    
    public String getConfig() throws FlooringMasteryFilePersistenceException;
    
    public void saveCurrentProgress() throws FlooringMasteryFilePersistenceException;

    public List<Order> getOrdersByDate(LocalDate dateToSearch);

    public Order populateNewOrderInfo(Order newOrder)throws FlooringMasteryFilePersistenceException;

    public boolean validateNewOrderProduct(Order newOrder) throws FlooringMasteryInvalidProductException;

    public boolean validateNewOrderState(Order newOrder) throws FlooringMasteryInvalidStateException;
    
    public Order editExistingOrder(String orderIDOfOrderToEdit, Order editedOrder, String configType);
    
    public Order getOrderByID(String orderID) throws FlooringMasteryDoesNotExistException;
    
    public Order deleteOrder(Order orderToDelete, String configType);
    
    public boolean validateDateToSearch(LocalDate dateToSearch) throws FlooringMasteryDoesNotExistException;
    
    public Order addNewOrder(Order newOrder);
    
    public List<Product> getProducts ();
    
    public List<Tax> getStates();
    
    public String generateTimeStampEnteredDate(String enteredDate);
    
}
