/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations.reservationcuponentry;

import com.swevolution.cucurumbe.business.entityfacades.AdendumFacade;
import com.swevolution.cucurumbe.business.entityfacades.ReservationsCuponEntryFacade;
import com.swevolution.cucurumbe.controllers.reservations.ReservationEditController;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Adendum;
import com.swevolution.cucurumbe.model.entities.ReservationCuponEntry;
import com.swevolution.cucurumbe.model.entities.Tour;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class ReservationCuponEntryController implements Serializable {

    @Inject
    private ReservationEditController reservationEditController;
    @EJB
    private AdendumFacade aFacade;
    @EJB
    private ReservationsCuponEntryFacade rcFacade;
    private List<ReservationCuponEntry> reservationCupons;
    private Tour servicioCuponeado;
    private String promo;
    private boolean transportationIncluded;
    private String vehicleType;
    private boolean buceo;

    //@PostConstruct
    private void init() {
        reservationCupons = rcFacade.findByReservation(reservationEditController.getReservacion().getId());
        if (reservationCupons == null) {
            reservationCupons = new ArrayList<>();
        }
    }

    public void deleteCupon(ReservationCuponEntry cupon) {
        try {
            reservationCupons.remove(cupon);
            rcFacade.remove(cupon);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void editCupon(RowEditEvent event) {
        try {
            ReservationCuponEntry cupon = (ReservationCuponEntry) event.getObject();
            rcFacade.edit(cupon);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void addCupon() {
        try {
            ReservationCuponEntry cupon = new ReservationCuponEntry();
            Adendum adendum = aFacade.find(
                    reservationEditController.getReservacion().getAgencia(),
                    reservationEditController.getReservacion().getHotel().getOperation(),
                    servicioCuponeado,
                    promo,
                    transportationIncluded,
                    vehicleType,
                    buceo);
            if (adendum != null) {
                cupon.copyAdendumInfo(adendum, reservationEditController.getReservacion());
            } else {
                cupon.resetCupon(reservationEditController.getReservacion(), servicioCuponeado, promo, transportationIncluded, vehicleType);
            }
            rcFacade.create(cupon);
            reservationCupons.add(cupon);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }

    }

    public List<ReservationCuponEntry> getReservationCupons() {
        return reservationCupons;
    }

    public void setReservationCupons(List<ReservationCuponEntry> reservationCupons) {
        this.reservationCupons = reservationCupons;
    }

    public Tour getServicioCuponeado() {
        return servicioCuponeado;
    }

    public void setServicioCuponeado(Tour servicioCuponeado) {
        this.servicioCuponeado = servicioCuponeado;
    }

    public boolean isTransportationIncluded() {
        return transportationIncluded;
    }

    public void setTransportationIncluded(boolean transportationIncluded) {
        this.transportationIncluded = transportationIncluded;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public boolean isBuceo() {
        return buceo;
    }

    public void setBuceo(boolean buceo) {
        this.buceo = buceo;
    }

}
