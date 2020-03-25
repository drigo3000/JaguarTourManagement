/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entities.user.services;

import com.swevolution.jaguartourmanagement.business.entityfacades.UserFacade;
import com.swevolution.jaguartourmanagement.controllers.user.exceptions.InvalidUserException;
import com.swevolution.jaguartourmanagement.business.entities.user.qualifiers.UserEvent;
import com.swevolution.jaguartourmanagement.model.entities.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;

/**
 *
 * @author rxkx
 */
@Dependent
public class UserCRUDService implements Serializable {

    @EJB
    private UserFacade userFacade;

    public void add(@Observes @UserEvent(UserEvent.Type.CREATE) User user) throws InvalidUserException {
        try {
            userFacade.create(user);
        } catch (Exception e) {
            throw new InvalidUserException("Invalid User");
        }

    }

    public void edit(@Observes @UserEvent(UserEvent.Type.UPDATE) User user) throws InvalidUserException {
        try {
            userFacade.edit(user);
            user = userFacade.find(user.getLogin());
        } catch (Exception e) {
            throw new InvalidUserException("Invalid User");
        }
    }

    public void editPassword(@Observes @UserEvent(UserEvent.Type.PASSWORD_UPDATE) User user) throws InvalidUserException {
        try {
            userFacade.resetPassword(user.getLogin(), user.getPassword());
            user = userFacade.find(user.getLogin());
        } catch (Exception e) {
            throw new InvalidUserException("Invalid User");
        }
    }

    public void delete(@Observes @UserEvent(UserEvent.Type.DELETE) User user) throws InvalidUserException {
        try {
            userFacade.remove(user);
        } catch (Exception e) {
            throw new InvalidUserException("Invalid User");
        }
    }
}
