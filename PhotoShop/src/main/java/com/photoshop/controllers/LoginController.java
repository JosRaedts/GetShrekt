/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.user.User;
import com.photoshop.models.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author fhict
 */
@Controller
public class LoginController {
    
    @Autowired
    private UserDao userDao;
    
       @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) {
        map.put("msg", "Hello photoshop users");
        map.put("test", "testen van github account");
        return "login";
    }
        @RequestMapping(value="/login/checkLogin", method = RequestMethod.POST)
    public String checkLogin(@RequestParam("name") String name,
            @RequestParam("schoolcode") String code, ModelMap map){
        User user = userDao.authenticate(name, code);
        if (user != null) {
            return "index"; //hij zou nu ingelogd moeten zijn.
        }
        else{
            return "login"; //teruggeleid naar de index pagina of inlogpagina
        }
    }
}
