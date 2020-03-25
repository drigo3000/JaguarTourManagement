/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations;

import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsFacade;
import com.swevolution.jaguartourmanagement.business.session.SessionInfo;
import com.swevolution.jsf.webutils.JsfUtil;
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
@ViewScoped
@Named
public class ReservationFilterController implements Serializable {

    @Inject
    private ReservationFilterOptionsController rfoController;
    @Inject
    private ReservationTotalsController totalsController;
    @EJB
    private SessionInfo session;

    @EJB
    private ReservationsFacade rFacade;

    private List<Reservation> reservations;

    public void search() {
        String query = rfoController.createQueryString("SELECT r");
        reservations = rFacade.find(query, rfoController.getFrom(),
                rfoController.getTo(), rfoController.getCupon(), rfoController.getClaveDeConfirma(),
                rfoController.getServicio(), rfoController.getGrupo(), rfoController.getAgencia(),
                rfoController.getRep(), rfoController.getHotel(),
                rfoController.getReservo(), rfoController.isDateOperated());
        totalsController.getPaxInformation(reservations);
        JsfUtil.addSuccessMessage("Búsqueda completada<br/>Total de registros: " + reservations.size());
    }

    @PostConstruct
    private void init() {
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

}
