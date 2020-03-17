/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.tour;

import com.swevolution.cucurumbe.business.entityfacades.TourFacade;
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
public class TourNameValidator {

    @EJB
    private TourFacade tourFacade;
    @Inject
    private TourEditManager tourEditManager;

    public TourNameValidator() {
    }

    public void validateEdit(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        if (!tourEditManager.getTour().getName().equals(String.valueOf(value))) {
            List<Tour> tours = tourFacade.findByExactName(String.valueOf(value));
            if (tours != null && !tours.isEmpty()) {
                FacesMessage msg = new FacesMessage("Error", "Duplicado detectado");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }

    }

    public void validateAdd(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        List<Tour> tours = tourFacade.findByExactName(String.valueOf(value));
        if (tours != null && !tours.isEmpty()) {
            FacesMessage msg = new FacesMessage("Error", "Registro duplicado detectado");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
