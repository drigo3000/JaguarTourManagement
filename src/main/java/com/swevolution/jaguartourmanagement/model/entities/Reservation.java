/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.model.entities;

import com.swevolution.jaguartourmanagement.model.entities.util.PK_Long_Entity;
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
@Table(name = "RESERVATION", catalog = "JTM")
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

    //EXCURSION
    private LocalDate fechaOperacion;
    private LocalDate fechaReserva;

    @ManyToOne
    private Hotel hotel;
    @ManyToOne
    private Agency agencia;
    @ManyToOne
    private User quienReserva;
    @ManyToOne
    private Tour tour;
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

    public int getAdultoCortesia() {
        return adultoCortesia;
    }

    public void setAdultoCortesia(int adultoCortesia) {
        this.adultoCortesia = adultoCortesia;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

}
