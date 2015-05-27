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
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.photoshop.models.orderrow.OrderRowDao;
import com.photoshop.misc.Pdfgenerator;
import com.photoshop.models.address.Address;
import com.photoshop.models.orderrow.OrderRow;
import com.photoshop.models.product.ProductDao;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.core.env.Environment;

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
    private Environment env;
    

    private Order order;
    private Pdfgenerator pdf;

    //http://www.vogella.com/tutorials/JavaPDF/article.html infromatie pdf creator
    public OrderController() {
    }

    @RequestMapping(value = "/startpage", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.STUDENT)) {
            return "order/startpage";
        }
        return "redirect:../";
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
            pdf = new Pdfgenerator(order,env);
            return "order/startpage";
        }
        return "redirect:../";
    }
}