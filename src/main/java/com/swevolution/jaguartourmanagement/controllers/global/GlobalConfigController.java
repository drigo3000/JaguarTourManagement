/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.global;

import com.swevolution.jaguartourmanagement.business.entityfacades.CucurumbeGlobalFacade;
import com.swevolution.jaguartourmanagement.model.entities.GlobalConfiguration;
import com.swevolution.jsf.webutils.JsfUtil;
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
public class GlobalConfigController implements Serializable {

    @EJB
    private CucurumbeGlobalFacade cgFacade;
    private GlobalConfiguration cg;

    public void save() {
        try {
            cgFacade.edit(cg);
            JsfUtil.addSuccessMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public GlobalConfigController() {
    }

    @PostConstruct
    private void init() {
        cg = cgFacade.find("global");
        if (cg == null) {
            cg = new GlobalConfiguration();
            cg.setActive(true);
            cg.setAppDescription("Sw-Evolution 2019");
            cg.setAppName("Cucurumbe Analytics");
            cg.setLogin("global");
            cg.setTipoDeCambio(BigDecimal.ZERO);

            cgFacade.create(cg);
        }
    }

    public GlobalConfiguration getCg() {
        return cg;
    }

    public void setCg(GlobalConfiguration cg) {
        this.cg = cg;
    }

}
