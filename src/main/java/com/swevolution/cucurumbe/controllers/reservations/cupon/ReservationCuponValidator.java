/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations.cupon;

import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.controllers.reservations.ReservationCreateController;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Agency;
import com.swevolution.cucurumbe.model.entities.Reservation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class ReservationCuponValidator implements Serializable {

    @EJB
    private ReservationsFacade rFacade;
    @Inject
    private ReservationCreateController rCreate;
    private List<Reservation> reservasConCupon;

    public void validateCupon(ValueChangeEvent e) {
        Agency a = rCreate.getReservacion().getAgencia();
        if (a == null) {
            reservasConCupon = new ArrayList<>();
        } else {
            String c = e.getNewValue().toString();
            reservasConCupon = rFacade.findDuplicateCupon(c, a);
            if (reservasConCupon != null && !reservasConCupon.isEmpty()) {
                JsfUtil.addErrorMessage("Error", "Cupon duplicado en sistema");
            }
        }
    }

    public List<Reservation> getReservasConCupon() {
        return reservasConCupon;
    }

    public void setReservasConCupon(List<Reservation> reservasConCupon) {
        this.reservasConCupon = reservasConCupon;
    }

}
