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
public class HotelZonasEditController implements Serializable {

    @EJB
    private HotelFacade hFacade;
    private List<Hotel> hoteles;
    private List<Hotel> selectedHotels;
    private String zona;
    private String subzona;
    private String sububicacion;

    private String zonaEdit;
    private String subzonaEdit;
    private String sububicacionEdit;

    public void editHotels() {
        try {
            for (Hotel h : selectedHotels) {
                h.setOperation(zonaEdit);
                h.setSubOperation(subzonaEdit);
                h.setSubLocation(sububicacionEdit);
                hFacade.edit(h);
            }
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    @PostConstruct
    private void init() {
        zonaEdit = "";
        subzona = "";
        sububicacion = "";
        subzonaEdit = "";
        sububicacionEdit = "";
    }

    public void search() {
        try {
            hoteles = hFacade.findByPositionInZone(zona, subzona, sububicacion);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }

    }

    public List<Hotel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(List<Hotel> hoteles) {
        this.hoteles = hoteles;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSubzona() {
        return subzona;
    }

    public void setSubzona(String subzona) {
        this.subzona = subzona;
    }

    public String getSububicacion() {
        return sububicacion;
    }

    public void setSububicacion(String sububicacion) {
        this.sububicacion = sububicacion;
    }

    public List<Hotel> getSelectedHotels() {
        return selectedHotels;
    }

    public void setSelectedHotels(List<Hotel> selectedHotels) {
        this.selectedHotels = selectedHotels;
    }

    public String getZonaEdit() {
        return zonaEdit;
    }

    public void setZonaEdit(String zonaEdit) {
        this.zonaEdit = zonaEdit;
    }

    public String getSubzonaEdit() {
        return subzonaEdit;
    }

    public void setSubzonaEdit(String subzonaEdit) {
        this.subzonaEdit = subzonaEdit;
    }

    public String getSububicacionEdit() {
        return sububicacionEdit;
    }

    public void setSububicacionEdit(String sububicacionEdit) {
        this.sububicacionEdit = sububicacionEdit;
    }

}
