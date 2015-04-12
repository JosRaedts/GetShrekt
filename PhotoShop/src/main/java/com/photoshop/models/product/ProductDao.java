/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.product;

import com.photoshop.models.Database;
import com.photoshop.models.photographer.Photographer;
import com.photoshop.models.photographer.PhotographerDao;
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
 * @author Jos
 */
@Component
public class ProductDao extends Database{
    
    @Autowired
    private PhotographerDao photographerdao;
    
    public ProductDao()
    {
        super();
    }
    
    public List<Product> getList()
    {
        List<Product> photos = new ArrayList();
        try {
            String querystring = "SELECT * FROM products";
            PreparedStatement stat = conn.prepareStatement(querystring);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                photos.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photos;
    }
    
    public List<Product> getPriceList(Photographer photographer)
    {
        List<Product> products = new ArrayList();
        try {
            String querystring = "SELECT p.id AS id, p.name AS name, p.height AS height, p.width AS width, p.imageURL AS imageURL, p.active AS active, pp.photographer_id AS photographer_id, pp.product_id AS product_id, pp.price AS price FROM products p, productprice_photographer pp WHERE pp.product_id = p.id AND pp.photographer_id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photographer.getId());
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                products.add(build(rs)); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    public Product getById(int id)
    {
        Product photographer = null;
        try {
            String querystring = "SELECT * FROM products WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                photographer = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photographer;
    }
    
    public boolean idExists(int id)
    {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM products WHERE id = ?";
            PreparedStatement stat;
            stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
               exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }
    
    public boolean save(Product product)
    {
        try {
            String querystring = null;
            boolean exists = idExists(product.getId());
            if(exists)
            {
                querystring = "UPDATE products SET name = ?, height = ?, width = ?, imageURL = ?, active = ? WHERE id = ?";                                
            }
            else
            {
                querystring = "INSERT INTO products(name, height, width, imageURL, active) VALUES(?, ?, ?, ?, ?)";
            }
            
            PreparedStatement stat = conn.prepareStatement(querystring);
            
            stat.setString(1, product.getName());
            stat.setInt(2, product.getHeight());
            stat.setInt(3, product.getWidth());
            stat.setString(4, product.getImageURL());
            if(product.getActive())
            {
                stat.setInt(5, 1);
            }
            else{
                stat.setInt(5, 0);
            }
            if(exists)
            {
                stat.setInt(6, product.getId());
            }
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean saveProductPrice(Product product, Photographer photographer)
    {
        try {
            String querystring = null;
                        
            querystring = "UPDATE productprice_photographer SET price = ? WHERE photographer_id = ? AND product_id = ?";                                
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setDouble(1, product.getPrice());
            stat.setInt(2, photographer.getId());
            stat.setInt(3, product.getId());
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void delete(Product photo)
    {
        try {
            String querystring = "UPDATE photos SET active=0 WHERE id=?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photo.getId());
            stat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Product build(ResultSet rs)
    {
        Product product = null;
        try {            
            product = new Product(this);
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setHeight(rs.getInt("height"));
            product.setWidth(rs.getInt("width"));
            product.setImageURL(rs.getString("imageURL"));
            if(rs.getInt("active") != 0){
                product.setActive(true);
            }
            else{
                product.setActive(false);
            }
            if(rs.getDouble("price") != 0.0){
                Photographer photographer = photographerdao.getById(rs.getInt("photographer_id"));
                product.setPrice(photographer, rs.getDouble("price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
}
