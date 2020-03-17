/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations;

import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.model.entities.Reservation;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class ReservacionesByNameAutocompleteController implements Serializable {

    @EJB
    private ReservationsFacade rFacade;

    public List<Reservation> filterReservations(String name) {
        return rFacade.findByCustomerName(name);
    }
}
