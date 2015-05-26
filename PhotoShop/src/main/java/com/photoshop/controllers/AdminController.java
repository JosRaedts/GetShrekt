/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.admin.Admin;
import com.photoshop.models.admin.AdminDao;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.school.SchoolDao;
import com.photoshop.models.schoolClass.SchoolClassDao;
import com.photoshop.models.student.Student;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Willem
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private SchoolDao schooldao;
    @Autowired
    private PhotographerDao photoDao;
    
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String getSalesInfo(ModelMap map, HttpServletRequest request) {
//        return "admin/";
//    }
    
       @RequestMapping(value = "/accountgegevens", method = RequestMethod.GET)
    public String VraagAccountInfoOp(ModelMap map, HttpServletRequest request) {
       int userID = 0;
       UserType userType;
       String userName = "";
       Admin admin = null;

       try {
           userType = (UserType)request.getSession().getAttribute("UserType");
           userID = (int)request.getSession().getAttribute("UserID");
           switch(userType){
                case ADMIN: admin = adminDao.getById(userID);
                    System.out.println("User ID = " + userID);
                    map.put("Type", "admin");
                    map.put("UserName", admin.getUsername());                   
                    map.put("Name", admin.getName());
                    return "admin/accountgegevens";
                default: System.out.println("invalid type");
                     break;
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return "home";
       }
       return "home";
    }
    
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(@RequestParam("username") String username,
            @RequestParam("name") String name,
            ModelMap map, HttpServletRequest request) { 
        System.out.println("Student is being modified");

        if (!name.equals("") && !username.equals("")) {
            try {    
                int userID = (int)request.getSession().getAttribute("UserID");
                Admin admin = this.adminDao.getById(userID);
                System.out.println("de naam van te student is " + admin.getName());
                if (admin != null) {
                    System.out.println("Student is being saved");
                    admin.setName(name);
                    admin.setUsername(username);
                    admin.save();
                    
                    String newP = request.getParameter("newPassword");
                    String confP = request.getParameter("confirmPassword");
                    
                    if (!newP.equals("") && !confP.equals("") && newP.equals(confP)) {
                        admin.setPassword(newP);
                        admin.save();
                    } else {
                        System.out.println("password not changed");
                    }
                } else {
                    System.out.println("student was null");
                }
                
            } catch (Exception e) {
                System.out.println("Student was null");
            }
        }
        
        return "home";
    }
    
//    @RequestMapping(value = "/barcodes", method = RequestMethod.GET)
//    public String getbarcodes(ModelMap map, HttpServletRequest request) {
//        System.out.println("barcode function called..");
//        map.put("Scholen", this.schooldao.getList());
//        return "admin/barcodes"; 
//    }
    
       @RequestMapping(value = "/monitoring", method = RequestMethod.GET)
    public String Monitoring(ModelMap map, HttpServletRequest request) {
        return "admin/monitoring";
    }
}
