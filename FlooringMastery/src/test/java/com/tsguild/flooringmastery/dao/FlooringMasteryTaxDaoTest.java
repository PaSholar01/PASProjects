/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.util.List;
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
public class FlooringMasteryTaxDaoTest {

    FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoImpl();

    public FlooringMasteryTaxDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        List<Tax> taxList = taxDao.listAllTaxes();
        for (Tax currentTax : taxList) {
            taxDao.removeTax(currentTax.getState());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTax method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testGetTax() {

        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(new BigDecimal("6.5"));

        taxDao.addTax(tax.getState(), tax);

        assertEquals("KY", taxDao.getTax("KY").getState());
    }

    /**
     * Test of addTax method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testAddTax() {

        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(new BigDecimal("6.5"));

        taxDao.addTax(tax.getState(), tax);

        assertEquals("KY", taxDao.getTax("KY").getState());
    }

    /**
     * Test of removeTax method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testRemoveTax() {
        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(new BigDecimal("6.5"));

        taxDao.addTax(tax.getState(), tax);

        assertEquals("KY", taxDao.getTax("KY").getState());

        assertEquals(1, taxDao.listAllTaxes().size());

        taxDao.removeTax("KY");

        assertEquals(0, taxDao.listAllTaxes().size());

    }

    /**
     * Test of editTax method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testEditTax() {

        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(new BigDecimal("6.5"));

        taxDao.addTax(tax.getState(), tax);

        assertEquals("6.5", taxDao.getTax("KY").getTaxRate());
        
        Tax tax2 = new Tax();
       tax.setTaxRate(new BigDecimal("7.6"));
        
        taxDao.editTax("KY", tax2);
        
        assertEquals("7.6", taxDao.getTax("KY").getTaxRate());
    }

}
