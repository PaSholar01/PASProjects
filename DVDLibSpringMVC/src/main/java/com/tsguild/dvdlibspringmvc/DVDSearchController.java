/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.dvdlibspringmvc;

import com.tsguild.dvdlibspringmvc.dao.DVDLibDao;
import com.tsguild.dvdlibspringmvc.dao.SearchTerm;
import com.tsguild.dvdlibspringmvc.model.DVD;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class DVDSearchController {

    private DVDLibDao dao;

    @Inject
    public DVDSearchController(DVDLibDao dao) {
        this.dao = dao;
    }
    
     @RequestMapping(value="/displaySearchPage", method=RequestMethod.GET)
    public String displaySearchPage(Model model){
        
        return "searchpage";
    }

    @RequestMapping(value = "/searchDVDs", method = RequestMethod.POST)
    public String searchDVDs(HttpServletRequest request, Model model) {

        Map<SearchTerm, String> criteria = new HashMap<>();

        String termToSearch = request.getParameter("search-bar");
        String currentSelectTerm = request.getParameter("search-category");

        if (currentSelectTerm != null && !currentSelectTerm.isEmpty() && currentSelectTerm.equals("title")) {
            criteria.put(SearchTerm.TITLE, termToSearch);
        }

        if (currentSelectTerm != null && !currentSelectTerm.isEmpty() && currentSelectTerm.equals("release")) {
            criteria.put(SearchTerm.RELEASE_DATE, termToSearch);
        }

        if (currentSelectTerm != null && !currentSelectTerm.isEmpty() && currentSelectTerm.equals("director")) {
            criteria.put(SearchTerm.DIRECTOR, termToSearch);
        }

        if (currentSelectTerm != null && !currentSelectTerm.isEmpty() && currentSelectTerm.equals("rating")) {
            criteria.put(SearchTerm.RATING, termToSearch);
        }
        
        List<DVD> dvdList = dao.searchDVDs(criteria);

        model.addAttribute("dvdList", dvdList);
        
        return "searchpage";
    }
}
