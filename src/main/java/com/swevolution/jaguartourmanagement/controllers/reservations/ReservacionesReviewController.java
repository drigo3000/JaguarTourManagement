/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations;

import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsFacade;
import com.swevolution.jaguartourmanagement.model.entities.Reservation;
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
public class ReservacionesReviewController implements Serializable {

    @Inject
    ReservationTotalsController totalsController;
    @EJB
    private ReservationsFacade rFacade;
    private List<Reservation> reservaciones;

    @PostConstruct
    private void init() {
        reservaciones = rFacade.getForReview();
        totalsController.getPaxInformation(reservaciones);
    }

    public List<Reservation> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reservation> reservaciones) {
        this.reservaciones = reservaciones;
    }

}
