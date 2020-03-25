/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.addon;

import com.swevolution.jaguartourmanagement.business.entityfacades.AddOnFacade;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.AddOn;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class AddOnCreateController implements Serializable {

    @EJB
    private AddOnFacade aFacade;
    private AddOn addon;

    public void create() {
        try {
            aFacade.create(addon);
            reset();
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    @PostConstruct
    private void init() {
        reset();
    }

    public AddOn getAddon() {
        return addon;
    }

    public void setAddon(AddOn addon) {
        this.addon = addon;
    }

    private void reset() {
        addon = new AddOn();
        addon.setActive(true);
        addon.setDescription("");
        addon.setEsquemaComisionUSD(true);
        addon.setIncluye("");
        addon.setName("");
        addon.setPrecioVenta(BigDecimal.ZERO);
    }

    public void resetComission() {
        addon.setComisionPesos(BigDecimal.ZERO);
        addon.setComisionUSD(BigDecimal.ZERO);
    }

}
