/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.user;

import com.swevolution.cucurumbe.business.entityfacades.UserFacade;
import com.swevolution.cucurumbe.model.entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@ViewScoped
@Named
public class UserSearchBean implements Serializable {

    @EJB
    private UserFacade userFacade;
    private List<User> users;

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void search(String name, int limit, int offset) {
        users = userFacade.getUsers(name,
                limit,
                offset);
    }

    public void contactSearch(String keywords, int limit, int offset) {
        users = userFacade.getUsers(keywords, limit, offset);
    }

}
