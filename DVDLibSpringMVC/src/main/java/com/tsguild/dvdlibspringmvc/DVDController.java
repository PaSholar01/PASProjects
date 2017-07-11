/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.dvdlibspringmvc;

import com.tsguild.dvdlibspringmvc.dao.DVDLibDao;
import com.tsguild.dvdlibspringmvc.model.DVD;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class DVDController {
    
    private DVDLibDao dao;
    
    @Inject
    public DVDController(DVDLibDao dao){
        this.dao = dao;
    }
    public DVDController() {
        
    }
    
    @RequestMapping(value="/displayDVDPage", method=RequestMethod.GET)
    public String displayDVDPage() {
        return "dvds";
    }
    
    @RequestMapping(value="/displayCreateDVD", method=RequestMethod.GET)
    public String displayCreateDVD() {
        return "createdvd";
    }
    
    @RequestMapping(value="/displayDVDsTable", method=RequestMethod.GET)
    public String displayDVDsTable(Model model){
        
        List<DVD> dvdList = dao.getAllDVDs();
        
        model.addAttribute("dvdList", dvdList);
        
        return "welcome";
    }
    
    
    @RequestMapping(value="/createDVD", method=RequestMethod.POST)
    public String createDVD(HttpServletRequest request){
        
        DVD newDVD = new DVD();
        newDVD.setDvdTitle(request.getParameter("dvdTitleCreate"));
        newDVD.setReleaseDate(request.getParameter("releaseYearCreate"));
        newDVD.setDirector(request.getParameter("directorCreate"));
        newDVD.setRating(request.getParameter("ratingCreate"));
        newDVD.setExtraNotes(request.getParameter("extraNotesCreate"));
        
        dao.addDVD(newDVD);
        
        return "redirect:displayDVDsTable";
    }
    
    @RequestMapping(value="/displayDVDDetails", method=RequestMethod.GET)
    public String displayDVDDetails(HttpServletRequest request, Model model){
        
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        
        DVD dvd = dao.getDVDById(dvdId);
        model.addAttribute("dvd", dvd);
        
        return "displaydvd";
    }
    
    @RequestMapping(value="/displayEditDVD", method=RequestMethod.GET)
    public String displayEditDVD(HttpServletRequest request, Model model){
        
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        
        DVD dvd = dao.getDVDById(dvdId);
        model.addAttribute("dvd", dvd);
        
        return "editdvd";
    }
    
    @RequestMapping(value="/editDVD", method=RequestMethod.POST)
    public String editDVD(@Valid @ModelAttribute("dvd") DVD dvd, BindingResult result){
        
        if(result.hasErrors()){
            return "displayEditDVD";
        }
        
        dao.editDVD(dvd);
        
        return "redirect:displayDVDsTable";
    }
    
    @RequestMapping(value="/deleteDVD", method=RequestMethod.GET)
    public String deleteDVD(HttpServletRequest request){
        
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        
        dao.removeDVD(dvdId);
        
        return "redirect:displayDVDsTable";
    }
}
