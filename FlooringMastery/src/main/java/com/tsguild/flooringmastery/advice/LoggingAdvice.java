/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.advice;

import com.tsguild.flooringmastery.dao.FlooringMasteryAuditDao;
import com.tsguild.flooringmastery.dao.FlooringMasteryFilePersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class LoggingAdvice {

    private final FlooringMasteryAuditDao auditDao;

    public LoggingAdvice(FlooringMasteryAuditDao auditDao) {

        this.auditDao = auditDao;
    }
    
    public void createExceptionLogEntry(JoinPoint jp, Exception exceptionName){
        
        Object[] args = jp.getArgs();
        
        String logEntry = jp.getSignature().getName() + " : " + exceptionName.getClass().getSimpleName() + " : ";
        
        for(Object currentArg: args){
            logEntry += currentArg;
        }
        
        try{
            auditDao.writeToAudit(logEntry);
        } catch(FlooringMasteryFilePersistenceException e){
            System.err.println(
            "ERROR: Failure writing to audit log occured in Logging Advice");
        }
    }

}
