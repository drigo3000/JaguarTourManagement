/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.tour.pub;

import com.swevolution.jsf.webutils.locale.CurrencySelectorManager;
import com.swevolution.jsf.webutils.locale.LocaleSelectorManager;
import com.swevolution.jsf.webutils.WebUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class TourSearch implements Serializable {

    private String keywords = "";
    @Inject
    private LocaleSelectorManager localeSelector;
    @Inject
    private CurrencySelectorManager currencySelector;
    private String currency;
    private String locale;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String goResults() {
        return "/results?faces-redirect=true&k=" + WebUtil.encode(keywords);
    }

    @PostConstruct
    private void init() {
        currency = currencySelector.getCurrencyString();
        locale = localeSelector.getLocale().getLanguage();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
