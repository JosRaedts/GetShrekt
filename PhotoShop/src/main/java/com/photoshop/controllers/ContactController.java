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
 *
 * @author fhict
 */
@Controller
public class ContactController {
      @RequestMapping(value = "/contact", method = RequestMethod.GET)
   public String login(ModelMap map) {
       map.put("msg", "Hello photoshop users");
       map.put("test", "testen van github account");
       return "contact";
   }
}