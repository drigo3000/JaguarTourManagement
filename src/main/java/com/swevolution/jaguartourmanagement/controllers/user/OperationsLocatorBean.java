/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.user;

import com.swevolution.jaguartourmanagement.business.entityfacades.UserFacade;
import com.swevolution.jaguartourmanagement.business.session.SessionInfo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class OperationsLocatorBean implements Serializable {

    @EJB
    private UserFacade userFacade;
    @EJB
    private SessionInfo session;

    public List<String> getOperations() {
        return userFacade.getOperations(session.getCurrentUser().getCompany());
    }
}
