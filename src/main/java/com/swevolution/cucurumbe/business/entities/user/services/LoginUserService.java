/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.entities.user.services;

import com.swevolution.cucurumbe.business.entities.user.qualifiers.UserEvent;
import com.swevolution.cucurumbe.business.entityfacades.UserFacade;
import com.swevolution.cucurumbe.controllers.user.events.UserLoginEvent;
import com.swevolution.cucurumbe.controllers.user.exceptions.InvalidUserCredentialsException;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.User;
import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

/**
 *
 * @author rxkx
 */
@RequestScoped
public class LoginUserService {

    @EJB
    private UserFacade userFacade;

    public void login(@Observes @Priority(100) @UserEvent(UserEvent.Type.LOGIN) UserLoginEvent loginEvent)
            throws ServletException, InvalidUserCredentialsException, Exception {
        User user = userFacade.find(loginEvent.getLogin());
        if (user != null && !user.getLogin().isEmpty() && user.isActive()) {
            JsfUtil.getHttpServletRequest().login(loginEvent.getLogin(), loginEvent.getPassword());
            if (loginEvent.isRememberMe()) {
                Cookie cookie = new Cookie("bdtuser", loginEvent.getLogin());
                cookie.setMaxAge(60 * 60 * 24 * 365);
                cookie.setHttpOnly(true);

                Cookie passCookie = new Cookie("bdtpass", loginEvent.getPassword());
                passCookie.setMaxAge(60 * 60 * 24 * 365);
                passCookie.setHttpOnly(true);

                JsfUtil.addCookie(cookie);
                JsfUtil.addCookie(passCookie);
            }

            Cookie rememberMeCookie = new Cookie("rememberMe", String.valueOf(loginEvent.isRememberMe()));
            rememberMeCookie.setMaxAge(60 * 60 * 24 * 365);
            rememberMeCookie.setHttpOnly(true);
            JsfUtil.addCookie(rememberMeCookie);

        } else {
            throw new InvalidUserCredentialsException("Invalid User Credentials");
        }
    }

}
