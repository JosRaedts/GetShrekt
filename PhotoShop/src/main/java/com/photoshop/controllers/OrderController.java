/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.IUser;
import com.photoshop.models.UserType;
import com.photoshop.models.admin.AdminDao;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.student.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bart
 */
@RequestMapping("/order")
public class OrderController extends AbstractController  {
    
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private PhotographerDao photographerDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RedirectAttributes redirectAttributes;

    public OrderController()
    {
    }
    
    @RequestMapping(value = "/startpage", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.STUDENT)) 
        {
            return "order/startpage";
        }
        return "redirect:../";
    }
    
           @RequestMapping(value = "/orderoverzicht", method = RequestMethod.GET)
    public String Monitoring(ModelMap map, HttpServletRequest request) {
        return "admin/monitoring";
    }
}
