package com.swevolution.cucurumbe.controllers.locale;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;

@Named
@ViewScoped
public class LocaleSelectorManager implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LocaleSelectorManager.class.getName());

    private Locale locale;

    public SelectItem[] getAvailableCountries() {
        try {
            return JsfUtil.getSelectItems(Arrays.asList(Locale.getISOCountries()), true);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
            return null;
        }
    }

    public SelectItem[] getAvailableLanguages() {
        try {
            return JsfUtil.getSelectItems(Arrays.asList(Locale.getISOLanguages()), true);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
            return null;
        }
    }

    public Locale getLocale() {
        if (this.locale == null) {
            Cookie localeCookie = JsfUtil.getCookie("locale");
            String lang = JsfUtil.getRequestParameter("lo");
            if (lang != null && !lang.isEmpty()) {
                JsfUtil.addCookie(new Cookie("locale", lang));
                return buildLocale(lang);
            } else if (localeCookie != null) {
                return buildLocale(localeCookie.getValue());
            }
            return JsfUtil.getDefaultLocale();
        }
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Remove
    public void terminarSesion() {
        locale = null;
    }

    @PostConstruct
    private void init() {
        getLocale();
    }

    public String getMonth(LocalDate localDate) {
        switch (localDate.getMonthValue()) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "";
        }
    }

    private Locale buildLocale(String lang) {
        switch (lang) {
            case "en":
                return new Locale.Builder().setLanguage(lang).setRegion("US").build();
            case "es":
                return new Locale.Builder().setLanguage(lang).setRegion("MX").build();
            case "fr":
                return new Locale.Builder().setLanguage(lang).setRegion("FR").build();
            default:
                return new Locale.Builder().setLanguage(lang).setRegion("US").build();
        }
    }
}
