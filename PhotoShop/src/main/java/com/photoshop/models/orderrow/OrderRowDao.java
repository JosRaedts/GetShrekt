/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.orderrow;

import com.mysql.jdbc.Statement;
import com.photoshop.models.Database;
import com.photoshop.models.product.Product;
import com.photoshop.models.product.ProductDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Willem
 */
@Component
public class OrderRowDao extends Database {

    @Autowired
    private ProductDao productDao;
    
    public OrderRowDao()
    {
        super();
    }
    
    public List<OrderRow> getList()
    {
        List<OrderRow> orderregel = new ArrayList();
        try {
            String querystring = "SELECT * FROM order_regels";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                orderregel.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderRowDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderregel;
    }
    
    
    public OrderRow getById(int id)
    {
        OrderRow or = null;
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
            Logger.getLogger(OrderRowDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OrderRowDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }
    
    public boolean save(OrderRow or)
    {
        try {
            PreparedStatement stat;
            String querystring = null;
            boolean exists = idExists(or.getId());
            if(exists)
            {
                querystring = "UPDATE order_regels SET order_id = ?, product_id = ?, photographer_id = ?, photo_id = ?, aantal = ?, productprice = ? WHERE id = ?";
                stat = conn.prepareStatement(querystring);
            }
            else
            {
                querystring = "INSERT INTO order_regels(order_id, product_id, photographer_id, photo_id, aantal, productprice) VALUES(?, ?, ?, ?, ?, ?)";
                stat = conn.prepareStatement(querystring, Statement.RETURN_GENERATED_KEYS);
            }

            stat.setInt(1, or.getOrder_id());
            stat.setInt(2, or.getProduct_id());
            stat.setInt(3, or.getPhotographer_id());
            stat.setInt(4, or.getPhoto_id());
            stat.setInt(5, or.getAantal());
            stat.setDouble(6, or.getProductprice());

            if(exists)
            {
                stat.setInt(7, or.getId());
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
            Logger.getLogger(OrderRowDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
    private OrderRow build(ResultSet rs)
    {
        OrderRow or = null;
        try {
            or = new OrderRow();
            or.setId(rs.getInt("id"));
            or.setOrder_id(rs.getInt("order_id"));
            or.setPhoto_id(rs.getInt("photo_id"));
            or.setPhotographer_id(rs.getInt("photographer_id"));
            or.setProduct_id(rs.getInt("product_id"));
            or.setAantal(rs.getInt("aantal"));
            or.setProductprice(rs.getDouble("productprice"));
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderRowDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return or;
    }

    public List<OrderRow> getOrderRowByOrderNr(int id){
        ArrayList<OrderRow> orderregels = new ArrayList<OrderRow>();
        try {
            String querystring = "SELECT * FROM order_regels where order_id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                orderregels.add(build(rs));
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderRowDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderregels;
    }
    
    public int getNumberOfSalesForPhotographer(int photograhper_id) {
        int i = 0;
        try {
            String querystring = "SELECT aantal as aantal FROM order_regels where photographer_id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photograhper_id);
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                i = i + rs.getInt("aantal");
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderRowDao.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return i;
    }
    
     public List<int[]> getTopProducts()
    {
        List<int[]> products = new ArrayList();
        try {
            String querystring = "SELECT product_id as id, SUM( aantal ) AS aantal\n" +
                                    "FROM order_regels\n" +
                                    "GROUP BY product_id\n" +
                                    "ORDER BY SUM( aantal ) DESC \n" +
                                    "LIMIT 5";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            while(rs.next()) {
                int[] array = new int[2];
                array[0] = rs.getInt("id");
                array[1] = rs.getInt("aantal");
                products.add(array);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderRowDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
}
