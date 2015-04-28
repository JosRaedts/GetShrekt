/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.photo;

import com.photoshop.models.school.School;
import com.photoshop.models.schoolClass.SchoolClass;
import com.photoshop.models.student.Student;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Jos
 */
public class Photo {
    
    private int id;
    private int height;
    private int width;
    private String thumbnailURL;
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
        this.dao.delete(id);
    }

    public void save() { this.dao.save(this); }
    
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
    
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getLowResURL() {
        return lowResURL;
    }
    
    public String getHighResURL() {
        return highResURL;
    }

    public void setThumbnailURL(String URL) {
        this.thumbnailURL = URL;
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
        this.date = date;
    }

    public List<Student> getStudents() { return this.dao.getStudents(this);}

    public List<SchoolClass> getSchoolClasses() { return this.dao.getSchoolClasses(this);}

    public List<School> getSchools() { return this.dao.getSchools(this);}

    public void saveStudents(List<Student> students) { this.dao.saveStudents(this, students);}

    public void saveSchoolClasses(List<SchoolClass> schoolClasses) { this.dao.saveSchoolClasses(this, schoolClasses);}

    public void saveSchools(List<School> schools) { this.dao.saveSchools(this, schools);}
}


