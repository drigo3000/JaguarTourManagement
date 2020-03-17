/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.model.entities;

import com.swevolution.cucurumbe.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "RESERVATION", catalog = "CUCURUMBE")
@XmlRootElement
public class Reservation extends PK_Long_Entity implements Serializable, Cloneable {

    //PARTICIPANTES
    private String nombreCliente;
    private int adulto;
    private int adultoCortesia;
    private String referenciaCortesia;
    private int nino;
    private int infante;
    private String noCupon;
    private String habitacion;
    private String idioma;
    private String pickup;
    private String meetingPoint;
    private String claveConfirmacion;
    private String quienAutoriza;
    private String observacionesOperativas;
    private String observacionesContables;
    private String observacionesGenerales;
    private String quienGeneraReserva;
    private String tipoVehiculo;
    private String nacionalidad;
    private boolean review;

    //OPERACIONES
    private String tarjeta;
    private String operador;
    private String unidad;
    private String unidadRetorno;
    private String operadorRetorno;
    private String dropoff;
    private String guia;

    //EXCURSION
    private LocalDate fechaOperacion;
    private LocalDate fechaReserva;

    private String color;
    private String sucursales;
    private String lugarReserva;

    //CUPONEADO
    private int adultoCuponeado;
    private int ninoCuponeado;
    private int infanteCuponeado;

    private boolean aut;
    private boolean est;
    private String comida;
    private String noJeep;

    @ManyToOne
    private Hotel hotel;
    @ManyToOne
    private Agency agencia;
    @ManyToOne
    private User quienReserva;
    @ManyToOne
    private Tour servicio;
    @ManyToOne
    private Tour tourCuponeado;
    @ManyToOne
    private Representante representante;

    private String observacionesDirectos;
    private BigDecimal pronosticoUSD;
    private BigDecimal pronosticoMXN;
    private BigDecimal realUSD;
    private BigDecimal realMXN;
    private BigDecimal realTCUSD;
    private BigDecimal realTCMXN;
    private BigDecimal dtvUSD;
    private BigDecimal dtvMXN;
    private int adultosReales;
    private int ninosReales;
    private int infantesReales;
    private int buceoAdultos;
    private int buceoAdultosReales;
    private int buceoNinos;
    private int buceoNinosReales;
    private String tipoBuceo;
    private boolean noShow;
    private boolean cuponPendiente;
    private boolean cuponCancelado;
    private boolean cuponDevuelto;
    private BigDecimal facturacionUSD;
    private BigDecimal facturacionMXN;
    private BigDecimal comisionesRepUSD;
    private BigDecimal otrosIngresosMXN;
    private BigDecimal fotografiaMXN;
    private BigDecimal fotografiaUSD;
    private BigDecimal segurosUSD;
    private BigDecimal segurosMXN;
    private String cuponNuevo;
    private LocalDate fechaRecuperado;
    private LocalDate fechaFacturado;
    private String observacionesDiferenciasPax;
    @Lob
    private String log;

    //ADENDUM FIELDS
    boolean conTransportacion;
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
    //IMA
    private BigDecimal imaAdultoUSD;
    private BigDecimal imaNinoUSD;
    private BigDecimal imaAdultoMXN;
    private BigDecimal imaNinoMXN;

    //HORARIOS
    @ManyToOne
    private HorarioTurnoTour horario;

    private boolean facturadoPesos;

    public BigDecimal getTotalCashUSD() {
        BigDecimal total = BigDecimal.ZERO;
        try {
            total = total.add(realUSD);
            total = total.add(realTCUSD);
            total = total.add(dtvUSD);
            total = total.subtract(comisionesRepUSD);
            return total;
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

    }

    public BigDecimal getTotalCashMXN() {
        BigDecimal total = BigDecimal.ZERO;
        try {
            total = total.add(realMXN);
            total = total.add(realTCMXN);
            total = total.add(dtvMXN);
            total = total.add(otrosIngresosMXN);
            return total;

        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

    }

    public int getTotalPax() {
        return this.getAdulto() + this.getNino() + this.getInfante();
    }

    public BigDecimal getDiferenciaPesos() {
        BigDecimal totalPesosDeUSD = getTotalCashUSD().multiply(new BigDecimal("19.0"));
        BigDecimal totalMXN = totalPesosDeUSD.add(getTotalCashMXN());
        BigDecimal totalPronosticoMXNDeUSD = getPronosticoUSD().multiply(new BigDecimal("19.0"));
        BigDecimal totalPronostico = getPronosticoMXN().add(totalPronosticoMXNDeUSD);
        BigDecimal totalComisionesRepMXN = getComisionesRepUSD().multiply(new BigDecimal("19.0"));
        BigDecimal totalDeducciones = totalPronostico.add(totalComisionesRepMXN);
        return totalMXN.subtract(totalDeducciones);
    }

    public BigDecimal getTotalGeneralUSD() {
        try {
            return getTotalCashUSD().add(facturacionUSD);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

    }

    public BigDecimal getTotalGeneralMXN() {
        try {
            return getTotalCashMXN().add(facturacionMXN);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

    }

    public Reservation() {
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSucursales() {
        return sucursales;
    }

    public void setSucursales(String sucursales) {
        this.sucursales = sucursales;
    }

    public Tour getServicio() {
        return servicio;
    }

    public void setServicio(Tour servicio) {
        this.servicio = servicio;
    }

    public String getLugarReserva() {
        return lugarReserva;
    }

    public void setLugarReserva(String lugarReserva) {
        this.lugarReserva = lugarReserva;
    }

    public int getAdulto() {
        return adulto;
    }

    public void setAdulto(int adulto) {
        this.adulto = adulto;
    }

    public int getNino() {
        return nino;
    }

    public void setNino(int nino) {
        this.nino = nino;
    }

    public int getInfante() {
        return infante;
    }

    public void setInfante(int infante) {
        this.infante = infante;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNoCupon() {
        return noCupon;
    }

    public void setNoCupon(String noCupon) {
        this.noCupon = noCupon;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getNoJeep() {
        return noJeep;
    }

    public void setNoJeep(String noJeep) {
        this.noJeep = noJeep;
    }

    public String getClaveConfirmacion() {
        return claveConfirmacion;
    }

    public void setClaveConfirmacion(String claveConfirmacion) {
        this.claveConfirmacion = claveConfirmacion;
    }

    public String getQuienAutoriza() {
        return quienAutoriza;
    }

    public void setQuienAutoriza(String quienAutoriza) {
        this.quienAutoriza = quienAutoriza;
    }

    public String getObservacionesOperativas() {
        return observacionesOperativas;
    }

    public void setObservacionesOperativas(String observacionesOperativas) {
        this.observacionesOperativas = observacionesOperativas;
    }

    public String getObservacionesContables() {
        return observacionesContables;
    }

    public void setObservacionesContables(String observacionesContables) {
        this.observacionesContables = observacionesContables;
    }

    public String getObservacionesGenerales() {
        return observacionesGenerales;
    }

    public void setObservacionesGenerales(String observacionesGenerales) {
        this.observacionesGenerales = observacionesGenerales;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Agency getAgencia() {
        return agencia;
    }

    public void setAgencia(Agency agencia) {
        this.agencia = agencia;
    }

    public User getQuienReserva() {
        return quienReserva;
    }

    public void setQuienReserva(User quienReserva) {
        this.quienReserva = quienReserva;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDate fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getObservacionesDirectos() {
        return observacionesDirectos;
    }

    public void setObservacionesDirectos(String observacionesDirectos) {
        this.observacionesDirectos = observacionesDirectos;
    }

    public BigDecimal getPronosticoUSD() {
        if (pronosticoUSD != null) {
            return pronosticoUSD;
        } else {
            return BigDecimal.ZERO;
        }
    }

    public void setPronosticoUSD(BigDecimal pronosticoUSD) {
        this.pronosticoUSD = pronosticoUSD;
    }

    public BigDecimal getPronosticoMXN() {
        if (pronosticoUSD != null) {
            return pronosticoMXN;
        } else {
            return BigDecimal.ZERO;
        }

    }

    public void setPronosticoMXN(BigDecimal pronosticoMXN) {
        this.pronosticoMXN = pronosticoMXN;
    }

    public BigDecimal getRealUSD() {
        return realUSD;
    }

    public void setRealUSD(BigDecimal realUSD) {
        this.realUSD = realUSD;
    }

    public BigDecimal getRealTCUSD() {
        return realTCUSD;
    }

    public void setRealTCUSD(BigDecimal realTCUSD) {
        this.realTCUSD = realTCUSD;
    }

    public BigDecimal getRealTCMXN() {
        return realTCMXN;
    }

    public void setRealTCMXN(BigDecimal realTCMXN) {
        this.realTCMXN = realTCMXN;
    }

    public BigDecimal getDtvUSD() {
        return dtvUSD;
    }

    public void setDtvUSD(BigDecimal dtvUSD) {
        this.dtvUSD = dtvUSD;
    }

    public BigDecimal getDtvMXN() {
        return dtvMXN;
    }

    public void setDtvMXN(BigDecimal dtvMXN) {
        this.dtvMXN = dtvMXN;
    }

    public int getAdultosReales() {
        return adultosReales;
    }

    public void setAdultosReales(int adultosReales) {
        this.adultosReales = adultosReales;
    }

    public int getNinosReales() {
        return ninosReales;
    }

    public void setNinosReales(int ninosReales) {
        this.ninosReales = ninosReales;
    }

    public int getInfantesReales() {
        return infantesReales;
    }

    public void setInfantesReales(int infantesReales) {
        this.infantesReales = infantesReales;
    }

    public int getBuceoAdultos() {
        return buceoAdultos;
    }

    public void setBuceoAdultos(int buceoAdultos) {
        this.buceoAdultos = buceoAdultos;
    }

    public int getBuceoNinos() {
        return buceoNinos;
    }

    public void setBuceoNinos(int buceoNinos) {
        this.buceoNinos = buceoNinos;
    }

    public BigDecimal getFacturacionUSD() {
        return facturacionUSD;
    }

    public void setFacturacionUSD(BigDecimal facturacionUSD) {
        this.facturacionUSD = facturacionUSD;
    }

    public BigDecimal getFacturacionMXN() {
        return facturacionMXN;
    }

    public void setFacturacionMXN(BigDecimal facturacionMXN) {
        this.facturacionMXN = facturacionMXN;
    }

    public String getCuponNuevo() {
        return cuponNuevo;
    }

    public void setCuponNuevo(String cuponNuevo) {
        this.cuponNuevo = cuponNuevo;
    }

    public LocalDate getFechaRecuperado() {
        return fechaRecuperado;
    }

    public void setFechaRecuperado(LocalDate fechaRecuperado) {
        this.fechaRecuperado = fechaRecuperado;
    }

    public LocalDate getFechaFacturado() {
        return fechaFacturado;
    }

    public void setFechaFacturado(LocalDate fechaFacturado) {
        this.fechaFacturado = fechaFacturado;
    }

    public String getObservacionesDiferenciasPax() {
        return observacionesDiferenciasPax;
    }

    public void setObservacionesDiferenciasPax(String observacionesDiferenciasPax) {
        this.observacionesDiferenciasPax = observacionesDiferenciasPax;
    }

    public BigDecimal getRealMXN() {
        return realMXN;
    }

    public void setRealMXN(BigDecimal realMXN) {
        this.realMXN = realMXN;
    }

    public int getBuceoAdultosReales() {
        return buceoAdultosReales;
    }

    public void setBuceoAdultosReales(int buceoAdultosReales) {
        this.buceoAdultosReales = buceoAdultosReales;
    }

    public int getBuceoNinosReales() {
        return buceoNinosReales;
    }

    public void setBuceoNinosReales(int buceoNinosReales) {
        this.buceoNinosReales = buceoNinosReales;
    }

    public boolean isCuponPendiente() {
        return cuponPendiente;
    }

    public void setCuponPendiente(boolean cuponPendiente) {
        this.cuponPendiente = cuponPendiente;
    }

    public boolean isCuponCancelado() {
        return cuponCancelado;
    }

    public void setCuponCancelado(boolean cuponCancelado) {
        this.cuponCancelado = cuponCancelado;
    }

    public boolean isCuponDevuelto() {
        return cuponDevuelto;
    }

    public void setCuponDevuelto(boolean cuponDevuelto) {
        this.cuponDevuelto = cuponDevuelto;
    }

    public boolean getNoShow() {
        return noShow;
    }

    public void setNoShow(boolean noShow) {
        this.noShow = noShow;
    }

    public BigDecimal getComisionesRepUSD() {
        return comisionesRepUSD;
    }

    public void setComisionesRepUSD(BigDecimal comisionesRepUSD) {
        this.comisionesRepUSD = comisionesRepUSD;
    }

    public BigDecimal getOtrosIngresosMXN() {
        return otrosIngresosMXN;
    }

    public void setOtrosIngresosMXN(BigDecimal otrosIngresosMXN) {
        this.otrosIngresosMXN = otrosIngresosMXN;
    }

    public BigDecimal getFotografiaMXN() {
        return fotografiaMXN;
    }

    public void setFotografiaMXN(BigDecimal fotografiaMXN) {
        this.fotografiaMXN = fotografiaMXN;
    }

    public BigDecimal getFotografiaUSD() {
        return fotografiaUSD;
    }

    public void setFotografiaUSD(BigDecimal fotografiaUSD) {
        this.fotografiaUSD = fotografiaUSD;
    }

    public BigDecimal getSegurosUSD() {
        return segurosUSD;
    }

    public void setSegurosUSD(BigDecimal segurosUSD) {
        this.segurosUSD = segurosUSD;
    }

    public BigDecimal getSegurosMXN() {
        return segurosMXN;
    }

    public void setSegurosMXN(BigDecimal segurosMXN) {
        this.segurosMXN = segurosMXN;
    }

    public boolean isConTransportacion() {
        return conTransportacion;
    }

    public void setConTransportacion(boolean conTransportacion) {
        this.conTransportacion = conTransportacion;
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

    public BigDecimal getNetoUSD() {
        return getNetoAdultosUSD().add(getNetoNinosUSD());
    }

    public BigDecimal getNetoMXN() {
        return getNetoAdultosMXN().add(getNetoNinosMXN());
    }

    public BigDecimal getNetoRealesUSD() {
        return getNetoAdultosRealesUSD().add(getNetoNinosRealesUSD());
    }

    public BigDecimal getNetoRealesMXN() {
        return getNetoAdultosRealesMXN().add(getNetoNinosRealesMXN());
    }

    public boolean isAut() {
        return aut;
    }

    public void setAut(boolean aut) {
        this.aut = aut;
    }

    public boolean isEst() {
        return est;
    }

    public void setEst(boolean est) {
        this.est = est;
    }

    public BigDecimal getNetoAdultosUSD() {
        try {
            return getPrecioAdultoUSD().multiply(getMultiplyPercent(getComisionAdultoUSD())).multiply(new BigDecimal(getAdulto()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getNetoAdultosRealesUSD() {
        try {
            return getPrecioAdultoUSD().multiply(getMultiplyPercent(
                    getComisionAdultoUSD())).multiply(new BigDecimal(getAdultosReales()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getNetoNinosUSD() {
        try {
            return getPrecioNinoUSD().multiply(getMultiplyPercent(
                    getComisionNinoUSD())).multiply(new BigDecimal(getNino()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getNetoNinosRealesUSD() {
        try {
            return getPrecioNinoUSD().multiply(getMultiplyPercent(
                    getComisionNinoUSD())).multiply(new BigDecimal(getNinosReales()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getNetoAdultosMXN() {
        try {
            return getPrecioAdultoMXN().multiply(
                    getMultiplyPercent(getComisionAdultoMXN())).multiply(new BigDecimal(getAdulto()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getNetoAdultosRealesMXN() {
        try {
            return getPrecioAdultoMXN().multiply(
                    getMultiplyPercent(getComisionAdultoMXN())).multiply(new BigDecimal(getAdultosReales()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getNetoNinosMXN() {
        try {
            return getPrecioNinoMXN().multiply(getMultiplyPercent(getComisionNinoMXN())).multiply(new BigDecimal(getNino()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getNetoNinosRealesMXN() {
        try {
            return getPrecioNinoMXN().multiply(getMultiplyPercent(getComisionNinoMXN()))
                    .multiply(new BigDecimal(getNinosReales()));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal getMultiplyPercent(BigDecimal commision) {
        try {
            BigDecimal one = new BigDecimal("1");
            return one.subtract(commision);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

    }

    public boolean isFacturadoPesos() {
        return facturadoPesos;
    }

    public void setFacturadoPesos(boolean facturadoPesos) {
        this.facturadoPesos = facturadoPesos;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getQuienGeneraReserva() {
        return quienGeneraReserva;
    }

    public void setQuienGeneraReserva(String quienGeneraReserva) {
        this.quienGeneraReserva = quienGeneraReserva;
    }

    public String getTipoBuceo() {
        return tipoBuceo;
    }

    public void setTipoBuceo(String tipoBuceo) {
        this.tipoBuceo = tipoBuceo;
    }

    public Tour getTourCuponeado() {
        return tourCuponeado;
    }

    public void setTourCuponeado(Tour tourCuponeado) {
        this.tourCuponeado = tourCuponeado;
    }

    public int getAdultoCuponeado() {
        return adultoCuponeado;
    }

    public void setAdultoCuponeado(int adultoCuponeado) {
        this.adultoCuponeado = adultoCuponeado;
    }

    public int getNinoCuponeado() {
        return ninoCuponeado;
    }

    public void setNinoCuponeado(int ninoCuponeado) {
        this.ninoCuponeado = ninoCuponeado;
    }

    public int getInfanteCuponeado() {
        return infanteCuponeado;
    }

    public void setInfanteCuponeado(int infanteCuponeado) {
        this.infanteCuponeado = infanteCuponeado;
    }

    public int getAdultoCortesia() {
        return adultoCortesia;
    }

    public void setAdultoCortesia(int adultoCortesia) {
        this.adultoCortesia = adultoCortesia;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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

    public String getReferenciaCortesia() {
        return referenciaCortesia;
    }

    public void setReferenciaCortesia(String referenciaCortesia) {
        this.referenciaCortesia = referenciaCortesia;
    }

    public HorarioTurnoTour getHorario() {
        return horario;
    }

    public void setHorario(HorarioTurnoTour horario) {
        this.horario = horario;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getUnidadRetorno() {
        return unidadRetorno;
    }

    public void setUnidadRetorno(String unidadRetorno) {
        this.unidadRetorno = unidadRetorno;
    }

    public String getOperadorRetorno() {
        return operadorRetorno;
    }

    public void setOperadorRetorno(String operadorRetorno) {
        this.operadorRetorno = operadorRetorno;
    }

    public String getDropoff() {
        return dropoff;
    }

    public void setDropoff(String dropoff) {
        this.dropoff = dropoff;
    }

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    @Override
    public Reservation clone() {
        try {
            return (Reservation) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean isReview() {
        return review;
    }

    public void setReview(boolean review) {
        this.review = review;
    }

}
