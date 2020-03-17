/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations;

import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Reservation;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class ReservacionesEfectivasController implements Serializable {

    @Inject
    private ReservationTotalsController totalsController;
    @EJB
    private ReservationsFacade rFacade;
    private LocalDate from;
    private LocalDate to;
    private List<Reservation> reservas;

    public void search() {
        try {
            reservas = rFacade.findEfectivas(from, to);
            totalsController.getPaxInformation(reservas);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }

    }

    @PostConstruct
    private void init() {
        from = JsfUtil.getCancunDate();
        to = JsfUtil.getCancunDate();
        reservas = new ArrayList<>();
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public List<Reservation> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservation> reservas) {
        this.reservas = reservas;
    }

}
