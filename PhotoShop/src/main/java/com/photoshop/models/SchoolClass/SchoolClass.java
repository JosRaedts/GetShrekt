/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.SchoolClass;

/**
 *
 * @author Casper
 */
public class SchoolClass {
    private int id;
    private String name;
    private final SchoolClassDao dao;
    
    public SchoolClass()
    {
        this.dao = new SchoolClassDao();
    }
    
    public SchoolClass(SchoolClassDao dao)
    {
        this.dao= dao;
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
    
}