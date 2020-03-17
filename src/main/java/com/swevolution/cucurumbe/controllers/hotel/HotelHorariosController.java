/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.hotel;

import com.swevolution.cucurumbe.business.entityfacades.HorarioTurnoTourFacade;
import com.swevolution.cucurumbe.model.entities.HorarioTurnoTour;
import com.swevolution.cucurumbe.model.entities.Hotel;
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
public class HotelHorariosController implements Serializable {

    private Hotel hotel;

    @EJB
    private HorarioTurnoTourFacade httFacade;

    public void search() {
        if (hotel != null) {
            horarios = httFacade.findByHotel(hotel);
        } else {
            horarios = new ArrayList<>();
        }

    }

    private List<HorarioTurnoTour> horarios;

    public List<HorarioTurnoTour> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioTurnoTour> horarios) {
        this.horarios = horarios;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}
