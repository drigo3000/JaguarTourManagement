/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.ima;

import com.swevolution.cucurumbe.business.entityfacades.IMAFacade;
import com.swevolution.cucurumbe.controllers.tour.TourEditManager;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Agency;
import com.swevolution.cucurumbe.model.entities.IMA;
import com.swevolution.cucurumbe.model.entities.Tour;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class IMAController implements Serializable {

    @Inject
    private TourEditManager tourEditManager;
    @EJB
    private IMAFacade imaFacade;
    private List<IMA> imas;
    private IMA ima;
    private BigDecimal imaAdultoUSD;
    private BigDecimal imaNinoUSD;
    private BigDecimal imaAdultoCZM;
    private BigDecimal imaNinoCZM;
    private BigDecimal imaAdultoMXN;
    private BigDecimal imaNinoMXN;
    private Agency agencia;
    private IMA selectedIMA;

    public void create() {
        try {
            Tour tour = tourEditManager.getTour();
            ima.setTour(tour);
            imaFacade.create(ima);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    @PostConstruct
    private void init() {
        reset();
        resetNewIma();
        imas = imaFacade.findByTourID(tourEditManager.getTour().getId());
    }

    public List<IMA> getImas() {
        return imas;
    }

    public void setImas(List<IMA> imas) {
        this.imas = imas;
    }

    public IMA getIma() {
        return ima;
    }

    public void setIma(IMA ima) {
        this.ima = ima;
    }

    public void rowEdit(RowEditEvent event) {
        try {
            IMA i = (IMA) event.getObject();
            boolean contained = false;
            for (IMA ima : imas) {
                if (!ima.getId().equals(i.getId())) {
                    if (ima.getAgencia().getId().equals(i.getAgencia().getId())) {
                        contained = true;
                    }
                }
            }
            if (!contained) {
                imaFacade.edit(i);
                JsfUtil.addSuccessMessage("Exito");
            } else {
                JsfUtil.addErrorMessage("Agencia duplicada");
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }

    }

    public void crearNuevo() {
        try {
            boolean contained = false;
            for (IMA im : imas) {
                if (im.getAgencia().equals(agencia)) {
                    contained = true;
                }
            }
            if (!contained) {
                IMA i = new IMA();
                i.setActive(true);
                i.setImaAdultoMXN(imaAdultoMXN);
                i.setImaAdultoUSD(imaAdultoUSD);
                i.setImaNinoMXN(imaNinoMXN);
                i.setImaNinoUSD(imaNinoUSD);
                i.setImaAdultoCZM(imaAdultoCZM);
                i.setImaNinoCZM(imaNinoCZM);
                i.setZona("");
                i.setAgencia(agencia);
                i.setTour(tourEditManager.getTour());
                imaFacade.create(i);
                imas.add(i);
                resetNewIma();
                JsfUtil.addSuccessMessage("Exito");
            } else {
                JsfUtil.addErrorMessage("Esa agencia ya existe en la lista");
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }

    }

    private void reset() {
        ima = new IMA();
        ima.setActive(true);
        ima.setImaAdultoMXN(BigDecimal.ZERO);
        ima.setImaAdultoUSD(BigDecimal.ZERO);
        ima.setImaNinoMXN(BigDecimal.ZERO);
        ima.setImaNinoUSD(BigDecimal.ZERO);
        ima.setImaNinoCZM(BigDecimal.ZERO);
        ima.setImaAdultoCZM(BigDecimal.ZERO);
        ima.setZona("");
        ima.setAgencia(null);
        ima.setTour(null);
    }

    private void resetNewIma() {
        imaAdultoMXN = BigDecimal.ZERO;
        imaAdultoUSD = BigDecimal.ZERO;
        imaNinoMXN = BigDecimal.ZERO;
        imaNinoUSD = BigDecimal.ZERO;
        imaAdultoCZM = BigDecimal.ZERO;
        imaNinoCZM = BigDecimal.ZERO;
        agencia = null;
    }

    public IMAFacade getImaFacade() {
        return imaFacade;
    }

    public void setImaFacade(IMAFacade imaFacade) {
        this.imaFacade = imaFacade;
    }

    public BigDecimal getImaAdultoUSD() {
        return imaAdultoUSD;
    }

    public void setImaAdultoUSD(BigDecimal imaAdultoUSD) {
        this.imaAdultoUSD = imaAdultoUSD;
    }

    public BigDecimal getImaNinoUSD() {
        return imaNinoUSD;
    }

    public void setImaNinoUSD(BigDecimal imaNinoUSD) {
        this.imaNinoUSD = imaNinoUSD;
    }

    public BigDecimal getImaAdultoMXN() {
        return imaAdultoMXN;
    }

    public void setImaAdultoMXN(BigDecimal imaAdultoMXN) {
        this.imaAdultoMXN = imaAdultoMXN;
    }

    public BigDecimal getImaNinoMXN() {
        return imaNinoMXN;
    }

    public void setImaNinoMXN(BigDecimal imaNinoMXN) {
        this.imaNinoMXN = imaNinoMXN;
    }

    public Agency getAgencia() {
        return agencia;
    }

    public void setAgencia(Agency agencia) {
        this.agencia = agencia;
    }

    public IMA getSelectedIMA() {
        return selectedIMA;
    }

    public void setSelectedIMA(IMA selectedIMA) {
        this.selectedIMA = selectedIMA;
    }

    public void deleteSelectedIMA() {
        try {
            imaFacade.remove(selectedIMA);
            imas = imaFacade.findByTourID(tourEditManager.getTour().getId());
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public BigDecimal getImaAdultoCZM() {
        return imaAdultoCZM;
    }

    public void setImaAdultoCZM(BigDecimal imaAdultoCZM) {
        this.imaAdultoCZM = imaAdultoCZM;
    }

    public BigDecimal getImaNinoCZM() {
        return imaNinoCZM;
    }

    public void setImaNinoCZM(BigDecimal imaNinoCZM) {
        this.imaNinoCZM = imaNinoCZM;
    }

}
