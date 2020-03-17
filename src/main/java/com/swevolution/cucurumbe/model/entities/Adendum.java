/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.model.entities;

import com.swevolution.cucurumbe.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "ADENDUM", catalog = "CUCURUMBE")
@XmlRootElement
public class Adendum extends PK_Long_Entity implements Serializable {

    private String zona;
    @ManyToOne
    private Tour tour;
    @ManyToOne
    private Agency agencia;
    private String tipoVehiculo;
    private String promo;
    private boolean incluyeTransportacion;

    private BigDecimal precioAdultoUSD;
    private BigDecimal comisionAdultoUSD;

    private BigDecimal precioNinoUSD;
    private BigDecimal comisionNinoUSD;

    private BigDecimal precioAdultoMXN;
    private BigDecimal comisionAdultoMXN;

    private BigDecimal precioNinoMXN;
    private BigDecimal comisionNinoMXN;

    private BigDecimal impuestoUSD;

    private boolean buceo;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    public Adendum() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public BigDecimal getNetoAdultoUSD() {
        try {
            return getPrecioAdultoUSD().multiply(getMultiplyPercent(getComisionAdultoUSD()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

    }

    public BigDecimal getNetoNinoUSD() {
        try {
            return getPrecioNinoUSD().multiply(getMultiplyPercent(getComisionNinoUSD()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getNetoAdultoMXN() {
        try {
            return getPrecioAdultoMXN().multiply(getMultiplyPercent(getComisionAdultoMXN()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getNetoNinoMXN() {
        try {
            return getPrecioNinoMXN().multiply(getMultiplyPercent(getComisionNinoMXN()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
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

    public BigDecimal getImpuestoUSD() {
        return impuestoUSD;
    }

    public void setImpuestoUSD(BigDecimal impuestoUSD) {
        this.impuestoUSD = impuestoUSD;
    }

    public BigDecimal getNetoCuponeado(int adultosCuponeado, int ninosCuponeado, BigDecimal impuestoCuponeado) {
        BigDecimal netoCuponeado = getNetoAdultoUSD().multiply(new BigDecimal(adultosCuponeado));
        netoCuponeado = netoCuponeado.add(getNetoNinoUSD().multiply(new BigDecimal(ninosCuponeado)));
        netoCuponeado = netoCuponeado.add(impuestoCuponeado.multiply(new BigDecimal(adultosCuponeado + ninosCuponeado)));
        return netoCuponeado;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public boolean isIncluyeTransportacion() {
        return incluyeTransportacion;
    }

    public void setIncluyeTransportacion(boolean incluyeTransportacion) {
        this.incluyeTransportacion = incluyeTransportacion;
    }

    public boolean isBuceo() {
        return buceo;
    }

    public void setBuceo(boolean buceo) {
        this.buceo = buceo;
    }

}
