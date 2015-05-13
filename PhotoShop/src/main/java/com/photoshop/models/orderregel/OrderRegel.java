/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.orderregel;

import com.photoshop.models.order.OrderDao;

/**
 *
 * @author Willem
 */
public class OrderRegel {
    private int id;
    private int order_id;
    private int product_id;
    private int photographer_id;
    private int photo_id;
    private int aantal;   
    
    private OrderRegelDao dao;
    
    public OrderRegel() {
        this.dao = new OrderRegelDao();
    }
    
    public OrderRegel(OrderRegelDao dao) {
        this.dao = dao;
    }

    public int getId() { return id;}
    public int getOrder_id() { return order_id;}
    public int getProduct_id() { return product_id;}
    public int getPhotographer_id() { return photographer_id;}
    public int getPhoto_id() { return photo_id;}
    public int getAantal() { return aantal;}
    public OrderRegelDao getDao() { return dao;}
    public void setId(int id) { this.id = id;}
    public void setOrder_id(int order_id) { this.order_id = order_id;}
    public void setProduct_id(int product_id) { this.product_id = product_id;}
    public void setPhotographer_id(int photographer_id) { this.photographer_id = photographer_id;}
    public void setPhoto_id(int photo_id) { this.photo_id = photo_id;}
    public void setAantal(int aantal) { this.aantal = aantal;}
    public void setDao(OrderRegelDao dao) { this.dao = dao;}
    
    
    
}
