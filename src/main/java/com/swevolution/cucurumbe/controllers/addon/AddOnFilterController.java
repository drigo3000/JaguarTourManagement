/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.addon;

import com.swevolution.cucurumbe.business.entityfacades.AddOnFacade;
import com.swevolution.cucurumbe.model.entities.AddOn;
import java.io.Serializable;
import java.util.List;
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
public class AddOnFilterController implements Serializable {

    @EJB
    private AddOnFacade aFacade;
    private List<AddOn> addOns;

    @PostConstruct
    private void init() {
        addOns = aFacade.all();
    }

    public List<AddOn> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<AddOn> addOns) {
        this.addOns = addOns;
    }

}
