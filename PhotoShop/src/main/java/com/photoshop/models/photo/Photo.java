/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.photo;

import com.photoshop.models.IUser;
import com.photoshop.models.UserType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private boolean active;
    private Date date;
    private int photographerid;
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
    
    public int getPhotographerID(){
        return photographerid;
    }
    
    public void setPhotographerID(int id){
        this.photographerid = id;
    }
    
    public boolean getActive(){
        return active;
    }
    
    public void setActive(boolean active){
        this.active = active;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        DateFormat datum = new SimpleDateFormat("ddmmYYYY");
        datum.format(date);
        System.out.println(date);
        this.date = date;
    }
}


