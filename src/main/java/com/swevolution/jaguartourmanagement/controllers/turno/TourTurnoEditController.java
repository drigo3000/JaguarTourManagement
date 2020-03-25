/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.turno;

import com.swevolution.jaguartourmanagement.business.entityfacades.HorarioTurnoTourFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.HotelFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TurnoTourFacade;
import com.swevolution.jaguartourmanagement.controllers.tour.TourEditManager;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.HorarioTurnoTour;
import com.swevolution.jaguartourmanagement.model.entities.Hotel;
import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;
import java.io.Serializable;
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
public class TourTurnoEditController implements Serializable {

    @Inject
    private TourEditManager tourEditController;
    @EJB
    private HorarioTurnoTourFacade httFacade;
    @EJB
    private TurnoTourFacade ttFacade;
    @EJB
    private HotelFacade hotelFacade;
    private TurnoTour turno;
    private TurnoTour nuevo;
    private TurnoTour turnoDuplicarHorarios;
    private List<TurnoTour> turnos;
    private List<HorarioTurnoTour> horarios;
    private List<HorarioTurnoTour> selectedSchedules;
    private String scheduleString;
    private String nuevoScheduleString;
    private String scheduleNotes;
    private boolean active;

    @PostConstruct
    private void init() {
        active = true;
        turnos = ttFacade.findByTour(tourEditController.getTour());
        if (turnos == null || turnos.isEmpty()) {
            TurnoTour t = getDefaultTurno();
            ttFacade.create(t);
            getTurnos().add(t);
        }
        loadHorariosTurno();
        nuevo = new TurnoTour();
    }

    private TurnoTour getDefaultTurno() {
        TurnoTour t = new TurnoTour();
        t.setActive(true);
        t.setDescription("Primer Turno");
        t.setName("Descripción Primer Turno");
        t.setTour(tourEditController.getTour());
        return t;
    }

    public TurnoTour getNuevo() {
        return nuevo;
    }

    public void setNuevo(TurnoTour nuevo) {
        this.nuevo = nuevo;
    }

    public TurnoTour getTurnoDuplicarHorarios() {
        return turnoDuplicarHorarios;
    }

    public void setTurnoDuplicarHorarios(TurnoTour turnoDuplicarHorarios) {
        this.turnoDuplicarHorarios = turnoDuplicarHorarios;
    }

    public void copiarDeHorario() {
        try {
            if (turnoDuplicarHorarios != null) {
                if (selectedSchedules != null && !selectedSchedules.isEmpty()) {
                    for (HorarioTurnoTour horario : selectedSchedules) {
                        HorarioTurnoTour h = httFacade.find(horario.getHotel(), turnoDuplicarHorarios);
                        horario.setPickup(h.getPickup());
                        horario.setNotes(h.getNotes());
                        httFacade.edit(horario);
                    }
                } else {
                    JsfUtil.addErrorMessage("Error, no ha seleccionado horarios.");
                }

            } else {
                JsfUtil.addErrorMessage("Error, no ha seleccionado turno.");
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void addTurno() {
        try {
            nuevo.setActive(true);
            nuevo.setTour(tourEditController.getTour());
            ttFacade.create(nuevo);
            turno = nuevo;
            turnos = ttFacade.findByTour(tourEditController.getTour());
            loadHorariosTurno();
            nuevo = new TurnoTour();
            turno = new TurnoTour();
            horarios = null;
            JsfUtil.addSuccessMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void editarTurno() {
        try {
            ttFacade.edit(turno);
            loadHorariosTurno();
            JsfUtil.addSuccessMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public void updateSchedules() {
        try {
            if (selectedSchedules != null && !selectedSchedules.isEmpty()) {
                for (HorarioTurnoTour h : getSelectedSchedules()) {
                    h.setPickup(scheduleString);
                    h.setNuevoPickup(nuevoScheduleString);
                    h.setNotes(scheduleNotes);
                    h.setActive(active);
                    httFacade.edit(h);

                }
                scheduleNotes = "";
                scheduleString = "";
                nuevoScheduleString = "";
                active = true;
                loadHorariosTurno();
                JsfUtil.addSuccessMessage("Éxito");
            } else {
                JsfUtil.addErrorMessage("Error, no ha seleccionado horarios");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Error");
        }
    }

    public TurnoTour getTurno() {
        if (turno == null) {
            turno = new TurnoTour();
        }
        return turno;
    }

    public void setTurno(TurnoTour turno) {
        this.turno = turno;
    }

    private void loadHorariosTurno() {
        try {
            if (turno != null) {
                horarios = new ArrayList<>();
                for (Hotel hotel : hotelFacade.findByPosition()) {
                    HorarioTurnoTour horario = httFacade.findHorarioTurnoTour(turno, hotel);
                    if (horario == null) {
                        horario = new HorarioTurnoTour();
                        horario.setHotel(hotel);
                        horario.setNotes("");
                        horario.setPickup("06:00");
                        horario.setTurno(turno);
                        httFacade.create(horario);
                    }
                    horarios.add(horario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public List<TurnoTour> getTurnos() {
        if (turnos == null) {
            turnos = new ArrayList<>();
        }
        return turnos;
    }

    public void setTurnos(List<TurnoTour> turnos) {
        this.turnos = turnos;
    }

    public List<HorarioTurnoTour> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioTurnoTour> horarios) {
        this.horarios = horarios;
    }

    public void cambiarDeTurno(TurnoTour turno) {
        this.turno = turno;
        loadHorariosTurno();
        JsfUtil.addSuccessMessage("Se seleccionó turno: " + turno.getName());
    }

    public List<HorarioTurnoTour> getSelectedSchedules() {
        if (selectedSchedules == null) {
            selectedSchedules = new ArrayList<>();
        }
        return selectedSchedules;
    }

    public void setSelectedSchedules(List<HorarioTurnoTour> selectedSchedules) {
        this.selectedSchedules = selectedSchedules;
    }

    public String getScheduleString() {
        return scheduleString;
    }

    public void setScheduleString(String scheduleString) {
        this.scheduleString = scheduleString;
    }

    public String getScheduleNotes() {
        return scheduleNotes;
    }

    public void setScheduleNotes(String scheduleNotes) {
        this.scheduleNotes = scheduleNotes;
    }

    public String getNuevoScheduleString() {
        return nuevoScheduleString;
    }

    public void setNuevoScheduleString(String nuevoScheduleString) {
        this.nuevoScheduleString = nuevoScheduleString;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
