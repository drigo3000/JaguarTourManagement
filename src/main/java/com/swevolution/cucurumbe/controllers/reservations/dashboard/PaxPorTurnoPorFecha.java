/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations.dashboard;

import com.swevolution.cucurumbe.business.entityfacades.TurnoTourFacade;
import com.swevolution.cucurumbe.controllers.reservations.ReservationsUtilityController;
import com.swevolution.cucurumbe.model.entities.TurnoTour;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
public class PaxPorTurnoPorFecha implements Serializable {

    @Inject
    private ReservationsUtilityController resUtilities;
    private List<PaxPorTurno> paxPorTurnos;
    private List<TurnoTour> turnos;
    private LocalDate date;
    @EJB
    private TurnoTourFacade ttFacade;

    public void search() {
        paxPorTurnos = new ArrayList<>();
        turnos = ttFacade.findAllByTourName();
        desplegarPaxTurnos();
    }

    private void desplegarPaxTurnos() {
        if (date != null) {
            for (TurnoTour t : turnos) {
                PaxPorTurno pax = new PaxPorTurno();
                pax.setPax(String.valueOf(resUtilities.getNumeroPorTurno(date, date, t)));
                pax.setDesglocePax(resUtilities.getStringPaxPorTurno(date, date, t));
                pax.setTurno(t);
                paxPorTurnos.add(pax);
            }
        }

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<PaxPorTurno> getPaxPorTurnos() {
        return paxPorTurnos;
    }

    public void setPaxPorTurnos(List<PaxPorTurno> paxPorTurnos) {
        this.paxPorTurnos = paxPorTurnos;
    }

    public List<TurnoTour> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<TurnoTour> turnos) {
        this.turnos = turnos;
    }

}
