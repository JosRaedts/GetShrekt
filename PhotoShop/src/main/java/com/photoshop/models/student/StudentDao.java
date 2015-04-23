/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.student;

import com.photoshop.models.Database;
import com.photoshop.models.photo.Photo;
import com.photoshop.models.schoolClass.SchoolClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bram
 */
@Component
public class StudentDao extends Database  {
    
    public StudentDao()
    {
        super();
    }
    
    public List<Student> getList()
    {
        List<Student> students = new ArrayList();
        try {
            String querystring = "SELECT * FROM students";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                students.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    
    public List<Student> getStudentsBySchoolclass(SchoolClass schoolclass)
    {
        List<Student> students = new ArrayList();
        try {
            String querystring = "SELECT * FROM students WHERE schoolclass_id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, schoolclass.getId());
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                students.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    
    public Student getById(int id)
    {
        int test = 0;
        Student student = null;
        try {
            String querystring = "SELECT * FROM students WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                test++;
                student = build(rs);
                System.out.println(test);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(student.getName());
        return student;
    }
    
    public boolean idExists(int id)
    {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM students WHERE id = ?";
            PreparedStatement stat;
            stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
               exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }
    
    public void save(Student student)
    {
        try {
            PreparedStatement stat;
            String querystring;
            boolean exists = idExists(student.getId());
            if(exists)
            {
                querystring = "UPDATE students SET studentnr = ?, name = ?, address = ?, city = ?, zipcode = ?, username = ?, password = ?, schoolclass_id = ? WHERE id = ?";
                stat = conn.prepareStatement(querystring);
            }
            else
            {
                querystring = "INSERT INTO students(studentnr, name, address, city, zipcode, username, password, schoolclass_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                stat = conn.prepareStatement(querystring, Statement.RETURN_GENERATED_KEYS);
            }

            stat.setInt(1, student.getStudentnr());
            stat.setString(2, student.getName());
            stat.setString(3, student.getAddress());
            stat.setString(4, student.getCity());
            stat.setString(5, student.getZipcode());
            stat.setString(6, student.getUsername());
            stat.setString(7, student.getPassword());
            stat.setInt(8, student.getSchoolclass_id());
            if(exists)
            {
                stat.setInt(9, student.getId());
            }
            stat.execute();
            if(!exists)
            {
                ResultSet rs = stat.getGeneratedKeys();
                student.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addPhoto(Student student, Photo photo)
    {
        try {
            String querystring;
            querystring = "INSERT INTO student_photos(studentID, photoID) VALUES(?, ?)";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, student.getId());
            stat.setInt(2, photo.getId());
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removePhoto(Student student, Photo photo) {
        try {
            String querystring;
            querystring = "DELETE FROM student_photos WHERE studentID = ? AND photoID = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, student.getId());
            stat.setInt(2, photo.getId());
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Student student)
    {
        try {
            String querystring = "DELETE FROM students WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, student.getId());
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Student authenticate(String username, String password)
    {
        Student student = null;
        try {
            String querystring = "SELECT * FROM students WHERE username = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setString(1, username);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                student = build(rs);
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if(!passwordEncoder.matches(password, rs.getString("password")))
                {
                    student = null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }
    
    public Student build(ResultSet rs)
    {
        Student student = null;
        try {            
            student = new Student(this);
            student.setId(rs.getInt("id"));
            student.setStudentnr(rs.getInt("studentnr"));
            student.setName(rs.getString("name"));
            student.setUsername(rs.getString("username"));
            student.setAddress(rs.getString("address"));
            student.setCity(rs.getString("city"));
            student.setZipcode(rs.getString("zipcode"));
            student.setSchoolclass_id(rs.getInt("schoolclass_id"));
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    public boolean doIHaveAccess(Student student, Photo photo) {
        try {
            String querystring = "SELECT COUNT(*) as rows FROM photos WHERE id=? AND id IN (SELECT photoID FROM student_photos WHERE photoID=? AND studentID=? ) OR id IN (SELECT photoID FROM schoolclass_photos WHERE photoID=? AND schoolclassID IN (SELECT schoolclass_id FROM students WHERE id=?)) OR id IN (SELECT photoID FROM school_photos WHERE photoID=? AND schoolID IN (SELECT school_id FROM schoolclasses WHERE id IN (SELECT schoolclass_id FROM students WHERE id=?)))";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photo.getId());
            stat.setInt(2, photo.getId());
            stat.setInt(3, student.getId());
            stat.setInt(4, photo.getId());
            stat.setInt(5, student.getId());
            stat.setInt(6, photo.getId());
            stat.setInt(7, student.getId());
            ResultSet rs = stat.executeQuery();

            rs.next();
            return rs.getInt("rows") > 0;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
