/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.tour.pub;

import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
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
public class TourSiteMap implements Serializable {

    @EJB
    private TourFacade tourFacade;
    @Inject
    private LocaleSelectorManager localeManager;
    private String lang = JsfUtil.getRequestParameter("lo");
    private List<Tour> results;

    public List<Tour> getResults() {
        return results;
    }

    public void setResults(List<Tour> results) {
        this.results = results;
    }

    @PostConstruct
    private void init() {
        if (lang == null || lang.isEmpty()) {
            lang = localeManager.getLocale().getLanguage();
        }
        search();
    }

    private void search() {
        results = tourFacade.getForSiteMap(lang);
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
