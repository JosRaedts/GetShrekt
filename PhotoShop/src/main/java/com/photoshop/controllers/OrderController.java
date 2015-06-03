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
import com.photoshop.misc.Indexkaartgenerator;
import com.photoshop.models.orderrow.OrderRowDao;
import com.photoshop.misc.Factuurgenerator;
import com.photoshop.misc.ImageManager;
import com.photoshop.misc.Mailgenerator;
import com.photoshop.models.address.Address;
import com.photoshop.models.cartproduct.Cartproduct;
import com.photoshop.models.orderrow.OrderRow;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.product.ProductDao;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private MessageSource messageSource;

    @Autowired
    private Environment env;

    private Order order;
    private Factuurgenerator pdf;
    private Indexkaartgenerator index;

    //http://www.vogella.com/tutorials/JavaPDF/article.html infromatie pdf creator
    public OrderController() {
    }
    
    @RequestMapping(value = "/overzicht", method = RequestMethod.GET)
    public String Monitoring(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.ADMIN)) {
            map.put("orders", this.orderDao.getList());
            System.out.println("Yay :)");
            return "order/overzicht";
        }
        System.out.println("Yay :)");
        return "redirect:../";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String history(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.STUDENT)) {
            int studentid = (int) request.getSession().getAttribute("UserID");
            Student student = (Student) this.getUser();
            map.put("studentnaam", student.getName());
            map.put("orders", this.orderDao.getOrderlistByStudentId(studentid));
            return "order/history";
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/indexkaart /{OrderId:^[0-9]+$}", method = RequestMethod.GET)
    public String Indexkaart(ModelMap map, HttpServletRequest request, HttpServletResponse response, @PathVariable("OrderId") int id) throws InterruptedException, IOException {
        if (this.authenticate(UserType.STUDENT)) {
            // use the response passed as parameter
            String filename = env.getProperty("logo") + "Indexkaart " + id + ".pdf";
            File file = new File(filename);
            InputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServletOutputStream out = response.getOutputStream();
            IOUtils.copy(in, out);
            response.flushBuffer();
        }
        return "redirect:../../";
    }

    @RequestMapping(value = "/factuur/{OrderId:^[0-9]+$}", method = RequestMethod.GET)
    public String Factuur(ModelMap map, HttpServletRequest request, HttpServletResponse response, @PathVariable("OrderId") int id) throws InterruptedException, IOException {
        if (this.authenticate(UserType.STUDENT)) {

            String filename = env.getProperty("logo") + "Factuur " + id + ".pdf";
            File file = new File(filename);
            InputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServletOutputStream out = response.getOutputStream();
            IOUtils.copy(in, out);
            response.flushBuffer();
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

//    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
//    public String pdf(ModelMap map, HttpServletRequest request,Locale locale) throws IOException {
//        if (this.authenticate(UserType.STUDENT)) {
//            this.order = this.orderDao.getById(1);
//            this.order.setInvoiceaddress(new Address("Willem de kok", "Orion 32", "5527CR", "Hapert", "0612345678"));
//            Mailgenerator mail = new Mailgenerator();
//            mail.Sendmail("willem1995@hotmail.com", order, this.env);
//            pdf = new Factuurgenerator(order, env, messageSource,locale);
//            index = new Indexkaartgenerator(order, env, photoDao, messageSource, locale);
//            return "redirect:../";
//        }
//        return "redirect:../";
//    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public String address(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.STUDENT)) {
            Student student = (Student) this.getUser();
            if (student.getCartProducts().size() > 0) {
                map.put("student", student);
                return "order/addressdetail";
            }
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public String saveaddress(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.STUDENT)) {
            System.out.println("test");
            Student student = (Student) this.getUser();
            if (student.getCartProducts().size() > 0) {
                Address invoiceaddress = new Address(request.getParameter("invoice_name"), request.getParameter("invoice_address"), request.getParameter("invoice_zipcode"), request.getParameter("invoice_city"), request.getParameter("invoice_phone"));
                Address shippingaddress;
                if (request.getParameter("sameaddress") == null) {
                    shippingaddress = new Address(request.getParameter("shipping_name"), request.getParameter("shipping_address"), request.getParameter("shipping_zipcode"), request.getParameter("shipping_city"), request.getParameter("shipping_phone"));
                } else {
                    shippingaddress = invoiceaddress;
                }

                request.getSession().setAttribute("invoiceaddress", invoiceaddress);
                request.getSession().setAttribute("shippingaddress", shippingaddress);

                return "redirect:../payment";
            }
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/view/{format:low|high|thumb}/{order_row_id:^[0-9]+$}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(HttpServletRequest response, @PathVariable("format") String format, @PathVariable("order_row_id") int id) throws IOException {
        OrderRow orderRow = orderrowDao.getById(id);
        if (orderRow != null) {
            String filename = "";
            switch (format) {
                case "high":
                    filename = env.getProperty("uploadDir") + "orders/"+orderRow.getOrder_id()+"/"+orderRow.getId()+"/highres.jpg";
                    break;
                case "low":
                    filename = env.getProperty("uploadDir") + "orders/"+orderRow.getOrder_id()+"/"+orderRow.getId()+"/lowres.jpg";
                    break;
                case "thumb":
                    filename = env.getProperty("uploadDir") + "orders/"+orderRow.getOrder_id()+"/"+orderRow.getId()+"/thumb.jpg";
                    break;
            }

            InputStream in = new FileInputStream(filename);
            BufferedImage img = ImageIO.read(in);
            if (format.equals("low")) {
                BufferedImage watermark = ImageIO.read(new FileInputStream(env.getProperty("uploadDir") + "watermerk.png"));
                ImageManager imageManager = new ImageManager();
                watermark = imageManager.resize(watermark, img.getHeight(), img.getWidth());
                Watermark filter = new Watermark(Positions.CENTER, watermark, 0.2f);
                img = filter.apply(img);
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            ImageIO.write(img, "jpg", bos);
            byte[] image = bos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); //or what ever type it is
            headers.setContentLength(image.length);

            return new HttpEntity<byte[]>(image, headers);
        } else {
            return null;
        }
    }
    
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(ModelMap map, HttpServletRequest request) 
    {
        return "order/success";
    }
}
