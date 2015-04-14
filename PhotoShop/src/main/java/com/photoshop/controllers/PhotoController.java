/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.misc.ImageManager;
import com.photoshop.models.UserType;
import com.photoshop.models.photo.Photo;
import com.photoshop.models.photo.PhotoDao;
import com.photoshop.models.photo.PhotoJson;
import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.school.School;
import com.photoshop.models.school.SchoolDao;
import com.photoshop.models.schoolClass.SchoolClass;
import com.photoshop.models.schoolClass.SchoolClassDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
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
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private SchoolClassDao schoolClassDao;

    @Autowired
    private SchoolDao schoolDao;
    
    @Autowired
    private ImageManager imageManager;

    @RequestMapping(value = {"/upload", "/upload/do_upload"}, method = RequestMethod.GET)
    public String upload() {
        if (this.authenticate(UserType.PHOTOGRAPHER)) {
            return "photo/upload";
        } else {
            return this.backendLogin();
        }
    }

    @RequestMapping(value = "/upload/do_upload", method = RequestMethod.POST)
    public @ResponseBody
    JsonObject do_upload(MultipartHttpServletRequest request, HttpServletRequest response) {
        if (this.authenticate(UserType.PHOTOGRAPHER)) {
            Photographer photographer = (Photographer) this.getUser();
            Iterator<String> itr = request.getFileNames();
            while (itr.hasNext()) {
                try {
                    MultipartFile mpf = request.getFile(itr.next());
                    String path = env.getProperty("uploadDir");
                    String originalFilename = mpf.getOriginalFilename();
                    String newFilename = System.currentTimeMillis() + "-" + mpf.getOriginalFilename();
                    FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(path + newFilename));
                    BufferedImage bimg = ImageIO.read(new File(path + newFilename));
                    
                    BufferedImage blowres = bimg;
                    BufferedImage bthumbnail = bimg;
                    
                    if(bimg.getHeight() > bimg.getWidth())
                    {
                        if(bimg.getHeight() > 1024)
                        {
                            int ratio = bimg.getHeight() / 1024;
                            blowres = imageManager.resize(bimg, 1024, bimg.getWidth()/ratio);
                        }
                        if(bimg.getHeight() > 100)
                        {
                            int ratio = bimg.getHeight() / 100;
                            blowres = imageManager.resize(bimg, 100, bimg.getWidth()/ratio);
                        }
                    }
                    else
                    if(bimg.getWidth()> bimg.getHeight())
                    {
                        if(bimg.getWidth() > 1024)
                        {
                            int ratio = bimg.getWidth() / 1024;
                            blowres = imageManager.resize(bimg, bimg.getHeight()/ratio, 1024);
                        }
                        if(bimg.getWidth() > 100)
                        {
                            int ratio = bimg.getWidth() / 100;
                            blowres = imageManager.resize(bimg, bimg.getHeight()/ratio, 100);
                        }
                    }


                    File lowdir = new File(path + "/low");
                    if (!lowdir.exists()){ lowdir.mkdirs(); }
                    File thumbdir = new File(path + "/thumb");
                    if (!thumbdir.exists()){ thumbdir.mkdirs(); }
                    
                    String lowres = path + "/low/lowres-" + newFilename;
                    String thumbnail = path + "/thumb/thumb-" + newFilename;
                    ImageIO.write(blowres, "jpg", new FileOutputStream(lowres));
                    ImageIO.write(bthumbnail, "jpg", new FileOutputStream(thumbnail));

                    Photo photo = new Photo();
                    photo.setActive(true);
                    java.util.Date utilDate = new java.util.Date();
                    photo.setDate(new Date(utilDate.getTime()));
                    photo.setHeight(bimg.getHeight());
                    photo.setWidth(bimg.getWidth());
                    photo.setHighResURL(newFilename);
                    photo.setLowResURL(lowres);
                    photo.setPhotographerID(photographer.getId());
                    photo.save();

                    String[] file = originalFilename.split("\\.");
                    String type = file[0].split("-")[0];
                    int id = Integer.parseInt(file[0].split("-")[1]);

                    switch (type) {
                        case "student":
                            Student student = studentDao.getById(id);
                            student.addPhoto(photo);
                            break;
                        case "school":
                            School school = schoolDao.getById(id);
                            school.addPhoto(photo);
                            break;
                        case "schoolclass":
                            SchoolClass schoolClass = schoolClassDao.getById(id);
                            schoolClass.addPhoto(photo);
                            break;
                    }

                    PhotoJson photoJson = new PhotoJson(request.getRequestURI() + "/photo/view/low/" + photo.getId(),
                            request.getRequestURI() + "/photo/view/low/" + photo.getId(),
                            originalFilename,
                            "image/jpeg",
                            String.valueOf(mpf.getBytes()),
                            "",
                            "POST");
                    //JSON
                    JsonObjectBuilder jsonArrayBuilder = Json.createObjectBuilder();
                    jsonArrayBuilder.add("files", Json.createArrayBuilder()
                            .add(Json.createArrayBuilder()));
                    return jsonArrayBuilder.build();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/view/{format:low|high}/{photoId:^[0-9]+$}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(HttpServletRequest response, @PathVariable("format") String format, @PathVariable("photoId") int id) throws IOException {
        Photo photo = photodao.getById(id);
        if (photo != null) {
            String filename = "";
            switch (format) {
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
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request) {
        if (this.authenticate(UserType.ADMIN)) {
            map.put("pictures", photodao.getList());
            return "photo/list";
        }
        return "redirect:../";
    }
    
    @RequestMapping(value = "/mypictures", method = RequestMethod.GET)
    public String mypictures(ModelMap map, HttpServletRequest request)
    {
        int userID = (int)request.getSession().getAttribute("UserID");
        map.put("Photo", photodao.getPhotosByStudent(userID));
        map.put("studentnaam", request.getSession().getAttribute("UserName").toString());
        return "photo/mypictures";
    }
    
    @RequestMapping(value = "/photodetail", method = RequestMethod.GET)
    public String detail(ModelMap map, HttpServletRequest request) {
        return "photo/photodetail";
    }
}
