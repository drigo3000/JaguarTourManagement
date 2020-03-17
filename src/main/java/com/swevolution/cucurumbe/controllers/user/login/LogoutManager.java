/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.user.login;

import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;

/**
 *
 * @author Rxkx
 */
@Named
@RequestScoped
public class LogoutManager {

    public void invalidateSession() throws ServletException {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml?logout=true");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage("Error");
        }
    }

}
