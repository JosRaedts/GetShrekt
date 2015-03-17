/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.admin;

import com.photoshop.models.photographer.*;
import com.photoshop.models.student.*;
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
 * @author Casper
 */
@Component
public class AdminDao extends Database  {
    
    public AdminDao()
    {
        super();
    }
    
    public List<Admin> getList()
    {
        List<Admin> admins = new ArrayList();
        try {
            String querystring = "SELECT * FROM admins";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                admins.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admins;
    }
    
    public Admin getById(int id)
    {
        Admin admin = null;
        try {
            String querystring = "SELECT * FROM admins WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                admin = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
    
    public boolean idExists(int id)
    {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM admins WHERE id = ?";
            PreparedStatement stat;
            stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
               exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }
    
    public boolean save(Admin admin)
    {
        try {
            String querystring = null;
            boolean exists = idExists(admin.getId());
            if(exists)
            {
                querystring = "UPDATE admins SET name = ?, username = ?, password = ? WHERE id = ?";                                
            }
            else
            {
                querystring = "INSERT INTO admins(name, username, password) VALUES(?, ?, ?)";
            }
            
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setString(1, admin.getName());
            stat.setString(2, admin.getUsername());
            stat.setString(3, admin.getPassword());
            if(exists)
            {
                stat.setInt(4, admin.getId());
            }
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void delete(Admin admin)
    {
        try {
            String querystring = "DELETE FROM admins WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, admin.getId());
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Admin authenticate(String username, String password)
    {
        Admin admin = null;
        try {
            String querystring = "SELECT * FROM admins WHERE username = ? AND password = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setString(1, username);
            stat.setString(2, password);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                admin = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
    
    private Admin build(ResultSet rs)
    {
        Admin admin = null;
        try {            
            admin = new Admin(this);
            admin.setId(rs.getInt("id"));
            admin.setName(rs.getString("name"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
}
