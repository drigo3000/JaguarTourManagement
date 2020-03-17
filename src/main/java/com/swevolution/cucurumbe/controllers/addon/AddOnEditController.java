/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.addon;

import com.swevolution.cucurumbe.business.entityfacades.AddOnFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.AddOn;
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
public class AddOnEditController implements Serializable {

    @EJB
    private AddOnFacade aFacade;
    private AddOn addon;

    public void edit() {
        try {
            aFacade.edit(addon);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    @PostConstruct
    private void init() {
        long id = Long.parseLong(JsfUtil.getRequestParameter("id"));
        addon = aFacade.find(id);
    }

    public AddOn getAddon() {
        return addon;
    }

    public void setAddon(AddOn addon) {
        this.addon = addon;
    }

    public void resetComission() {
        addon.setComisionPesos(BigDecimal.ZERO);
        addon.setComisionUSD(BigDecimal.ZERO);
    }

}
