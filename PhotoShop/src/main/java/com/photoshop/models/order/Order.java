/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.order;

import com.photoshop.models.address.Address;
import com.photoshop.models.orderrow.OrderRow;
import com.photoshop.models.orderrow.OrderRowDao;
import com.photoshop.models.student.Student;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Willem
 */
@Component
public class Order {

    private int id;
    private Student student;
    private Timestamp datum;
    private OrderEnum status;
    private Address shippingaddress;
    private Address invoiceaddress;
    private String factuur;
    private String indexkaart;

    private List<OrderRow> orderrows;

    @Autowired
    private OrderDao dao;

    @Autowired
    private OrderRowDao orderrowDao;

    public Order() {
    }

    public void save() {
        if(this.dao == null)
        {
            this.dao = new OrderDao();
        }
        this.dao.save(this);
    }

    public List<Order> getOrders() {
        return this.dao.getList();
    }

    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Timestamp getDatum() {
        return datum;
    }

    public String getDatumAsString() {
        String newstring = new SimpleDateFormat("dd-MM-yyyy").format(this.datum);
        return newstring;
    }

    public List<OrderRow> getOrderRows() {
        if (orderrowDao == null) {
            orderrowDao = new OrderRowDao();
        }
        return orderrowDao.getOrderRowByOrderNr(id);
    }

    public OrderEnum getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    public void setStatus(OrderEnum status) {
        this.status = status;
    }

    public void setShippingaddress(Address shippingaddress) {
        this.shippingaddress = shippingaddress;
    }

    public void setInvoiceaddress(Address invoiceaddress) {
        this.invoiceaddress = invoiceaddress;
    }

    public Address getShippingaddress() {
        return shippingaddress;
    }

    public Address getInvoiceaddress() {
        return invoiceaddress;
    }

    public String getFactuur() {
        return factuur;
    }

    public String getIndexkaart() {
        return indexkaart;
    }

    public void setFactuur(String factuur) {
        this.factuur = factuur;
    }

    public void setIndexkaart(String indexkaart) {
        this.indexkaart = indexkaart;
    }

}
