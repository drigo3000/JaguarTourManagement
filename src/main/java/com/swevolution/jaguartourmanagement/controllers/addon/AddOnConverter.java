/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.addon;

import com.swevolution.jaguartourmanagement.business.entityfacades.AddOnFacade;
import com.swevolution.jaguartourmanagement.model.entities.AddOn;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@RequestScoped
public class AddOnConverter implements Converter {

    @EJB
    private AddOnFacade controller;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return controller.find(Long.parseLong(value));
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof AddOn) {
            return ((AddOn) value).getId().toString();
        }
        return null;
    }

}
