/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.session;

import com.swevolution.jaguartourmanagement.business.entityfacades.UserFacade;
import com.swevolution.jaguartourmanagement.model.entities.User;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@Stateful
public class SessionInfo implements Serializable {

    @Resource
    private SessionContext context;
    @EJB
    private UserFacade userController;
    private User user;
    private boolean mobileView = true;

    public boolean isActiveUser() {
        try {
            return getCurrentUser().isActive();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUserLogin() {
        try {
            return context.getCallerPrincipal().getName();
        } catch (Exception e) {
            return null;
        }

    }

    public User getCurrentUser() {
        if (user == null || user.getLogin() == null || user.getLogin().isEmpty()) {
            return searchForCurrentUser();
        }
        return user;
    }

    public User searchForCurrentUser() {
        try {
            return userController.find(getCurrentUserLogin());
        } catch (Exception e) {
            return null;
        }
    }

    public String getCurrentOperation() {
        try {
            return getCurrentUser().getOperation();
        } catch (Exception e) {
            return null;
        }
    }

    public SecurityClearance getUserSecurityClearance() {
        try {
            return SecurityClearance.valueOf(getCurrentUser().getRole());
        } catch (Exception e) {
            return SecurityClearance.GUEST;
        }
    }

    public boolean isMobileView() {
        return mobileView;
    }

    public void setMobileView(boolean mobileView) {
        this.mobileView = mobileView;
    }

}
