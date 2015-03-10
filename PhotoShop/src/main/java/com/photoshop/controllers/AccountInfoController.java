package com.photoshop.controllers;

import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private StudentDao studentDao;
    
   @RequestMapping(value = "/accountgegevens", method = RequestMethod.GET)
   public String index(ModelMap map, HttpServletRequest request) {
       int userID = 0;
       String userName = "";
       Student student;
       
       try {
           userID = (int)request.getSession().getAttribute("UserID");
            System.out.println("User ID = " + userID);
           student = studentDao.getById(userID);
       } catch (Exception e) {
           student = null;
       }
            
       if (student == null) {
           System.out.println("Student leeg");
           return "index";
       }
       else {
           System.out.println("User was ingelogd");
           map.put("UserName", student.getUsername());
           map.put("Name", student.getName());
       }
       System.out.println("Student gevuld");
       return "accountgegevens";
   }
}
