/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;


import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import javax.servlet.http.HttpServletRequest;
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
    private StudentDao studentDao;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) {
        map.put("msg", "Hello photoshop users");
        map.put("test", "testen van github account");
        return "login";
    }
    
    @RequestMapping(value = "/photographer/login", method = RequestMethod.GET)
    public String photographerLogin(ModelMap map) {
        return "photographerLogin";
    }
  
    @RequestMapping(value="/login/checkLogin", method = RequestMethod.POST)
    public String checkLogin(@RequestParam("name") String name,
            @RequestParam("schoolcode") String code, ModelMap map, HttpServletRequest request) {
        Student student = studentDao.authenticate(name, code);
        if (student != null) {
            request.getSession().setAttribute("UserID", student.getId());
            request.getSession().setAttribute("UserName", student.getUsername());
            return "redirect:../"; //hij zou nu ingelogd moeten zijn.
        } else {
            request.getSession().setAttribute("UserID", null);
            request.getSession().setAttribute("UserName", "");
            return "redirect:"; //teruggeleid naar de index pagina of inlogpagina
        }
    }
}
