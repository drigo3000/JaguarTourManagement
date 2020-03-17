/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.user;

import com.swevolution.cucurumbe.business.entities.user.qualifiers.UserEvent;
import com.swevolution.cucurumbe.business.session.SessionInfo;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class UserPasswordUpdateController implements Serializable {

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{5,}$",
            message = "Invalid password format")
    private String password;
    @EJB
    private SessionInfo session;
    private User user;
    @Inject
    @UserEvent(UserEvent.Type.PASSWORD_UPDATE)
    private Event<User> updateEvent;

    public String editPassword() {
        try {
            user.setPassword(password);
            user.setNeedsResetPassword(false);
            updateEvent.fire(user);
            password = "";
            return "/priv/index.xhtml?faces-redirect=true";
        } catch (EJBException e) {
            if (e.getCause() instanceof OptimisticLockException) {
                JsfUtil.addErrorMessage("Representative information has changed since it was originally loaded. Please reload page and review changes before saving changes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PostConstruct
    private void init() {
        user = (User) session.getCurrentUser();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
