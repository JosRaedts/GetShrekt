/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.cartproduct;

/**
 *
 * @author pc
 */
public class Cartproduct {
    
    
    private int id;
    private double price;
    private String content;
    private int amount;
    private int studentid;
    private int photoid;
    
    
    private final CartproductDao dao;
    
    public Cartproduct()
    {
        this.dao = new CartproductDao();
    }
    
    public Cartproduct(CartproductDao dao)
    {
        this.dao = dao;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public int getAmount(){
        return amount;
    }
    
    public void setAmount(int amount){
        this.amount = amount;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public int getPhotoID()
    {
        return photoid;
    }
    
    public void setPhotoID(int photoid)
    {
        this.photoid = photoid;
    }
    
    public int getStudentID()
    {
        return studentid;
    }
    
    public void setStudentID(int studentid)
    {
        this.studentid = studentid;
    }
    
}
