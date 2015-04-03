/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.photo.PhotoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
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
        return "photo/upload";
    }

    @RequestMapping(value = "/upload/do_upload", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String do_upload(MultipartHttpServletRequest request, HttpServletRequest response)
    {
        Iterator<String> itr = request.getFileNames();
        while(itr.hasNext())
        {
            try {
                MultipartFile mpf = request.getFile(itr.next());
                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(env.getProperty("uploadDir") + System.currentTimeMillis() + "-" + mpf.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "photo/upload";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap map, HttpServletRequest request)
    {
        map.put("pictures", photodao.getList());
        return "photo/list";
    }
}

