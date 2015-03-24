/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.photographer;

import com.photoshop.models.student.*;
import com.photoshop.models.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Casper
 */
@Component
public class PhotographerDao extends Database  {
    
    public PhotographerDao()
    {
        super();
    }
    
    public List<Photographer> getList()
    {
        List<Photographer> photographers = new ArrayList();
        try {
            String querystring = "SELECT * FROM photographers";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                photographers.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhotographerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photographers;
    }
    
    public Photographer getById(int id)
    {
        Photographer photographer = null;
        try {
            String querystring = "SELECT * FROM photographers WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                photographer = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhotographerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photographer;
    }
    
    public boolean idExists(int id)
    {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM photographers WHERE id = ?";
            PreparedStatement stat;
            stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
               exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotographerDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }
    
    public boolean save(Photographer photographer)
    {
        try {
            String querystring = null;
            boolean exists = idExists(photographer.getId());
            if(exists)
            {
                querystring = "UPDATE photographers SET name = ?, username = ?, password = ? WHERE id = ?";                                
            }
            else
            {
                querystring = "INSERT INTO photographers(name, username, password) VALUES(?, ?, ?)";
            }
            
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setString(1, photographer.getName());
            stat.setString(2, photographer.getUsername());
            stat.setString(3, photographer.getPassword());
            if(exists)
            {
                stat.setInt(4, photographer.getId());
            }
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PhotographerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void delete(Photographer photographer)
    {
        try {
            String querystring = "DELETE FROM photographers WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photographer.getId());
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PhotographerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Photographer authenticate(String username, String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Photographer photographer = null;
        try {
            String querystring = "SELECT * FROM photographers WHERE username = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setString(1, username);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                photographer = build(rs);
                if(!passwordEncoder.matches(password, rs.getString("password")))
                {
                    photographer = null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhotographerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photographer;
    }
    
    private Photographer build(ResultSet rs)
    {
        Photographer photographer = null;
        try {            
            photographer = new Photographer(this);
            photographer.setId(rs.getInt("id"));
            photographer.setName(rs.getString("name"));
            photographer.setUsername(rs.getString("username"));
            
        } catch (SQLException ex) {
            Logger.getLogger(PhotographerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photographer;
    }
}
