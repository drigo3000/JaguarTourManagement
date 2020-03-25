/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.hotel;

import com.swevolution.jaguartourmanagement.business.entityfacades.HotelFacade;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.Hotel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class HotelPositionController implements Serializable {

    @EJB
    private HotelFacade hotelFacade;
    private List<Hotel> hotels;
    private String zona;
    private String subZona;

    public void filtrarHotelesPorZona() {
        hotels = hotelFacade.findByPositionInZone(zona);
    }

    public void updatePositions() {
        int pos = 1;
        switch (zona) {
            case "RVM":
                break;
            case "CUN":
                pos = pos + 1000;
                break;
            case "CZM":
                pos = pos + 10000;
                break;
        }

        for (Hotel h : hotels) {
            h.setPosition(pos);
            hotelFacade.edit(h);
            pos++;
        }
        JsfUtil.addInfoMessage("Se actualizaron las posiciones");
    }

    @PostConstruct
    private void init() {
        hotels = new ArrayList<>();
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSubZona() {
        return subZona;
    }

    public void setSubZona(String subZona) {
        this.subZona = subZona;
    }

}
