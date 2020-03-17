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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "HORARIOTURNOTOUR", catalog = "CUCURUMBE")
@XmlRootElement
public class HorarioTurnoTour extends PK_Long_Entity implements Serializable {

    @NotNull
    @ManyToOne
    private TurnoTour turno;
    @NotNull
    @ManyToOne
    private Hotel hotel;
    private String pickup;
    private String notes;
    private String nuevoPickup;

    public HorarioTurnoTour() {
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public TurnoTour getTurno() {
        return turno;
    }

    public void setTurno(TurnoTour turno) {
        this.turno = turno;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getNuevoPickup() {
        return nuevoPickup;
    }

    public void setNuevoPickup(String nuevoPickup) {
        this.nuevoPickup = nuevoPickup;
    }

}
