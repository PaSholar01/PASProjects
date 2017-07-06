/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryAuditDaoFileImpl implements FlooringMasteryAuditDao {

    private final String AUDIT_FILE = "auditFile.txt";

    @Override
    public void writeToAudit(String message) throws FlooringMasteryFilePersistenceException {

        PrintWriter saveToAudit = null;

        try {
            saveToAudit = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FlooringMasteryFilePersistenceException("Failed to save to audit log.");
        }

        LocalDate timeStamp = LocalDate.now();
        saveToAudit.println(timeStamp.toString() + " : " + message);
        saveToAudit.flush();
        saveToAudit.close();
    }

}
