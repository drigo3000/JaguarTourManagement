/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations.rendimiento;

import com.swevolution.cucurumbe.business.entityfacades.IMAFacade;
import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.controllers.reservations.ReservationTotalsController;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Agency;
import com.swevolution.cucurumbe.model.entities.IMA;
import com.swevolution.cucurumbe.model.entities.Reservation;
import com.swevolution.cucurumbe.model.entities.Tour;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class AgenciaRendimientoController implements Serializable {

    @EJB
    private ReservationsFacade rFacade;
    @Inject
    private ReservationTotalsController totalsController;
    @EJB
    private IMAFacade imaFacade;
    private LocalDate from;
    private LocalDate to;
    private List<Reservation> reservas;
    private List<Reservation> reservasCZM;
    private List<Reservation> reservasRVMCUN;
    private List<RegistroRendimientoAgencia> registrosRVMCUN;
    private List<RegistroRendimientoAgencia> registrosCZM;
    private String sucursal;
    private Agency agency;
    private BigDecimal incentivoPax;

    private BigDecimal ingresoBrutoTotal;
    private BigDecimal costoTotal;
    private BigDecimal incentivoTotal;
    private BigDecimal rendimientoTotal;

    private BigDecimal ingresoBrutoTotalCZM;
    private BigDecimal costoTotalCZM;
    private BigDecimal incentivoTotalCZM;
    private BigDecimal rendimientoTotalCZM;

    private int adultos;
    private int ninos;

    private int adultosCZM;
    private int ninosCZM;

    public void search() {
        try {
            resetearContenedores();
            reservas = rFacade.findEfectivas(agency, sucursal, from, to);
            totalsController.getPaxInformation(reservas);
            obtenerReservasRVMCUN();
            obtenerReservasCZM();
            crearInfoRVMCUN();
            crearInfoCZM();
            obtenerTotales();
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void search(Agency agencia) {
        try {
            resetearContenedores();
            reservas = rFacade.findEfectivas(agencia, sucursal, from, to);
            totalsController.getPaxInformation(reservas);
            obtenerReservasRVMCUN();
            obtenerReservasCZM();
            crearInfoRVMCUN();
            crearInfoCZM();
            obtenerTotales();
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Error");
        }
    }

    @PostConstruct
    public void init() {
        resetearContenedores();
        from = JsfUtil.getInicioMes();
        to = JsfUtil.getFinMes();
        incentivoPax = new BigDecimal("5.00");
        registrosRVMCUN = new ArrayList<>();
        registrosCZM = new ArrayList<>();
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public List<Reservation> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservation> reservas) {
        this.reservas = reservas;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    private void obtenerReservasRVMCUN() {
        reservasRVMCUN = new ArrayList<>();
        for (Reservation r : reservas) {
            if (r.getLugarReserva().equals("RVM") || r.getLugarReserva().equals("CUN")) {
                reservasRVMCUN.add(r);
            }
        }
    }

    private void obtenerReservasCZM() {
        reservasCZM = new ArrayList<>();
        for (Reservation r : reservas) {
            if (r.getLugarReserva().equals("CZM")) {
                reservasCZM.add(r);
            }
        }
    }

    private void resetearContenedores() {
        reservas = new ArrayList<>();
    }

    private void crearInfoRVMCUN() {
        registrosRVMCUN = new ArrayList<>();
        for (Reservation r : reservasRVMCUN) {
            RegistroRendimientoAgencia reg = buscarRegistro(registrosRVMCUN, r.getServicio());
            if (reg != null) {
                reg.setPaxAdultos(reg.getPaxAdultos() + r.getAdulto() - r.getAdultoCortesia());
                reg.setPaxNinos(reg.getPaxNinos() + r.getNino());
            } else {
                reg = new RegistroRendimientoAgencia();
                reg.setAgencia(r.getAgencia());
                reg.setClasificacion(r.getAgencia().getClasificacion());
                reg.setPaxAdultos(r.getAdulto() - r.getAdultoCortesia());
                reg.setPaxNinos(r.getNino());
                reg.setServicio(r.getServicio());
                reg.setZona("RVM/CUN");
                registrosRVMCUN.add(reg);
            }
        }

        Collections.sort(registrosRVMCUN, new Comparator<RegistroRendimientoAgencia>() {
            @Override
            public int compare(RegistroRendimientoAgencia o1, RegistroRendimientoAgencia o2) {
                return o1.getServicio().getName().compareTo(o2.getServicio().getName());
            }
        });
    }

    private void crearInfoCZM() {
        registrosCZM = new ArrayList<>();
        for (Reservation r : reservasCZM) {
            RegistroRendimientoAgencia reg = buscarRegistro(registrosCZM, r.getServicio());
            if (reg != null) {
                reg.setPaxAdultos(reg.getPaxAdultos() + r.getAdulto() - r.getAdultoCortesia());
                reg.setPaxNinos(reg.getPaxNinos() + r.getNino());
            } else {
                reg = new RegistroRendimientoAgencia();
                reg.setAgencia(r.getAgencia());
                reg.setClasificacion(r.getAgencia().getClasificacion());
                reg.setPaxAdultos(r.getAdulto() - r.getAdultoCortesia());
                reg.setPaxNinos(r.getNino());
                reg.setServicio(r.getServicio());
                reg.setZona("CZM");
                registrosCZM.add(reg);
            }
        }

        Collections.sort(registrosCZM, new Comparator<RegistroRendimientoAgencia>() {
            @Override
            public int compare(RegistroRendimientoAgencia o1, RegistroRendimientoAgencia o2) {
                return o1.getServicio().getName().compareTo(o2.getServicio().getName());
            }
        });
    }

    private RegistroRendimientoAgencia buscarRegistro(List<RegistroRendimientoAgencia> registros, Tour tour) {
        for (RegistroRendimientoAgencia r : registros) {
            if (r.getServicio().getId() == tour.getId()) {
                return r;
            }
        }
        return null;
    }

    private void obtenerTotales() {
        ingresoBrutoTotal = BigDecimal.ZERO;
        costoTotal = BigDecimal.ZERO;
        incentivoTotal = BigDecimal.ZERO;
        rendimientoTotal = BigDecimal.ZERO;
        adultos = 0;
        adultosCZM = 0;
        ninos = 0;
        ninosCZM = 0;
        for (RegistroRendimientoAgencia r : registrosRVMCUN) {
            ingresoBrutoTotal = ingresoBrutoTotal.add(r.getIngresoTotalBruto());
            costoTotal = costoTotal.add(r.getCostoTotalUSD());
            incentivoTotal = incentivoTotal.add(r.getTotalIncentivo(incentivoPax));
            rendimientoTotal = rendimientoTotal.add(r.getRendimiento());
            adultos += r.getPaxAdultos();
            ninos += r.getPaxNinos();
        }

        ingresoBrutoTotalCZM = BigDecimal.ZERO;
        costoTotalCZM = BigDecimal.ZERO;
        incentivoTotalCZM = BigDecimal.ZERO;
        rendimientoTotalCZM = BigDecimal.ZERO;
        for (RegistroRendimientoAgencia r : registrosCZM) {
            ingresoBrutoTotalCZM = ingresoBrutoTotalCZM.add(r.getIngresoTotalBrutoCZM());
            costoTotalCZM = costoTotalCZM.add(r.getCostoTotalCZM());
            incentivoTotalCZM = incentivoTotalCZM.add(r.getTotalIncentivo(incentivoPax));
            rendimientoTotalCZM = rendimientoTotalCZM.add(r.getRendimientoCZM());
            adultosCZM += r.getPaxAdultos();
            ninosCZM += r.getPaxNinos();
        }
    }

    public class RegistroRendimientoAgencia {

        private Agency agencia;
        private String zona;
        private String clasificacion;
        private Tour servicio;
        private int paxAdultos;
        private int paxNinos;

        public BigDecimal getRendimiento() {
            BigDecimal numero1 = getIngresoTotalBruto().subtract(getCostoTotalUSD());
            BigDecimal numero2 = numero1.subtract(getTotalIncentivo(incentivoPax));
            return numero2;
        }

        public BigDecimal getRendimientoCZM() {
            BigDecimal numero1 = getIngresoTotalBrutoCZM().subtract(getCostoTotalCZM());
            BigDecimal numero2 = numero1.subtract(getTotalIncentivo(incentivoPax));
            return numero2;
        }

        public BigDecimal getIngresoBrutoAdulto() {
            try {
                IMA ima = imaFacade.findByTourAgency(servicio.getId(), agencia.getId());
                BigDecimal imaAdulto;
                if (ima != null) {
                    imaAdulto = ima.getImaAdultoUSD();
                } else {
                    imaAdulto = servicio.getImaAdultoUSD();
                }
                return imaAdulto.multiply(new BigDecimal(getPaxAdultos()));
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }

        public BigDecimal getIngresoBrutoAdultoCZM() {
            try {
                IMA ima = imaFacade.findByTourAgency(servicio.getId(), agencia.getId());
                BigDecimal imaAdulto;
                if (ima != null) {
                    imaAdulto = ima.getImaAdultoCZM();
                } else {
                    imaAdulto = servicio.getImaAdultoCZM();
                }
                return imaAdulto.multiply(new BigDecimal(getPaxAdultos()));
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }

        public BigDecimal getIngresoBrutoNino() {
            try {
                IMA ima = imaFacade.findByTourAgency(servicio.getId(), agencia.getId());
                BigDecimal imaNino;
                if (ima != null) {
                    imaNino = ima.getImaNinoUSD();
                } else {
                    imaNino = servicio.getImaNinoUSD();
                }
                return imaNino.multiply(new BigDecimal(getPaxNinos()));
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }

        public BigDecimal getIngresoBrutoNinoCZM() {
            try {
                IMA ima = imaFacade.findByTourAgency(servicio.getId(), agencia.getId());
                BigDecimal imaNino;
                if (ima != null) {
                    imaNino = ima.getImaNinoCZM();
                } else {
                    imaNino = servicio.getImaNinoCZM();
                }
                return imaNino.multiply(new BigDecimal(getPaxNinos()));
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }

        public BigDecimal getTotalIncentivo(BigDecimal incentivoPorPax) {
            return incentivoPorPax.multiply(new BigDecimal(getTotalPax()));
        }

        public BigDecimal getIngresoTotalBruto() {
            return getIngresoBrutoNino().add(getIngresoBrutoAdulto());
        }

        public BigDecimal getIngresoTotalBrutoCZM() {
            return getIngresoBrutoNinoCZM().add(getIngresoBrutoAdultoCZM());
        }

        public BigDecimal getIngresoAntesDeIncentivos() {
            return getIngresoTotalBruto().min(getCostoTotalUSD());
        }

        public BigDecimal getIngresoAntesDeIncentivosCZM() {
            return getIngresoTotalBrutoCZM().min(getCostoTotalCZM());
        }

        public String getZona() {
            return zona;
        }

        public void setZona(String zona) {
            this.zona = zona;
        }

        public String getClasificacion() {
            return clasificacion;
        }

        public void setClasificacion(String clasificacion) {
            this.clasificacion = clasificacion;
        }

        public Tour getServicio() {
            return servicio;
        }

        public void setServicio(Tour servicio) {
            this.servicio = servicio;
        }

        public int getPaxAdultos() {
            return paxAdultos;
        }

        public void setPaxAdultos(int paxAdultos) {
            this.paxAdultos = paxAdultos;
        }

        public int getPaxNinos() {
            return paxNinos;
        }

        public void setPaxNinos(int paxNinos) {
            this.paxNinos = paxNinos;
        }

        public Agency getAgencia() {
            return agencia;
        }

        public int getTotalPax() {
            return this.getPaxAdultos() + this.getPaxNinos();
        }

        public void setAgencia(Agency agencia) {
            this.agencia = agencia;
        }

        public BigDecimal getCostoTotalUSD() {
            try {
                BigDecimal costoAdultos = servicio.getCostoUnitarioAdulto().multiply(new BigDecimal(paxAdultos));
                BigDecimal costoNinos = servicio.getCostoUnitarioNino().multiply(new BigDecimal(paxNinos));
                return costoAdultos.add(costoNinos);
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }

        public BigDecimal getCostoTotalCZM() {
            try {
                BigDecimal costoAdultos = servicio.getCostoUnitarioAdultoCZM().multiply(new BigDecimal(paxAdultos));
                BigDecimal costoNinos = servicio.getCostoUnitarioNinoCZM().multiply(new BigDecimal(paxNinos));
                return costoAdultos.add(costoNinos);
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }

    }

    public List<Reservation> getReservasCZM() {
        return reservasCZM;
    }

    public void setReservasCZM(List<Reservation> reservasCZM) {
        this.reservasCZM = reservasCZM;
    }

    public List<Reservation> getReservasRVMCUN() {
        return reservasRVMCUN;
    }

    public void setReservasRVMCUN(List<Reservation> reservasRVMCUN) {
        this.reservasRVMCUN = reservasRVMCUN;
    }

    public List<RegistroRendimientoAgencia> getRegistrosRVMCUN() {
        return registrosRVMCUN;
    }

    public void setRegistrosRVMCUN(List<RegistroRendimientoAgencia> registrosRVMCUN) {
        this.registrosRVMCUN = registrosRVMCUN;
    }

    public List<RegistroRendimientoAgencia> getRegistrosCZM() {
        return registrosCZM;
    }

    public void setRegistrosCZM(List<RegistroRendimientoAgencia> registrosCZM) {
        this.registrosCZM = registrosCZM;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public BigDecimal getIncentivoPax() {
        return incentivoPax;
    }

    public void setIncentivoPax(BigDecimal incentivoPax) {
        this.incentivoPax = incentivoPax;
    }

    public BigDecimal getIngresoBrutoTotal() {
        return ingresoBrutoTotal;
    }

    public void setIngresoBrutoTotal(BigDecimal ingresoBrutoTotal) {
        this.ingresoBrutoTotal = ingresoBrutoTotal;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public BigDecimal getIncentivoTotal() {
        return incentivoTotal;
    }

    public void setIncentivoTotal(BigDecimal incentivoTotal) {
        this.incentivoTotal = incentivoTotal;
    }

    public BigDecimal getRendimientoTotal() {
        return rendimientoTotal;
    }

    public void setRendimientoTotal(BigDecimal rendimientoTotal) {
        this.rendimientoTotal = rendimientoTotal;
    }

    public BigDecimal getIngresoBrutoTotalCZM() {
        return ingresoBrutoTotalCZM;
    }

    public void setIngresoBrutoTotalCZM(BigDecimal ingresoBrutoTotalCZM) {
        this.ingresoBrutoTotalCZM = ingresoBrutoTotalCZM;
    }

    public BigDecimal getCostoTotalCZM() {
        return costoTotalCZM;
    }

    public void setCostoTotalCZM(BigDecimal costoTotalCZM) {
        this.costoTotalCZM = costoTotalCZM;
    }

    public BigDecimal getIncentivoTotalCZM() {
        return incentivoTotalCZM;
    }

    public void setIncentivoTotalCZM(BigDecimal incentivoTotalCZM) {
        this.incentivoTotalCZM = incentivoTotalCZM;
    }

    public BigDecimal getRendimientoTotalCZM() {
        return rendimientoTotalCZM;
    }

    public void setRendimientoTotalCZM(BigDecimal rendimientoTotalCZM) {
        this.rendimientoTotalCZM = rendimientoTotalCZM;
    }

    public BigDecimal getPorcentajeCosto() {
        try {
            return getCostoTotal().divide(getIngresoBrutoTotal(), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {;
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPorcentajeCostoCZM() {
        try {
            return getCostoTotalCZM().divide(getIngresoBrutoTotalCZM(), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

    }

    public BigDecimal getPorcentajeIncentivo() {
        try {
            return getIncentivoTotal().divide(getIngresoBrutoTotal(), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPorcentajeIncentivoCZM() {
        try {
            return getIncentivoTotalCZM().divide(getIngresoBrutoTotalCZM(), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPorcentajeRendimiento() {
        try {
            return getRendimientoTotal().divide(getIngresoBrutoTotal(), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPorcentajeRendimientoCZM() {
        try {
            return getRendimientoTotalCZM().divide(getIngresoBrutoTotalCZM(), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public int getAdultos() {
        return adultos;
    }

    public void setAdultos(int adultos) {
        this.adultos = adultos;
    }

    public int getNinos() {
        return ninos;
    }

    public void setNinos(int ninos) {
        this.ninos = ninos;
    }

    public int getAdultosCZM() {
        return adultosCZM;
    }

    public void setAdultosCZM(int adultosCZM) {
        this.adultosCZM = adultosCZM;
    }

    public int getNinosCZM() {
        return ninosCZM;
    }

    public void setNinosCZM(int ninosCZM) {
        this.ninosCZM = ninosCZM;
    }

    public int getTotalPax() {
        return getAdultos() + getAdultosCZM() + getNinos() + getNinosCZM();
    }

}
