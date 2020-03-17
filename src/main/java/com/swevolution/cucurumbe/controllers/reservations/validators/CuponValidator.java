/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations.validators;

import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.model.entities.Reservation;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author rxkx
 */
@Named
@RequestScoped
public class CuponValidator implements Validator {

    @EJB
    private ReservationsFacade rFacade;

    public CuponValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        List<Reservation> res = rFacade.findByCupon(String.valueOf(value));
        if (res != null && res.size() > 0) {
            FacesMessage msg = new FacesMessage("Error", "Duplicado");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
