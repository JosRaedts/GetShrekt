/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.product;

import com.photoshop.models.photographer.Photographer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jos
 */
@Component
public class Product {
    
    private int id;
    private String name;
    private int height;
    private int width;
    private String imageURL;
    private boolean active;
    private Double price;
    
    @Autowired
    private ProductDao dao;
    
    public Product(){
    }
    
    public void save()
    {
        if(this.dao == null)
        {
            dao = new ProductDao();
        }
        this.dao.save(this);
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
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
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
    
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String URL) {
        this.imageURL = URL;
    }
    
    public boolean getActive(){
        return active;
    }
    
    public void setActive(boolean active){
        this.active = active;
    }
    
    public void setPrice(Double price){
        if(price >= 0)
        {
        this.price = price;
        }
    }
    
    public Double getPrice(){
        return price;
    }
}
