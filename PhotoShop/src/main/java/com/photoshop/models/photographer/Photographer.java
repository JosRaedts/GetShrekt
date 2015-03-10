/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.photographer;

/**
 *
 * @author Casper
 */
public class Photographer {
    
    private int id;
    private String name;
    private String username;
    private String password;
    private final PhotographerDao dao;
    
    public Photographer()
    {
        this.dao = new PhotographerDao();
    }
    
    public Photographer(PhotographerDao dao)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
