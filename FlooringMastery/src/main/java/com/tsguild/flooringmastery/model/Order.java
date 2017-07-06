/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author apprentice
 */
public class Order {

    private String orderID;
    private String customerName;
    private String state;
    private String productName;
    private String orderDate;
    private BigDecimal taxRate;
    private BigDecimal area;
    private BigDecimal matCostSqFt;
    private BigDecimal laborCostSqFt;
    private BigDecimal matCost;
    private BigDecimal laborCost;
    private BigDecimal orderTax;
    private BigDecimal totalOrderCost;

    public Order(String orderID) {
        /*Random randomizer = new Random();
         Integer random = randomizer.nextInt(1000000000);
         this.orderID = random.toString();*/
        this.orderID = orderID;
    }
    
    public Order(){
        
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getOrderID() {
        return orderID;
    }
    
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getMatCostSqFt() {
        return matCostSqFt;
    }

    public void setMatCostSqFt(BigDecimal matCostSqFt) {
        this.matCostSqFt = matCostSqFt;
    }

    public BigDecimal getLaborCostSqFt() {
        return laborCostSqFt;
    }

    public void setLaborCostSqFt(BigDecimal laborCostSqFt) {
        this.laborCostSqFt = laborCostSqFt;
    }

    public BigDecimal getMatCost() {
        return matCost;
    }

    public void setMatCost(BigDecimal matCost) {
        this.matCost = matCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(BigDecimal orderTax) {
        this.orderTax = orderTax;
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

    public void setTotalOrderCost(BigDecimal totalOrderCost) {
        this.totalOrderCost = totalOrderCost;
    }

}
