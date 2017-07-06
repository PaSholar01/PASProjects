/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ConfigurationDaoImpl implements ConfirgurationDao{
    
    private final String CONFIGURATION_FILE = "configuration.txt";
    
    @Override
    public String loadFile() throws FlooringMasteryFilePersistenceException {
        
        Scanner console;
        
        try{
            console = new Scanner( new BufferedReader( new FileReader(CONFIGURATION_FILE)));
        } catch(FileNotFoundException e){
            throw new FlooringMasteryFilePersistenceException("The file could not be loaded...");
        }
        
        String currentLine = "";
        
        while(console.hasNextLine()){
            currentLine = console.nextLine();
        }
        
        console.close();
        
        return currentLine;
    }
    
}
