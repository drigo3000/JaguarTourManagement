/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.agency;

import com.swevolution.cucurumbe.business.entityfacades.AgencyFacade;
import com.swevolution.cucurumbe.controllers.reservations.rendimiento.AgenciaRendimientoController;
import com.swevolution.cucurumbe.model.entities.Agency;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class AgencyPerformanceDashboard implements Serializable {

    @EJB
    private AgencyFacade aFacade;
    @Inject
    private AgenciaRendimientoController arController;

    private List<Agency> agencias;
    private List<String> sucursales;
    private List<ConcentradoRendimientoAgencias> concentradoAgencias;
    private ConcentradoRendimientoAgencias concentradoAdencia;

    @PostConstruct
    private void init() {
        agencias = aFacade.findAllByName();
        sucursales = new ArrayList<>();
        sucursales.add("CUCURUMBE");
        sucursales.add("CATAMARAN");
        sucursales.add("ONLINE");

        initAgenciesInfo();
    }

    public void initAgenciesInfo() {
        concentradoAgencias = new ArrayList<>();
        for (Agency a : agencias) {
            for (String s : sucursales) {
                arController.setSucursal(s);
                arController.search(a);
                ConcentradoRendimientoAgencias c = new ConcentradoRendimientoAgencias();
                c.setId(UUID.randomUUID().toString());
                c.setAgencia(a);
                c.setSucursal(s);
                c.setPax(arController.getTotalPax());
                c.setIngresoBruto(arController.getIngresoBrutoTotal().add(arController.getIngresoBrutoTotalCZM()));
                c.setCostoTotal(arController.getCostoTotal().add(arController.getCostoTotalCZM()));
                c.setIncentivoTotal(arController.getIncentivoTotal().add(arController.getIncentivoTotalCZM()));
                c.setRendimiento(arController.getRendimientoTotal().add(arController.getRendimientoTotalCZM()));
                concentradoAgencias.add(c);
            }
        }
    }

    public class ConcentradoRendimientoAgencias {

        private String id;
        private Agency agencia;
        private String sucursal;
        private int pax;
        private BigDecimal ingresoBruto;
        private BigDecimal costoTotal;
        private BigDecimal incentivoTotal;
        private BigDecimal rendimiento;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSucursal() {
            return sucursal;
        }

        public void setSucursal(String sucursal) {
            this.sucursal = sucursal;
        }

        public Agency getAgencia() {
            return agencia;
        }

        public void setAgencia(Agency agencia) {
            this.agencia = agencia;
        }

        public BigDecimal getCostoTotal() {
            return costoTotal;
        }

        public void setCostoTotal(BigDecimal costoTotal) {
            this.costoTotal = costoTotal;
        }

        public int getPax() {
            return pax;
        }

        public void setPax(int pax) {
            this.pax = pax;
        }

        public BigDecimal getIngresoBruto() {
            return ingresoBruto;
        }

        public void setIngresoBruto(BigDecimal ingresoBruto) {
            this.ingresoBruto = ingresoBruto;
        }

        public BigDecimal getIncentivoTotal() {
            return incentivoTotal;
        }

        public void setIncentivoTotal(BigDecimal incentivoTotal) {
            this.incentivoTotal = incentivoTotal;
        }

        public BigDecimal getRendimiento() {
            return rendimiento;
        }

        public void setRendimiento(BigDecimal rendimiento) {
            this.rendimiento = rendimiento;
        }

        public BigDecimal getPorcentajeCosto() {
            try {
                return getCostoTotal().divide(getIngresoBruto(), 4, RoundingMode.HALF_UP);
            } catch (Exception e) {;
                return BigDecimal.ZERO;
            }
        }

        public BigDecimal getPorcentajeIncentivo() {
            try {
                return getIncentivoTotal().divide(getIngresoBruto(), 4, RoundingMode.HALF_UP);
            } catch (Exception e) {;
                return BigDecimal.ZERO;
            }
        }

        public BigDecimal getPorcentajeRendimiento() {
            try {
                return getRendimiento().divide(getIngresoBruto(), 4, RoundingMode.HALF_UP);
            } catch (Exception e) {;
                return BigDecimal.ZERO;
            }
        }

    }

    public AgencyFacade getaFacade() {
        return aFacade;
    }

    public void setaFacade(AgencyFacade aFacade) {
        this.aFacade = aFacade;
    }

    public List<Agency> getAgencias() {
        return agencias;
    }

    public void setAgencias(List<Agency> agencias) {
        this.agencias = agencias;
    }

    public List<String> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<String> sucursales) {
        this.sucursales = sucursales;
    }

    public List<ConcentradoRendimientoAgencias> getConcentradoAgencias() {
        return concentradoAgencias;
    }

    public void setConcentradoAgencias(List<ConcentradoRendimientoAgencias> concentradoAgencias) {
        this.concentradoAgencias = concentradoAgencias;
    }

    public void viewPerformance() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        PrimeFaces.current().dialog().openDynamic("dialogs/performance", options, null);
    }

    public ConcentradoRendimientoAgencias getConcentradoAdencia() {
        return concentradoAdencia;
    }

    public void setConcentradoAdencia(ConcentradoRendimientoAgencias concentradoAdencia) {
        this.concentradoAdencia = concentradoAdencia;
    }

    public int getNumeroPax(List<ConcentradoRendimientoAgencias> registros) {
        int total = 0;
        for (ConcentradoRendimientoAgencias c : registros) {
            total += c.getPax();
        }
        return total;
    }

    public BigDecimal sumaTotalIngresoBruto(List<ConcentradoRendimientoAgencias> registros) {
        BigDecimal total = BigDecimal.ZERO;
        for (ConcentradoRendimientoAgencias c : registros) {
            total = total.add(c.getIngresoBruto());
        }
        return total;
    }

    public BigDecimal sumaCostoTotal(List<ConcentradoRendimientoAgencias> registros) {
        BigDecimal total = BigDecimal.ZERO;
        for (ConcentradoRendimientoAgencias c : registros) {
            total = total.add(c.getCostoTotal());
        }
        return total;
    }

    public BigDecimal sumaIncentivoTotal(List<ConcentradoRendimientoAgencias> registros) {
        BigDecimal total = BigDecimal.ZERO;
        for (ConcentradoRendimientoAgencias c : registros) {
            total = total.add(c.getIncentivoTotal());
        }
        return total;
    }

    public BigDecimal sumaRendimientoTotal(List<ConcentradoRendimientoAgencias> registros) {
        BigDecimal total = BigDecimal.ZERO;
        for (ConcentradoRendimientoAgencias c : registros) {
            total = total.add(c.getRendimiento());
        }
        return total;
    }

    public BigDecimal getPorcentajeCosto(List<ConcentradoRendimientoAgencias> registros) {
        try {
            return sumaCostoTotal(registros).divide(sumaTotalIngresoBruto(registros), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPorcentajeIncentivo(List<ConcentradoRendimientoAgencias> registros) {
        try {
            return sumaIncentivoTotal(registros).divide(sumaTotalIngresoBruto(registros), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPorcentajeRendimiento(List<ConcentradoRendimientoAgencias> registros) {
        try {
            return sumaRendimientoTotal(registros).divide(sumaTotalIngresoBruto(registros), 4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

}
