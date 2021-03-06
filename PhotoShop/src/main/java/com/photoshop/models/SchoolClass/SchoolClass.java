/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.schoolClass;

import com.photoshop.models.photo.Photo;
import com.photoshop.models.school.School;
import com.photoshop.models.school.SchoolDao;
import com.photoshop.models.student.Student;
import com.photoshop.models.student.StudentDao;
import java.util.List;

/**
 *
 * @author Casper
 */
public class SchoolClass {
    private int id;
    private String name;
    private final SchoolClassDao dao;
    private List<Student> students;
    private int school_id;
    
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
    
    public void setStudents(List<Student> students){
        //todo
    }
    
    public School getSchool(){
        
        SchoolDao schooldao = new SchoolDao();
        return schooldao.getById(this.school_id);
    }
    
    public void setSchool(School school){
        //todo
        this.school_id = school.getId();
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }
    
    public List<Student> getStudents()
    {
        StudentDao studentdao = new StudentDao();
        return studentdao.getStudentsBySchoolclass(this);
    }

    public void addPhoto(Photo photo)
    {
        this.dao.addPhoto(this, photo);
    }

    public void removePhoto(Photo photo)
    {
        this.dao.removePhoto(this, photo);
    }
}
