/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.admin.AdminDao;
import com.photoshop.models.order.Order;
import com.photoshop.models.order.OrderDao;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bart
 */
@RequestMapping("/order")
@Controller
public class OrderController extends AbstractController  {
    
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private PhotographerDao photographerDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RedirectAttributes redirectAttributes;
    @Autowired
    private OrderDao orderDao;

    public OrderController()
    {
    }
    
    @RequestMapping(value = "/startpage", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.STUDENT)) 
        {
            return "order/startpage";
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/orderoverzicht", method = RequestMethod.GET)
    public String Monitoring(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.ADMIN)) 
        {
            map.put("orders", this.orderDao.getList());
            map.put("studentdao", this.studentDao);
            System.out.println("Yay :)");
            return "order/orderoverzicht";
        }
        System.out.println("Yay :)");
        return "redirect:../";
    }
    
    @RequestMapping(value = "/detail/{OrderId:^[0-9]+$}", method = RequestMethod.GET)
    public String detail(ModelMap map, HttpServletRequest request, @PathVariable("OrderId") int id) {
        if (this.authenticate(UserType.ADMIN)) 
        {
            Order order = orderDao.getById(id);
            Student student = studentDao.getById(order.getStudent_id());
            map.put("order", order);
            map.put("student", student);
            //map.put("productlist", orderDao.getProductList(id));
            return "order/detail";
        }
        return "redirect:../../";
    }
}
