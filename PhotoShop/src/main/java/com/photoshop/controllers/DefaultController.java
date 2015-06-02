/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.admin.Admin;
import com.photoshop.models.admin.AdminDao;
import com.photoshop.models.orderrow.OrderRowDao;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.product.Product;
import com.photoshop.models.product.ProductDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *

 * @author casper
**/

@Controller
public class DefaultController extends AbstractController {
   
    @Autowired
    private PhotoDao photoDao;
    
    @Autowired
    private PhotographerDao photographerDao;
    
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private FlashMap flashMap;
    
    @Autowired
    private OrderRowDao orderRowDao;
    
    @Autowired
    private ProductDao  productDao;
    
            
   @RequestMapping(value = {"/", "/index.html"}, method = RequestMethod.GET)
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
            return "home";
       } catch(Exception e) {
            map.put("msg", "Hello new user, please sign in");
            map.put("test", "testen van github account");
            return "home";
       }
   }  
   
   @RequestMapping(value = "/contact", method = RequestMethod.GET)
   public String contact(ModelMap map) {
       map.put("msg", "Hello photoshop users");
       map.put("test", "testen van github account");
       return "contact";
   }
   
   @RequestMapping(value = "/admin", method = RequestMethod.GET)
   public String admin(ModelMap map,HttpServletRequest request) {
        if(authenticate(UserType.PHOTOGRAPHER))
        {    
            int photocount = this.photoDao.getPhotosByPhotographer(Integer.parseInt(request.getSession().getAttribute("UserID").toString())).size();
            map.put("Photocount", photocount);
            map.put("size", this.orderRowDao.getNumberOfSalesForPhotographer(Integer.parseInt(request.getSession().getAttribute("UserID").toString())));
            
            System.out.println(photocount);
            
            int lol = 1;
            for (int[] array : this.orderRowDao.getTopProducts()) {
                Product product = this.productDao.getById(array[0]);
                map.put("product" + lol, product);
                map.put("aantal" + lol, array[1]);
                lol++;
            }
            
            return "admin/home";
        } else if (authenticate(UserType.ADMIN)) {
            int photocount = this.photoDao.getTotalCount();
            map.put("Photocount", photocount);
            
            ArrayList<String> list = new ArrayList();
            for (Photographer p : this.photographerDao.getList()) {
                list.add(p.getName());
            }
            
            int i = 0;
            ArrayList<String[]> information = new ArrayList();
            for (Photographer p : this.photographerDao.getList()) {
                String[] strings = new String[2];
                strings[0] = p.getName();
                strings[1] = this.orderRowDao.getNumberOfSalesForPhotographer(p.getId()) + "";
                information.add(strings);
                i = i + this.orderRowDao.getNumberOfSalesForPhotographer(p.getId());
            }
            
            map.put("photographers", list);
            map.put("photographerDao", this.photographerDao);
            map.put("size", i);
            map.put("information", information);
            
            int lol = 1;
            for (int[] array : this.orderRowDao.getTopProducts()) {
                Product product = this.productDao.getById(array[0]);
                map.put("product" + lol, product);
                map.put("aantal" + lol, array[1]);
                lol++;
            }
            return "admin/home";
        }
        return "redirect:admin/login";
       
   }
   
   @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String Login(ModelMap map,HttpServletRequest request, @ModelAttribute("redirectURI") String redirecto ) {
        map.put("Accountmade", request.getSession().getAttribute("Accountmade"));
        request.getSession().removeAttribute("Accountmade");

        System.out.println("puzza2" + flashMap.get("redirectURI"));
        return "login";
    }
    
    @RequestMapping(value = "/admin/loginerror", method = RequestMethod.GET)
    public String LoginError(ModelMap map,HttpServletRequest request, @ModelAttribute("redirectURI") String redirecto ) {
        map.put("Accountmade", request.getSession().getAttribute("Accountmade"));
        request.getSession().removeAttribute("Accountmade");
        return "loginerror";
    }
    
    @RequestMapping(value="/admin/checkLogin", method = RequestMethod.POST)
    public String checkLogin(@RequestParam("name") String name, @RequestParam("password") String password, ModelMap map, HttpServletRequest request) {
        String redirectURL = "redirect:../../admin";

        if(request.getSession().getAttribute("redirectURL") != null)
        {
            redirectURL = "redirect:"+(String) request.getSession().getAttribute("redirectURL");
        }

        Admin admin = adminDao.authenticate(name, password);
        Photographer photographer = photographerDao.authenticate(name, password);
        if(admin != null){
            request.getSession().setAttribute("UserID", admin.getId());
            request.getSession().setAttribute("UserName", admin.getUsername());
            request.getSession().setAttribute("UserType", UserType.ADMIN);
            request.getSession().setAttribute("redirectURL", null);
            request.getSession().setAttribute("status", 1);
            return redirectURL;
        } else if (photographer != null) {
            request.getSession().setAttribute("UserID", photographer.getId());
            request.getSession().setAttribute("UserName", photographer.getUsername());
            request.getSession().setAttribute("UserType", UserType.PHOTOGRAPHER);
            request.getSession().setAttribute("redirectURL", null);
            request.getSession().setAttribute("status", 1);
            return redirectURL;
        } else{
            request.getSession().setAttribute("UserID", null);
            request.getSession().setAttribute("UserName", "");
            request.getSession().setAttribute("UserType", "");
            request.getSession().setAttribute("status", 0);
            return "redirect:../../admin/loginerror";
        }
    }
   
   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(ModelMap map, HttpServletRequest request) {
        request.getSession().setAttribute("UserID", null);
        request.getSession().setAttribute("UserName", "");
        request.getSession().setAttribute("UserType", "");
        return "redirect:";      
   }

}
