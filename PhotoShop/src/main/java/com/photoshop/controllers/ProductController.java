/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.product.Product;
import com.photoshop.models.product.ProductDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Casper
 */
@RequestMapping("/product")
@Controller
public class ProductController extends AbstractController{
    
    @Autowired
    private ProductDao productDao;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            map.put("products", productDao.getList());
            return "product/list";
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addpage(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            return "product/add";
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            Product temp = new Product();
            try {
                int bi = Integer.valueOf(request.getParameter("active"));
                temp.setActive(bi != 0);
                temp.setImageURL(request.getParameter("preview"));
                temp.setName(request.getParameter("name"));
                temp.setHeight(Integer.parseInt(request.getParameter("height")));
                temp.setWidth(Integer.parseInt(request.getParameter("width")));
                System.out.println(temp.getHeight());
                System.out.println(temp.getWidth());
                productDao.save(temp);

            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }

            return "redirect:../product/list";
        }
        return "redirect:../";
    }
}
