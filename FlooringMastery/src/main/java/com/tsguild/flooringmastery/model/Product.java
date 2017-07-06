/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.model;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Product {
    
    private String productName;
    private BigDecimal matCostSqFt;
    private BigDecimal laborCostSqFt;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    
}
