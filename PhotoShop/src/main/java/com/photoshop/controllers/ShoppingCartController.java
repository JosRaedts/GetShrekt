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
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

            //System.out.println(request.getParameter("photo_id"));
        //System.out.println(request.getParameter("photo_data"));
        //System.out.println(request.getAttribute("products[]").toString());
        //System.out.println(request.getParameter("product_id"));
        //System.out.println(request.getParameter("product_qty"));
        int userID = 0;
        Student student;
        try {

            student = (Student) this.getUser();
            userID = student.getId();

            int photoid = Integer.parseInt(request.getParameter("photo_id"));
            int productid = Integer.parseInt(request.getParameter("product_id"));
            double price = cartproductDao.getPrice(photoid, productid);
            int amount = Integer.parseInt(request.getParameter("product_qty"));
            String name = cartproductDao.getName(productid);

            Cartproduct temp = new Cartproduct();
            temp.setPrice(price);
            temp.setAmount(amount);
            temp.setPhotoID(photoid);
            temp.setStudentID(userID);
            temp.setContent(name);
            cartproductDao.addToCart(temp);
            //System.out.println("test");
            //System.out.println(products);
            ///Cartproduct temp = cartproductDao.getById(Integer.parseInt(request.getParameter("id")));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:list";
        }

        return "redirect:../";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String orderProducts(ModelMap map, HttpServletRequest request) {
        
        
        return null;
    }
    
    
}
