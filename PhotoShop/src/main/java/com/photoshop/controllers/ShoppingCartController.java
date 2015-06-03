/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.misc.ImageManager;
import com.photoshop.models.IUser;
import com.photoshop.models.cartproduct.Cartproduct;
import com.photoshop.models.cartproduct.CartproductDao;
import com.photoshop.models.imgdata.Filter;
import com.photoshop.models.imgdata.Imgdata;
import com.photoshop.models.order.Order;
import com.photoshop.models.order.OrderDao;
import com.photoshop.models.order.OrderEnum;
import com.photoshop.models.orderrow.OrderRow;
import com.photoshop.models.orderrow.OrderRowDao;
import com.photoshop.models.photo.Photo;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "redirect:shoppingcart/list";
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

            File dir = new File(env.getProperty("uploadDir") + "cart/"+temp.getId());
            FileUtils.cleanDirectory(dir);
            dir.delete();

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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String redirecttolist(ModelMap map, HttpServletRequest request) {
        try {
            Thread.sleep(1500);

            return "redirect:../shoppingcart/list";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:../";
        }
    }
    @Autowired
    private Environment env;

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
            int filterid = Integer.valueOf(request.getParameter("photo_filter"));
            Imgdata imgdata = new Imgdata(x, y, height, width, Filter.values()[filterid]);
            cartproductDao.saveImageData(imgdata);
            Cartproduct temp = new Cartproduct();
            temp.setPrice(price);
            temp.setAmount(amount);
            temp.setPhotoID(photoid);
            temp.setStudentID(userID);
            temp.setContent(name);
            temp.setProductId(productid);
            temp.setImageId(imgdata.getId());
            cartproductDao.addToCart(temp);

            ImageManager.modding(imgdata, photoDao.getById(photoid), env, env.getProperty("uploadDir") + "cart/"+temp.getId(), temp.getId());
            return "redirect:../shoppingcart/list";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:../";
        }

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
    
    @RequestMapping(value = "/view/{format:low|high|thumb}/{cartproductid:^[0-9]+$}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(HttpServletRequest response, @PathVariable("format") String format, @PathVariable("cartproductid") int id) throws IOException {
        Cartproduct cartproduct = cartproductDao.getById(id);
        if (cartproduct != null) {
            String filename = "";
            switch (format) {
                case "high":
                    filename = env.getProperty("uploadDir") + "cart/"+cartproduct.getId()+"/highres.jpg";
                    break;
                case "low":
                    filename = env.getProperty("uploadDir") + "cart/"+cartproduct.getId()+"/lowres.jpg";
                    break;
                case "thumb":
                    filename = env.getProperty("uploadDir") + "cart/"+cartproduct.getId()+"/thumb.jpg";
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

}
