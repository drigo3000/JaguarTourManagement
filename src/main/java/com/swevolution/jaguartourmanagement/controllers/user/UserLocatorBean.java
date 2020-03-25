/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.user;

import com.swevolution.jaguartourmanagement.business.entityfacades.UserFacade;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@ViewScoped
@Named
public class UserLocatorBean implements Serializable {

    @EJB
    private UserFacade userFacade;
    private String id;
    private User user;

    @PostConstruct
    private void init() {
        id = JsfUtil.getRequestParameter("id");
        if (id != null && !id.isEmpty()) {
            user = userFacade.find(id);
        } else {
            user = new User();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
