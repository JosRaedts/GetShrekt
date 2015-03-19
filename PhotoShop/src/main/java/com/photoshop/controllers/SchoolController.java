/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.school.School;
import com.photoshop.models.school.SchoolDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pc
 */
@RequestMapping("/school")
@Controller
public class SchoolController {
    
    @Autowired
    private SchoolDao schoolDao;
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request)
    {
        map.put("schools", schoolDao.getList());
        return "school/list";
    }
    
//    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//    public String schoolClasses(ModelMap map, HttpServletRequest request)
//    {
//        School temp = schoolDao.getById(Integer.parseInt(request.getParameter("id")));
//        map.put("school", temp.getSchoolClasses());
//        return "schoolclass/list";
//    }
    
    //Student aanpassen
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap map, HttpServletRequest request)
    {
        map.put("school", schoolDao.getById(Integer.parseInt(request.getParameter("id"))));
        
        return "school/edit";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(ModelMap map, HttpServletRequest request)
    {
        
        School temp = schoolDao.getById(Integer.parseInt(request.getParameter("id")));
        
        if(temp != null)
        {
            temp.setAddress(request.getParameter("address"));
            temp.setName(request.getParameter("name"));
            temp.setCity(request.getParameter("city"));
            temp.setZipcode(request.getParameter("zipcode"));
            temp.setCode(request.getParameter("code"));
            schoolDao.save(temp);
        }
        else
        {
            System.out.println("Invalid ID");
        }
        
        
        return "redirect:list?id=";
    } 
}
