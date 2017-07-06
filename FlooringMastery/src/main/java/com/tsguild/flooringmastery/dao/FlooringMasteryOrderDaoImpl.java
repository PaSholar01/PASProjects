/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    private Map<String, Order> orderMap = new HashMap<>();

    private String DELIMITER = ",";

    @Override
    public void load() throws FlooringMasteryFilePersistenceException, FlooringMasteryFileValidationException {
        
        File dateFolder = new File("Order_Date_Files");
        File dateFolderErrors = new File("Order_Date_Error_Files");
        
        List<String> dateFiles = getListAllDateFiles();
        List<String> orderIDs = new ArrayList<>();
        Scanner console = null;

        for (String currentFile : dateFiles) {
            boolean hasErrors = false;
            try {
                console = new Scanner(new BufferedReader(new FileReader(currentFile)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryFilePersistenceException("The file could not be loaded...");
            }

            String currentLine = "";
            String[] currentTokens;

            while (console.hasNextLine()) {
                try {
                    currentLine = console.nextLine();
                    currentTokens = currentLine.split(",");

                    Order currentOrder = new Order(currentTokens[0]);
                    currentOrder.setCustomerName(currentTokens[1]);
                    currentOrder.setState(currentTokens[2]);
                    currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                    currentOrder.setProductName(currentTokens[4]);
                    currentOrder.setArea(new BigDecimal(currentTokens[5]));
                    currentOrder.setMatCostSqFt(new BigDecimal(currentTokens[6]));
                    currentOrder.setLaborCostSqFt(new BigDecimal(currentTokens[7]));
                    currentOrder.setMatCost(new BigDecimal(currentTokens[8]));
                    currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                    currentOrder.setTotalOrderCost(new BigDecimal(currentTokens[10]));
                    currentOrder.setOrderTax(new BigDecimal(currentTokens[11]));
                    currentOrder.setOrderDate(currentFile);

                    for (String currentID : orderIDs) {
                        if (currentOrder.getOrderID().equalsIgnoreCase(currentID)) {
                            throw new NumberFormatException();
                        }
                    }

                    orderIDs.add(currentTokens[0]);
                    orderMap.put(currentOrder.getOrderID(), currentOrder);

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {

                    hasErrors = true;
                    //throw new FlooringMasteryFileValidationException("The file " + currentFile + " could not be loaded due to errors.");
                    PrintWriter save = null;

                    try {
                        save = new PrintWriter(new FileWriter(dateFolderErrors + "/" + currentFile.substring(16, 32) + ".txt", true));
                    } catch (IOException x) {
                        throw new FlooringMasteryFilePersistenceException("The error wins...");
                    }

                    save.println(currentLine + "===*ERROR DETECTED*===");
                    save.flush();
                    save.close();
                }

            }
        }
    }
    
    public String loadAndSaveOrderID() throws FlooringMasteryFilePersistenceException {
        
        String uniqueID;
        
        Scanner console = null;
        
        try {
            console = new Scanner(new BufferedReader(new FileReader("uniqueID.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryFilePersistenceException("The file could not be loaded...");
        }
        
        String updatedUniqueID = "";
        
        if (console.hasNextLine()) {
            updatedUniqueID = console.nextLine();
        }
        
        uniqueID = updatedUniqueID;
        
        int intUpdatedUniqueID = Integer.parseInt(updatedUniqueID);
        
        intUpdatedUniqueID += 1;
        
        updatedUniqueID = String.valueOf(intUpdatedUniqueID);
        
        PrintWriter save = null;
        
        try {
            save = new PrintWriter(new FileWriter("uniqueID.txt"));
        } catch (IOException e) {
            throw new FlooringMasteryFilePersistenceException("The file could not be loaded...");
        }
        
        save.println(updatedUniqueID);
        save.flush();
        save.close();
        
        return uniqueID;
    }

//THESE ARE FOR THE ORDERMAP--------------------------------------------------------------------------------------------------------------------
//==============================================================================================================================================
//==============================================================================================================================================
    @Override
    public Order getOrder(String orderID) {
        return orderMap.get(orderID);
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orderMap.values());
    }

    @Override
    public Order addOrder(String orderID, Order order
    ) {
        Order newOrder = orderMap.put(orderID, order);
        return newOrder;
    }

    @Override
    public Order editOrder(String orderID, Order order) {
        Order editedOrder = orderMap.replace(orderID, order);
        return editedOrder;
    }

    @Override
    public Order removeOrder(String orderID
    ) {
        Order removedOrder = orderMap.remove(orderID);
        return removedOrder;
    }

    @Override
    public File[] getAllPaths() {
        
        File dateFolder = new File("Order_Date_Files");
        
        return dateFolder.listFiles();
    }

    @Override
    public List<String> getListAllDates() {
        
        File dateFolder = new File("Order_Date_Files");
        
        File[] dateFolderFiles = dateFolder.listFiles();

        List<String> dateFiles = new ArrayList<>();

        for (File currentFile : dateFolderFiles) {

            if (currentFile.isFile()) {
                dateFiles.add(currentFile.toString().substring(24, 32));
            }
        }
        

        return dateFiles;
    }
    
    private List<String> getListAllDateFiles() {
        
        File dateFolder = new File("Order_Date_Files");
        
        File[] dateFolderFiles = dateFolder.listFiles();
        
        List<String> dateFiles = new ArrayList<>();
        
        for(File currentFile : dateFolderFiles){
            
            if(currentFile.isFile()) {
                dateFiles.add(currentFile.toString());
            }
        }
        
        return dateFiles;
    }

    @Override
    public void save() throws FlooringMasteryFilePersistenceException {

        PrintWriter save = null;

        List<Order> orderList = getAllOrders();

        Map<String, List<Order>> dateMap = orderList.stream()
                .collect(Collectors.groupingBy(Order::getOrderDate));

        Set<String> keySet = dateMap.keySet();

        for (String currentKey : keySet) {

            try {
                save = new PrintWriter(new FileWriter(currentKey));
            } catch (IOException e) {
                throw new FlooringMasteryFilePersistenceException("The file could not be found...");
            }

            List<Order> dateOrderList = dateMap.get(currentKey);

            for (Order currentOrder : dateOrderList) {

                save.println(currentOrder.getOrderID() + "," + currentOrder.getCustomerName()
                        + "," + currentOrder.getState() + "," + currentOrder.getTaxRate() + ","
                        + currentOrder.getProductName() + "," + currentOrder.getArea() + ","
                        + currentOrder.getMatCostSqFt() + "," + currentOrder.getLaborCostSqFt() + ","
                        + currentOrder.getMatCost() + "," + currentOrder.getLaborCost() + ","
                        + currentOrder.getOrderTax() + "," + currentOrder.getTotalOrderCost());
                save.flush();
            }
            save.close();
        }


        /*save.println(currentOrder.getOrderID() + "," + currentOrder.getCustomerName()
                        + "," + currentOrder.getState() + "," + currentOrder.getTaxRate() + ","
                        + currentOrder.getProductName() + "," + currentOrder.getArea() + ","
                        + currentOrder.getMatCostSqFt() + "," + currentOrder.getLaborCostSqFt() + ","
                        + currentOrder.getMatCost() + "," + currentOrder.getLaborCost() + ","
                        + currentOrder.getOrderTax() + "," + currentOrder.getTotalOrderCost());*/
    }

}
