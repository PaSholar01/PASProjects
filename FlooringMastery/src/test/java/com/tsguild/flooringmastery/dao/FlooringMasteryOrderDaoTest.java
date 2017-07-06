/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Order;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryOrderDaoTest {

    private FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoImpl();

    public FlooringMasteryOrderDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Order> orderList = orderDao.getAllOrders();
        for (Order currentOrder : orderList) {
            orderDao.removeOrder(currentOrder.getOrderID());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of loadOrderFile method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testLoadOrderFile() throws Exception {
        try{
        orderDao.load();
        } catch(Exception e){
            fail("Exception should not be thrown on load!");
        }
    }

    /**
     * Test of saveFile method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testSaveFile() throws Exception {
        List<Order> orderList = orderDao.getAllOrders();
        Map<String, List<Order>> mapDates = orderList.stream()
                .collect(Collectors.groupingBy(Order::getOrderDate));
        try{
        orderDao.save();
        } catch(Exception e){
            fail("Exception should not be thrown on save!!!");
        }
    }

    /**
     * Test of getOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testGetAddOrder() {
        
        Map<String, Order> orderMap = new HashMap<>();
        
        Order order1 = new Order("001");
        order1.setCustomerName("Paul");
        order1.setState("KY");
        order1.setTaxRate(new BigDecimal("6.75"));
        order1.setArea(new BigDecimal("500"));
        order1.setLaborCost(new BigDecimal("5.00"));
        order1.setLaborCostSqFt(new BigDecimal("5.00"));
        order1.setMatCost(new BigDecimal("5.00"));
        order1.setMatCostSqFt(new BigDecimal("1.50"));
        order1.setOrderDate("BlahDiBloohBlah");
        order1.setOrderTax(new BigDecimal("545"));
        order1.setProductName("Tile");
        order1.setTotalOrderCost(new BigDecimal("1000"));
        
        orderMap.put(order1.getOrderID(), order1);
        
        assertEquals(orderMap.size(), 1);
        
        Order order1Get = orderMap.get("001");
        
        assertEquals("Paul", order1Get.getCustomerName());
        
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testGetAllOrders() {
        
        
        Order order1 = new Order("001");
        order1.setCustomerName("Paul");
        order1.setState("KY");
        order1.setTaxRate(new BigDecimal("6.75"));
        order1.setArea(new BigDecimal("500"));
        order1.setLaborCost(new BigDecimal("5.00"));
        order1.setLaborCostSqFt(new BigDecimal("5.00"));
        order1.setMatCost(new BigDecimal("5.00"));
        order1.setMatCostSqFt(new BigDecimal("1.50"));
        order1.setOrderDate("BlahDiBloohBlah");
        order1.setOrderTax(new BigDecimal("545"));
        order1.setProductName("Tile");
        order1.setTotalOrderCost(new BigDecimal("1000"));
        
        orderDao.addOrder(order1.getOrderID(), order1);
        
        Order order2 = new Order("002");
        order2.setCustomerName("Paul");
        order2.setState("KY");
        order2.setTaxRate(new BigDecimal("6.75"));
        order2.setArea(new BigDecimal("500"));
        order2.setLaborCost(new BigDecimal("5.00"));
        order2.setLaborCostSqFt(new BigDecimal("5.00"));
        order2.setMatCost(new BigDecimal("5.00"));
        order2.setMatCostSqFt(new BigDecimal("1.50"));
        order2.setOrderDate("BlahDiBloohBlah");
        order2.setOrderTax(new BigDecimal("545"));
        order2.setProductName("Tile");
        order2.setTotalOrderCost(new BigDecimal("1000"));
        
        orderDao.addOrder(order2.getOrderID(), order2);
        
        List<Order> orderList = orderDao.getAllOrders();
        
        assertEquals(2, orderList.size());
    }

    /**
     * Test of editOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testEditOrder() {
        
        
        Order order1 = new Order();
        order1.setOrderID("001");
        order1.setCustomerName("Paul");
        order1.setState("KY");
        order1.setTaxRate(new BigDecimal("6.75"));
        order1.setArea(new BigDecimal("500"));
        order1.setLaborCost(new BigDecimal("5.00"));
        order1.setLaborCostSqFt(new BigDecimal("5.00"));
        order1.setMatCost(new BigDecimal("5.00"));
        order1.setMatCostSqFt(new BigDecimal("1.50"));
        order1.setOrderDate("BlahDiBloohBlah");
        order1.setOrderTax(new BigDecimal("545"));
        order1.setProductName("Tile");
        order1.setTotalOrderCost(new BigDecimal("1000"));
        
        orderDao.addOrder(order1.getOrderID(), order1);
        
        Order order2 = new Order();
        order2.setOrderID("001");
        order2.setCustomerName("Ron");
        order2.setState("WI");
        order2.setTaxRate(new BigDecimal("6.75"));
        order2.setArea(new BigDecimal("500"));
        order2.setLaborCost(new BigDecimal("5.00"));
        order2.setLaborCostSqFt(new BigDecimal("5.00"));
        order2.setMatCost(new BigDecimal("5.00"));
        order2.setMatCostSqFt(new BigDecimal("1.50"));
        order2.setOrderDate("BlahDiBloohBlah");
        order2.setOrderTax(new BigDecimal("545"));
        order2.setProductName("Tile");
        order2.setTotalOrderCost(new BigDecimal("1000"));
        
        orderDao.editOrder("001", order2);
        Order orderEdited = orderDao.getOrder("001");
        assertEquals("WI", orderEdited.getState());

    }

    /**
     * Test of removeOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testRemoveOrder() {
        
        Order order1 = new Order();
        order1.setOrderID("001");
        order1.setCustomerName("Paul");
        order1.setState("KY");
        order1.setTaxRate(new BigDecimal("6.75"));
        order1.setArea(new BigDecimal("500"));
        order1.setLaborCost(new BigDecimal("5.00"));
        order1.setLaborCostSqFt(new BigDecimal("5.00"));
        order1.setMatCost(new BigDecimal("5.00"));
        order1.setMatCostSqFt(new BigDecimal("1.50"));
        order1.setOrderDate("BlahDiBloohBlah");
        order1.setOrderTax(new BigDecimal("545"));
        order1.setProductName("Tile");
        order1.setTotalOrderCost(new BigDecimal("1000"));
        
        orderDao.addOrder(order1.getOrderID(), order1);
        
        List<Order> orderList = orderDao.getAllOrders();
        
        assertEquals(1, orderList.size());
        
        orderDao.removeOrder("001");
        
        orderList = orderDao.getAllOrders();
        
        assertEquals(0, orderList.size());
    }

    /**
     * Test of getListAllDateFiles method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testGetListAllDateFiles() {
        
       List<String> dateList = orderDao.getListAllDates();
    }

    /**
     * Test of getAllFiles method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testGetAllFiles() {
        File[] folder = orderDao.getAllPaths();
    }

}
