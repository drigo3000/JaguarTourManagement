/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.addon.ventaaddons;

import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.AddOn;
import com.swevolution.cucurumbe.model.entities.Reservation;
import com.swevolution.cucurumbe.model.entities.User;
import java.io.Serializable;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class VentaAddOnFilterOptionsController implements Serializable {

    private User guia;
    private User creador;
    private LocalDate from;
    private LocalDate to;
    private AddOn addOn;
    private Reservation reserva;

    @PostConstruct
    private void init() {
        from = JsfUtil.getInicioMes();
        to = JsfUtil.getFinMes();
    }

    public User getGuia() {
        return guia;
    }

    public void setGuia(User guia) {
        this.guia = guia;
    }

    public User getCreador() {
        return creador;
    }

    public void setCreador(User creador) {
        this.creador = creador;
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

    public AddOn getAddOn() {
        return addOn;
    }

    public void setAddOn(AddOn addOn) {
        this.addOn = addOn;
    }

    public Reservation getReserva() {
        return reserva;
    }

    public void setReserva(Reservation reserva) {
        this.reserva = reserva;
    }

}
