/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.utility;

/**
 *
 * @author Rxkx
 */
public abstract class EntityCrudController<T> {

    protected T entity;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public void edit() {
        try {
            action();
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public abstract void init();

    public abstract void reset();

    public abstract void action();

}
