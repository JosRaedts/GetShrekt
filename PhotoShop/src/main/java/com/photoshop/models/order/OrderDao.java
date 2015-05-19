/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.order;

import com.mysql.jdbc.Statement;
import com.photoshop.models.Database;
import com.photoshop.models.product.Product;
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
public class OrderDao extends Database{
    
    public OrderDao()
    {
        super();
    }
    
    public List<Order> getList()
    {
        List<Order> orders = new ArrayList();
        try {
            String querystring = "SELECT * FROM orders";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                orders.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    /*public List<Product> getProductList(int id){
        ArrayList<Product> photos = new ArrayList<Product>();
        try {
            String querystring = "SELECT id, height, width, thumbnailURL, lowresURL, highresURL, photographer_id, active, date FROM photos WHERE photographer_id = ? ORDER BY date";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                photos.add(build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photos;
    }*/
        
    public Order getById(int id)
    {
        Order order = null;
        try {
            String querystring = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                order = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
    
    public boolean idExists(int id)
    {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement stat;
            stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
               exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }
    
    public boolean save(Order order)
    {
        try {
            PreparedStatement stat;
            String querystring = null;
            boolean exists = idExists(order.getId());
            if(exists)
            {
                querystring = "UPDATE orders SET student_id = ?, datum = ?, status = ?";
                stat = conn.prepareStatement(querystring);
            }
            else
            {
                querystring = "INSERT INTO orders(student_id, datum, status) VALUES(?, ?, ?)";
                stat = conn.prepareStatement(querystring, Statement.RETURN_GENERATED_KEYS);
            }

            stat.setInt(1, order.getStudent_id());
            stat.setTimestamp(2, order.getDatum());
            stat.setInt(3, Integer.parseInt(order.getStatus().toString()));
            
            stat.execute();
            if(!exists)
            {
                ResultSet rs = stat.getGeneratedKeys();
                rs.next();
                order.setId(rs.getInt(1));
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    private Order build(ResultSet rs)
    {
        Order order = null;
        try {            
            order = new Order(this);
            order.setId(rs.getInt("id"));
            order.setStudent_id(rs.getInt("student_id"));
            order.setDatum(rs.getTimestamp(3));
            
            switch (rs.getInt("status")) {
            case 1:  order.setStatus(OrderEnum.NIET_BETAALD);
                     break;
            case 2:  order.setStatus(OrderEnum.BETAALD);
                     break;
            case 3:  order.setStatus(OrderEnum.IN_BEHANDELING);
                     break;
            case 4:  order.setStatus(OrderEnum.VERZONDEN);
                     break;
            case 5:  order.setStatus(OrderEnum.ONTVANGEN);
                     break;
            default: order.setStatus(OrderEnum.NIET_BETAALD);
                     break;
        }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
    
//    public List<Order> getOrdersByPhotographer(int id){
//        ArrayList<Order> orders = new ArrayList<Order>();
//        try {
//            String querystring = "SELECT id, height, width, thumbnailURL, lowresURL, highresURL, photographer_id, active, date FROM photos WHERE photographer_id = ? ORDER BY date";
//            PreparedStatement stat = conn.prepareStatement(querystring);
//            stat.setInt(1, id);
//            ResultSet rs = stat.executeQuery();
//            while (rs.next()) {
//                orders.add(build(rs));
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(PhotoDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return orders;
//    }
}
