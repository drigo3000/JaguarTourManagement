/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.tour;

import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
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
public class TourConverter implements Converter {

    @EJB
    private TourFacade controller;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return controller.find(Long.parseLong(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No Valido"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Tour) {
            return ((Tour) value).getId().toString();
        }
        return null;
    }

}
