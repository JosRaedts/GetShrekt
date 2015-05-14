/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.orderregel;

import com.mysql.jdbc.Statement;
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
 * @author Willem
 */
@Component
public class OrderRegelDao extends Database {

    public OrderRegelDao()
    {
        super();
    }
    
    public List<OrderRegel> getList()
    {
        List<OrderRegel> photos = new ArrayList();
        try {
            String querystring = "SELECT * FROM order_regels";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                photos.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderRegelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photos;
    }
    
    
    public OrderRegel getById(int id)
    {
        OrderRegel or = null;
        try {
            String querystring = "SELECT * FROM order_regels WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                or = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderRegelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return or;
    }
    
    public boolean idExists(int id) 
    {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM order_regels WHERE id = ?";
            PreparedStatement stat;
            stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
               exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderRegelDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }
    
    public boolean save(OrderRegel or)
    {
        try {
            PreparedStatement stat;
            String querystring = null;
            boolean exists = idExists(or.getId());
            if(exists)
            {
                querystring = "UPDATE order_regels SET order_id = ?, product_id = ?, photographer_id = ?, photo_id = ?, aantal = ? WHERE id = ?";
                stat = conn.prepareStatement(querystring);
            }
            else
            {
                querystring = "INSERT INTO order_regels(order_id, product_id, photographer_id, photo_id, aantal) VALUES(?, ?, ?, ?, ?)";
                stat = conn.prepareStatement(querystring, Statement.RETURN_GENERATED_KEYS);
            }

            stat.setInt(1, or.getOrder_id());
            stat.setInt(2, or.getProduct_id());
            stat.setInt(3, or.getPhotographer_id());
            stat.setInt(4, or.getPhoto_id());
            stat.setInt(5, or.getAantal());

            if(exists)
            {
                stat.setInt(6, or.getId());
            }
            stat.execute();
            if(!exists)
            {
                ResultSet rs = stat.getGeneratedKeys();
                rs.next();
                or.setId(rs.getInt(1));
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderRegelDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
    private OrderRegel build(ResultSet rs)
    {
        OrderRegel or = null;
        try {            
            or = new OrderRegel(this);
            or.setId(rs.getInt("id"));
            or.setOrder_id(rs.getInt("height"));
            or.setPhoto_id(rs.getInt("width"));
            or.setPhotographer_id(rs.getInt("thumbnailURL"));
            or.setProduct_id(rs.getInt("lowresURL"));
            or.setAantal(rs.getInt("highresURL"));
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderRegelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return or;
    }

    public List<OrderRegel> getOrderRegelsByOrderNr(int id){
        ArrayList<OrderRegel> regels = new ArrayList<OrderRegel>();
        try {
            String querystring = "SELECT * FROM order_regels where order_id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                regels.add(build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderRegelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regels;
    }
}
