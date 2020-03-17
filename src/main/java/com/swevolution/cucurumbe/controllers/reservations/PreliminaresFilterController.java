/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations;

import com.swevolution.cucurumbe.business.entityfacades.AgencyFacade;
import com.swevolution.cucurumbe.business.entityfacades.HotelFacade;
import com.swevolution.cucurumbe.business.entityfacades.RepresentativeFacade;
import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.business.entityfacades.TourFacade;
import com.swevolution.cucurumbe.business.entityfacades.UserFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Reservation;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class PreliminaresFilterController implements Serializable {

    @Inject
    private ReservationFilterOptionsController rfoController;
    @Inject
    private ReservationTotalsController totalsController;
    @EJB
    private AgencyFacade aFacade;
    @EJB
    private UserFacade uFacade;
    @EJB
    private TourFacade tFacade;
    @EJB
    private HotelFacade hFacade;
    @EJB
    private RepresentativeFacade repFacade;
    @EJB
    private ReservationsFacade rFacade;

    private List<Reservation> reservations;

    public void search() {
        LocalDateTime today = JsfUtil.getCancunNow();
        LocalDateTime from = null;
        LocalDateTime to = null;
        switch (rfoController.getView()) {
            case 1: {
                from = null;
                to = LocalDateTime.of(today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        12, 0, 0);
                break;
            }
            case 2: {
                from = LocalDateTime.of(today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        12, 0, 1);
                to = LocalDateTime.of(today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        15, 0, 0);
                break;
            }
            case 3: {
                from = LocalDateTime.of(today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        15, 0, 1);
                to = LocalDateTime.of(today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        17, 0, 0);
                break;
            }
            case 4: {
                from = LocalDateTime.of(today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        17, 0, 1);
                to = LocalDateTime.of(today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        20, 0, 0);
                break;
            }
            case 5: {
                from = LocalDateTime.of(today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        20, 0, 1);
                to = LocalDateTime.of(today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        23, 59, 59);
                break;
            }
        }

        LocalDate operationDate = JsfUtil.getCancunDate().plusDays(1);
        String query = rfoController.createPreliminaresQueryString("SELECT r");
        reservations = rFacade.findPreliminar(
                query, operationDate,
                from, to, rfoController.getCupon(), rfoController.getClaveDeConfirma(),
                rfoController.getServicio(), rfoController.getGrupo(), rfoController.getAgencia(),
                rfoController.getRep(), rfoController.getHotel(), rfoController.getReservo(), rfoController.getView());
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
