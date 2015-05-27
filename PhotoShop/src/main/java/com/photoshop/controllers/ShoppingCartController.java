/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.cartproduct.Cartproduct;
import com.photoshop.models.cartproduct.CartproductDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
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
public class ShoppingCartController extends AbstractController {

    @Autowired
    private CartproductDao cartproductDao;

    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        int userID;
        Student student;

        try {
            student = (Student) this.getUser();
            userID = student.getId();
            map.put("cartproducts", cartproductDao.getList(userID));
            return "shoppingcart/list";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String selectAmount(ModelMap map, HttpServletRequest request) {
        try {
            Cartproduct temp = cartproductDao.getById(Integer.parseInt(request.getParameter("id")));

            if (temp != null) {
                temp.setAmount(Integer.parseInt(request.getParameter("amount")));
                cartproductDao.save(temp);
                return "redirect:list";
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:list";
        }

        return "redirect:../";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteRecord(ModelMap map, HttpServletRequest request) {
        try {
            Cartproduct temp = cartproductDao.getById(Integer.parseInt(request.getParameter("id")));
            if (temp != null) {
                cartproductDao.delete(temp);
                return "redirect:list";
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:list";
        }

        return "redirect:../";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(ModelMap map, HttpServletRequest request) {
        try {
            System.out.println(request.getParameter("photo_id").toString());
            System.out.println(request.getParameter("photo_data").toString());

            String[] testarray = request.getParameterValues("products");
            for(String s : testarray)
            {
                System.out.println("HOI:   " + s);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:list";
        }

        return "redirect:../";
    }

}
