/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.cartproduct;

import com.photoshop.models.Database;
import com.photoshop.models.imgdata.Imgdata;
import com.photoshop.models.product.ProductDao;
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
 * @author pc
 */
@Component
public class CartproductDao extends Database {

    public CartproductDao() {
        super();
    }

    public List<Cartproduct> getList(int id) {
        List<Cartproduct> products = new ArrayList();
        try {
            String querystring = "SELECT * FROM cartproducts WHERE student_id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                products.add(build(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public Cartproduct getById(int id) {
        Cartproduct product = null;
        try {
            String querystring = "SELECT * FROM cartproducts WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                product = build(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public boolean idExists(int id) {
        boolean exists = false;
        try {
            String querystring = "SELECT * FROM cartproducts WHERE id = ?";
            PreparedStatement stat;
            stat = conn.prepareStatement(querystring);
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    public boolean save(Cartproduct product) {
        try {
            String querystring = null;
            boolean exists = idExists(product.getId());
            if (exists) {
                querystring = "UPDATE cartproducts SET price = ?, amount = ? WHERE id = ?";
            } else {
                querystring = "INSERT INTO cartproducts(price, amount) VALUES(?, ?)";
            }

            PreparedStatement stat = conn.prepareStatement(querystring);

            stat.setDouble(1, product.getPrice());
            stat.setInt(2, product.getAmount());

            if (exists) {
                stat.setInt(3, product.getId());
            }
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private Cartproduct build(ResultSet rs) {
        Cartproduct product = null;
        try {
            product = new Cartproduct(this);
            product.setId(rs.getInt("id"));
            product.setContent(rs.getString("content"));
            product.setPrice(rs.getDouble("price"));
            product.setAmount(rs.getInt("amount"));
            product.setPhotoID(rs.getInt("photo_id"));
            product.setProductId(rs.getInt("product_id"));
            product.setStudentID(rs.getInt("student_id"));
            product.setImageId(rs.getInt("image_id"));

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public boolean delete(Cartproduct product) {
        try {
            String querystring = null;
            boolean exists = idExists(product.getId());
            if (exists) {
                querystring = "DELETE FROM cartproducts WHERE id = ?";
            } else {
                return false;
            }

            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, product.getId());
            stat.execute();

            PreparedStatement stat2 = conn.prepareStatement("DELETE FROM imagedata WHERE id = ?");
            stat2.setInt(1, product.getImageId());
            stat2.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean addToCart(Cartproduct product) {
        try {
            String querystring = null;
            querystring = "INSERT INTO cartproducts(content, price, amount, student_id, photo_id, product_id, image_id) VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stat = conn.prepareStatement(querystring, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, product.getContent());
            stat.setDouble(2, product.getPrice());
            stat.setInt(3, product.getAmount());
            stat.setInt(4, product.getStudentID());

            stat.setInt(5, product.getPhotoID());
            stat.setInt(6, product.getProductId());
            stat.setInt(7, product.getImageId());
            stat.execute();

            ResultSet rs = stat.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public double getPrice(int photoid, int productid) {
        double price = 0;

        try {
            String querystring = "SELECT price as price FROM productprice_photographer WHERE photographer_id = (SELECT photographer_id FROM photos WHERE id = ?) AND product_id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, photoid);
            stat.setInt(2, productid);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return price;
    }

    public String getName(int productid) {
        String name = "";
        try {
            String querystring = "SELECT name as name FROM products WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, productid);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return name;
    }
    
    public boolean saveImageData(Imgdata imgdata)
    {
        try {
            String querystring = null;
            boolean exists = idExists(imgdata.getId());
            if (exists) {
                querystring = "UPDATE imagedata SET x = ?, y = ?, height = ?, width = ?, filter = ? WHERE id = ?";
            } else {
                querystring = "INSERT INTO imagedata(x, y, height, width, filter) VALUES(?, ?, ?, ?, ?)";
            }

            PreparedStatement stat = conn.prepareStatement(querystring, Statement.RETURN_GENERATED_KEYS);

            stat.setFloat(1, imgdata.getX());
            stat.setFloat(2, imgdata.getY());
            stat.setFloat(3, imgdata.getHeight());
            stat.setFloat(4, imgdata.getWidth());
            stat.setString(5, imgdata.getFilter().toString());

            if (exists) {
                stat.setInt(3, imgdata.getId());
            }
            stat.execute();
            if(!exists)
            {
                ResultSet rs = stat.getGeneratedKeys();
                rs.next();
                imgdata.setId(rs.getInt(1));
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean clearCart(int studentid)
    {
        try {
            String querystring = null;

                querystring = "DELETE FROM cartproducts WHERE student_id = ?";

            PreparedStatement stat = conn.prepareStatement(querystring);
            stat.setInt(1, studentid);
            stat.execute();

          
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

}
