/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.model.entities;

import com.swevolution.jaguartourmanagement.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "ESCENARIOS_COMERCIALES", catalog = "JTM")
@XmlRootElement
public class EscenarioComercial extends PK_Long_Entity implements Serializable {

    @NotNull
    @ManyToOne
    private Agency agencia;
    private String tipoTarifa;
    private String destino;
    @NotNull
    @ManyToOne
    private Tour tour;
    private BigDecimal tarifaPublicaAdulto;
    private BigDecimal tarifaPublicaNino;
    private BigDecimal tarifaNetaAdulto;
    private BigDecimal tarifaNetaNino;
    private BigDecimal comision;
    private BigDecimal impuesto;
    private BigDecimal imaAdulto;
    private BigDecimal imaNino;

    public EscenarioComercial() {
    }

    public Agency getAgencia() {
        return agencia;
    }

    public void setAgencia(Agency agencia) {
        this.agencia = agencia;
    }

    public String getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(String tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public BigDecimal getTarifaPublicaAdulto() {
        return tarifaPublicaAdulto;
    }

    public void setTarifaPublicaAdulto(BigDecimal tarifaPublicaAdulto) {
        this.tarifaPublicaAdulto = tarifaPublicaAdulto;
    }

    public BigDecimal getTarifaPublicaNino() {
        return tarifaPublicaNino;
    }

    public void setTarifaPublicaNino(BigDecimal tarifaPublicaNino) {
        this.tarifaPublicaNino = tarifaPublicaNino;
    }

    public BigDecimal getTarifaNetaAdulto() {
        return tarifaNetaAdulto;
    }

    public void setTarifaNetaAdulto(BigDecimal tarifaNetaAdulto) {
        this.tarifaNetaAdulto = tarifaNetaAdulto;
    }

    public BigDecimal getTarifaNetaNino() {
        return tarifaNetaNino;
    }

    public void setTarifaNetaNino(BigDecimal tarifaNetaNino) {
        this.tarifaNetaNino = tarifaNetaNino;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getImaAdulto() {
        return imaAdulto;
    }

    public void setImaAdulto(BigDecimal imaAdulto) {
        this.imaAdulto = imaAdulto;
    }

    public BigDecimal getImaNino() {
        return imaNino;
    }

    public void setImaNino(BigDecimal imaNino) {
        this.imaNino = imaNino;
    }

}
