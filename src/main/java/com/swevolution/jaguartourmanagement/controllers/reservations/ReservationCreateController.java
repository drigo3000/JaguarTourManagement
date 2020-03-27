/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations;

import com.swevolution.jaguartourmanagement.business.entityfacades.AdendumFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.HorarioTurnoTourFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.IMAFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.RepresentativeFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsCuponEntryFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TurnoTourFacade;
import com.swevolution.jaguartourmanagement.business.session.SessionInfo;
import com.swevolution.jaguartourmanagement.controllers.turno.TurnoTourUtils;
import com.swevolution.jaguartourmanagement.model.entities.Agency;
import com.swevolution.jaguartourmanagement.model.entities.HorarioTurnoTour;
import com.swevolution.jaguartourmanagement.model.entities.Hotel;
import com.swevolution.jaguartourmanagement.model.entities.Representante;
import com.swevolution.jaguartourmanagement.model.entities.Reservation;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;
import com.swevolution.jsf.webutils.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class ReservationCreateController implements Serializable {

    @Inject
    private ReservationGroupTotals groupTotals;
    @Inject
    private UserReservationsController userReservations;
    @Inject
    private TurnoTourUtils turnoTourUtils;
    @Inject
    private ReservationsUtilityController resUtils;
    @EJB
    private ReservationsCuponEntryFacade rcFacade;
    @EJB
    private HorarioTurnoTourFacade httFacade;
    @EJB
    private RepresentativeFacade repFacade;
    @EJB
    private AdendumFacade adendumFacade;
    @EJB
    private SessionInfo session;
    @EJB
    private ReservationsFacade rFacade;
    @EJB
    private IMAFacade imaFacade;
    @EJB
    private TurnoTourFacade ttFacade;
    private Reservation reservacion;
    private boolean displayConfirmMessage;
    private boolean displayConfirmation;
    private Map<String, Object> options;

    private int activeTab = 0;

    //HORARIOS TOUR
    private List<HorarioTurnoTour> horarios;

    private List<TurnoTour> turnosServicio;

    //PAX POR TIPO VEHICULO
    private String paxPorTipoVehiculo;

    public void create() {
        try {
            reservacion.setAdultosReales(reservacion.getAdulto());
            reservacion.setNinosReales(reservacion.getNino());
            reservacion.setInfantesReales(reservacion.getInfante());
            reservacion.setClaveConfirmacion(resUtils.getConfirma(reservacion));
            reservacion.setLog(getReservationCreteLog());
            rFacade.create(reservacion);
            userReservations.search();
            displayConfirmMessage = false;
            displayConfirmation = true;
            JsfUtil.addSuccessMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    @PostConstruct
    private void init() {
        options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 690);
        options.put("height", 540);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("maximizable", "true");
        reset();
    }

    public Reservation getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservation reservacion) {
        this.reservacion = reservacion;
    }

    public void reset() {
        activeTab = 0;
        reservacion = new Reservation();
        reservacion.setQuienReserva(session.getCurrentUser());
        reservacion.setQuienGeneraReserva("REP");
        reservacion.setActive(true);
        reservacion.setAdulto(0);
        reservacion.setClaveConfirmacion("");
        reservacion.setFechaOperacion(null);
        reservacion.setFechaReserva(JsfUtil.getCancunDate());
        reservacion.setHabitacion("");
        reservacion.setIdioma("");
        reservacion.setInfante(0);
        reservacion.setMeetingPoint("");
        reservacion.setNino(0);
        reservacion.setNoCupon("");
        reservacion.setNombreCliente("");
        reservacion.setObservacionesContables("");
        reservacion.setObservacionesGenerales("");
        reservacion.setObservacionesOperativas("");
        reservacion.setPickup("");
        reservacion.setQuienAutoriza("");
        reservacion.setAdultosReales(0);
        reservacion.setCuponNuevo("");
        reservacion.setDtvMXN(BigDecimal.ZERO);
        reservacion.setDtvUSD(BigDecimal.ZERO);
        reservacion.setFacturacionMXN(BigDecimal.ZERO);
        reservacion.setFacturacionUSD(BigDecimal.ZERO);
        reservacion.setInfantesReales(0);
        reservacion.setNinosReales(0);
        reservacion.setPronosticoMXN(BigDecimal.ZERO);
        reservacion.setPronosticoUSD(BigDecimal.ZERO);
        reservacion.setRealMXN(BigDecimal.ZERO);
        reservacion.setRealTCMXN(BigDecimal.ZERO);
        reservacion.setRealTCUSD(BigDecimal.ZERO);
        reservacion.setRealUSD(BigDecimal.ZERO);
        reservacion.setFotografiaMXN(BigDecimal.ZERO);
        reservacion.setFotografiaUSD(BigDecimal.ZERO);
        reservacion.setComisionesRepUSD(BigDecimal.ZERO);
        reservacion.setSegurosMXN(BigDecimal.ZERO);
        reservacion.setSegurosUSD(BigDecimal.ZERO);
        reservacion.setOtrosIngresosMXN(BigDecimal.ZERO);
        reservacion.setFacturadoPesos(true);
        reservacion.setTipoVehiculo("N/A");
        reservacion.setHorario(null);
        displayConfirmMessage = false;
        displayConfirmation = false;
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
        horarios = httFacade.findHorariosTurnoTourHotel(reservacion.getTour(), reservacion.getHotel());
        reservacion.setHotel(hotel);
        reservacion.setMeetingPoint(hotel.getLugarPickup());
        reservacion.setPickup("");
        reservacion.setHorario(null);
    }

    public void turnoChangedEvent(SelectEvent event) {
        HorarioTurnoTour horario = (HorarioTurnoTour) event.getObject();
        reservacion.setHorario(horario);
        reservacion.setPickup(turnoTourUtils.getHorarioPorFecha(horario, reservacion.getFechaOperacion()));
    }

    public void hotelChanged(SelectEvent event) {
        try {
            Hotel hotel = (Hotel) event.getObject();
            setHotelInformation(hotel);
        } catch (Exception e) {
        }

    }

    public void servicioChanged(SelectEvent event) {
        try {
            Tour tour = (Tour) event.getObject();
            setServicioInfo(tour);
        } catch (Exception e) {

        }
    }

    private void setServicioInfo(Tour servicio) {
        reservacion.setTour(servicio);
        reservacion.setTipoVehiculo("N/A");
        horarios = httFacade.findHorariosTurnoTourHotel(reservacion.getTour(), reservacion.getHotel());
        reservacion.setPickup("");
        groupTotals.updateTotals(reservacion.getTour().getGrupo(), reservacion.getFechaOperacion());
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
        paxPorTipoVehiculo = resUtils.getTotalReservaciones(reservacion.getFechaOperacion(),
                reservacion.getFechaOperacion(), tipoVehiculo);
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
        groupTotals.updateTotals(reservacion.getTour().getGrupo(), reservacion.getFechaOperacion());
    }

    public boolean isDisplayConfirmMessage() {
        return displayConfirmMessage;
    }

    public void setDisplayConfirmMessage(boolean displayConfirmMessage) {
        this.displayConfirmMessage = displayConfirmMessage;
    }

    public void validate() {
        if ((reservacion.getAdulto() + reservacion.getNino() + reservacion.getInfante()) > 0) {
            displayConfirmMessage = true;
        } else {
            JsfUtil.addErrorMessage("No Pax debe ser mayor a 0");
        }
    }

    public void modify() {
        displayConfirmMessage = false;
    }

    public List<HorarioTurnoTour> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioTurnoTour> horarios) {
        this.horarios = horarios;
    }

    public int getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(int activeTab) {
        this.activeTab = activeTab;
    }

    public String getPaxPorTipoVehiculo() {
        return paxPorTipoVehiculo;
    }

    public void setPaxPorTipoVehiculo(String paxPorTipoVehiculo) {
        this.paxPorTipoVehiculo = paxPorTipoVehiculo;
    }

    private String getReservationCreteLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("<br/>*********************************<br/>");
        sb.append("Creado: ").append(JsfUtil.getCancunNow()).append("<br/>");
        sb.append(session.getCurrentUser().getLogin())
                .append(" - ")
                .append(session.getCurrentUser().getName())
                .append("<br/>");
        sb.append("*********************************<br/>");
        return sb.toString();
    }

    public boolean isDisplayConfirmation() {
        return displayConfirmation;
    }

    public void setDisplayConfirmation(boolean displayConfirmation) {
        this.displayConfirmation = displayConfirmation;
    }

}
