/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations.agency;

import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsFacade;
import com.swevolution.jaguartourmanagement.controllers.agency.AgencyEditController;
import com.swevolution.jsf.webutils.JsfUtil;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class AgencySalesController implements Serializable {

    @Inject
    private AgencyEditController agencyEditController;
    @EJB
    private ReservationsFacade rFacade;
    private List<AgencySalesInfo> agencySales;
    private String reporte;
    private int cantidad;
    private LineChartModel lineModel1;
    private int max = 0;

    public void search() {
        try {
            agencySales = new ArrayList<>();
            getInfo();
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    @PostConstruct
    private void init() {
        cantidad = 4;
        reporte = "SEMANAL";
        agencySales = new ArrayList<>();
        getInfo();
    }

    private void getInfo() {
        LocalDate today = JsfUtil.getCancunDate();
        switch (reporte) {
            case "DIARIO":
                for (int i = 0; i < cantidad; i++) {
                    LocalDate from = today.minusDays(i);
                    LocalDate to = today.minusDays(i);
                    YearMonth yearMonthObject = YearMonth.of(from.getYear(), from.getMonthValue());
                    int daysInMonth = yearMonthObject.lengthOfMonth();
                    AgencySalesInfo a = new AgencySalesInfo();
                    a.setMeta(agencyEditController.getAgency().getMetaMensual() / daysInMonth);
                    a.setAdultos(rFacade.countAdults(from, to, agencyEditController.getAgency()));
                    a.setNinos(rFacade.countNinos(from, to, agencyEditController.getAgency()));
                    a.setInfantes(rFacade.countInfante(from, to, agencyEditController.getAgency()));
                    a.setFrom(from);
                    a.setTo(to);
                    agencySales.add(a);
                }
                break;
            case "SEMANAL":
                LocalDate monday = today.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
                for (int i = 0; i < cantidad; i++) {
                    if (i != 0) {
                        monday = monday.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
                    }
                    int meta = 0;
                    for (int d = 0; d < 7; d++) {
                        LocalDate day = monday.plusDays(d);
                        YearMonth yearMonthObject = YearMonth.of(day.getYear(), day.getMonthValue());
                        int daysInMonth = yearMonthObject.lengthOfMonth();
                        meta += agencyEditController.getAgency().getMetaMensual() / daysInMonth;
                    }
                    AgencySalesInfo a = new AgencySalesInfo();
                    a.setAdultos(rFacade.countAdults(monday, monday.plusDays(6), agencyEditController.getAgency()));
                    a.setNinos(rFacade.countNinos(monday, monday.plusDays(6), agencyEditController.getAgency()));
                    a.setInfantes(rFacade.countInfante(monday, monday.plusDays(6), agencyEditController.getAgency()));
                    a.setFrom(monday);
                    a.setTo(monday.plusDays(6));
                    a.setMeta(meta);
                    agencySales.add(a);
                }
                break;
            case "MENSUAL":
                for (int i = 0; i < cantidad; i++) {
                    YearMonth month = YearMonth.from(today.minusMonths(i));
                    LocalDate start = month.atDay(1);
                    LocalDate end = month.atEndOfMonth();
                    AgencySalesInfo a = new AgencySalesInfo();
                    a.setAdultos(rFacade.countAdults(start, end, agencyEditController.getAgency()));
                    a.setNinos(rFacade.countNinos(start, end, agencyEditController.getAgency()));
                    a.setInfantes(rFacade.countInfante(start, end, agencyEditController.getAgency()));
                    a.setFrom(start);
                    a.setTo(end);
                    a.setMeta(agencyEditController.getAgency().getMetaMensual());
                    agencySales.add(a);
                }
                break;
            case "ANUAL":
                LocalDate reference = today;
                for (int i = 0; i < cantidad; i++) {
                    reference = reference.minusYears(i);
                    LocalDate start = reference.with(TemporalAdjusters.firstDayOfYear());
                    LocalDate end = reference.with(TemporalAdjusters.lastDayOfYear());
                    AgencySalesInfo a = new AgencySalesInfo();
                    a.setAdultos(rFacade.countAdults(start, end, agencyEditController.getAgency()));
                    a.setNinos(rFacade.countNinos(start, end, agencyEditController.getAgency()));
                    a.setInfantes(rFacade.countInfante(start, end, agencyEditController.getAgency()));
                    a.setFrom(start);
                    a.setTo(end);
                    a.setMeta(agencyEditController.getAgency().getMetaMensual() * 12);
                    agencySales.add(a);
                }
                break;
        }
        lineModel1 = initLinearModel();
        lineModel1.setTitle(reporte);
        lineModel1.setLegendPosition("e");
        lineModel1.setShowPointLabels(true);
        lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Fechas"));
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("No. Pax");
        yAxis.setMin(0);
    }

    public class AgencySalesInfo {

        private long reservas;
        private long adultos;
        private long ninos;
        private long infantes;
        private LocalDate from;
        private LocalDate to;
        private int meta;

        public AgencySalesInfo() {
        }

        public long getReservas() {
            return reservas;
        }

        public void setReservas(long reservas) {
            this.reservas = reservas;
        }

        public long getAdultos() {
            return adultos;
        }

        public void setAdultos(long adultos) {
            this.adultos = adultos;
        }

        public long getNinos() {
            return ninos;
        }

        public void setNinos(long ninos) {
            this.ninos = ninos;
        }

        public long getInfantes() {
            return infantes;
        }

        public void setInfantes(long infantes) {
            this.infantes = infantes;
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

        public int getMeta() {
            return meta;
        }

        public void setMeta(int meta) {
            this.meta = meta;
        }

        public long getTotalPax() {
            return getAdultos() + getNinos() + getInfantes();
        }
    }

    public List<AgencySalesInfo> getAgencySales() {
        return agencySales;
    }

    public void setAgencySales(List<AgencySalesInfo> agencySales) {
        this.agencySales = agencySales;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        ChartSeries series1 = new ChartSeries();
        series1.setLabel("Pax");
        ChartSeries series2 = new ChartSeries();
        series2.setLabel("Meta");
        for (int i = agencySales.size() - 1; i >= 0; i--) {
            AgencySalesInfo a = agencySales.get(i);
            series1.set(a.getFrom().getDayOfMonth() + "/" + a.getFrom().getMonthValue(), a.getTotalPax());
            series2.set(a.getFrom().getDayOfMonth() + "/" + a.getFrom().getMonthValue(), a.getMeta());
        }
        model.addSeries(series1);
        model.addSeries(series2);
        return model;
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

}
