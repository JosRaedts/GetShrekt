/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.orderrow;

import com.photoshop.models.order.OrderDao;
import com.photoshop.models.product.Product;
import com.photoshop.models.product.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Willem
 */
@Component
public class OrderRow {
    private int id;
    private int order_id;
    private int product_id;
    private int photographer_id;
    private int photo_id;
    private int aantal;   
    
    @Autowired
    private OrderRowDao dao;
    
    @Autowired
    private ProductDao productdao;
    
    public OrderRow() {
        this.dao = new OrderRowDao();
    }
    
    public OrderRow(OrderRowDao dao) {
        this.dao = dao;
    }

    public int getId() { return id;}
    public int getOrder_id() { return order_id;}
    public int getProduct_id() { return product_id;}
    public int getPhotographer_id() { return photographer_id;}
    public int getPhoto_id() { return photo_id;}
    public int getAantal() { return aantal;}
    public Product getProduct() { return productdao.getById(this.product_id); }
    
    public void setId(int id) { this.id = id;}
    public void setOrder_id(int order_id) { this.order_id = order_id;}
    public void setProduct_id(int product_id) { this.product_id = product_id;}
    public void setPhotographer_id(int photographer_id) { this.photographer_id = photographer_id;}
    public void setPhoto_id(int photo_id) { this.photo_id = photo_id;}
    public void setAantal(int aantal) { this.aantal = aantal;}
    
    
    
}
