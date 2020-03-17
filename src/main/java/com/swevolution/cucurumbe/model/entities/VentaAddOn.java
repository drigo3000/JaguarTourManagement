/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.model.entities;

import com.swevolution.cucurumbe.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "VENTA_ADDON", catalog = "CUCURUMBE")
@XmlRootElement
public class VentaAddOn extends PK_Long_Entity implements Serializable {

    @ManyToOne
    private AddOn addOn;
    @ManyToOne
    private User creador;
    @ManyToOne
    private User guia;
    @ManyToOne
    private Reservation reserva;
    private int cantidad;

    public VentaAddOn() {
    }

    public AddOn getAddOn() {
        return addOn;
    }

    public void setAddOn(AddOn addOn) {
        this.addOn = addOn;
    }

    public User getCreador() {
        return creador;
    }

    public void setCreador(User creador) {
        this.creador = creador;
    }

    public User getGuia() {
        return guia;
    }

    public void setGuia(User guia) {
        this.guia = guia;
    }

    public Reservation getReserva() {
        return reserva;
    }

    public void setReserva(Reservation reserva) {
        this.reserva = reserva;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
