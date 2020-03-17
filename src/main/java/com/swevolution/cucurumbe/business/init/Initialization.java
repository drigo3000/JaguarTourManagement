package com.swevolution.cucurumbe.business.init;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.swevolution.cucurumbe.business.entityfacades.CucurumbeGlobalFacade;
import com.swevolution.cucurumbe.business.entityfacades.UserFacade;
import com.swevolution.cucurumbe.model.entities.CucurumbeGlobal;
import com.swevolution.cucurumbe.model.entities.User;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Rxkx
 */
@Singleton
@Startup
public class Initialization implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserFacade controller;
    @EJB
    private CucurumbeGlobalFacade cgFacade;

    @PostConstruct
    public void doInitialization() {
        addWebAdmin();
        addGlobalSettings();
    }

    @Asynchronous
    private void addWebAdmin() {
        User admin = controller.find("admin");
        if (admin == null) {
            admin = new User();
            admin.setActive(true);
            admin.setLogin("admin");
            admin.setEmail("rramos@cucurumbe.com");
            admin.setCompany("Cucurumbe");
            admin.setPassword("webadmin");
            admin.setName("Rodrigo");
            admin.setRole("WEBADMIN");
            admin.setOperation("RIVIERA");
            controller.create(admin);
        }
    }

    private void addGlobalSettings() {
        CucurumbeGlobal cg = cgFacade.find("cucurumbe");
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
}
