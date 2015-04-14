/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.product;

import com.photoshop.models.photographer.Photographer;

/**
 *
 * @author Jos
 */
public class Product {
    
    private int id;
    private String name;
    private int height;
    private int width;
    private String imageURL;
    private boolean active;
    private Double price;
    private final ProductDao dao;
    
    public Product()
    {
        this.dao = new ProductDao();
    }
    
    public Product(ProductDao dao)
    {
        this.dao = dao;
    }
    
    public void save()
    {
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
        this.price = price;
    }
    
    public Double getPrice(){
        return price;
    }
}
