/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import com.tsguild.flooringmastery.model.Tax;
import java.io.BufferedReader;
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

/**
 *
 * @author apprentice
 */
public class FlooringMasteryTaxDaoImpl implements FlooringMasteryTaxDao {

    private Map<String, Tax> taxMap = new HashMap<>();

    private String DELIMITER = ",";

    private String TAX_FILE;

    public FlooringMasteryTaxDaoImpl(String fileName) {
        this.TAX_FILE = fileName;
    }

    public FlooringMasteryTaxDaoImpl() {

    }

    @Override
    public void load() throws FlooringMasteryFilePersistenceException {
        Scanner console = null;

        try {

            console = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));

        } catch (FileNotFoundException e) {

            throw new FlooringMasteryFilePersistenceException("Product File Unavailable", e);
        }

        String currentLine;
        String[] currentTokens;

        while (console.hasNextLine()) {

            currentLine = console.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Tax currentTax = new Tax();

            currentTax.setState(currentTokens[0]);
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

            taxMap.put(currentTax.getState(), currentTax);
        }

        console.close();
    }

    @Override
    public void save() throws FlooringMasteryFilePersistenceException {

        PrintWriter save;

        try {
            save = new PrintWriter(new FileWriter(TAX_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryFilePersistenceException("Failed to save file", e);
        }

        List<Tax> taxList = this.listAllTaxes();

        for (Tax currentTax : taxList) {

            save.println(currentTax.getState() + "," + currentTax.getTaxRate());
            save.flush();
        }

        save.close();

    }

    @Override
    public List<Tax> listAllTaxes() {

        return new ArrayList<>(taxMap.values());
    }

    @Override
    public Tax getTax(String state) {
        return taxMap.get(state);
    }

    @Override
    public Tax addTax(String state, Tax tax) {
        Tax newTax = taxMap.put(state, tax);
        return newTax;
        
    }

    @Override
    public Tax removeTax(String state) {
        Tax removedTax = taxMap.remove(state);
        return removedTax;
    }

    @Override
    public Tax editTax(String state, Tax tax) {
        Tax editedTax = taxMap.replace(state, tax);
        return editedTax;
    }
}
