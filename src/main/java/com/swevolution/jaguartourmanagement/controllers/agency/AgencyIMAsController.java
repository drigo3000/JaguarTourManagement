/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.agency;

import com.swevolution.jaguartourmanagement.business.entityfacades.IMAFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.IMA;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class AgencyIMAsController implements Serializable {

    @Inject
    private AgencyEditController agencyEditController;
    @EJB
    private IMAFacade imaFacade;
    @EJB
    private TourFacade tFacade;
    private List<Tour> servicios;
    private List<ImasPorTour> imasPorTour;

    @PostConstruct
    private void init() {
        servicios = tFacade.findAllByName();
        imasPorTour = new ArrayList<>();
        for (Tour t : servicios) {
            ImasPorTour i = new ImasPorTour();
            i.setTour(t);
            IMA ima = imaFacade.findByTourAgency(t.getId(), agencyEditController.getAgency().getId());
            if (ima != null) {
                i.setIma(ima);
            } else {
                ima = new IMA();
                ima.setActive(true);
                ima.setAgencia(null);
                ima.setTour(t);
                ima.setZona("");
                i.setIma(ima);
            }
            imasPorTour.add(i);
        }
    }

    public class ImasPorTour {

        private Tour tour;
        private IMA ima;

        public Tour getTour() {
            return tour;
        }

        public void setTour(Tour tour) {
            this.tour = tour;
        }

        public IMA getIma() {
            return ima;
        }

        public void setIma(IMA ima) {
            this.ima = ima;
        }

    }

    public List<Tour> getServicios() {
        return servicios;
    }

    public void setServicios(List<Tour> servicios) {
        this.servicios = servicios;
    }

    public List<ImasPorTour> getImasPorTour() {
        return imasPorTour;
    }

    public void setImasPorTour(List<ImasPorTour> imasPorTour) {
        this.imasPorTour = imasPorTour;
    }

    public void rowEdit(RowEditEvent event) {
        try {
            ImasPorTour ipt = (ImasPorTour) event.getObject();
            tFacade.edit(ipt.getTour());
            IMA im = ipt.getIma();
            if (ipt.getIma().getAgencia() != null) {
                imaFacade.edit(ipt.getIma());
            } else {
                im.setAgencia(agencyEditController.getAgency());
                if (im.getImaAdultoUSD() != null || im.getImaNinoUSD() != null || im.getImaAdultoCZM() != null || im.getImaNinoCZM() != null) {
                    imaFacade.create(im);
                }
            }
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Error");
        }

    }

}
