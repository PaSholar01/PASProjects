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
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import static org.springframework.http.RequestEntity.method;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@CrossOrigin
@Controller
public class RESTController {
    
    private DVDLibDao dao;
    
    @Inject
    public RESTController(DVDLibDao dao){
        this.dao = dao;
    }
    
    @RequestMapping(value="/dvd/{dvdId}", method=RequestMethod.GET)
    @ResponseBody
    public DVD getDVD(@PathVariable("id") long id){
        return dao.getDVDById(id);
    }
    
    @RequestMapping(value="/dvd", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DVD createDVD(@Valid @RequestBody DVD dvd){
        return dao.addDVD(dvd);
    }
    
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDVD(@PathVariable("id") long id){
        dao.removeDVD(id);
    }
    
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editDVD(@PathVariable("id") long id, @Valid @RequestBody DVD dvd){
        dvd.setDvdId(id);
        dao.editDVD(dvd);
    }
    
    @RequestMapping(value="/dvds", method=RequestMethod.GET)
    @ResponseBody
    public List<DVD> getAllDVDs(){
        return dao.getAllDVDs();
    }
}
