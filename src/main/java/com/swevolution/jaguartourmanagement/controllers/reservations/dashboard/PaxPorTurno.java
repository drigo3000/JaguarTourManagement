/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations.dashboard;

import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;

/**
 *
 * @author Rxkx
 */
public class PaxPorTurno {

    private String pax;
    private TurnoTour turno;
    private String desglocePax;

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public TurnoTour getTurno() {
        return turno;
    }

    public void setTurno(TurnoTour turno) {
        this.turno = turno;
    }

    public String getDesglocePax() {
        return desglocePax;
    }

    public void setDesglocePax(String desglocePax) {
        this.desglocePax = desglocePax;
    }

}
