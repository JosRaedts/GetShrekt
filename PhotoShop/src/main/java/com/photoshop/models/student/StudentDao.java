/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.student;

import com.photoshop.models.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

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
            String querystring = null;
            boolean exists = idExists(student.getId());
            if(exists)
            {
                querystring = "UPDATE students SET studentnr = ?, name = ?, address = ?, city = ?, zipcode = ?, username = ?, password = ?, schoolclass_id = ? WHERE id = ?";                                
            }
            else
            {
                querystring = "INSERT INTO students(studentnr, name, address, city, zipcode, username, password, schoolclass_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            }
            
            
            PreparedStatement stat = conn.prepareStatement(querystring);
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
            String querystring = "SELECT * FROM students WHERE username = ? AND password = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setString(1, username);
            stat.setString(2, password);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                student = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }
    
    private Student build(ResultSet rs)
    {
        Student student = null;
        try {            
            student = new Student(this);
            student.setId(rs.getInt("id"));
            student.setStudentnr(rs.getInt("studentnr"));
            student.setName(rs.getString("name"));
            student.setUsername(rs.getString("username"));
            student.setPassword(rs.getString("password"));
            student.setAddress(rs.getString("address"));
            student.setCity(rs.getString("city"));
            student.setZipcode(rs.getString("zipcode"));
            student.setSchoolclass_id(rs.getInt("schoolclass_id"));
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }
}
