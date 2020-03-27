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
public class TourResults implements Serializable {

    @EJB
    private TourFacade tourFacade;
    @Inject
    private LocaleSelectorManager localeManager;
    @Inject
    private GlobalConfigController config;
    private final String keywords = JsfUtil.getRequestParameter("k");
    private String lang = JsfUtil.getRequestParameter("lo");
    private int limit;
    private List<Tour> results;
    private long count = 0;
    private int page = 0;
    private int offset = 0;

    public String getKeywords() {
        return keywords;
    }

    public List<Tour> getResults() {
        return results;
    }

    public void setResults(List<Tour> results) {
        this.results = results;
    }

    @PostConstruct
    private void init() {
        limit = config.getCg().getSearchLimit();
        if (lang == null || lang.isEmpty()) {
            lang = localeManager.getLocale().getLanguage();
        }
        count = tourFacade.countByKeywords(keywords, lang, limit, offset, true);
        search();
    }

    private void search() {
        results = tourFacade.getByKeywords(keywords, lang, limit, offset, true);
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void stepFoward() {
        page += 1;
        offset = page * limit;
        search();
    }

    public void stepBackward() {
        if (page > 0) {
            page -= 1;
            offset = page * limit;
            search();
        }
    }

    public boolean hasNextPage() {
        return ((page + 1) * limit < count);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
