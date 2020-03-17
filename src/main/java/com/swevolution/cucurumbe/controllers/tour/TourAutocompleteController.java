/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.tour;

import com.swevolution.cucurumbe.business.entityfacades.TourFacade;
import com.swevolution.cucurumbe.model.entities.Tour;
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
public class TourAutocompleteController implements Serializable {

    @EJB
    private TourFacade tourFacade;

    public List<Tour> filterTour(String query) {
        return tourFacade.getByName(query, 10, 0);
    }

    public List<String> getGrupos(String query) {
        return tourFacade.getGrupos(query, 10, 0);
    }
}
