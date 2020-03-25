/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.user;

import com.swevolution.jaguartourmanagement.business.session.SessionInfo;
import com.swevolution.jsf.webutils.JsfUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class UserListView implements Serializable {

    @EJB
    private SessionInfo session;
    @Inject
    private UserSearchBean userSearchBean;

    private int limit = 10;
    private String name = "";
    private int offset = 0;

    public void search() {
        userSearchBean.search(name, limit, offset);
        JsfUtil.addSuccessMessage("Usuarios encontrados: " + userSearchBean.getUsers().size());
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @PostConstruct
    private void init() {
    }

}
