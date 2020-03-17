/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations.rendimiento;

import com.swevolution.cucurumbe.business.entityfacades.IMAFacade;
import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.controllers.agency.AgencyEditController;
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
public class AgenciaRendimientoCompletoController implements Serializable {

    @EJB
    private ReservationsFacade rFacade;
    @Inject
    private ReservationTotalsController totalsController;
    @Inject
    private AgencyEditController agencyEditController;
    @EJB
    private IMAFacade imaFacade;
    private LocalDate from;
    private LocalDate to;
    private List<Reservation> reservas;
    private BigDecimal incentivoPax;
    private ContenedorAgencia contenedor;
    private Agency agency;

    public void search(Agency agency) {
        try {
            resetearContenedores();
            reservas = rFacade.findEfectivas(agency, from, to);
            totalsController.getPaxInformation(reservas);
            crearEstructura();
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void search() {
        try {
            resetearContenedores();
            reservas = rFacade.findEfectivas(agencyEditController.getAgency(), from, to);
            totalsController.getPaxInformation(reservas);
            crearEstructura();
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

    private void resetearContenedores() {
        reservas = new ArrayList<>();
    }

    private RegistroRendimientoAgencia buscarRegistro(List<RegistroRendimientoAgencia> registros, Tour tour) {
        for (RegistroRendimientoAgencia r : registros) {
            if (r.getServicio().getId() == tour.getId()) {
                return r;
            }
        }
        return null;
    }

    private void crearEstructura() {
        contenedor = new ContenedorAgencia();
        contenedor.setSucursales(new ArrayList<>());
        agregarSucursales();
        agregarZonas();
        agregarRegistros();
    }

    private void agregarSucursales() {
        Sucursal cucurumbe = new Sucursal();
        cucurumbe.setName("CUCURUMBE");
        cucurumbe.setZonas(new ArrayList<>());

        Sucursal catamaran = new Sucursal();
        catamaran.setName("CATAMARAN");
        catamaran.setZonas(new ArrayList<>());

        Sucursal online = new Sucursal();
        online.setName("ONLINE");
        online.setZonas(new ArrayList<>());

        contenedor.getSucursales().add(cucurumbe);
        contenedor.getSucursales().add(catamaran);
        contenedor.getSucursales().add(online);

    }

    private void agregarZonas() {
        for (Sucursal s : contenedor.getSucursales()) {
            Zona rvmcun = new Zona();
            rvmcun.setName("RVM/CUN");
            rvmcun.setRegistrosRendimiento(new ArrayList<>());
            s.getZonas().add(rvmcun);

            Zona czm = new Zona();
            czm.setName("CZM");
            czm.setRegistrosRendimiento(new ArrayList<>());
            s.getZonas().add(czm);

        }
    }

    private void agregarRegistros() {
        for (Sucursal s : contenedor.getSucursales()) {
            for (Zona z : s.getZonas()) {
                generarRegistrosPorSucursal(s, z);
            }
        }
    }

    private void generarRegistrosPorSucursal(Sucursal s, Zona z) {
        List<RegistroRendimientoAgencia> registros = new ArrayList<>();
        for (Reservation r : reservas) {
            if (r.getSucursales().equals(s.getName())) {
                switch (z.getName()) {
                    case "RVM/CUN":
                        if (r.getLugarReserva().equals("CUN") || r.getLugarReserva().equals("RVM")) {
                            RegistroRendimientoAgencia reg = buscarRegistro(registros, r.getServicio());
                            if (reg != null) {
                                reg.setPaxAdultos(reg.getPaxAdultos() + r.getAdulto() - r.getAdultoCortesia());
                                reg.setPaxNinos(reg.getPaxNinos() + r.getNino());
                            } else {
                                reg = new RegistroRendimientoAgencia();
                                reg.setZona("RVM/CUN");
                                reg.setAgencia(r.getAgencia());
                                reg.setClasificacion(r.getAgencia().getClasificacion());
                                reg.setPaxAdultos(r.getAdulto() - r.getAdultoCortesia());
                                reg.setPaxNinos(r.getNino());
                                reg.setServicio(r.getServicio());
                                registros.add(reg);
                            }
                        }
                        break;
                    case "CZM":
                        if (r.getLugarReserva().equals("CZM")) {
                            RegistroRendimientoAgencia reg = buscarRegistro(registros, r.getServicio());
                            if (reg != null) {
                                reg.setPaxAdultos(reg.getPaxAdultos() + r.getAdulto() - r.getAdultoCortesia());
                                reg.setPaxNinos(reg.getPaxNinos() + r.getNino());
                            } else {
                                reg = new RegistroRendimientoAgencia();
                                reg.setZona("CZM");
                                reg.setAgencia(r.getAgencia());
                                reg.setClasificacion(r.getAgencia().getClasificacion());
                                reg.setPaxAdultos(r.getAdulto() - r.getAdultoCortesia());
                                reg.setPaxNinos(r.getNino());
                                reg.setServicio(r.getServicio());
                                registros.add(reg);
                            }
                        }
                        break;
                }
            }
        }
        z.setRegistrosRendimiento(registros);
    }

    public class ContenedorAgencia {

        private String name;
        private List<Sucursal> sucursales;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Sucursal> getSucursales() {
            return sucursales;
        }

        public void setSucursales(List<Sucursal> sucursales) {
            this.sucursales = sucursales;
        }

    }

    public class Sucursal {

        private String name;
        private List<Zona> zonas;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Zona> getZonas() {
            return zonas;
        }

        public void setZonas(List<Zona> zonas) {
            this.zonas = zonas;
        }

    }

    public class Zona {

        private String name;

        private List<RegistroRendimientoAgencia> registrosRendimiento;

        public List<RegistroRendimientoAgencia> getRegistrosRendimiento() {
            return registrosRendimiento;
        }

        public void setRegistrosRendimiento(List<RegistroRendimientoAgencia> registrosRendimiento) {
            this.registrosRendimiento = registrosRendimiento;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class RegistroRendimientoAgencia {

        private String zona;
        private Agency agencia;
        private String clasificacion;
        private Tour servicio;
        private int paxAdultos;
        private int paxNinos;

        public BigDecimal getRendimiento() {
            BigDecimal numero1 = getIngresoTotalBruto().subtract(getCostoTotalUSD());
            BigDecimal numero2 = numero1.subtract(getTotalIncentivo(incentivoPax));
            return numero2;
        }

        public BigDecimal getIngresoBrutoAdulto() {
            try {
                IMA ima = imaFacade.findByTourAgency(servicio.getId(), agencia.getId());
                BigDecimal imaAdulto;
                if (ima != null) {
                    if (zona.equals("RVM/CUN")) {
                        imaAdulto = ima.getImaAdultoUSD();
                    } else {
                        imaAdulto = ima.getImaAdultoCZM();
                    }
                } else {
                    if (zona.equals("RVM/CUN")) {
                        imaAdulto = servicio.getImaAdultoUSD();
                    } else {
                        imaAdulto = servicio.getImaAdultoCZM();
                    }

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
                    if (zona.equals("RVM/CUN")) {
                        imaNino = ima.getImaNinoUSD();
                    } else {
                        imaNino = ima.getImaNinoCZM();
                    }
                } else {
                    if (zona.equals("RVM/CUN")) {
                        imaNino = servicio.getImaNinoUSD();
                    } else {
                        imaNino = servicio.getImaNinoCZM();
                    }

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

        public BigDecimal getIngresoAntesDeIncentivos() {
            return getIngresoTotalBruto().min(getCostoTotalUSD());
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
                BigDecimal costoAdultos;
                BigDecimal costoNinos;
                if (zona.equals("RVM/CUN")) {
                    costoAdultos = servicio.getCostoUnitarioAdulto().multiply(new BigDecimal(paxAdultos));
                    costoNinos = servicio.getCostoUnitarioNino().multiply(new BigDecimal(paxNinos));
                } else {
                    costoAdultos = servicio.getCostoUnitarioAdultoCZM().multiply(new BigDecimal(paxAdultos));
                    costoNinos = servicio.getCostoUnitarioNinoCZM().multiply(new BigDecimal(paxNinos));
                }
                return costoAdultos.add(costoNinos);
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }

        public String getZona() {
            return zona;
        }

        public void setZona(String zona) {
            this.zona = zona;
        }

    }

    public ContenedorAgencia getContenedor() {
        return contenedor;
    }

    public void setContenedor(ContenedorAgencia contenedor) {
        this.contenedor = contenedor;
    }

    public BigDecimal sumaIngresoTotalBruto(List<RegistroRendimientoAgencia> registros) {
        BigDecimal total = BigDecimal.ZERO;
        for (RegistroRendimientoAgencia r : registros) {
            total = total.add(r.getIngresoTotalBruto());
        }
        return total;
    }

    public BigDecimal sumaCostoTotal(List<RegistroRendimientoAgencia> registros) {
        BigDecimal total = BigDecimal.ZERO;
        for (RegistroRendimientoAgencia r : registros) {
            total = total.add(r.getCostoTotalUSD());
        }
        return total;
    }

    public BigDecimal sumaIncentivoTotal(List<RegistroRendimientoAgencia> registros) {
        BigDecimal total = BigDecimal.ZERO;
        for (RegistroRendimientoAgencia r : registros) {
            total = total.add(r.getTotalIncentivo(incentivoPax));
        }
        return total;
    }

    public BigDecimal sumaRendimientoTotal(List<RegistroRendimientoAgencia> registros) {
        BigDecimal total = BigDecimal.ZERO;
        for (RegistroRendimientoAgencia r : registros) {
            total = total.add(r.getRendimiento());
        }
        return total;
    }

    public BigDecimal getIncentivoPax() {
        return incentivoPax;
    }

    public void setIncentivoPax(BigDecimal incentivoPax) {
        this.incentivoPax = incentivoPax;
    }

    public BigDecimal getPorcentajeCosto(List<RegistroRendimientoAgencia> registros) {
        try {
            return sumaCostoTotal(registros).divide(sumaIngresoTotalBruto(registros), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPorcentajeIncentivo(List<RegistroRendimientoAgencia> registros) {
        try {
            return sumaIncentivoTotal(registros).divide(sumaIngresoTotalBruto(registros), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPorcentajeRendimiento(List<RegistroRendimientoAgencia> registros) {
        try {
            return sumaRendimientoTotal(registros).divide(sumaIngresoTotalBruto(registros), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public int getAdultos(List<RegistroRendimientoAgencia> registros) {
        int total = 0;
        for (RegistroRendimientoAgencia r : registros) {
            total += r.getPaxAdultos();
        }
        return total;
    }

    public int getNinos(List<RegistroRendimientoAgencia> registros) {
        int total = 0;
        for (RegistroRendimientoAgencia r : registros) {
            total += r.getPaxNinos();
        }
        return total;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

}
