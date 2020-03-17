/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.representative;

import com.swevolution.cucurumbe.business.entityfacades.RepresentativeFacade;
import com.swevolution.cucurumbe.controllers.agency.AgencyEditController;
import com.swevolution.cucurumbe.model.entities.Representante;
import java.io.Serializable;
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
@Named
@ViewScoped
public class RepresentativesByAgencyController implements Serializable {

    @EJB
    private RepresentativeFacade repFacade;
    private List<Representante> reps;
    @Inject
    private AgencyEditController agencyController;

    @PostConstruct
    private void init() {
        reps = repFacade.getByName("", agencyController.getAgency(), 1000, 0);
    }

    public List<Representante> getReps() {
        return reps;
    }

    public void setReps(List<Representante> reps) {
        this.reps = reps;
    }

}
