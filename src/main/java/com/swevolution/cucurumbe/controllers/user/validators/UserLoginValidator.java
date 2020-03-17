/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.user.validators;

import com.swevolution.cucurumbe.model.entities.User;
import com.swevolution.cucurumbe.business.entityfacades.UserFacade;
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
public class UserLoginValidator implements Validator {

    @EJB
    private UserFacade userFacade;

    public UserLoginValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        User registeredUser = userFacade.find(String.valueOf(value));
        if (registeredUser != null) {
            FacesMessage msg = new FacesMessage("Error", "Duplicate login detected");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
