/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.photo.Photo;
import com.photoshop.models.product.Product;
import com.photoshop.models.product.ProductDao;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Casper
 */
@RequestMapping("/product")
@Controller
public class ProductController extends AbstractController{
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private Environment env;
    
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
 
    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file, ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();

                    // Creating the directory to store file
                    String rootPath = env.getProperty("uploadDir");
                    File dir = new File(rootPath + "products");
                    if (!dir.exists())
                        dir.mkdirs();

                    // Create the file on server
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + file.getOriginalFilename());
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    //System.out.println("Server File Location="+ serverFile.getAbsolutePath());

                    //System.out.println("You successfully uploaded file=" + file.getOriginalFilename());
                } catch (Exception e) {
                    System.out.println( "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
                }
                Product temp = new Product();
                try {
                    int bi = Integer.valueOf(request.getParameter("active"));
                    temp.setActive(bi != 0);
                    temp.setImageURL(file.getOriginalFilename());
                    temp.setName(request.getParameter("name"));
                    temp.setHeight(Integer.parseInt(request.getParameter("height")));
                    temp.setWidth(Integer.parseInt(request.getParameter("width")));
                    productDao.save(temp);

                } catch(Exception ex) {
                    System.out.println(ex.getMessage());
                    return "redirect:/product/add";
                }

                return "redirect:../product/list";
            } else {
                System.out.println("You failed to upload " + file.getOriginalFilename()
                        + " because the file was empty.");
                return "redirect:/product/add";
            }
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            map.put("product", productDao.getById(Integer.parseInt(request.getParameter("id"))));

            return "product/edit";
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam("file") MultipartFile file, ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.ADMIN)) {
            Product temp = productDao.getById(Integer.parseInt(request.getParameter("id")));
            if (temp != null) {
                 if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();

                    // Creating the directory to store file
                    String rootPath = env.getProperty("uploadDir");
                    File dir = new File(rootPath + "products");
                    if (!dir.exists())
                        dir.mkdirs();

                    // Create the file on server
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + file.getOriginalFilename());
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();
                    temp.setImageURL(file.getOriginalFilename());
                    //System.out.println("Server File Location="+ serverFile.getAbsolutePath());

                    //System.out.println("You successfully uploaded file=" + file.getOriginalFilename());
                } catch (Exception e) {
                    System.out.println( "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
                }}
                int bi = Integer.valueOf(request.getParameter("active"));
                    temp.setActive(bi != 0);
                    //temp.setImageURL(file.getOriginalFilename());
                    temp.setName(request.getParameter("name"));
                    temp.setHeight(Integer.parseInt(request.getParameter("height")));
                    temp.setWidth(Integer.parseInt(request.getParameter("width")));
                    temp.save();
                return "redirect:list";

            } else {
                System.out.println("Invalid ID");
                return "redirect:../";
            }
        }
        return "redirect:../";
    }
        
    @RequestMapping(value = "/view/{photoId:^[0-9]+$}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(HttpServletRequest response, @PathVariable("photoId") int id) throws IOException {
        Product product = productDao.getById(id);
        if(product != null) {
            String filename = "";
            filename = env.getProperty("uploadDir") +"products/"+ product.getImageURL();

            InputStream in = new FileInputStream(filename);
            BufferedImage img = ImageIO.read(in);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            ImageIO.write(img, "png", bos);
            byte[] image = bos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); //or what ever type it is
            headers.setContentLength(image.length);

            return new HttpEntity<byte[]>(image, headers);
        }
        else
        {
            return null;
        }
    }
    
    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public String setPrice(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.PHOTOGRAPHER)) {
            int userID = (int)request.getSession().getAttribute("UserID");
            map.put("products", productDao.getPriceList(userID));
            return "product/set";
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public String setPricePost(ModelMap map, HttpServletRequest request) {
        if (authenticate(UserType.PHOTOGRAPHER)) {
            try {
                for(String id : Collections.list(request.getParameterNames()))
                {
                    if(Integer.parseInt(id)>0)
                    {
                        int userID = (int)request.getSession().getAttribute("UserID");
                        Product p = new Product();
                        p = productDao.getById(Integer.parseInt(id));
                        p.setPrice(Double.parseDouble(request.getParameter(id)));
                        p.save();
                        productDao.saveProductPrice(p, userID);
                    }
                }
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
                return "redirect:/product/set";
            }

            return "redirect:../admin";
        }
        return "redirect:../";
    }
}
