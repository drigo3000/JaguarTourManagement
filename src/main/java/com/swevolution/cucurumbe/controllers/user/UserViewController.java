/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.user;

import com.swevolution.cucurumbe.business.entities.user.qualifiers.UserEvent;
import com.swevolution.cucurumbe.business.entityfacades.UserFacade;
import com.swevolution.cucurumbe.business.session.SessionInfo;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class UserViewController implements Serializable {

    @EJB
    private UserFacade userFacade;
    @EJB
    private SessionInfo session;
    @Inject
    private UserLocatorBean userLocator;

    @Inject
    @UserEvent(UserEvent.Type.CREATE)
    private Event<User> addEvent;

    @Inject
    @UserEvent(UserEvent.Type.UPDATE)
    private Event<User> updateEvent;

    @Inject
    @UserEvent(UserEvent.Type.PASSWORD_UPDATE)
    private Event<User> passwordUpdateEvent;

    private User user;
    private UploadedFile file;

    public UserViewController() {
    }

    public void add() {
        try {
            addEvent.fire(user);
            user = new User();
            defaults();
            JsfUtil.addSuccessMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void resetPassword() {
        try {
            user.setNeedsResetPassword(true);
            user.setPassword("1234");
            passwordUpdateEvent.fire(user);
            JsfUtil.addSuccessMessage("Éxito");
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void edit() {
        try {
            updateEvent.fire(user);
            user = userFacade.find(user.getLogin());
            JsfUtil.addSuccessMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    @PostConstruct
    private void init() {
        user = userLocator.getUser();
        defaults();
    }

    private void defaults() {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            user.setCompany(session.getCurrentUser().getCompany());
            user.setActive(true);
            user.setEmail("");
            user.setOperation(session.getCurrentOperation());
            user.setNeedsResetPassword(true);
            user.setRole("GUEST");
            user.setPassword("1234");
        }
    }

    public List<String> getCompanies() {
        return userFacade.getCompanies();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
