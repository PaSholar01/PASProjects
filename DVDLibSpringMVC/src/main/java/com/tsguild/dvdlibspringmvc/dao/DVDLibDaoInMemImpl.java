/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.dvdlibspringmvc.dao;

import com.tsguild.dvdlibspringmvc.model.DVD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DVDLibDaoInMemImpl implements DVDLibDao{
    
    private Map<Long, DVD> dvdMap = new HashMap<>();

    private static long dvdIdCounter = 0;
    
    @Override
    public DVD addDVD(DVD dvd) {
        
        dvd.setDvdId(dvdIdCounter);
        
        dvdIdCounter++;
        
        dvdMap.put(dvd.getDvdId(), dvd);
        
        return dvd;
    }

    @Override
    public void removeDVD(long dvdId) {
        
        dvdMap.remove(dvdId);
        
    }

    @Override
    public void editDVD(DVD dvd) {
       
        dvdMap.put(dvd.getDvdId(), dvd);
        
    }

    @Override
    public List<DVD> getAllDVDs() {
        
        List<DVD> dvdList = new ArrayList(dvdMap.values());
        return dvdList;
    }

    @Override
    public DVD getDVDById(long dvdId) {
        
        DVD dvd = dvdMap.get(dvdId);
        return dvd;
    }

    @Override
    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria) {
        
           //Get all the search term values from the map
        String titleSearchCriteria = 
                criteria.get(SearchTerm.TITLE);
        String releaseDateSearchCriteria =
                criteria.get(SearchTerm.RELEASE_DATE);
        String directorSearchCriteria =
                criteria.get(SearchTerm.DIRECTOR);
        String ratingSearchCriteria = 
                criteria.get(SearchTerm.RATING);
        
        
        //Now we declare all the Predicate conditions...
        //Predicate is a functional interface with one method
        //called test(T t) that returns a boolean.
        /*Since Predicate is generic, we get to specify the type of object we want
        T to be - in our case, we want T to be of type Contact. This means the 
        Predicates declared here will hace one method: boolean test(Contact c)
        */
        
        Predicate<DVD> titleMatchPredicate;
        Predicate<DVD> releaseDateMatchPredicate;
        Predicate<DVD> directorMatchPredicate;
        Predicate<DVD> ratingMatchPredicate;
        
        /* The following predicate is used as a placeholder.
        It will always return true so that we can use this when search terms are empty
        
        */
        
        Predicate<DVD> truePredicate = (c) -> {
            return true;
        };
        
        
        
        if(titleSearchCriteria == null ||
           titleSearchCriteria.isEmpty()) {
            titleMatchPredicate = truePredicate;
        } else {
            titleMatchPredicate = 
                    (c) -> c.getDvdTitle().contains(titleSearchCriteria);
        }
        
        if(releaseDateSearchCriteria == null ||
           releaseDateSearchCriteria.isEmpty()){
            releaseDateMatchPredicate = truePredicate;
        } else {
            releaseDateMatchPredicate =
                    (c) -> c.getReleaseDate().contains(releaseDateSearchCriteria);
        }
        
        if(directorSearchCriteria == null ||
            directorSearchCriteria.isEmpty()){
            directorMatchPredicate = truePredicate;
        } else {
            directorMatchPredicate =
                    (c) -> c.getDirector().contains(directorSearchCriteria);
        }
            
        if(ratingSearchCriteria == null || 
           ratingSearchCriteria.isEmpty()){
            ratingMatchPredicate = truePredicate;
        } else {
            ratingMatchPredicate = 
                    (c) -> c.getRating().contains(ratingSearchCriteria);
        }
       
        
        //Return the list of contacts that match the given criteria.
        //we do this by AND ing all the predicates together in a filter
        //operation
        
        return dvdMap.values().stream()
                .filter(titleMatchPredicate
                        .and(releaseDateMatchPredicate)
                        .and(directorMatchPredicate)
                        .and(ratingMatchPredicate))
                .collect(Collectors.toList());
    }
    
}
