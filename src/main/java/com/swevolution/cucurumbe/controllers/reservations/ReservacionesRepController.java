/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations;

import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.controllers.representative.RepresentativeEditController;
import com.swevolution.cucurumbe.model.entities.Reservation;
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
public class ReservacionesRepController implements Serializable {

    @Inject
    private RepresentativeEditController repEditController;
    @EJB
    private ReservationsFacade resFacade;
    private List<Reservation> reservas;
    private int limit = 200;
    private int offset = 0;

    @PostConstruct
    private void init() {
        reservas = resFacade.findByRep(repEditController.getRepresentante(), limit, offset);
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

}
