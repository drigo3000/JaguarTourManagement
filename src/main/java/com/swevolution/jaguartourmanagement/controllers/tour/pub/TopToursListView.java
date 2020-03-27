/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.tour.pub;

import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
import com.swevolution.jaguartourmanagement.controllers.global.GlobalConfigController;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jsf.webutils.locale.LocaleSelectorManager;
import java.io.Serializable;
import java.util.List;
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
public class TopToursListView implements Serializable {

    @EJB
    private TourFacade tourFacade;
    @Inject
    private LocaleSelectorManager localeSelector;
    private List<Tour> tours;
    @Inject
    private GlobalConfigController config;

    public TopToursListView() {
    }

    @PostConstruct
    private void init() {
        String langCode = localeSelector.getLocale().getLanguage();
        if (langCode == null || langCode.isEmpty()) {
            langCode = JsfUtil.getDefaultLocale().getLanguage();
        }
        tours = tourFacade.findTopTours(langCode, config.getCg().getSearchLimit());
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}
