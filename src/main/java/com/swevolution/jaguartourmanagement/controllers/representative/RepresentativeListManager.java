/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.representative;

import com.swevolution.jaguartourmanagement.business.entityfacades.AgencyFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.RepresentativeFacade;
import com.swevolution.jaguartourmanagement.model.entities.Agency;
import com.swevolution.jaguartourmanagement.model.entities.Representante;
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
public class RepresentativeListManager implements Serializable {

    @EJB
    private AgencyFacade aFacade;
    @EJB
    private RepresentativeFacade repController;
    private List<Representante> reps;
    private int limit = 10;
    private String name = "";
    private Agency agencia;

    public void search() {
        reps = repController.getByName(name, limit, 0);
    }

    public void searchWithAgency() {
        reps = repController.getByName(name, agencia, limit, 0);

    }

    public RepresentativeListManager() {
    }

    public List<Representante> getAgencies() {
        return reps;
    }

    public void setAgencies(List<Representante> reps) {
        this.reps = reps;
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

    public void selectRepresentante(Representante rep) {
        PrimeFaces.current().dialog().closeDynamic(rep);
    }

    public List<Representante> filterRepresentative(String query) {
        return repController.getByName(query, 10, 0, true);
    }

    public Agency getAgencia() {
        return agencia;
    }

    public void setAgencia(Agency agencia) {
        this.agencia = agencia;
    }

    public List filterAgencies(String query) {
        return aFacade.getByName(query, 10, 0);
    }

}
