/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.representative;

import com.swevolution.cucurumbe.business.entityfacades.RepresentativeFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Agency;
import com.swevolution.cucurumbe.model.entities.Representante;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class RepresentativeEditController implements Serializable {

    @EJB
    private RepresentativeFacade repController;
    private Representante rep;
    private Long id;

    public Representante getRepresentante() {
        return rep;
    }

    public void setRepresentante(Representante rep) {
        this.rep = rep;
    }

    public RepresentativeEditController() {
    }

    public void edit() {
        try {
            repController.edit(rep);
            rep = null;
            rep = repController.find(id);
            JsfUtil.addInfoMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public String remove() {
        try {
            repController.remove(rep);
            return "index?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Unable to delete rep");
            return null;
        }

    }

    @PostConstruct
    private void init() {
        id = Long.parseLong(JsfUtil.getRequestParameter("id"));
        rep = repController.find(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void openAgencySearchDialog() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 440);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("maximizable", "true");

        PrimeFaces.current()
                .dialog().openDynamic("dialogs/agencySearchDialog",
                        options, null);
    }

    public void handleAgencyFromDialog(SelectEvent event) {
        try {
            Agency agency = (Agency) event.getObject();
            rep.setAgencia(agency);
        } catch (Exception e) {

        }
    }
}
