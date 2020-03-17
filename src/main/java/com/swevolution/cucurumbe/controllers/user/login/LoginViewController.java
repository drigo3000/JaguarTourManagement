/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.user.login;

import com.swevolution.cucurumbe.business.session.SessionInfo;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.controllers.user.events.UserLoginEvent;
import com.swevolution.cucurumbe.business.entities.user.qualifiers.UserEvent;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rxkx
 */
@Named
@ViewScoped
public class LoginViewController implements Serializable {

    @Inject
    private SessionInfo session;
    @Inject
    @UserEvent(UserEvent.Type.LOGIN)
    private Event<UserLoginEvent> loginEvent;

    @NotNull
    private String username;
    @NotNull
    private String password;
    private boolean rememberMe;

    public String login() {
        try {
            loginEvent.fire(new UserLoginEvent(username, password, rememberMe));
            return getNavString();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error", e.getMessage());
            return null;
        }
    }

    public LoginViewController() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String getNavString() {
        return "/priv/index.xhtml?a=index&faces-redirect=true";
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @PostConstruct
    private void init() {
        try {
            Cookie cookie = JsfUtil.getCookie("rememberMe");
            if (cookie != null) {
                rememberMe = Boolean.getBoolean(cookie.getValue());
            }
        } catch (Exception e) {
            rememberMe = false;
        }

    }

}
