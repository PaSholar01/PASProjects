/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

/**
 *
 * @author apprentice
 */
public interface ConfirgurationDao {
    
    public String loadFile() throws FlooringMasteryFilePersistenceException;
}
