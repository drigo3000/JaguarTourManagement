package com.swevolution.jaguartourmanagement.model.entities.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Rxkx
 */
@Embeddable
public class Address implements Serializable {
    @Column(name="STREET", length = 255, nullable = true)
    private String street;
    @Column(name="CITY", length = 255, nullable = true)
    private String city;
    @Column(name="ZIP_CODE", length = 255, nullable = true)
    private String zipCode;
    @Column(name="COUNTRY", length = 255, nullable = true)
    private String country;

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
