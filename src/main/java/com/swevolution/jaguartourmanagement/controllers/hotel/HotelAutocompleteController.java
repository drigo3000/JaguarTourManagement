/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.hotel;

import com.swevolution.jaguartourmanagement.business.entityfacades.HotelFacade;
import com.swevolution.jaguartourmanagement.model.entities.Hotel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class HotelAutocompleteController implements Serializable {

    @EJB
    private HotelFacade hFacade;
    private List<String> allNames;

    public List<Hotel> filterHotels(String query) {
        return hFacade.getByName("", query, true, 10, 0);
    }

    public List<String> findHotel(String query) {
        List<String> filtered = new ArrayList<>();
        if (allNames == null) {
            allNames = hFacade.getNames();
        }
        for (String name : allNames) {
            if (name.toLowerCase().contains(query.toLowerCase())) {
                filtered.add(name);
            }
        }
        return filtered;
    }
}
