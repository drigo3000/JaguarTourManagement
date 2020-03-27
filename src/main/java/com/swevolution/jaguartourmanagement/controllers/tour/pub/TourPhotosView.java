/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.tour.pub;

import com.swevolution.jaguartourmanagement.business.entityfacades.TourPhotoFacade;
import com.swevolution.jaguartourmanagement.model.entities.TourPhoto;
import com.swevolution.jsf.webutils.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class TourPhotosView implements Serializable {

    @EJB
    private TourPhotoFacade tourPhotoFacade;
    private List<TourPhoto> photos;
    private final long id = Long.parseLong(JsfUtil.getRequestParameter("id"));

    public List<TourPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<TourPhoto> photos) {
        this.photos = photos;
    }

    public long getId() {
        return id;
    }

    public TourPhotosView() {
    }

    @PostConstruct
    private void init() {
        photos = tourPhotoFacade.findByTour(id);
    }
}
