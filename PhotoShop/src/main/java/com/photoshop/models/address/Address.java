/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshop.models.address;

import java.io.Serializable;

/**
 *
 * @author bart
 */
public class Address implements Serializable {
    private String klantnaam;
    private String adres;
    private String postcode;
    private String woonplaats;
    private String telefoonnummer;
    private int id;
    
    
    public Address(String klantnaam, String adres, String postcode, String woonplaats, String telefoonnummer) {
        this.klantnaam = klantnaam;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.telefoonnummer = telefoonnummer;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    

    public String getKlantnaam() {
        return klantnaam;
    }

    public String getAdres() {
        return adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }
    
    
    
}
