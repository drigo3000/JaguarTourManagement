/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.locale;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import org.primefaces.component.calendar.Calendar;

/**
 *
 * @author Rxkx
 */
@Named
@RequestScoped
public class LocalDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(uic, fc));
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(uic, fc));
        return formatter.format((LocalDate) value);
    }

    private String extractPattern(UIComponent component, FacesContext context) {
        // try to get infos from calendar component
        if (component instanceof Calendar) {
            Calendar calendarComponent = (Calendar) component;
            return calendarComponent.getPattern();
        }

        return null;
    }
}
