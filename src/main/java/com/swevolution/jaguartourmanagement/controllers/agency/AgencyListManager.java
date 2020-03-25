/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.agency;

import com.swevolution.jaguartourmanagement.business.entityfacades.AgencyFacade;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.Agency;
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
public class AgencyListManager implements Serializable {

    @EJB
    private AgencyFacade agencyController;
    private List<Agency> agencies;
    private int limit = 10;
    ;
    private String name = "";

    public void search() {
        agencies = agencyController.getByName(name, limit, 0);
        JsfUtil.addSuccessMessage("Agencias encontradas: " + agencies.size());
    }

    public AgencyListManager() {
    }

    public List<Agency> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

    public AgencyFacade getAgencyController() {
        return agencyController;
    }

    public void setAgencyController(AgencyFacade agencyController) {
        this.agencyController = agencyController;
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

    public void selectAgency(Agency agency) {
        PrimeFaces.current().dialog().closeDynamic(agency);
    }

    public List<Agency> filterAgency(String query) {
        return agencyController.getByName(query, 10, 0, true);
    }

}
