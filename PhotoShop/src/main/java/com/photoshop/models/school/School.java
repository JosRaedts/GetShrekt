/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.school;

import com.photoshop.models.SchoolClass.SchoolClass;
import java.util.List;

/**
 *
 * @author Casper
 */
public class School{
    
    private int id;
    private String name;
    private String address;
    private String city;
    private String zipcode;
    private String code;
    private final SchoolDao dao;
    
    public School()
    {
        this.dao = new SchoolDao();
    }
    
    public School(SchoolDao dao)
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public List<SchoolClass> getSchoolClasses(){
        //to do
        return null;
    }
    
    public void setSchoolClasses(List<SchoolClass> schoolclasses){
        //todo
    }
}
