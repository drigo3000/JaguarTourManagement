/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.global;

import com.swevolution.cucurumbe.business.entityfacades.CucurumbeGlobalFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.CucurumbeGlobal;
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
public class CucurumbeGlobalController implements Serializable {

    @EJB
    private CucurumbeGlobalFacade cgFacade;
    private CucurumbeGlobal cg;

    public void save() {
        try {
            cgFacade.edit(cg);
            JsfUtil.addSuccessMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public CucurumbeGlobalController() {
    }

    @PostConstruct
    private void init() {
        cg = cgFacade.find("cucurumbe");
        if (cg == null) {
            cg = new CucurumbeGlobal();
            cg.setActive(true);
            cg.setAppDescription("Sw-Evolution 2019");
            cg.setAppName("Cucurumbe Analytics");
            cg.setLogin("cucurumbe");
            cg.setTipoDeCambio(BigDecimal.ZERO);

            cgFacade.create(cg);
        }
    }

    public CucurumbeGlobal getCg() {
        return cg;
    }

    public void setCg(CucurumbeGlobal cg) {
        this.cg = cg;
    }

}
