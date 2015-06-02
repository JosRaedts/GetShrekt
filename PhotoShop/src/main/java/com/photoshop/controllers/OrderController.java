/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.misc.Factuurgenerator;
import com.photoshop.misc.Indexkaartgenerator;
import com.photoshop.models.UserType;
import com.photoshop.models.address.Address;
import com.photoshop.models.admin.AdminDao;
import com.photoshop.models.order.Order;
import com.photoshop.models.order.OrderDao;
import com.photoshop.models.orderrow.OrderRowDao;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.photographer.PhotographerDao;
import com.photoshop.models.product.ProductDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bart
 */
@RequestMapping("/order")
@Controller
public class OrderController extends AbstractController {

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
    @Autowired
    private OrderRowDao orderrowDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private PhotoDao photoDao;
    
    @Autowired
    private Environment env;
    

    private Order order;
    private Factuurgenerator pdf;
    private Indexkaartgenerator index;

    //http://www.vogella.com/tutorials/JavaPDF/article.html infromatie pdf creator
    public OrderController() {
    }
    
    @RequestMapping(value = "/orderoverzicht", method = RequestMethod.GET)
    public String Monitoring(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.ADMIN)) {
            map.put("orders", this.orderDao.getList());
            System.out.println("Yay :)");
            return "order/orderoverzicht";
        }
        System.out.println("Yay :)");
        return "redirect:../";
    }
    
    @RequestMapping(value = "/orderhistory", method = RequestMethod.GET)
    public String orderhistory(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.STUDENT)) {
            map.put("orders", this.orderDao.getList());
            
            return "order/orderhistory";
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/indexkaart /{OrderId:^[0-9]+$}", method = RequestMethod.GET)
    public String Indexkaart(ModelMap map, HttpServletRequest request, @PathVariable("OrderId") int id) throws InterruptedException {
        if (this.authenticate(UserType.STUDENT)) {

            String filename = env.getProperty("logo") + "Indexkaart " + id + ".pdf";
            String OS = System.getProperty("os.name").toLowerCase();
            OS = OS.toLowerCase();
            if (OS.contains("windows")) {
                try {
                    Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename);
                    p.waitFor();
                    return "redirect:../../order/orderhistory";
                } catch (IOException ex) {
                    Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (OS.contains("mac")) {
                try {
                    Process p = Runtime.getRuntime().exec(new String[]{"/usr/bin/open", filename});
                    p.waitFor();
                    return "redirect:../../order/orderhistory";
                } catch (IOException ex) {
                    Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "redirect:../../";
    }
    
    @RequestMapping(value = "/factuur/{OrderId:^[0-9]+$}", method = RequestMethod.GET)
    public String Factuur(ModelMap map, HttpServletRequest request, @PathVariable("OrderId") int id) throws InterruptedException {
        if (this.authenticate(UserType.STUDENT)) {
            
            String filename = env.getProperty("logo") + "Factuur " + id + ".pdf";
            String OS = System.getProperty("os.name").toLowerCase();
            OS = OS.toLowerCase();
            if (OS.contains("windows")) {
                try {
                    Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename);
                    p.waitFor();
                    return "redirect:../../order/orderhistory";
                } catch (IOException ex) {
                    Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(OS.contains("mac"))
            {
                try {
                    Process p = Runtime.getRuntime().exec(new String[]{"/usr/bin/open", filename});
                    p.waitFor();
                    return "redirect:../../order/orderhistory";
                } catch (IOException ex) {
                    Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "redirect:../../";
    }

    @RequestMapping(value = "/detail/{OrderId:^[0-9]+$}", method = RequestMethod.GET)
    public String detail(ModelMap map, HttpServletRequest request, @PathVariable("OrderId") int id) {
        if (this.authenticate(UserType.ADMIN)) {
            Order order = orderDao.getById(id);
            Student student = order.getStudent();
            map.put("order", order);
            map.put("student", student);
            map.put("productlist", orderrowDao.getOrderRowByOrderNr(id));
            return "order/detail";
        }
        return "redirect:../../";
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public String pdf(ModelMap map, HttpServletRequest request) throws IOException {
        if (this.authenticate(UserType.STUDENT)) {
            this.order = this.orderDao.getById(1);
            this.order.setInvoiceaddress( new Address("Willem de kok","Oorion 32","5527CR","Hapert","0612345678"));
            pdf = new Factuurgenerator(order,env);
            index = new Indexkaartgenerator(order,env,photoDao);
            return "redirect:../";
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public String address(ModelMap map, HttpServletRequest request)  {
        if (this.authenticate(UserType.STUDENT)) {
            Student student = (Student) this.getUser();
            if(student.getCartProducts().size() > 0)
            {
                map.put("student", student);
                return "order/addressdetail";
            }
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public String saveaddress(ModelMap map, HttpServletRequest request)  {
        if (this.authenticate(UserType.STUDENT)) {
            System.out.println("test");
            Student student = (Student) this.getUser();
            if(student.getCartProducts().size() > 0)
            {
                Address invoiceaddress = new Address(request.getParameter("invoice_name"), request.getParameter("invoice_address"), request.getParameter("invoice_zipcode"), request.getParameter("invoice_city"), request.getParameter("invoice_phone"));
                Address shippingaddress;
                if(request.getParameter("sameaddress") != null)
                {
                    shippingaddress = new Address(request.getParameter("shipping_name"), request.getParameter("shipping_address"), request.getParameter("shipping_zipcode"), request.getParameter("shipping_city"), request.getParameter("shipping_phone"));
                }
                else
                {
                    shippingaddress = invoiceaddress;
                }

                request.getSession().setAttribute("invoiceaddress", invoiceaddress);
                request.getSession().setAttribute("shippingaddress", shippingaddress);

                return "redirect:../payment";
            }
        }
        return "redirect:../";
    }
}