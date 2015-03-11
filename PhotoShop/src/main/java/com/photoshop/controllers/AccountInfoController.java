package com.photoshop.controllers;

import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.photographer.PhotographerDao;
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
    
    @Autowired
    private PhotographerDao photographerDao;
    
   @RequestMapping(value = "/accountgegevens", method = RequestMethod.GET)
   public String index(ModelMap map, HttpServletRequest request) {
       int userID = 0;
       Type userType;
       String userName = "";
       Student student = null;
       Photographer photographer = null;
       
       try {
           userType = (Type)request.getSession().getAttribute("UserType");
           userID = (int)request.getSession().getAttribute("UserID");
           switch(userType){
                case ADMIN: System.out.println("not implemented");
                   break;
                case PHOTOGRAPHER: photographer = photographerDao.getById(userID);
                    System.out.println("User ID = " + userID);
                    map.put("UserName", photographer.getUsername());
                    map.put("Name", photographer.getName());
                    return "accountgegevens";
                case STUDENT: student = studentDao.getById(userID);
                    System.out.println("User ID = " + userID);
                    map.put("UserName", student.getUsername());
                    map.put("Name", student.getName());
                    return "accountgegevens";
                default: System.out.println("invalid type");
                     break;

           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return "index";
       }
       return"index";
    }
}
