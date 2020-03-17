/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.tour;

import com.swevolution.cucurumbe.business.entityfacades.PromoTourFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.PromoTour;
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
public class PromoTourController implements Serializable {

    @Inject
    private TourEditManager tourEditManager;
    @EJB
    private PromoTourFacade ptFacade;
    private List<PromoTour> promosTour;
    private PromoTour promoTour;
    private PromoTour selectedPromo;

    public void create() {
        try {
            if (valid(promoTour.getName())) {
                ptFacade.create(promoTour);
                JsfUtil.addSuccessMessage("Exito");
                promosTour.add(promoTour);
                resetEntity();
            } else {
                JsfUtil.addErrorMessage("Duplicado");
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void delete(PromoTour promo) {
        try {
            promosTour.remove(promo);
            ptFacade.remove(promo);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }

    }

    @PostConstruct
    private void init() {
        resetEntity();
        promosTour = ptFacade.findByTour(tourEditManager.getTour());
    }

    public List<PromoTour> getPromosTour() {
        return promosTour;
    }

    public void setPromosTour(List<PromoTour> promosTour) {
        this.promosTour = promosTour;
    }

    public PromoTour getPromoTour() {
        return promoTour;
    }

    public void setPromoTour(PromoTour vehiculoTour) {
        this.promoTour = vehiculoTour;
    }

    private void resetEntity() {
        promoTour = new PromoTour();
        promoTour.setActive(true);
        promoTour.setDescription("");
        promoTour.setName("");
        promoTour.setTour(tourEditManager.getTour());
    }

    private boolean valid(String name) {
        boolean valid = true;
        for (PromoTour v : promosTour) {
            if (v.getName().equals(name)) {
                valid = false;
            }
        }
        return valid;
    }

    public PromoTour getSelectedPromo() {
        return selectedPromo;
    }

    public void setSelectedPromo(PromoTour selectedPromo) {
        this.selectedPromo = selectedPromo;
    }

}
