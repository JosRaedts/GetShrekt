/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bram
 */
abstract public class Database {
    
    protected static Connection conn = null;
    
    public Database()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(this.conn != null)
            {
                this.conn = (Connection) DriverManager.getConnection("jdbc:mysql://stormhost.nl:3306/admin_photo", "admin_photo", "pizza");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
