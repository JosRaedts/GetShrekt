/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**

/**
 *
 * @author bart
 */
@Controller
public class RegisterController {
     @RequestMapping(value = "/register", method = RequestMethod.GET)
   public String register(ModelMap map) {
       map.put("admin", "Admin panel");
       map.put("photographer", "Register of a photographer");
       return "register";
   }
}
