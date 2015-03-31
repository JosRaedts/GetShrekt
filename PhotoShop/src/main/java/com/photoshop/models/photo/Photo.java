/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.photo;

import com.photoshop.models.IUser;
import com.photoshop.models.UserType;

/**
 *
 * @author Casper
 */
public class Photo {
    
    private int id;
    private int height;
    private int width;
    private String lowResURL;
    private String highResURL;
    private final PhotoDao dao;
    
    public Photo()
    {
        this.dao = new PhotoDao();
    }
    
    public Photo(PhotoDao dao)
    {
        this.dao = dao;
    }
    
    public void delete()
    {
        this.dao.delete(this);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getLowResURL() {
        return lowResURL;
    }
    
    public String getHighResURL() {
        return highResURL;
    }

    public void setHighResURL(String URL) {
        this.highResURL = URL;
    }
    
    public void setLowResURL(String URL) {
        this.lowResURL = URL;
    }
}


