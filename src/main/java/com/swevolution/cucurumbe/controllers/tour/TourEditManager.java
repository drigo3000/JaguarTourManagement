/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.tour;

import com.swevolution.cucurumbe.business.entityfacades.AgencyFacade;
import com.swevolution.cucurumbe.business.entityfacades.TourFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Tour;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class TourEditManager implements Serializable {

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

}
