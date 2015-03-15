/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author john
 */
@Controller
public class DefaultController extends AbstractController {
    
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String index(ModelMap map, HttpServletRequest request) {
       try {
            HttpSession session = request.getSession();       
            int userID = (int)session.getAttribute("UserID");
            String userName = (String)session.getAttribute("UserName");
            UserType type= (UserType)session.getAttribute("UserType");
            
            map.put("msg", "Hello " + userName + " met ID: " + userID +" van type: "+ type.toString());
            map.put("test", "testen van github account");
            if(authenticate(UserType.STUDENT))
            {
                System.out.println("Student ingelogd");
            }
            if(authenticate(UserType.PHOTOGRAPHER))
            {
                System.out.println("Photographer ingelogd");
            }
            System.out.println("test123");
            return "home";
       } catch(Exception e) {
            map.put("msg", "Hello new user, please sign in");
            map.put("test", "testen van github account");
            System.out.println("testdefault");
            return "home";
       }
   }  
   
   @RequestMapping(value = "/contact", method = RequestMethod.GET)
   public String contact(ModelMap map) {
       map.put("msg", "Hello photoshop users");
       map.put("test", "testen van github account");
       return "contact";
   }
}
