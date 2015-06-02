/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.cartproduct.Cartproduct;
import com.photoshop.models.cartproduct.CartproductDao;
import com.photoshop.models.imgdata.Filter;
import com.photoshop.models.imgdata.Imgdata;
import com.photoshop.models.order.Order;
import com.photoshop.models.order.OrderDao;
import com.photoshop.models.order.OrderEnum;
import com.photoshop.models.orderrow.OrderRow;
import com.photoshop.models.orderrow.OrderRowDao;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderRowDao orderrowDao;

    @Autowired
    private PhotoDao photoDao;

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
            float x = Float.valueOf(request.getParameter("photo_data[x]"));
            float y = Float.valueOf(request.getParameter("photo_data[y]"));
            float height = Float.valueOf(request.getParameter("photo_data[height]"));
            float width = Float.valueOf(request.getParameter("photo_data[width]"));
            
            Imgdata imgdata = new Imgdata(x,y,height,width,Filter.COLOR);

            Cartproduct temp = new Cartproduct();
            temp.setPrice(price);
            temp.setAmount(amount);
            temp.setPhotoID(photoid);
            temp.setStudentID(userID);
            temp.setContent(name);
            cartproductDao.addToCart(temp);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:list";
        }

        return "redirect:../";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String orderProducts(ModelMap map, HttpServletRequest request) {

        int userID = 0;
        Student student;
        Order neworder = new Order();
        OrderRow newrow = new OrderRow();
        List<Cartproduct> products = new ArrayList();
        try {
            student = (Student) this.getUser();
            userID = student.getId();
            Date date = new Date();

            neworder.setStudent(student);
            neworder.setDatum(new Timestamp(date.getTime()));
            neworder.setStatus(OrderEnum.NIET_BETAALD);
            neworder.setFactuur("factuur");
            neworder.setIndexkaart("indexkaart");
            
            orderDao.save(neworder);
            neworder.setFactuur("Factuur " + neworder.getId());
            neworder.setIndexkaart("Indexkaart " + neworder.getId());
            orderDao.save(neworder);
            
            products = cartproductDao.getList(userID);

            for (Cartproduct c : products) {
                newrow.setOrder_id(neworder.getId());
                newrow.setProduct_id(c.getId());
                newrow.setAantal(c.getAmount());
                newrow.setPhoto_id(c.getPhotoID());
                newrow.setPhotographer_id(photoDao.getById(c.getPhotoID()).getPhotographerID());
                newrow.setProductprice(c.getPrice());
                orderrowDao.save(newrow);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:list";
        }
        return "redirect:../order/detail";
    }

}
