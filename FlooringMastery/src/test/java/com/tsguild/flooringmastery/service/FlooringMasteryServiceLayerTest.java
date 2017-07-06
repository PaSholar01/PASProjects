/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.service;

import com.tsguild.flooringmastery.dao.ConfigurationDaoStub;
import com.tsguild.flooringmastery.dao.ConfirgurationDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryAuditDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryAuditDaoStub;
import com.tsguild.flooringmastery.dao.FlooringMasteryFilePersistenceException;
import com.tsguild.flooringmastery.dao.FlooringMasteryOrderDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryOrderDaoStub;
import com.tsguild.flooringmastery.dao.FlooringMasteryProductDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryProductDaoStub;
import com.tsguild.flooringmastery.dao.FlooringMasteryTaxDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryTaxDaoStub;
import com.tsguild.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryServiceLayerTest {

    private FlooringMasteryServiceLayer service;

    public FlooringMasteryServiceLayerTest() {

        FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoStub();
        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoStub();
        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoStub();
        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStub();
        ConfirgurationDao configDao = new ConfigurationDaoStub();

        service = new FlooringMasteryServiceLayerImpl(orderDao, productDao, taxDao, configDao, auditDao);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of loadOrders method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testLoad() throws Exception {
        try{
        service.load();
        } catch(Exception e){
            fail("Exception should not be thrown on load!!!!!");
        }
    }

    /**
     * Test of getConfig method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetConfig() throws Exception {

        String value = service.getConfig();
        assertEquals("training", value);
    }

    /**
     * Test of saveCurrentProgress method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testSaveCurrentProgress() throws Exception {
        try{
        service.saveCurrentProgress();
        } catch(Exception e){
            fail("Exception should not be thrown on save!!!");
        }
    }

    /**
     * Test of getOrdersByDate method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrdersByDate() {

        LocalDate ld = LocalDate.parse("2017-06-23");
        List<Order> orderListByDate = service.getOrdersByDate(ld);
        List<Order> filteredList = new ArrayList<>();

        for (Order currentOrder : orderListByDate) {
            if (currentOrder.getOrderDate().contains("06232017")) {
                filteredList.add(currentOrder);
            }
        }

        assertEquals(2, filteredList.size());
    }

    /**
     * Test of populateNewOrderInfo method, of class
     * FlooringMasteryServiceLayer.
     */
    @Test
    public void testPopulateNewOrderInfo() throws Exception {

        Order order3 = new Order("003");
        order3.setCustomerName("Paul");
        order3.setState("Ky");
        order3.setTaxRate(new BigDecimal("6.75"));
        order3.setArea(new BigDecimal("5.00"));
        order3.setOrderDate("Date_Folder_Orders/Orders_06232017.txt");
        order3.setProductName("tile");
        order3.setMatCostSqFt(new BigDecimal("5.50"));
        order3.setLaborCostSqFt(new BigDecimal("6.00"));
        order3.setMatCost(new BigDecimal("50.00"));
        order3.setLaborCost(new BigDecimal("500.00"));
        order3.setOrderTax(new BigDecimal("50.00"));
        order3.setTotalOrderCost(new BigDecimal("30000.00"));

        Order order4 = service.populateNewOrderInfo(order3);

        assertEquals("Paul", order4.getCustomerName());
    }

    /**
     * Test of validateNewOrderProduct method, of class
     * FlooringMasteryServiceLayer.
     */
    @Test
    public void testValidateNewOrderProduct() throws Exception {

        Order order3 = new Order("003");
        order3.setCustomerName("Paul");
        order3.setState("Ky");
        order3.setTaxRate(new BigDecimal("6.75"));
        order3.setArea(new BigDecimal("5.00"));
        order3.setOrderDate("Date_Folder_Orders/Orders_06232017.txt");
        order3.setProductName("tile");
        order3.setMatCostSqFt(new BigDecimal("5.50"));
        order3.setLaborCostSqFt(new BigDecimal("6.00"));
        order3.setMatCost(new BigDecimal("50.00"));
        order3.setLaborCost(new BigDecimal("500.00"));
        order3.setOrderTax(new BigDecimal("50.00"));
        order3.setTotalOrderCost(new BigDecimal("30000.00"));

        assertEquals(true, service.validateNewOrderProduct(order3));
    }

    /**
     * Test of validateNewOrderState method, of class
     * FlooringMasteryServiceLayer.
     */
    @Test
    public void testValidateNewOrderState() throws Exception {

        Order order3 = new Order("003");
        order3.setCustomerName("Paul");
        order3.setState("Ky");
        order3.setTaxRate(new BigDecimal("6.75"));
        order3.setArea(new BigDecimal("5.00"));
        order3.setOrderDate("Date_Folder_Orders/Orders_06232017.txt");
        order3.setProductName("tile");
        order3.setMatCostSqFt(new BigDecimal("5.50"));
        order3.setLaborCostSqFt(new BigDecimal("6.00"));
        order3.setMatCost(new BigDecimal("50.00"));
        order3.setLaborCost(new BigDecimal("500.00"));
        order3.setOrderTax(new BigDecimal("50.00"));
        order3.setTotalOrderCost(new BigDecimal("30000.00"));

        assertEquals(true, service.validateNewOrderState(order3));
    }

    /**
     * Test of editExistingOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testEditExistingOrderGetOrderByIDAndDeleteOrder() throws FlooringMasteryDoesNotExistException, FlooringMasteryFilePersistenceException {

        Order order3 = new Order("002");
        order3.setCustomerName("Paul");
        order3.setState("Ky");
        order3.setTaxRate(new BigDecimal("6.75"));
        order3.setArea(new BigDecimal("5.00"));
        order3.setOrderDate("2017-06-23");
        order3.setProductName("tile");
        order3.setMatCostSqFt(new BigDecimal("5.50"));
        order3.setLaborCostSqFt(new BigDecimal("6.00"));
        order3.setMatCost(new BigDecimal("50.00"));
        order3.setLaborCost(new BigDecimal("500.00"));
        order3.setOrderTax(new BigDecimal("50.00"));
        order3.setTotalOrderCost(new BigDecimal("30000.00"));

        Order originalOrder = service.getOrderByID("002");
        assertEquals("Bree", originalOrder.getCustomerName());

        Order orderToEdit = service.editExistingOrder(originalOrder.getOrderID(), order3, "production");
        
        assertEquals("Bree", orderToEdit.getCustomerName());
        
        Order editedOrder = service.getOrderByID("002");

        assertEquals("002", editedOrder.getOrderID());
        assertEquals("Paul", editedOrder.getCustomerName());
        
        Order deletedOrder = service.deleteOrder(service.getOrderByID("002"), service.getConfig());
        assertEquals("Paul", deletedOrder.getCustomerName());
        
        LocalDate ld = LocalDate.parse("2017-06-23");
        List<Order> orderList = service.getOrdersByDate(ld);
        assertEquals(1, orderList.size());
    }

    /**
     * Test of getAllOrdersByDate method, of class FlooringMasteryServiceLayer.
     */
   // @Test
   // public void testGetAllOrdersByDate() throws FlooringMasteryDoesNotExistException {
        
    //    Map<String, List<Order>> dateMap = service.getAllOrdersByDate();
   //     LocalDate ld = LocalDate.parse("2017-06-23");
     //   
     //   Order orderInMap = service.getOrderByID("002");
      //  
     //   List<Order> orderByDate = service.getOrdersByDate(ld);
        
     //   List<Order> orderByMap = dateMap.get(orderInMap.getOrderDate());
        
      //  assertEquals(orderByDate.size(), orderByMap.size());
        
    //}

    /**
     * Test of validateDateToSearch method, of class
     * FlooringMasteryServiceLayer.
     */
    @Test
    public void testValidateDateToSearch() throws Exception {
       
        LocalDate ld = LocalDate.parse("2017-06-23");
        
        assertEquals(true, service.validateDateToSearch(ld));
    }

}
