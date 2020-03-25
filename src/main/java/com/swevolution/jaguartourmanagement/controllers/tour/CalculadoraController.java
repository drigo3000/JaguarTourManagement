/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.tour;

import com.swevolution.jaguartourmanagement.business.entityfacades.AdendumFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.IMAFacade;
import com.swevolution.jaguartourmanagement.model.entities.Adendum;
import com.swevolution.jaguartourmanagement.model.entities.Agency;
import com.swevolution.jaguartourmanagement.model.entities.Hotel;
import com.swevolution.jaguartourmanagement.model.entities.IMA;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
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
public class CalculadoraController implements Serializable {

    @EJB
    private AdendumFacade adendumFacade;
    @EJB
    private IMAFacade imaFacade;
    private Tour tourOperado;
    private int adultosOperado;
    private int ninosOperado;
    private BigDecimal impuestoOperado;
    private BigDecimal imaOperado;
    private String promo;
    private boolean transportacion;
    private boolean buceo;
    private String tipoVehiculo;

    private Tour tourCuponeado;
    private int adultosCuponeado;
    private int ninosCuponeado;
    private BigDecimal impuestoCuponeado;
    private BigDecimal netoCuponeado;

    private Agency agencia;
    private Hotel hotel;

    public void calcular() {
        Adendum cuponeado = adendumFacade.find(agencia, hotel.getOperation(), tourCuponeado, promo, transportacion, tipoVehiculo, buceo);
        calcularIma();
        netoCuponeado = cuponeado.getNetoCuponeado(adultosCuponeado, ninosCuponeado, impuestoCuponeado);
    }

    @PostConstruct
    private void init() {
        impuestoCuponeado = BigDecimal.ZERO;
        impuestoOperado = BigDecimal.ZERO;

    }

    public Tour getTourOperado() {
        return tourOperado;
    }

    public void setTourOperado(Tour tourOperado) {
        this.tourOperado = tourOperado;
    }

    public int getAdultosOperado() {
        return adultosOperado;
    }

    public void setAdultosOperado(int adultosOperado) {
        this.adultosOperado = adultosOperado;
    }

    public int getNinosOperado() {
        return ninosOperado;
    }

    public void setNinosOperado(int ninosOperado) {
        this.ninosOperado = ninosOperado;
    }

    public BigDecimal getImpuestoOperado() {
        return impuestoOperado;
    }

    public void setImpuestoOperado(BigDecimal impuestoOperado) {
        this.impuestoOperado = impuestoOperado;
    }

    public Tour getTourCuponeado() {
        return tourCuponeado;
    }

    public void setTourCuponeado(Tour tourCuponeado) {
        this.tourCuponeado = tourCuponeado;
    }

    public int getAdultosCuponeado() {
        return adultosCuponeado;
    }

    public void setAdultosCuponeado(int adultosCuponeado) {
        this.adultosCuponeado = adultosCuponeado;
    }

    public int getNinosCuponeado() {
        return ninosCuponeado;
    }

    public void setNinosCuponeado(int ninosCuponeado) {
        this.ninosCuponeado = ninosCuponeado;
    }

    public BigDecimal getImpuestoCuponeado() {
        return impuestoCuponeado;
    }

    public void setImpuestoCuponeado(BigDecimal impuestoCuponeado) {
        this.impuestoCuponeado = impuestoCuponeado;
    }

    public Agency getAgencia() {
        return agencia;
    }

    public void setAgencia(Agency agencia) {
        this.agencia = agencia;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public BigDecimal getImaOperado() {
        return imaOperado;
    }

    public void setImaOperado(BigDecimal imaOperado) {
        this.imaOperado = imaOperado;
    }

    public BigDecimal getNetoCuponeado() {
        return netoCuponeado;
    }

    public void setNetoCuponeado(BigDecimal netoCuponeado) {
        this.netoCuponeado = netoCuponeado;
    }

    public void calcularIma() {
        IMA ima = imaFacade.findByTourAgency(tourOperado.getId(), agencia.getId());
        BigDecimal imaAdultos = ima.getImaAdultoUSD().multiply(new BigDecimal(adultosOperado));
        BigDecimal imaNinos = ima.getImaNinoUSD().multiply(new BigDecimal(ninosOperado));
        imaOperado = imaAdultos.add(imaNinos);
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public boolean isTransportacion() {
        return transportacion;
    }

    public void setTransportacion(boolean transportacion) {
        this.transportacion = transportacion;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public boolean isBuceo() {
        return buceo;
    }

    public void setBuceo(boolean buceo) {
        this.buceo = buceo;
    }

}
