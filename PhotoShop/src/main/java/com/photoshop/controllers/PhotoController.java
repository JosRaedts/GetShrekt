/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.controllers;

import com.photoshop.models.photo.PhotoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bram
 */

@RequestMapping("/photo")
@Controller
public class PhotoController extends AbstractController {
    
    @Autowired
    private PhotoDao photodao;
    
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload()
    {
        return "photo/upload";
    }
}
