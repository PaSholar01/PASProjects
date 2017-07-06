/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery;

import com.tsguild.flooringmastery.controller.FlooringMasteryController;
import com.tsguild.flooringmastery.dao.FlooringMasteryFilePersistenceException;
import com.tsguild.flooringmastery.service.FlooringMasteryInvalidProductException;
import com.tsguild.flooringmastery.service.FlooringMasteryInvalidStateException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) throws FlooringMasteryFilePersistenceException, FlooringMasteryInvalidProductException, FlooringMasteryInvalidStateException {

        /* UserIO myIO = new UserIOConsoleImpl();
        FlooringMasteryView myView = new FlooringMasteryView(myIO);
        FlooringMasteryProductDao pDao = new FlooringMasteryProductDaoImpl("products.txt");
        FlooringMasteryTaxDao tDao = new FlooringMasteryTaxDaoImpl("tax.txt");
        FlooringMasteryOrderDao oDao = new FlooringMasteryOrderDaoImpl();
        ConfirgurationDao configDao = new ConfigurationDaoImpl();
        FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerImpl(oDao, pDao, tDao, configDao);
        FlooringMasteryController myCommand = new FlooringMasteryController(myView, myService);

        myCommand.run();*/
        ApplicationContext ctxFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController myCommand = ctxFactory.getBean("controller", FlooringMasteryController.class);
        myCommand.run();
    }
}
