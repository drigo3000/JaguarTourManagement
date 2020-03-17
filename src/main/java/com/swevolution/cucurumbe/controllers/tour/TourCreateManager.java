/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.tour;

import com.swevolution.cucurumbe.business.adendum.AdendumUtil;
import com.swevolution.cucurumbe.business.entityfacades.TourFacade;
import com.swevolution.cucurumbe.business.entityfacades.TurnoTourFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Tour;
import com.swevolution.cucurumbe.model.entities.TurnoTour;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
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
public class TourCreateManager implements Serializable {

    @EJB
    private TourFacade tourController;
    @EJB
    private TurnoTourFacade turnoTourFacade;
    private Tour tour;
    @Inject
    private AdendumUtil adendumUtils;

    public void create() {
        try {
            tourController.create(tour);
            //adendumUtils.crearAdendumsTour(tour);
            TurnoTour turno = new TurnoTour();
            turno.setActive(true);
            turno.setDescription("Primer Turno");
            turno.setTour(tour);
            turno.setName("Primer Turno");
            turnoTourFacade.create(turno);
            JsfUtil.addInfoMessage("Éxito");
            init();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public TourFacade getTourController() {
        return tourController;
    }

    public void setTourController(TourFacade tourController) {
        this.tourController = tourController;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @PostConstruct
    private void init() {
        tour = new Tour();
        tour.setActive(true);
        tour.setDescription("");
        tour.setName("");
        tour.setGrupo("");
        tour.setImaAdultoCZM(BigDecimal.ZERO);
        tour.setImaAdultoMXN(BigDecimal.ZERO);
        tour.setImaAdultoUSD(BigDecimal.ZERO);
        tour.setImaNinoCZM(BigDecimal.ZERO);
        tour.setImaNinoMXN(BigDecimal.ZERO);
        tour.setImaNinoUSD(BigDecimal.ZERO);
        tour.setManejaVehiculo(false);
        tour.setCostoUnitarioAdulto(BigDecimal.ZERO);
        tour.setCostoUnitarioAdultoCZM(BigDecimal.ZERO);
        tour.setCostoUnitarioNino(BigDecimal.ZERO);
        tour.setCostoUnitarioNinoCZM(BigDecimal.ZERO);
    }

}
