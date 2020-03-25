/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations;

import com.swevolution.jaguartourmanagement.business.entityfacades.HorarioTurnoTourFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.IMAFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TurnoTourFacade;
import com.swevolution.jaguartourmanagement.controllers.reservations.reflection.ReservationReflectionController;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.Agency;
import com.swevolution.jaguartourmanagement.model.entities.HorarioTurnoTour;
import com.swevolution.jaguartourmanagement.model.entities.Hotel;
import com.swevolution.jaguartourmanagement.model.entities.IMA;
import com.swevolution.jaguartourmanagement.model.entities.Representante;
import com.swevolution.jaguartourmanagement.model.entities.Reservation;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class ReservationEditController implements Serializable {

    @Inject
    private ReservationGroupTotals groupTotals;
    @Inject
    private ReservationReflectionController reflection;
    @Inject
    private ReservationsUtilityController resUtils;
    @EJB
    private HorarioTurnoTourFacade httFacade;
    private Reservation reservacion;
    @EJB
    private ReservationsFacade rFacade;
    @EJB
    private IMAFacade imaFacade;
    private Map<String, Object> options;
    private boolean displayConfirmMessage;
    private boolean displayConfirmation;
    private int activeTab = 0;
    private Reservation reservacionOriginal;

    private List<HorarioTurnoTour> horarios;
    @EJB
    private TurnoTourFacade ttFacade;
    private List<TurnoTour> turnosServicio;

    //PAX POR TIPO VEHICULO
    private String paxPorTipoVehiculo;

    public BigDecimal getMinimoAceptable() {
        try {
            IMA ima = imaFacade.findByTourAgency(reservacion.getTourCuponeado().getId(), reservacion.getAgencia().getId());
            BigDecimal imaAdultos = ima.getImaAdultoUSD().multiply(new BigDecimal(reservacion.getAdulto()));
            BigDecimal imaNinos = ima.getImaNinoUSD().multiply(new BigDecimal(reservacion.getNino()));
            return imaAdultos.add(imaNinos);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }

    }

    public void edit() {
        try {
            reservacion.setPickup(reservacion.getHorario().getPickup());
            String log = reservacionOriginal.getLog();
            if (log == null) {
                log = "";
            }
            reservacion.setLog(log.concat(reflection.getDifferences(reservacionOriginal, reservacion)));
            reservacion.setClaveConfirmacion(resUtils.getConfirma(reservacionOriginal, reservacion));
            reservacion.setReview(resUtils.verifyIfNeedsReview(reservacionOriginal, reservacion));;
            rFacade.edit(reservacion);
            reservacionOriginal = reservacion.clone();
            displayConfirmMessage = false;
            displayConfirmation = true;
            JsfUtil.addSuccessMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void reset() {
        displayConfirmMessage = false;
        displayConfirmation = false;
        activeTab = 0;
    }

    @PostConstruct
    private void init() {
        options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("width", 690);
        options.put("height", 540);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("maximizable", "true");
        Long id = Long.parseLong(JsfUtil.getRequestParameter("id"));
        reservacion = rFacade.find(id);
        reservacionOriginal = reservacion.clone();
        horarios = httFacade.findHorariosTurnoTourHotel(reservacion.getServicio(), reservacion.getHotel());
        paxPorTipoVehiculo = resUtils.getTotalReservaciones(
                reservacion.getFechaOperacion(),
                reservacion.getFechaOperacion(),
                reservacion.getTipoVehiculo());
        groupTotals.updateTotals(reservacion.getServicio().getGrupo(), reservacion.getFechaOperacion());
    }

    public Reservation getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservation reservacion) {
        this.reservacion = reservacion;
    }

    public void openServicioSearchDialog() {
        PrimeFaces.current().dialog().openDynamic("dialogs/tourSearchDialog",
                options, null);
    }

    public void openHotelSearchDialog() {
        PrimeFaces.current().dialog().openDynamic("dialogs/hotelSearchDialog",
                options, null);
    }

    public void openRepresentativeSearchDialog() {
        PrimeFaces.current().dialog().openDynamic("dialogs/representativeSearchDialog",
                options, null);
    }

    public void openRepresentativeRegisterDialog() {

        PrimeFaces.current().dialog().openDynamic("dialogs/representativeRegisterDialog",
                options, null);
    }

    public void openAgencySearchDialog() {
        PrimeFaces.current().dialog().openDynamic("dialogs/agencySearchDialog",
                options, null);
    }

    public void handleHotelFromDialog(SelectEvent event) {
        try {
            Hotel hotel = (Hotel) event.getObject();
            setHotelInformation(hotel);
        } catch (Exception e) {

        }
    }

    private void setHotelInformation(Hotel hotel) {
        reservacion.setHotel(hotel);
        horarios = httFacade.findHorariosTurnoTourHotel(reservacion.getServicio(), reservacion.getHotel());
        reservacion.setHotel(hotel);
        reservacion.setLugarReserva(hotel.getOperation());
        reservacion.setMeetingPoint(hotel.getLugarPickup());
        reservacion.setPickup("");
    }

    public void hotelChanged(SelectEvent event) {
        Hotel hotel = (Hotel) event.getObject();
        setHotelInformation(hotel);
    }

    public void servicioChanged(SelectEvent event) {
        try {
            Tour tour = (Tour) event.getObject();
            setServicioInfo(tour);
        } catch (Exception e) {

        }

    }

    public void servicioCuponeadoChanged(SelectEvent event) {
        try {
            Tour tour = (Tour) event.getObject();
            setServicioCuponeadoInfo(tour);
        } catch (Exception e) {

        }
    }

    private void setServicioCuponeadoInfo(Tour servicio) {
        reservacion.setTourCuponeado(servicio);
    }

    private void setServicioInfo(Tour servicio) {
        reservacion.setServicio(servicio);
        reservacion.setTipoVehiculo("N/A");
        reservacion.setEst(false);
        reservacion.setAut(false);
        horarios = httFacade.findHorariosTurnoTourHotel(reservacion.getServicio(), reservacion.getHotel());
        turnosServicio = ttFacade.findByTour(servicio);
        reservacion.setPickup("");
        groupTotals.updateTotals(reservacion.getServicio().getGrupo(), reservacion.getFechaOperacion());
    }

    public void handleServicioCuponeadoFromDialog(SelectEvent event) {
        try {
            Tour tour = (Tour) event.getObject();
            setServicioCuponeadoInfo(tour);
        } catch (Exception e) {

        }
    }

    public void handleServicioFromDialog(SelectEvent event) {
        try {
            Tour tour = (Tour) event.getObject();
            setServicioInfo(tour);
        } catch (Exception e) {

        }
    }

    public void handleAgencyFromDialog(SelectEvent event) {
        try {
            Agency agency = (Agency) event.getObject();
            reservacion.setAgencia(agency);
        } catch (Exception e) {

        }
    }

    public void handleRepresentativeFromDialog(SelectEvent event) {
        try {
            Representante rep = (Representante) event.getObject();
            setRepInfo(rep);
        } catch (Exception e) {
        }
    }

    private void setRepInfo(Representante rep) {
        reservacion.setRepresentante(rep);
        reservacion.setAgencia(rep.getAgencia());
    }

    public void handleRepresentativeRegisterFromDialog(SelectEvent event) {
        try {
            Representante rep = (Representante) event.getObject();
            setRepInfo(rep);
        } catch (Exception e) {
        }
    }

    public void repChanged(SelectEvent event) {
        Representante rep = (Representante) event.getObject();
        reservacion.setAgencia(rep.getAgencia());
    }

    public void tipoVehiculoChanged(final AjaxBehaviorEvent event) {
        SelectOneMenu selectOneMenu = (SelectOneMenu) event.getSource();
        String tipoVehiculo = selectOneMenu.getValue().toString();
        paxPorTipoVehiculo = resUtils.getTotalReservaciones(
                reservacion.getFechaOperacion(),
                reservacion.getFechaOperacion(),
                tipoVehiculo);
    }

    public void dateChanged(SelectEvent event) {
        reservacion.setPickup("");
        reservacion.setHorario(null);
        if (!reservacion.getTipoVehiculo().equals("N/A")) {
            paxPorTipoVehiculo = resUtils.getTotalReservaciones(
                    reservacion.getFechaOperacion(),
                    reservacion.getFechaOperacion(),
                    reservacion.getTipoVehiculo()
            );
        } else {
            paxPorTipoVehiculo = "0.0.0";
        }
        groupTotals.updateTotals(reservacion.getServicio().getGrupo(), reservacion.getFechaOperacion());
    }

    public List<HorarioTurnoTour> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioTurnoTour> horarios) {
        this.horarios = horarios;
    }

    public boolean isDisplayConfirmMessage() {
        return displayConfirmMessage;
    }

    public void setDisplayConfirmMessage(boolean displayConfirmMessage) {
        this.displayConfirmMessage = displayConfirmMessage;
    }

    public void modify() {
        displayConfirmMessage = false;
    }

    public void validate() {
        if ((reservacion.getAdulto() + reservacion.getNino() + reservacion.getInfante()) > 0) {
            displayConfirmMessage = true;
        } else {
            JsfUtil.addErrorMessage("No Pax debe ser mayor a 0");
        }
    }

    public int getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(int activeTab) {
        this.activeTab = activeTab;
    }

    public void cambioTransportacion() {
        if (!reservacion.isConTransportacion()) {
            reservacion.setTipoVehiculo("N/A");
            reservacion.setPickup("");
            reservacion.setAut(false);
            reservacion.setEst(false);
        } else {
            reservacion.setAut(true);
            reservacion.setEst(true);
        }
    }

    public String getPaxPorTipoVehiculo() {
        return paxPorTipoVehiculo;
    }

    public void setPaxPorTipoVehiculo(String paxPorTipoVehiculo) {
        this.paxPorTipoVehiculo = paxPorTipoVehiculo;
    }

    public List<TurnoTour> getTurnosServicio() {
        return turnosServicio;
    }

    public void setTurnosServicio(List<TurnoTour> turnosServicio) {
        this.turnosServicio = turnosServicio;
    }

    public Reservation getReservacionOriginal() {
        return reservacionOriginal;
    }

    public void setReservacionOriginal(Reservation reservacionOriginal) {
        this.reservacionOriginal = reservacionOriginal;
    }

    public boolean isDisplayConfirmation() {
        return displayConfirmation;
    }

    public void setDisplayConfirmation(boolean displayConfirmation) {
        this.displayConfirmation = displayConfirmation;
    }

    public String getHorarioPorFecha(HorarioTurnoTour horario, LocalDate fechaOperacion) {
        try {
            LocalDate fechaCambio = horario.getTurno().getFechaDeCambio();
            if (fechaCambio != null) {
                if (fechaOperacion.equals(fechaCambio)
                        || fechaOperacion.isAfter(fechaCambio)) {
                    return horario.getNuevoPickup();
                }
            }
            return horario.getPickup();
        } catch (Exception e) {
            return "";
        }
    }

    public void turnoChangedEvent(SelectEvent event) {
        HorarioTurnoTour horario = (HorarioTurnoTour) event.getObject();
        reservacion.setHorario(horario);
        reservacion.setPickup(getHorarioPorFecha(horario, reservacion.getFechaOperacion()));
    }

}
