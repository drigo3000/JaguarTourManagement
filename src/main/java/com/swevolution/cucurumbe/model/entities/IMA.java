/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.model.entities;

import com.swevolution.cucurumbe.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "IMAS", catalog = "CUCURUMBE")
@XmlRootElement
public class IMA extends PK_Long_Entity implements Serializable {

    private String zona;
    @ManyToOne
    private Tour tour;
    @ManyToOne
    private Agency agencia;

    private BigDecimal imaAdultoUSD;
    private BigDecimal imaNinoUSD;
    private BigDecimal imaAdultoCZM;
    private BigDecimal imaNinoCZM;
    private BigDecimal imaAdultoMXN;
    private BigDecimal imaNinoMXN;

    public IMA() {
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Agency getAgencia() {
        return agencia;
    }

    public void setAgencia(Agency agencia) {
        this.agencia = agencia;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    private BigDecimal getMultiplyPercent(BigDecimal commision) {
        try {
            BigDecimal one = new BigDecimal("1");
            return one.subtract(commision);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
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

    public BigDecimal getIma(int adultosOperado, int ninosOperado) {
        BigDecimal imaOperado = getImaAdultoUSD().multiply(new BigDecimal(adultosOperado));
        imaOperado = imaOperado.add(getImaNinoUSD().multiply(new BigDecimal(ninosOperado)));
        return imaOperado;
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
