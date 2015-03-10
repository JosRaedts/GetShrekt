/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.user;

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
public class UserDao extends Database  {
    
    public UserDao()
    {
        super();
    }
    
    public List<User> getList()
    {
        List<User> users = new ArrayList();
        try {
            String querystring = "SELECT * FROM users";
            PreparedStatement stat = this.conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                users.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public User getById(int id)
    {
        User user = null;
        try {
            String querystring = "SELECT * FROM users WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                user = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public boolean idExists(int id)
    {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM users WHERE id = ?";
            PreparedStatement stat;
            stat = this.conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
               exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }
    
    public void save(User user)
    {
        try {
            String querystring = null;
            boolean exists = idExists(user.getId());
            if(exists)
            {
                querystring = "UPDATE users SET name = ?, username = ?, password = ? WHERE id = ?";                                
            }
            else
            {
                querystring = "INSERT INTO users(name, username, password) VALUES(?, ?, ?)";
            }
            
            PreparedStatement stat = this.conn.prepareStatement(querystring);
            stat.setString(1, user.getName());
            stat.setString(2, user.getUsername());
            stat.setString(3, user.getPassword());
            if(exists)
            {
                stat.setInt(4, user.getId());
            }
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(User user)
    {
        try {
            String querystring = "DELETE FROM users WHERE id = ?";
            PreparedStatement stat = this.conn.prepareStatement(querystring);
            stat.setInt(1, user.getId());
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User authenticate(String username, String password)
    {
        User user = null;
        try {
            System.out.println(username);
            System.out.println(password);
            String querystring = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setString(1, username);
            stat.setString(2, password);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                user = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    private User build(ResultSet rs)
    {
        User user = null;
        try {
            user = new User(this);
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
