/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.turno;

import com.swevolution.cucurumbe.business.entityfacades.HorarioTurnoTourFacade;
import com.swevolution.cucurumbe.controllers.hotel.HotelEditController;
import com.swevolution.cucurumbe.model.entities.HorarioTurnoTour;
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
public class HorariosTourHotelController implements Serializable {

    @EJB
    private HorarioTurnoTourFacade httFacade;
    @Inject
    private HotelEditController hotelEditController;
    private List<HorarioTurnoTour> horarios;

    @PostConstruct
    private void init() {
        horarios = httFacade.findByHotel(hotelEditController.getHotel());
    }

    public List<HorarioTurnoTour> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioTurnoTour> horarios) {
        this.horarios = horarios;
    }

}
