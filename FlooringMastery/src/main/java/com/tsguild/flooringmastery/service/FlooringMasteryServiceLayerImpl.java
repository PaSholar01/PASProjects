/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.service;

import com.tsguild.flooringmastery.dao.FlooringMasteryFileValidationException;
import com.tsguild.flooringmastery.dao.ConfirgurationDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryAuditDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryFilePersistenceException;
import com.tsguild.flooringmastery.dao.FlooringMasteryOrderDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryProductDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryTaxDao;
import com.tsguild.flooringmastery.model.Order;
import com.tsguild.flooringmastery.model.Product;
import com.tsguild.flooringmastery.model.Tax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {
    
    private FlooringMasteryOrderDao orderDao;
    private FlooringMasteryProductDao productDao;
    private FlooringMasteryTaxDao taxDao;
    private ConfirgurationDao configDao;
    private FlooringMasteryAuditDao auditDao;
    
    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrderDao orderDao,
            FlooringMasteryProductDao productDao,
            FlooringMasteryTaxDao taxDao, ConfirgurationDao configDao,
            FlooringMasteryAuditDao auditDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
        this.configDao = configDao;
        this.auditDao = auditDao;
    }

    //THESE METHODS ARE USED TO GET THE CONFIGURATION OF THE SYSTEM
    //=======================================================================================================================================
    //=======================================================================================================================================
    public String getConfig() throws FlooringMasteryFilePersistenceException {
        return configDao.loadFile();
    }

    //THESE METHODS ARE USED TO DISPLAY THE ORDERS FOR A PARTICULAR DATE
    //
    //
    //
    @Override
    public List<Order> getOrdersByDate(LocalDate dateToSearch) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        
        String stringDate = dateToSearch.format(formatter);
        
        List<Order> unFilteredOrders = orderDao.getAllOrders();
        
        List<Order> filteredOrders = new ArrayList<>();
        
        for (Order currentOrder : unFilteredOrders) {
            if (currentOrder.getOrderDate().contains(stringDate)) {
                filteredOrders.add(currentOrder);
            }
        }
        
        return filteredOrders;
        
    }
    
    @Override
    public boolean validateDateToSearch(LocalDate dateToSearch) throws FlooringMasteryDoesNotExistException {
        
        boolean doesItExist = false;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        
        String stringDate = dateToSearch.format(formatter);
        
        List<String> dateFiles = orderDao.getListAllDates();
        
        for (String currentDate : dateFiles) {
            
            if (currentDate.contains(stringDate)) {
                doesItExist = true;
            }
        }
        
        List<Order> orderList = orderDao.getAllOrders();
        
        for (Order currentOrder : orderList) {
            
            if (currentOrder.getOrderDate().contains(stringDate)) {
                doesItExist = true;
            }
        }
        
        if (doesItExist == false) {
            throw new FlooringMasteryDoesNotExistException("==========THE DATE DOES NOT EXIST IN THE SYSTEM===============" + "\n"
                    + " * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  ");
        }
        
        return doesItExist;
        
    }
    //END DISPLAY ORDERS METHODS------------------------------------------------------------------------------
    //========================================================================================================
    //========================================================================================================

    //THESE METHODS ARE USED TO ADD A NEW ORDER --------------------------------------------------------------
    //========================================================================================================
    //========================================================================================================
    @Override
    public boolean validateNewOrderState(Order newOrder) throws FlooringMasteryInvalidStateException {
        
        boolean isValid = false;
        
        String checkState = newOrder.getState();
        List<Tax> taxList = taxDao.listAllTaxes();
        for (Tax currentTax : taxList) {
            
            if (currentTax.getState().equalsIgnoreCase(checkState)) {
                isValid = true;
            }
            
        }
        
        if (isValid == false) {
            
            throw new FlooringMasteryInvalidStateException("==========THE STATE DOES NOT EXIST IN THE SYSTEM==============" + "\n"
                    + "=======The format must be a valid state (ex:KY,Ky,ky)=========");
        }
        
        return isValid;
    }
    
    @Override
    public boolean validateNewOrderProduct(Order newOrder) throws FlooringMasteryInvalidProductException {
        
        boolean isValid = false;
        
        String checkProduct = newOrder.getProductName();
        List<Product> productList = productDao.listAllProducts();
        for (Product currentProduct : productList) {
            if (currentProduct.getProductName().equalsIgnoreCase(checkProduct)) {
                isValid = true;
            }
            
        }
        
        if (isValid == false) {
            
            throw new FlooringMasteryInvalidProductException("=========THE PRODUCT DOES NOT EXIST IN THE SYSTEM=============" + "\n"
                    + "==============CHECK YOUR ENTRY AND TRY AGAIN==================");
        }
        
        return isValid;
    }
    
    @Override
    public List<Product> getProducts(){
        
        List<Product> productList = productDao.listAllProducts();
        return productList;
    }
    
    @Override
    public List<Tax> getStates(){
        
        List<Tax> taxList = taxDao.listAllTaxes();
        return taxList;
    }
    
    public String generateTimeStampFileFormat() {
        
        LocalDate ld = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String rawDate = ld.format(formatter);
        String timeStampFileString = "Order_Date_Files/Orders_" + rawDate + ".txt";
        return timeStampFileString;
    }
    
    @Override
    public String generateTimeStampEnteredDate(String enteredDate) {
        
        String timeStampString = "Order_Date_Files/Orders_" + enteredDate + ".txt";
        return timeStampString;
    }
    
    @Override
    public Order addNewOrder(Order newOrder) {
        
        return orderDao.addOrder(newOrder.getOrderID(), newOrder);
    }
    
    @Override
    public Order populateNewOrderInfo(Order newOrder) throws FlooringMasteryFilePersistenceException{
        
        boolean isUnique = false;
        
        Product newOrderProduct = productDao.getProduct(newOrder.getProductName().toLowerCase());
        Tax newOrderTax = taxDao.getTax(newOrder.getState().toUpperCase());
        
        newOrder.setMatCostSqFt(newOrderProduct.getMatCostSqFt().setScale(2, RoundingMode.HALF_UP));
        newOrder.setLaborCostSqFt(newOrderProduct.getLaborCostSqFt().setScale(2, RoundingMode.HALF_UP));
        
        String taxRate = newOrderTax.getTaxRate().toString();
        double doubleTaxRate = Double.parseDouble(taxRate);
        double taxRatePercent = doubleTaxRate / 100.00;
        BigDecimal taxRatePercentCalc = new BigDecimal(taxRatePercent);
        
        newOrder.setTaxRate(new BigDecimal(taxRate).setScale(2, RoundingMode.HALF_UP));
        newOrder.setMatCost((newOrder.getMatCostSqFt().multiply(newOrder.getArea())).setScale(2, RoundingMode.HALF_UP));
        newOrder.setLaborCost((newOrder.getLaborCostSqFt().multiply(newOrder.getArea())).setScale(2, RoundingMode.HALF_UP));
        newOrder.setOrderTax((taxRatePercentCalc.multiply(newOrder.getMatCost().add(newOrder.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        newOrder.setTotalOrderCost((newOrder.getOrderTax().add(newOrder.getLaborCost().add(newOrder.getMatCost()))).setScale(2, RoundingMode.HALF_UP));
        
        if (newOrder.getOrderDate() == null) {
            newOrder.setOrderDate(generateTimeStampFileFormat());
        } else {
            newOrder.setOrderDate(generateTimeStampEnteredDate(newOrder.getOrderDate()));
        }
        
        while (!isUnique) {
            
            String uniqueID = orderDao.loadAndSaveOrderID();
            List<Order> orderList = orderDao.getAllOrders();
            int counter = 0;
            
            for (Order currentOrder : orderList) {
                if (currentOrder.getOrderID().contentEquals(uniqueID)) {
                    counter++;
                }
            }
            
            if (counter == 0) {
                
                isUnique = true;
                newOrder.setOrderID(uniqueID);
            }
            
        }
        
        return newOrder;
    }

    //END METHODS FOR ADDING A NEW ORDER--------------------------------------------------------------------------------------------
    //==============================================================================================================================
    //==============================================================================================================================
    //METHODS FOR EDITING A NEW ORDER-----------------------------------------------------------------------------------------------
    //==============================================================================================================================
    //==============================================================================================================================
    @Override
    public Order editExistingOrder(String orderIDOfOrderToEdit, Order editedOrder, String configType) {
        
        List<Order> orderList = orderDao.getAllOrders();
        
        Map<String, List<Order>> mapByDate = orderList.stream()
                .collect(Collectors.groupingBy(Order::getOrderDate));
        
        String dateOfOrderToEdit = orderDao.getOrder(orderIDOfOrderToEdit).getOrderDate();
        
        List<Order> ordersInFileOfInterest = mapByDate.get(dateOfOrderToEdit);
        
        if (configType.contains("production")) {
            
            if (ordersInFileOfInterest.size() <= 1 && orderDao.getOrder(orderIDOfOrderToEdit).getOrderDate().compareTo(editedOrder.getOrderDate()) != 0) {
                
                File[] dateFiles = orderDao.getAllPaths();
                
                for (File currentFile : dateFiles) {
                    
                    if (currentFile.getPath().contains(dateOfOrderToEdit)) {
                        
                        currentFile.delete();
                    }
                }
            }
        }
        editedOrder.setOrderDate(generateTimeStampFileFormatForEdits(editedOrder.getOrderDate()));
        return orderDao.editOrder(orderIDOfOrderToEdit, editedOrder);
    }
    
    public String generateTimeStampFileFormatForEdits(String orderDateToFormat) {
        
        LocalDate ld = LocalDate.parse(orderDateToFormat);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String rawDate = ld.format(formatter);
        String timeStampFileString = "Order_Date_Files/Orders_" + rawDate + ".txt";
        return timeStampFileString;
    }
    
    @Override
    public Order getOrderByID(String orderID) throws FlooringMasteryDoesNotExistException {
        Order orderToEdit = orderDao.getOrder(orderID);
        if (orderToEdit == null) {
            throw new FlooringMasteryDoesNotExistException("===========THE ID DOES NOT EXIST IN THE SYSTEM================" + "\n"
                    + "   * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  " + "\n"
                    + "====IF YOU DO NOT KNOW THE ID OF THE ORDER YOU WISH TO EDIT===" + "\n"
                    + " PLEASE CHOOSE TO DISPLAY ORDERS BY DATE TO FIND THE ORDER ID " + "\n"
                    + "   * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  ");
        }
        return orderToEdit;
    }

    //END METHODS FOR EDITING A NEW ORDER-------------------------------------------------------------------------------------------
    //==============================================================================================================================
    //==============================================================================================================================
    //METHODS FOR REMOVING AN ORDER-------------------------------------------------------------------------------------------------
    //==============================================================================================================================
    //==============================================================================================================================
    @Override
    public Order deleteOrder(Order orderToDelete, String configType) {
        
        List<Order> orderList = orderDao.getAllOrders();
        
        Map<String, List<Order>> mapByDate = orderList.stream()
                .collect(Collectors.groupingBy(Order::getOrderDate));
        
        String dateOfOrderToDelete = orderToDelete.getOrderDate();
        
        List<Order> ordersInFileOfInterest = mapByDate.get(dateOfOrderToDelete);
        
        if (configType.contains("production")) {
            
            if (ordersInFileOfInterest.size() <= 1) {
                
                File[] dateFiles = orderDao.getAllPaths();
                
                for (File currentFile : dateFiles) {
                    
                    if (currentFile.getPath().contains(dateOfOrderToDelete)) {
                        
                        currentFile.delete();
                    }
                }
            }
        }
        
        return orderDao.removeOrder(orderToDelete.getOrderID());
    }

    //END METHODS FOR REMOVING AN ORDER---------------------------------------------------------------------------------------------
    //==============================================================================================================================
    //==============================================================================================================================
    @Override
    public void load() throws FlooringMasteryFilePersistenceException, FlooringMasteryFileValidationException {
      
        orderDao.load();
        taxDao.load();
        productDao.load();  
        
    }
    
    @Override
    public void saveCurrentProgress() throws FlooringMasteryFilePersistenceException {
       
        orderDao.save();
        taxDao.save();
        productDao.save();
  
    }
    
}
