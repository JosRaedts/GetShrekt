/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Willem
 */
@Controller
public class AccountInfoController {
    
    private StudentDao studentDao;
    
   @RequestMapping(value = "/accountinformatie", method = RequestMethod.GET)
   public String index(ModelMap map, HttpServletRequest request) {
       int userID = 0;
       String userName = "";
       Student student;
       
       try {
           userID = (int)request.getSession().getAttribute("UserID");
           student = studentDao.getById(userID);
       } catch (Exception e) {
           student = null;
       }
            
       if (student == null) {
           return "index";
       }
       else {
           System.out.println("User was ingelogd");
           map.put("UserName", student.getUsername());
           map.put("Name", student.getName());
       }
       return "accountgegevens";
   }
   
}
