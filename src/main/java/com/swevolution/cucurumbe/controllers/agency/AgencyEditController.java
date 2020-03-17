/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.agency;

import com.swevolution.cucurumbe.business.entityfacades.AgencyFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Agency;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class AgencyEditController implements Serializable {

    @EJB
    private AgencyFacade agencyController;
    private Agency agency;
    private Long id;

    private BigDecimal imaAdultoUSD;
    private BigDecimal imaAdultoMXN;
    private BigDecimal imaNinoUSD;
    private BigDecimal imaNinoMXN;
    private BigDecimal precioAdultoUSD;
    private BigDecimal comisionAdultoUSD;
    private BigDecimal cashAdultoUSD;
    private BigDecimal precioNinoUSD;
    private BigDecimal comisionNinoUSD;
    private BigDecimal cashNinoUSD;
    private BigDecimal precioAdultoMXN;
    private BigDecimal comisionAdultoMXN;
    private BigDecimal cashAdultoMXN;
    private BigDecimal precioNinoMXN;
    private BigDecimal comisionNinoMXN;
    private BigDecimal cashNinoMXN;
    private BigDecimal impuestoAdendum;

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public AgencyEditController() {
    }

    public void edit() {
        try {
            agencyController.edit(agency);
            agency = null;
            agency = agencyController.find(id);
            JsfUtil.addInfoMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public String remove() {
        try {
            agencyController.remove(agency);
            return "index?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Unable to delete agency");
            return null;
        }

    }

    @PostConstruct
    private void init() {
        imaAdultoMXN = BigDecimal.ZERO;
        imaAdultoUSD = BigDecimal.ZERO;
        imaNinoMXN = BigDecimal.ZERO;
        imaNinoUSD = BigDecimal.ZERO;
        precioAdultoMXN = BigDecimal.ZERO;
        precioAdultoUSD = BigDecimal.ZERO;
        precioNinoMXN = BigDecimal.ZERO;
        precioNinoUSD = BigDecimal.ZERO;
        comisionAdultoMXN = BigDecimal.ZERO;
        comisionAdultoUSD = BigDecimal.ZERO;
        comisionNinoMXN = BigDecimal.ZERO;
        comisionNinoUSD = BigDecimal.ZERO;
        cashAdultoMXN = BigDecimal.ZERO;
        cashAdultoUSD = BigDecimal.ZERO;
        cashNinoMXN = BigDecimal.ZERO;
        cashNinoUSD = BigDecimal.ZERO;
        impuestoAdendum = BigDecimal.ZERO;
        id = Long.parseLong(JsfUtil.getRequestParameter("id"));
        agency = agencyController.find(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecioAdultoUSD() {
        return precioAdultoUSD;
    }

    public void setPrecioAdultoUSD(BigDecimal precioAdultoUSD) {
        this.precioAdultoUSD = precioAdultoUSD;
    }

    public BigDecimal getComisionAdultoUSD() {
        return comisionAdultoUSD;
    }

    public void setComisionAdultoUSD(BigDecimal comisionAdultoUSD) {
        this.comisionAdultoUSD = comisionAdultoUSD;
    }

    public BigDecimal getCashAdultoUSD() {
        return cashAdultoUSD;
    }

    public void setCashAdultoUSD(BigDecimal cashAdultoUSD) {
        this.cashAdultoUSD = cashAdultoUSD;
    }

    public BigDecimal getPrecioNinoUSD() {
        return precioNinoUSD;
    }

    public void setPrecioNinoUSD(BigDecimal precioNinoUSD) {
        this.precioNinoUSD = precioNinoUSD;
    }

    public BigDecimal getComisionNinoUSD() {
        return comisionNinoUSD;
    }

    public void setComisionNinoUSD(BigDecimal comisionNinoUSD) {
        this.comisionNinoUSD = comisionNinoUSD;
    }

    public BigDecimal getCashNinoUSD() {
        return cashNinoUSD;
    }

    public void setCashNinoUSD(BigDecimal cashNinoUSD) {
        this.cashNinoUSD = cashNinoUSD;
    }

    public BigDecimal getPrecioAdultoMXN() {
        return precioAdultoMXN;
    }

    public void setPrecioAdultoMXN(BigDecimal precioAdultoMXN) {
        this.precioAdultoMXN = precioAdultoMXN;
    }

    public BigDecimal getComisionAdultoMXN() {
        return comisionAdultoMXN;
    }

    public void setComisionAdultoMXN(BigDecimal comisionAdultoMXN) {
        this.comisionAdultoMXN = comisionAdultoMXN;
    }

    public BigDecimal getCashAdultoMXN() {
        return cashAdultoMXN;
    }

    public void setCashAdultoMXN(BigDecimal cashAdultoMXN) {
        this.cashAdultoMXN = cashAdultoMXN;
    }

    public BigDecimal getPrecioNinoMXN() {
        return precioNinoMXN;
    }

    public void setPrecioNinoMXN(BigDecimal precioNinoMXN) {
        this.precioNinoMXN = precioNinoMXN;
    }

    public BigDecimal getComisionNinoMXN() {
        return comisionNinoMXN;
    }

    public void setComisionNinoMXN(BigDecimal comisionNinoMXN) {
        this.comisionNinoMXN = comisionNinoMXN;
    }

    public BigDecimal getCashNinoMXN() {
        return cashNinoMXN;
    }

    public void setCashNinoMXN(BigDecimal cashNinoMXN) {
        this.cashNinoMXN = cashNinoMXN;
    }

    public BigDecimal getImaAdultoUSD() {
        return imaAdultoUSD;
    }

    public void setImaAdultoUSD(BigDecimal imaAdultoUSD) {
        this.imaAdultoUSD = imaAdultoUSD;
    }

    public BigDecimal getImaAdultoMXN() {
        return imaAdultoMXN;
    }

    public void setImaAdultoMXN(BigDecimal imaAdultoMXN) {
        this.imaAdultoMXN = imaAdultoMXN;
    }

    public BigDecimal getImaNinoUSD() {
        return imaNinoUSD;
    }

    public void setImaNinoUSD(BigDecimal imaNinoUSD) {
        this.imaNinoUSD = imaNinoUSD;
    }

    public BigDecimal getImaNinoMXN() {
        return imaNinoMXN;
    }

    public void setImaNinoMXN(BigDecimal imaNinoMXN) {
        this.imaNinoMXN = imaNinoMXN;
    }

}
