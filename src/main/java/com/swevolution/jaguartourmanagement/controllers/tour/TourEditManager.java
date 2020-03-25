/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.tour;

import com.swevolution.jaguartourmanagement.business.entityfacades.AgencyFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TourPhotoFacade;
import com.swevolution.jaguartourmanagement.controllers.photo.PhotoFileUploadController;
import com.swevolution.jaguartourmanagement.controllers.photo.PhotoManagerUtil;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.TourPhoto;
import com.swevolution.jsf.webutils.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class TourEditManager implements Serializable {

    @Inject
    private PhotoFileUploadController photoController;
    @EJB
    private TourPhotoFacade tourPhotoFacade;
    @EJB
    private PhotoManagerUtil photoManager;
    @EJB
    private TourPhotoFacade pFacade;
    private List<TourPhoto> photos;
    @EJB
    private TourFacade tourController;
    @EJB
    private AgencyFacade agencyFacade;

    private Tour tour;

    private Long id;

    private BigDecimal precioAdultoUSD;

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public TourEditManager() {
    }

    public void edit() {
        try {
            tourController.edit(tour);
            tour = null;
            tour = tourController.find(id);
            crearPhotos();
            photos = pFacade.findByTour(tour.getId());
            JsfUtil.addInfoMessage("Successfully updated tour information.");
        } catch (EJBException e) {
            if (e.getCause() instanceof OptimisticLockException) {
                JsfUtil.addErrorMessage(e.getLocalizedMessage());
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e.getLocalizedMessage());
        }
    }

    public String remove() {
        try {
            tourController.remove(tour);
            return "index?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Unable to delete tour");
            return null;
        }

    }

    @PostConstruct
    private void init() {
        id = Long.parseLong(JsfUtil.getRequestParameter("id"));
        tour = tourController.find(id);
        photos = pFacade.findByTour(tour.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecioAdultoUSD() {
        return precioAdultoUSD;
    }

    public void setPrecioAdultoUSD(BigDecimal precioAdultoUSD) {
        this.precioAdultoUSD = precioAdultoUSD;
    }

    public List<TourPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<TourPhoto> photos) {
        this.photos = photos;
    }

    public void removePhoto(TourPhoto photo) {
        photos.remove(photo);
        pFacade.remove(photo);
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
