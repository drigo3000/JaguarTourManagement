/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.tour;

import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TourPhotoFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TurnoTourFacade;
import com.swevolution.jaguartourmanagement.controllers.photo.PhotoFileUploadController;
import com.swevolution.jaguartourmanagement.controllers.photo.PhotoManagerUtil;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.TourPhoto;
import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;
import com.swevolution.jsf.webutils.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class TourCreateManager implements Serializable {

    @EJB
    private TourPhotoFacade tourPhotoFacade;
    @EJB
    private PhotoManagerUtil photoManager;
    @EJB
    private TourFacade tourController;
    @Inject
    private PhotoFileUploadController photoController;
    @EJB
    private TurnoTourFacade turnoTourFacade;
    private Tour tour;

    @Inject

    public void create() {
        try {
            tourController.create(tour);
            crearTurnoBase();
            crearPhotos();
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
        tour.setImaAdultoMXN(BigDecimal.ZERO);
        tour.setImaAdultoUSD(BigDecimal.ZERO);
        tour.setImaNinoMXN(BigDecimal.ZERO);
        tour.setImaNinoUSD(BigDecimal.ZERO);
        tour.setManejaVehiculo(false);
    }

    private void crearTurnoBase() {
        TurnoTour turno = new TurnoTour();
        turno.setActive(true);
        turno.setDescription("Primer Turno");
        turno.setTour(tour);
        turno.setName("Primer Turno");
        turnoTourFacade.create(turno);
    }

    private void crearPhotos() {
        try {
            for (UploadedFile file : photoController.getUploadedFiles()) {
                TourPhoto photo = new TourPhoto();
                String fileName = UUID.randomUUID().toString();
                photoManager.uploadPhotoList(file.getInputstream(), fileName + ".jpg",
                        photoManager.getSaveLocationFolder("tours", ""), tour.getId() + "");
                photo.setLink(fileName);
                photo.setTour(tour);
                photo.setDescription(file.getFileName());
                tourPhotoFacade.create(photo);
            }
            photoController.reset();
        } catch (IOException e) {
        }

    }

}
