/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.tour;

import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class TourListManager implements Serializable {

    @EJB
    private TourFacade tourFacade;
    private List<Tour> tours;
    private int limit = 10000;
    ;
    private String name = "";

    public void search() {
        tours = tourFacade.getByName(name, limit, 0);
        JsfUtil.addSuccessMessage("Tours encontrados: " + tours.size());
    }

    public TourListManager() {
    }

    public List<Tour> getAgencies() {
        return tours;
    }

    public void setAgencies(List<Tour> tours) {
        this.tours = tours;
    }

    public TourFacade getTourController() {
        return tourFacade;
    }

    public void setTourController(TourFacade agencyController) {
        this.tourFacade = agencyController;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void selectTour(Tour tour) {
        PrimeFaces.current().dialog().closeDynamic(tour);
    }

    public List<Tour> filterTour(String query) {
        return tourFacade.getByName(query, 10, 0, true);
    }

}
