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
public class CustomerContactEditController implements Serializable {

    @EJB
    private CustomerContactFacade facade;
    private CustomerContact contacto;

    @PostConstruct
    private void init() {
        long id = Long.parseLong(JsfUtil.getRequestParameter("id"));
        contacto = facade.find(id);
    }

    public CustomerContact getContacto() {
        return contacto;
    }

    public void setContacto(CustomerContact contacto) {
        this.contacto = contacto;
    }

}
