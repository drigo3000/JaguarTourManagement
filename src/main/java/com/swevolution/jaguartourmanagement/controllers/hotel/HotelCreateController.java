/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.hotel;

import com.swevolution.jaguartourmanagement.business.entityfacades.HorarioTurnoTourFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.HotelFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TurnoTourFacade;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.HorarioTurnoTour;
import com.swevolution.jaguartourmanagement.model.entities.Hotel;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class HotelCreateController implements Serializable {

    @EJB
    private HotelFacade hotelController;
    private Hotel hotel;
    @EJB
    private HorarioTurnoTourFacade httFacade;
    @EJB
    private TourFacade tourFacade;
    @EJB
    private TurnoTourFacade ttFacade;

    public void create() {
        try {
            hotelController.create(hotel);
            for (Tour t : tourFacade.findAll()) {
                for (TurnoTour turno : ttFacade.findByTour(t)) {
                    HorarioTurnoTour horario = new HorarioTurnoTour();
                    horario.setActive(true);
                    horario.setHotel(hotel);
                    horario.setNotes("");
                    horario.setPickup("06:00");
                    horario.setTurno(turno);
                    httFacade.create(horario);
                }
            }
            JsfUtil.addInfoMessage("Éxito");
            init();
        } catch (EJBTransactionRolledbackException e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public HotelFacade getHotelController() {
        return hotelController;
    }

    public void setHotelController(HotelFacade hotelController) {
        this.hotelController = hotelController;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @PostConstruct
    private void init() {
        hotel = new Hotel();
        hotel.setActive(true);
        hotel.setOperation("");
    }

}
