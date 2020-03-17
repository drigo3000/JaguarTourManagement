/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.agency;

import com.swevolution.cucurumbe.business.adendum.AdendumUtil;
import com.swevolution.cucurumbe.business.entityfacades.AgencyFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Agency;
import java.io.Serializable;
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
public class AgencyCreateManager implements Serializable {

    @EJB
    private AgencyFacade agencyController;
    private Agency agency;
    @Inject
    private AdendumUtil adendumUtils;

    public void create() {
        try {
            agencyController.create(agency);
            JsfUtil.addInfoMessage("Éxito");
            //adendumUtils.crearAdendumsAgencia(agency);
            init();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public AgencyFacade getAgencyController() {
        return agencyController;
    }

    public void setAgencyController(AgencyFacade agencyController) {
        this.agencyController = agencyController;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @PostConstruct
    private void init() {
        agency = new Agency();
        agency.setActive(true);
        agency.setDescription("");
        agency.setName("");
    }
}
