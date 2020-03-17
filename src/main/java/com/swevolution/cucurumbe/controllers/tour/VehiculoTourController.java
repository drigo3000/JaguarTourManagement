/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.tour;

import com.swevolution.cucurumbe.business.entityfacades.VehiculoTourFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.VehiculoTour;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class VehiculoTourController implements Serializable {

    @Inject
    private TourEditManager tourEditManager;
    @EJB
    private VehiculoTourFacade vtFacade;
    private List<VehiculoTour> tiposVehiculo;
    private VehiculoTour vehiculoTour;

    public void create() {
        try {
            if (valid(vehiculoTour.getName())) {
                vtFacade.create(vehiculoTour);
                JsfUtil.addSuccessMessage("Exito");
                tiposVehiculo.add(vehiculoTour);
                resetEntity();
            } else {
                JsfUtil.addErrorMessage("Duplicado");
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    @PostConstruct
    private void init() {
        resetEntity();
        tiposVehiculo = vtFacade.findByTour(tourEditManager.getTour());
    }

    public List<VehiculoTour> getTiposVehiculo() {
        return tiposVehiculo;
    }

    public void setTiposVehiculo(List<VehiculoTour> tiposVehiculo) {
        this.tiposVehiculo = tiposVehiculo;
    }

    public VehiculoTour getVehiculoTour() {
        return vehiculoTour;
    }

    public void setVehiculoTour(VehiculoTour vehiculoTour) {
        this.vehiculoTour = vehiculoTour;
    }

    private void resetEntity() {
        vehiculoTour = new VehiculoTour();
        vehiculoTour.setActive(true);
        vehiculoTour.setDescription("");
        vehiculoTour.setName("");
        vehiculoTour.setTour(tourEditManager.getTour());
    }

    private boolean valid(String name) {
        boolean valid = true;
        for (VehiculoTour v : tiposVehiculo) {
            if (v.getName().equals(name)) {
                valid = false;
            }
        }
        return valid;
    }

}
