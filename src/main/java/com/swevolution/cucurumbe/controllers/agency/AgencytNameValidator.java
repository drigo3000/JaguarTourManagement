/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.agency;

import com.swevolution.cucurumbe.business.entityfacades.AgencyFacade;
import com.swevolution.cucurumbe.model.entities.Tour;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rxkx
 */
@Named
@RequestScoped
public class AgencytNameValidator {

    @EJB
    private AgencyFacade agencyFacade;
    @Inject
    private AgencyEditController agencyEditController;

    public AgencytNameValidator() {
    }

    public void validateEdit(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        if (!agencyEditController.getAgency().getName().equals(String.valueOf(value))) {
            List<Tour> tours = agencyFacade.findByExactName(String.valueOf(value));
            if (tours != null && !tours.isEmpty()) {
                FacesMessage msg = new FacesMessage("Error", "Duplicado detectado");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

    }

    public void validateAdd(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        List<Tour> tours = agencyFacade.findByExactName(String.valueOf(value));
        if (tours != null && !tours.isEmpty()) {
            FacesMessage msg = new FacesMessage("Error", "Registro duplicado detectado");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
