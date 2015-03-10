/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

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
 * @author john
 */
@Controller
public class DefaultController {
    
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String index(ModelMap map, HttpServletRequest request) {
       try {
            HttpSession session = request.getSession();       
            int userID = (int)session.getAttribute("UserID");
            String userName = (String)session.getAttribute("UserName");
            
            map.put("msg", "Hello " + userName + " met ID: " + userID);
            map.put("test", "testen van github account");
            
            return "index";
       } catch(Exception e) {
            map.put("msg", "Hello new user, please sign in");
            map.put("test", "testen van github account");
            
            return "index";
       }
   }
   
}
