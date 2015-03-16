/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.photographer.PhotographerDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Bram
 */
@RequestMapping("/photographer")
@Controller
public class PhotographerController {
    
    @Autowired
    private PhotographerDao photographerDao;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String photographerLogin(ModelMap map,HttpServletRequest request) {
        map.put("Accountmade", request.getSession().getAttribute("Accountmade"));
        request.getSession().removeAttribute("Accountmade");
        return "photographer/login";
    }
    
   //Toevoegen fotograaf
   @RequestMapping(value = "/add", method = RequestMethod.GET)
   public String add(ModelMap map) {
       return "photographer/add";
   }
   
       
    @RequestMapping(value="/add/addphotographer", method = RequestMethod.POST)
    public String Addphotographer(@RequestParam("name") String name,
            @RequestParam("password") String password, ModelMap map, HttpServletRequest request) {
        if (name.equals("") && password.equals("")) {
            System.out.println("mislukt");
            return "photographer/add";
        } else {
            System.out.println("gelukt");
            Photographer Photographer = new Photographer();
            Photographer.setName(name);
            Photographer.setPassword(password);
            Photographer.setUsername(name);
            if(photographerDao.save(Photographer))
            {
                request.getSession().setAttribute("Accountmade", "Accountmade");
            }
            
            return "photographer/login";
        }
    }
    
    @RequestMapping(value="/checkLogin", method = RequestMethod.POST)
    public String checkPhotographerLogin(@RequestParam("name") String name,
            @RequestParam("password") String password, ModelMap map, HttpServletRequest request) {
        Photographer photographer = photographerDao.authenticate(name, password);
        if (photographer != null) {
            request.getSession().setAttribute("UserID", photographer.getId());
            request.getSession().setAttribute("UserName", photographer.getUsername());
            request.getSession().setAttribute("UserType", UserType.PHOTOGRAPHER);
            return "redirect:../../"; 
        } else {
            request.getSession().setAttribute("UserID", null);
            request.getSession().setAttribute("UserName", "");
            request.getSession().setAttribute("UserType", "");
            return "redirect:../login";
        }
    }
}
