/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.order;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Willem
 */
public class Order {
    private int id;
    private int student_id;
    private Timestamp datum;
    private OrderEnum status;
    
    private final OrderDao dao;
    
    public Order() {
        this.dao = new OrderDao();
    }
    
    public Order(OrderDao dao) {
        this.dao = dao;
    }
    
    public void save() {
        this.dao.save(this);
    }
    
    public List<Order> getOrders() {
        return this.dao.getList();
    }

    public int getId() {return id;}

    public int getStudent_id() {return student_id;}

    public Timestamp getDatum() {return datum;}
    
    public String getDatumAsString() {
        return datum.getDay() + "-" + datum.getMonth() + "-" + (1900 + datum.getYear());
    }

    public OrderEnum getStatus() {return status;}

    public void setId(int id) {this.id = id;}

    public void setStudent_id(int student_id) {this.student_id = student_id;}

    public void setDatum(Timestamp datum) {this.datum = datum;}

    public void setStatus(OrderEnum status) {this.status = status;}   
    
}
