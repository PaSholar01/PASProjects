/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.dvdlibspringmvc.dao;

import com.tsguild.dvdlibspringmvc.model.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface DVDLibDao {
    
    public DVD addDVD(DVD dvd);
    
    public void removeDVD(long dvdId);
    
    public void editDVD(DVD dvd);
    
    public List<DVD> getAllDVDs();
    
    public DVD getDVDById(long dvdId);
    
    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria);
}
