/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Order;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryOrderDaoStub implements FlooringMasteryOrderDao {

    private Map<String, Order> orderMap = new HashMap<>();
    private Map<String, List<Order>> dateMap = new HashMap<>();
    private File dateFolder = new File("Order_Date_Files");
    private Order order1;
    private Order order2;

    public FlooringMasteryOrderDaoStub() {

        order1 = new Order("001");
        order1.setCustomerName("Paul");
        order1.setState("Ky");
        order1.setTaxRate(new BigDecimal("6.75"));
        order1.setArea(new BigDecimal("5.00"));
        order1.setOrderDate("Date_Folder_Orders/Orders_06232017.txt");
        order1.setProductName("Tile");
        order1.setMatCostSqFt(new BigDecimal("5.50"));
        order1.setLaborCostSqFt(new BigDecimal("6.00"));
        order1.setMatCost(new BigDecimal("50.00"));
        order1.setLaborCost(new BigDecimal("500.00"));
        order1.setOrderTax(new BigDecimal("50.00"));
        order1.setTotalOrderCost(new BigDecimal("30000.00"));

        order2 = new Order("002");
        order2.setCustomerName("Bree");
        order2.setState("Ky");
        order2.setTaxRate(new BigDecimal("6.75"));
        order2.setArea(new BigDecimal("5.00"));
        order2.setOrderDate("Date_Folder_Orders/Orders_06232017.txt");
        order2.setProductName("Tile");
        order2.setMatCostSqFt(new BigDecimal("5.50"));
        order2.setLaborCostSqFt(new BigDecimal("6.00"));
        order2.setMatCost(new BigDecimal("50.00"));
        order2.setLaborCost(new BigDecimal("500.00"));
        order2.setOrderTax(new BigDecimal("50.00"));
        order2.setTotalOrderCost(new BigDecimal("30000.00"));

        orderMap.put(order1.getOrderID(), order1);
        orderMap.put(order2.getOrderID(), order2);

    }

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
    public Order editOrder(String orderID, Order order
    ) {
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

        return dateFolder.listFiles();
    }

    @Override
    public List<String> getListAllDates() {

        //File dateFolder = new File("Order_Date_Files");
        File[] dateFolderFiles = dateFolder.listFiles();

        List<String> dateFiles = new ArrayList<>();

        for (File currentFile : dateFolderFiles) {

            if (currentFile.isFile()) {
                dateFiles.add(currentFile.toString());
            }
        }

        return dateFiles;
    }

    @Override
    public void load() throws FlooringMasteryFilePersistenceException, FlooringMasteryFileValidationException {
        
    }

    @Override
    public void save() throws FlooringMasteryFilePersistenceException {
        
    }

    @Override
    public String loadAndSaveOrderID() throws FlooringMasteryFilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
