package com.swevolution.jaguartourmanagement.business.init;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.swevolution.jaguartourmanagement.business.entityfacades.CucurumbeGlobalFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.UserFacade;
import com.swevolution.jaguartourmanagement.model.entities.GlobalConfiguration;
import com.swevolution.jaguartourmanagement.model.entities.User;
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
            admin.setEmail("rramos@sw-evolution.com");
            admin.setCompany("Cucurumbe");
            admin.setPassword("webadmin");
            admin.setName("Rodrigo");
            admin.setRole("WEBADMIN");
            admin.setOperation("RIVIERA");
            controller.create(admin);
        }
    }

    private void addGlobalSettings() {
        GlobalConfiguration config = cgFacade.find("global");
        if (config == null) {
            config = new GlobalConfiguration();
            config.setActive(true);
            config.setAppDescription("Sw-Evolution 2019");
            config.setAppName("Cucurumbe Analytics");
            config.setLogin("global");
            config.setTipoDeCambio(BigDecimal.ZERO);
            config.setHttp("http");
            config.setServerName("enter domain");
            config.setPathToResources("data/{project}/resources");
            config.setPathToWebResources("data/resources");
            config.setGoogleMapsAPIKey("");
            config.setActive(true);
            config.setAddThisURL("");
            config.setAdminEmailAccount("");
            config.setContactEmailAccount("");
            config.setContactPhone("");
            config.setDescription("");
            config.setEnterpriseName("JAGUAR");
            config.setEnterpriseNameShort("");
            config.setEnterpriseNameTrail("");
            config.setExchangeRate(BigDecimal.ZERO);
            config.setGlobalDiscount(BigDecimal.ZERO);
            config.setGoogleAnalyticsTrackingCode("");
            config.setLinkToFacebook("");
            config.setLinkToTwitter("");
            config.setLinkToYoutube("");
            config.setPaypalLiveAccount("");
            config.setPaypalLiveID("");
            config.setPaypalMode("sandbox");
            config.setPaypalSandboxAccount("");
            config.setPaypalSandboxID("");
            config.setSearchLimit(16);
            config.setTopTourLimit(6);
            cgFacade.create(config);
        }
    }
}
