/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.student;

import com.photoshop.models.SchoolClass.SchoolClass;
import com.photoshop.models.SchoolClass.SchoolClassDao;

/**
 *
 * @author Bram
 */
public class Student {
    
    private int id;
    private int studentnr;
    private String name;
    private String address;
    private String city;
    private String zipcode;
    private String username;
    private String password;
    private int schoolclass_id;
    private SchoolClass schoolclass = null;
            
    private final StudentDao dao;   
    
    public Student()
    {
        this.dao = new StudentDao();
    }
    
    public Student(StudentDao dao)
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
    
    public int getStudentnr() {
        return studentnr;
    }

    public void setStudentnr(int studentnr) {
        this.studentnr = studentnr;
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
    
    public SchoolClass getSchoolClass(){
        if(this.schoolclass == null)
        {
            SchoolClassDao schoolclassdao = new SchoolClassDao();
            this.schoolclass = schoolclassdao.getById(schoolclass_id);
        }
        return this.schoolclass;
    }
    
    public void setSchoolClass(SchoolClass schoolclass){
        this.schoolclass = schoolclass;
        if(schoolclass != null)
        {
            this.schoolclass_id = schoolclass.getId();
        }
        else
        {
            this.schoolclass_id = 0;
        }
    }
    
    public int getSchoolclass_id() {
        return schoolclass_id;
    }

    public void setSchoolclass_id(int schoolclass_id) {
        this.schoolclass_id = schoolclass_id;
    }
}
