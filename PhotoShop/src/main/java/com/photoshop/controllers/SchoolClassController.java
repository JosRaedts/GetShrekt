/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.school.School;
import com.photoshop.models.school.SchoolDao;
import com.photoshop.models.schoolClass.SchoolClass;
import com.photoshop.models.schoolClass.SchoolClassDao;
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
@RequestMapping("/schoolclass")
@Controller
public class SchoolClassController {

    @Autowired
    private SchoolDao schooldao;
    
    @Autowired
    private SchoolClassDao schoolclassDao;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request)
    {       
        School temp = schooldao.getById(Integer.parseInt(request.getParameter("id")));
        map.put("schoolclasses", temp.getSchoolClasses());
        
        return "schoolclass/list";
    }
    
    //Student aanpassen
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap map, HttpServletRequest request)
    {
        map.put("schoolclass", schoolclassDao.getById(Integer.parseInt(request.getParameter("id"))));
        
        return "schoolclass/edit";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(ModelMap map, HttpServletRequest request)
    {
        
        SchoolClass temp = schoolclassDao.getById(Integer.parseInt(request.getParameter("id")));
        int schoolid = 0;
        if(temp != null)
        {
            temp.setName(request.getParameter("name"));
            schoolid = temp.getId();
            schoolclassDao.save(temp);
            
        }
        else
        {
            System.out.println("Invalid ID");
        }
        
        
        return "redirect:list?id="+schoolid;
    } 
    
}
