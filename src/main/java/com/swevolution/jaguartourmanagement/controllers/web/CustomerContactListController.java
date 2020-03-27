/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.web;

import com.swevolution.jaguartourmanagement.business.entityfacades.CustomerContactFacade;
import com.swevolution.jaguartourmanagement.model.entities.CustomerContact;
import com.swevolution.jsf.webutils.JsfUtil;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class CustomerContactListController implements Serializable {

    @EJB
    private CustomerContactFacade facade;
    private List<CustomerContact> contactos;
    private LocalDate from;
    private LocalDate to;

    public void search() {
        contactos = facade.find(from, to);
    }

    @PostConstruct
    private void init() {
        from = JsfUtil.getCancunDate();
        to = JsfUtil.getCancunDate();
    }

    public List<CustomerContact> getContactos() {
        return contactos;
    }

    public void setContactos(List<CustomerContact> contactos) {
        this.contactos = contactos;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

}
