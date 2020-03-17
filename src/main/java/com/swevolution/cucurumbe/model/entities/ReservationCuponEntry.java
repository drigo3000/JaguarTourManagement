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
@Table(name = "RESERVATION_COUPON_ENTRY", catalog = "CUCURUMBE")
@XmlRootElement
public class ReservationCuponEntry extends PK_Long_Entity implements Serializable {

    private String noCupon;
    @ManyToOne
    private Reservation reservation;
    @ManyToOne
    private Tour tour;
    private int adults;
    private int children;
    private int infants;

    private String tipoVehiculo;
    private String promo;
    private boolean incluyeTransportacion;

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

    private BigDecimal impuestoUSD;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
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

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
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

    public BigDecimal getNetoCuponeado() {
        BigDecimal netoCuponeado = getNetoAdultoUSD().multiply(new BigDecimal(this.adults));
        netoCuponeado = netoCuponeado.add(getNetoNinoUSD().multiply(new BigDecimal(this.children)));
        netoCuponeado = netoCuponeado.add(impuestoUSD.multiply(new BigDecimal(adults + children)));
        return netoCuponeado;
    }

    public void resetCupon(Reservation reservation, Tour servicio, String promo, boolean incluyeTransportacion, String tipoVehiculo) {
        this.setActive(true);
        this.setAdults(0);
        this.setCashAdultoMXN(BigDecimal.ZERO);
        this.setCashAdultoUSD(BigDecimal.ZERO);
        this.setCashNinoMXN(BigDecimal.ZERO);
        this.setCashNinoUSD(BigDecimal.ZERO);
        this.setChildren(0);
        this.setComisionAdultoMXN(BigDecimal.ZERO);
        this.setComisionAdultoUSD(BigDecimal.ZERO);
        this.setComisionNinoMXN(BigDecimal.ZERO);
        this.setComisionNinoUSD(BigDecimal.ZERO);
        this.setImpuestoUSD(BigDecimal.ZERO);
        this.setInfants(0);
        this.setPrecioAdultoMXN(BigDecimal.ZERO);
        this.setPrecioAdultoUSD(BigDecimal.ZERO);
        this.setPrecioNinoMXN(BigDecimal.ZERO);
        this.setPrecioNinoUSD(BigDecimal.ZERO);
        this.setReservation(reservation);
        this.setTour(servicio);
        this.setPromo(promo);
        this.setIncluyeTransportacion(incluyeTransportacion);
        this.setTipoVehiculo(tipoVehiculo);
    }

    public void copyAdendumInfo(Adendum adendum, Reservation reservation) {
        this.setActive(true);

        this.setAdults(0);
        this.setChildren(0);
        this.setComisionAdultoMXN(adendum.getComisionAdultoMXN());
        this.setComisionAdultoUSD(adendum.getComisionAdultoUSD());
        this.setComisionNinoMXN(adendum.getComisionNinoMXN());
        this.setComisionNinoUSD(adendum.getComisionNinoUSD());
        this.setImpuestoUSD(adendum.getImpuestoUSD());
        this.setInfants(0);
        this.setPrecioAdultoMXN(adendum.getPrecioAdultoMXN());
        this.setPrecioAdultoUSD(adendum.getPrecioAdultoUSD());
        this.setPrecioNinoMXN(adendum.getPrecioNinoMXN());
        this.setPrecioNinoUSD(adendum.getPrecioNinoUSD());
        this.setTour(adendum.getTour());
        this.setReservation(reservation);
        this.setPromo(adendum.getPromo());
        this.setIncluyeTransportacion(adendum.isIncluyeTransportacion());
        this.setTipoVehiculo(adendum.getTipoVehiculo());
    }

    public String getNoCupon() {
        return noCupon;
    }

    public void setNoCupon(String noCupon) {
        this.noCupon = noCupon;
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

}
