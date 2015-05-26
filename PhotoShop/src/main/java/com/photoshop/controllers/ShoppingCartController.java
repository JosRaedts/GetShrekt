/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.cartproduct.CartproductDao;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pc
 */
@RequestMapping("/shoppingcart")
@Controller
public class ShoppingCartController extends AbstractController{
    
    @Autowired
    private CartproductDao cartproductDao;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServlet request)
    {
        try{
            //map.put("products", cartproductDao.getList());
            return "shoppingcart/list";
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            
        }
        
        return "redirect:../";
    }
    
}
