/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations.dashboard;

import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TurnoTourFacade;
import com.swevolution.jaguartourmanagement.controllers.reservations.ReservationsUtilityController;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.Reservation;
import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;
import java.io.Serializable;
import java.time.LocalDate;
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
public class DashboardReservationsController implements Serializable {

    @Inject
    private ReservationsUtilityController resUtilities;
    @EJB
    private ReservationsFacade rFacade;
    @EJB
    private TurnoTourFacade ttFacade;
    private List<Reservation> reservaciones;
    private LocalDate from;
    private LocalDate to;
    private String paxJeep;
    private String paxBuggy;
    private String paxReservados;
    private String paxManana;
    private String paxIncentivos;
    private String paxBuceo;
    private String paxCatamaranDeluxe;
    private String paxCamataranSunset;
    private List<TurnoTour> turnos;
    private List<PaxPorTurno> paxPorTurnos;

    //GRAPHS
    private LineChartModel lineModel2;
    private int maxReservas = 0;

    public void search() {
        resetInfo();
    }

    @PostConstruct
    private void init() {

        LocalDate today = from = JsfUtil.getCancunDate();
        from = today.plusDays(1);
        to = today.plusDays(1);
        resetInfo();
    }

    public List<Reservation> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reservation> reservaciones) {
        this.reservaciones = reservaciones;
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

    public String getPaxJeep() {
        return paxJeep;
    }

    public void setPaxJeep(String paxJeep) {
        this.paxJeep = paxJeep;
    }

    public String getPaxBuggy() {
        return paxBuggy;
    }

    public void setPaxBuggy(String paxBuggy) {
        this.paxBuggy = paxBuggy;
    }

    public String getPaxReservados() {
        return paxReservados;
    }

    public void setPaxReservados(String paxReservados) {
        this.paxReservados = paxReservados;
    }

    public String getPaxManana() {
        return paxManana;
    }

    public void setPaxManana(String paxManana) {
        this.paxManana = paxManana;
    }

    public String getPaxIncentivos() {
        return paxIncentivos;
    }

    public void setPaxIncentivos(String paxIncentivos) {
        this.paxIncentivos = paxIncentivos;
    }

    public String getPaxBuceo() {
        return paxBuceo;
    }

    public void setPaxBuceo(String paxBuceo) {
        this.paxBuceo = paxBuceo;
    }

    public String getPaxCatamaranDeluxe() {
        return paxCatamaranDeluxe;
    }

    public void setPaxCatamaranDeluxe(String paxCatamaranDeluxe) {
        this.paxCatamaranDeluxe = paxCatamaranDeluxe;
    }

    public String getPaxCamataranSunset() {
        return paxCamataranSunset;
    }

    public void setPaxCamataranSunset(String paxCamataranSunset) {
        this.paxCamataranSunset = paxCamataranSunset;
    }

    public List<TurnoTour> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<TurnoTour> turnos) {
        this.turnos = turnos;
    }

    private void desplegarPaxTurnos() {
        for (TurnoTour t : turnos) {
            PaxPorTurno pax = new PaxPorTurno();
            pax.setPax(String.valueOf(resUtilities.getNumeroPorTurno(from, to, t)));
            pax.setDesglocePax(resUtilities.getStringPaxPorTurno(from, to, t));
            pax.setTurno(t);
            paxPorTurnos.add(pax);
        }
    }

    private void resetInfo() {
        paxPorTurnos = new ArrayList<>();
        reservaciones = rFacade.findRangeOperation(from, to);
        paxJeep = resUtilities.getTotalReservaciones(from, to, "Jeep");
        paxBuggy = resUtilities.getTotalReservaciones(from, to, "Buggy");
        paxManana = String.valueOf(resUtilities.getStringTotalPaxFecha(from, to));
        paxReservados = String.valueOf(resUtilities.countTotalReservas(from, to));
        paxIncentivos = String.valueOf(rFacade.countIncentivos(from, to));
        paxBuceo = resUtilities.getPaxBuceo(from, to);
        paxCatamaranDeluxe = resUtilities.getPaxCatamaranDeluxe(from, to);
        paxCamataranSunset = resUtilities.getPaxCatamaranSunset(from, to);
        turnos = ttFacade.findAllByTourName();
        desplegarPaxTurnos();
        initNoReservasModel();
    }

    public List<PaxPorTurno> getPaxPorTurnos() {
        return paxPorTurnos;
    }

    public void setPaxPorTurno(List<PaxPorTurno> paxPorTurnos) {
        this.paxPorTurnos = paxPorTurnos;
    }

    private void initNoReservasModel() {
        lineModel2 = initModel();
        lineModel2.setTitle("No Reservas Por Día");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Fechas"));
        Axis yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("No. Reservas");
        yAxis.setMin(0);
        yAxis.setMax(maxReservas + 15);
    }

    private LineChartModel initModel() {
        LineChartModel model = new LineChartModel();
        ChartSeries dates = new ChartSeries();
        LocalDate date = from;
        dates.setLabel(from.getDayOfMonth() + "/" + from.getMonthValue() + " - " + to.getDayOfMonth() + "/" + to.getMonthValue());
        while (date.isBefore(to.plusDays(1))) {
            long max = rFacade.countReservas(date, date);
            dates.set(date.getDayOfMonth() + "/" + date.getMonthValue(), max);
            date = date.plusDays(1);
            if (max > maxReservas) {
                maxReservas = (int) max;
            }
        }
        model.addSeries(dates);
        return model;
    }

    public int getMaxReservas() {
        return maxReservas;
    }

    public void setMaxReservas(int maxReservas) {
        this.maxReservas = maxReservas;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
    }

}
