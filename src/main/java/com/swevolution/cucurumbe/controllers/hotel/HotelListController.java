/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.hotel;

import com.swevolution.cucurumbe.business.entityfacades.HotelFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Hotel;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class HotelListController implements Serializable {

    @EJB
    private HotelFacade hFacade;
    private List<Hotel> hotels;
    private int limit = 10;
    private int offset = 0;
    private String name = "";
    private String operation = "";

    public void search() {
        hotels = hFacade.getByName(operation, name, true, limit, offset);
        JsfUtil.addSuccessMessage("Hoteles encontrados: " + hotels.size());
    }

    public HotelListController() {
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void selectHotel(Hotel hotel) {
        PrimeFaces.current().dialog().closeDynamic(hotel);
    }

    public List<Hotel> filterHotels(String query) {
        return hFacade.getByName("", query, true, 10, 0);
    }

}
