/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.UserType;
import com.photoshop.models.photo.Photo;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.photographer.Photographer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Date;
import java.util.Iterator;

/**
 *
 * @author Bram
 */

@RequestMapping("/photo")
@Controller
public class PhotoController extends AbstractController {
    
    @Autowired
    private PhotoDao photodao;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private Environment env;

    @RequestMapping(value = {"/upload", "/upload/do_upload"}, method = RequestMethod.GET)
    public String upload()
    {
        if(this.authenticate(UserType.PHOTOGRAPHER)) {
            return "photo/upload";
        }
        else
        {
            return this.backendLogin();
        }
    }

    @RequestMapping(value = "/upload/do_upload", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String do_upload(MultipartHttpServletRequest request, HttpServletRequest response)
    {
        if(this.authenticate(UserType.PHOTOGRAPHER)) {
            Photographer photographer = (Photographer) this.getUser();
            Iterator<String> itr = request.getFileNames();
            while (itr.hasNext()) {
                try {
                    MultipartFile mpf = request.getFile(itr.next());
                    String path = env.getProperty("uploadDir");
                    String filename = System.currentTimeMillis() + "-" + mpf.getOriginalFilename();
                    FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(path + filename));

                    BufferedImage bimg = ImageIO.read(new File(path + filename));

                    Photo photo = new Photo();
                    photo.setActive(true);
                    java.util.Date utilDate = new java.util.Date();
                    photo.setDate(new Date(utilDate.getTime()));
                    photo.setHeight(bimg.getHeight());
                    photo.setWidth(bimg.getWidth());
                    photo.setHighResURL(filename);
                    photo.setLowResURL(filename);
                    photo.setPhotographerID(photographer.getId());
                    photo.save();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "photo/upload";
        }
        else
        {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = "/view/{format:low|high}/{photoId:^[0-9]+$}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(HttpServletRequest response, @PathVariable("format") String format, @PathVariable("photoId") int id) throws IOException {
        Photo photo = photodao.getById(id);
        if(photo != null) {
            String filename = "";
            switch(format) {
                case "high":
                    filename = env.getProperty("uploadDir") + photo.getHighResURL();
                    break;
                case "low":
                    filename = env.getProperty("uploadDir") + photo.getLowResURL();
                    break;
            }

            InputStream in = new FileInputStream(filename);
            BufferedImage img = ImageIO.read(in);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            ImageIO.write(img, "jpg", bos);
            byte[] image = bos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); //or what ever type it is
            headers.setContentLength(image.length);

            return new HttpEntity<byte[]>(image, headers);
        }
        else
        {
            return null;
        }
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request)
    {
        map.put("pictures", photodao.getList());
        return "photo/list";
    }
}

