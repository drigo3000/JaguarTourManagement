/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.tour;

import com.swevolution.cucurumbe.business.entityfacades.AdendumFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Adendum;
import com.swevolution.cucurumbe.model.entities.Agency;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class TourAdendumsController implements Serializable {

    @EJB
    private AdendumFacade aFacade;
    @Inject
    private TourEditManager tourEditManager;
    private List<Adendum> adendums;
    private Adendum selectedAdendum;
    private Agency agencia;
    private BigDecimal comisionUSD;
    private BigDecimal precioAdultoUSD;
    private BigDecimal precioNinoUSD;
    private BigDecimal impuestoUSD;
    private boolean transportacion;
    private String promo;
    private String tipoVehiculo;
    private String zona;
    private boolean buceo;

    @PostConstruct
    private void init() {
        adendums = aFacade.findByTour(tourEditManager.getTour());
        resetAdendumInfo();
    }

    public void editAdendum() {
        try {
            aFacade.edit(selectedAdendum);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void deleteSelectedAdendum() {
        try {
            aFacade.remove(selectedAdendum);
            adendums = aFacade.findByTour(tourEditManager.getTour());
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void deleteAdendum(Adendum adendum) {
        try {
            aFacade.remove(adendum);
            adendums = aFacade.findByTour(tourEditManager.getTour());
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void createNewAdendum() {
        try {
            Adendum add = aFacade.find(agencia, zona, tourEditManager.getTour(),
                    promo, transportacion, tipoVehiculo, buceo);
            if (add == null) {
                Adendum newAdendum = new Adendum();
                newAdendum.setAgencia(agencia);
                newAdendum.setComisionAdultoUSD(comisionUSD);
                newAdendum.setComisionNinoUSD(comisionUSD);
                newAdendum.setImpuestoUSD(impuestoUSD);
                newAdendum.setIncluyeTransportacion(transportacion);
                newAdendum.setPrecioAdultoUSD(precioAdultoUSD);
                newAdendum.setPrecioNinoUSD(precioNinoUSD);
                newAdendum.setPromo(promo);
                newAdendum.setTipoVehiculo(tipoVehiculo);
                newAdendum.setTour(tourEditManager.getTour());
                newAdendum.setZona(zona);
                newAdendum.setBuceo(buceo);
                aFacade.create(newAdendum);
                adendums = aFacade.findByTour(tourEditManager.getTour());
                resetAdendumInfo();
                JsfUtil.addSuccessMessage("Exito");
            } else {
                JsfUtil.addErrorMessage("Adendum duplicado");
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void rowEdit(RowEditEvent event) {
        try {
            Adendum a = (Adendum) event.getObject();
            a.setComisionNinoUSD(a.getComisionAdultoUSD());
            aFacade.edit(a);
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }

    }

    public List<Adendum> getAdendums() {
        if (adendums == null) {
            adendums = new ArrayList<>();
        }
        return adendums;
    }

    public void setAdendums(List<Adendum> adendums) {
        this.adendums = adendums;
    }

    public Adendum getSelectedAdendum() {
        return selectedAdendum;
    }

    public void setSelectedAdendum(Adendum selectedAdendum) {
        this.selectedAdendum = selectedAdendum;
    }

    public Agency getAgencia() {
        return agencia;
    }

    public void setAgencia(Agency agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getComisionUSD() {
        return comisionUSD;
    }

    public void setComisionUSD(BigDecimal comisionUSD) {
        this.comisionUSD = comisionUSD;
    }

    public BigDecimal getPrecioAdultoUSD() {
        return precioAdultoUSD;
    }

    public void setPrecioAdultoUSD(BigDecimal precioAdultoUSD) {
        this.precioAdultoUSD = precioAdultoUSD;
    }

    public BigDecimal getPrecioNinoUSD() {
        return precioNinoUSD;
    }

    public void setPrecioNinoUSD(BigDecimal precioNinoUSD) {
        this.precioNinoUSD = precioNinoUSD;
    }

    public BigDecimal getImpuestoUSD() {
        return impuestoUSD;
    }

    public void setImpuestoUSD(BigDecimal impuestoUSD) {
        this.impuestoUSD = impuestoUSD;
    }

    public boolean isTransportacion() {
        return transportacion;
    }

    public void setTransportacion(boolean transportacion) {
        this.transportacion = transportacion;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    private void resetAdendumInfo() {
        agencia = null;
        comisionUSD = BigDecimal.ZERO;
        precioAdultoUSD = BigDecimal.ZERO;
        precioNinoUSD = BigDecimal.ZERO;
        impuestoUSD = BigDecimal.ZERO;
        transportacion = false;
        promo = "N/A";
        tipoVehiculo = "N/A";
        zona = "N/A";
        buceo = false;
    }

    public boolean isBuceo() {
        return buceo;
    }

    public void setBuceo(boolean buceo) {
        this.buceo = buceo;
    }

}
