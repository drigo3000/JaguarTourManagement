/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.web;

import com.swevolution.jaguartourmanagement.business.entityfacades.CustomerContactFacade;
import com.swevolution.jaguartourmanagement.controllers.global.GlobalConfigController;
import com.swevolution.jaguartourmanagement.model.entities.CustomerContact;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jsf.webutils.locale.LocaleSelectorManager;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class AddCustomerContactController implements Serializable {

    @Inject
    private LocaleSelectorManager localeSelector;
    @EJB
    private CustomerContactFacade contactFacade;
    @Inject
    private GlobalConfigController config;

    private CustomerContact contact;

    public void create() {
        //TODO: Send email
        contactFacade.create(contact);
        JsfUtil.addSuccessMessage(JsfUtil.getResourceString("contact.success", localeSelector.getLocale()));
        init();
    }

    public AddCustomerContactController() {
    }

    public CustomerContact getContact() {
        return contact;
    }

    public void setContact(CustomerContact contact) {
        this.contact = contact;
    }

    @PostConstruct
    private void init() {
        contact = new CustomerContact();
        contact.setActive(true);
    }
}
