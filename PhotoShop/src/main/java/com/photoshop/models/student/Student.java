/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.student;

import com.photoshop.models.SchoolClass.SchoolClass;

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
        //todo
        return null;
    }
    
    public void setSchoolClass(SchoolClass schoolclass){
        //todo
    }
}
