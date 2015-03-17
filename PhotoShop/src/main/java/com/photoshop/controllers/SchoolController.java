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
        School temp = schoolDao.getById(2);
        System.out.println(temp.getName());
        return "school/list";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String schoolClasses(ModelMap map, HttpServletRequest request)
    {
        School temp = schoolDao.getById(Integer.parseInt(request.getParameter("id")));
        map.put("school", temp.getSchoolClasses());
        return "schoolclass/list";
    }
}
