/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.turno;

import com.swevolution.cucurumbe.business.entityfacades.TurnoTourFacade;
import com.swevolution.cucurumbe.model.entities.TurnoTour;
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
public class TurnoSearchController implements Serializable {

    @EJB
    private TurnoTourFacade ttFacade;

    public List<TurnoTour> searchQuery(String query) {
        return ttFacade.findByTourName(query);
    }
}
