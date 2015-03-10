/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
<<<<<<< HEAD
            @RequestParam("schoolcode") String code, ModelMap map, HttpServletRequest request) {
        User user = userDao.authenticate(name, code);
        if (user != null) {
            request.getSession().setAttribute("UserID", user.getId());
            request.getSession().setAttribute("UserName", user.getUsername());
            return "redirect:../"; //hij zou nu ingelogd moeten zijn.
        } else {
            request.getSession().setAttribute("UserID", null);
            request.getSession().setAttribute("UserName", "");
=======
            @RequestParam("schoolcode") String code, ModelMap map){
        User user = userDao.authenticate(name, code);
        if (user != null) {
            return "redirect:../"; //hij zou nu ingelogd moeten zijn.
        }
        else{
>>>>>>> origin/master
            return "redirect:"; //teruggeleid naar de index pagina of inlogpagina
        }
    }
}
