/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations;

import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsFacade;
import com.swevolution.jaguartourmanagement.business.session.SessionInfo;
import com.swevolution.jaguartourmanagement.model.entities.Reservation;
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
public class UserReservationsController implements Serializable {

    private List<Reservation> reservas;
    @EJB
    private ReservationsFacade rFacade;
    @EJB
    private SessionInfo session;
    private int limit = 10;
    private int offset = 0;

    @PostConstruct
    private void init() {
        search();
    }

    public List<Reservation> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservation> reservas) {
        this.reservas = reservas;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void search() {
        reservas = rFacade.findByUser(session.getCurrentUser(), limit, offset);
    }

}
