/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations.operaciones;

import com.swevolution.cucurumbe.business.entityfacades.AgencyFacade;
import com.swevolution.cucurumbe.business.entityfacades.HotelFacade;
import com.swevolution.cucurumbe.business.entityfacades.RepresentativeFacade;
import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.business.entityfacades.TourFacade;
import com.swevolution.cucurumbe.business.entityfacades.UserFacade;
import com.swevolution.cucurumbe.business.session.SessionInfo;
import com.swevolution.cucurumbe.controllers.reservations.ReservationFilterOptionsController;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Representante;
import com.swevolution.cucurumbe.model.entities.Reservation;
import com.swevolution.cucurumbe.model.entities.Tour;
import com.swevolution.cucurumbe.model.entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@ViewScoped
@Named
public class OperacionesReservationFilterController implements Serializable {

    @Inject
    private ReservationFilterOptionsController rfoController;
    @EJB
    private AgencyFacade aFacade;
    @EJB
    private UserFacade uFacade;
    @EJB
    private TourFacade tFacade;
    @EJB
    private SessionInfo session;
    @EJB
    private HotelFacade hFacade;
    @EJB
    private RepresentativeFacade repFacade;
    @EJB
    private ReservationsFacade rFacade;
    private long adultos;
    private long ninos;
    private long infantes;
    private long totalPax;

    //OPERACIONES
    private String unidad;
    private String operador;
    private String dropoff;
    private String tarjeta;
    private List<PaxPorUnidad> listaPaxPorUnidad;
    private List<Reservation> selectedReservations;
    private Map<String, List<Reservation>> mapaReservasAsignadas;
    private Map<String, List<Reservation>> mapaReservasDropoffs;
    private long reservasPendientes = 0;
    private long reservasCanceladas = 0;

    private List<Reservation> reservations;

    public void search() {
        reservations = rFacade.findForPickups(rfoController.getFrom());
        updateInformation();
        JsfUtil.addSuccessMessage("Búsqueda completada<br/>Total de registros: " + reservations.size());
    }

    @PostConstruct
    private void init() {
        unidad = "";
        operador = "";
        dropoff = "";
        tarjeta = "";
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Tour> filterTour(String query) {
        return tFacade.getByName(query, 10, 0);
    }

    public List<User> filterUser(String query) {
        return uFacade.getUsers(query, 10, 0);
    }

    public List<Representante> filterRepresentative(String query) {
        return repFacade.getByName(query, 10, 0);
    }

    public long count() {
        if (reservations != null) {
            return reservations.size();
        }
        return 0;
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

    public long getTotalPax() {
        return totalPax;
    }

    public void setTotalPax(long totalPax) {
        this.totalPax = totalPax;
    }

    //OPERACIONES
    public void setInfo() {
        try {
            for (Reservation r : selectedReservations) {
                r.setUnidad(unidad);
                r.setOperador(operador);
                rFacade.edit(r);
            }
            updateInformation();
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addSuccessMessage("Error");
        }

    }

    public void setDropoffs() {
        try {
            for (Reservation r : selectedReservations) {
                r.setUnidadRetorno(unidad);
                r.setOperadorRetorno(operador);
                r.setDropoff(dropoff);
                r.setTarjeta(tarjeta);
                rFacade.edit(r);
            }
            updateInformation();
            JsfUtil.addSuccessMessage("Exito");
        } catch (Exception e) {
            JsfUtil.addSuccessMessage("Error");
        }

    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getDropoff() {
        return dropoff;
    }

    public void setDropoff(String dropoff) {
        this.dropoff = dropoff;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void updateInformation() {
        adultos = 0;
        ninos = 0;
        infantes = 0;
        Map<String, PaxPorUnidad> mapaPaxPorUnidad = new HashMap<>();
        mapaReservasAsignadas = new HashMap<>();
        listaPaxPorUnidad = new ArrayList<>();
        reservasPendientes = 0;
        reservasCanceladas = 0;
        if (reservations != null) {
            for (Reservation r : reservations) {
                adultos += r.getAdulto();
                ninos += r.getNino();
                infantes += r.getInfante();
                if (r.isCuponCancelado()) {
                    reservasCanceladas++;
                    continue;
                } else if (r.getUnidad() != null && !r.getUnidad().isEmpty()) {
                    String unidad = r.getUnidad();
                    if (unidad != null && !unidad.isEmpty() && mapaPaxPorUnidad.containsKey(unidad)) {
                        PaxPorUnidad paxPorUnidad = mapaPaxPorUnidad.get(unidad);
                        long pax = paxPorUnidad.getPax();
                        pax += r.getTotalPax();
                        paxPorUnidad.setPax(pax);
                    } else {
                        PaxPorUnidad paxPorUnidad = new PaxPorUnidad();
                        paxPorUnidad.setOperador(r.getOperador());
                        paxPorUnidad.setUnidad(unidad);
                        paxPorUnidad.setPax(r.getTotalPax());
                        mapaPaxPorUnidad.putIfAbsent(unidad, paxPorUnidad);
                    }

                    if (unidad != null && !unidad.isEmpty()) {
                        if (mapaReservasAsignadas.containsKey(unidad)) {
                            List<Reservation> res = mapaReservasAsignadas.get(unidad);
                            res.add(r);
                            Collections.sort(res, new Comparator<Reservation>() {
                                @Override
                                public int compare(Reservation o1, Reservation o2) {
                                    return o1.getPickup().compareTo(o2.getPickup());
                                }
                            });
                        } else {
                            List<Reservation> reservations = new ArrayList<>();
                            reservations.add(r);
                            mapaReservasAsignadas.putIfAbsent(unidad, reservations);
                        }
                    }
                } else {
                    reservasPendientes++;
                }
            }
        }
        listaPaxPorUnidad = new ArrayList<>(mapaPaxPorUnidad.values());
        totalPax = adultos + ninos + infantes;

    }

    public class PaxPorUnidad {

        private String unidad;
        private String operador;
        private long pax;

        public String getUnidad() {
            return unidad;
        }

        public void setUnidad(String unidad) {
            this.unidad = unidad;
        }

        public String getOperador() {
            return operador;
        }

        public void setOperador(String operador) {
            this.operador = operador;
        }

        public long getPax() {
            return pax;
        }

        public void setPax(long pax) {
            this.pax = pax;
        }

    }

    public List<PaxPorUnidad> getListaPaxPorUnidad() {
        return listaPaxPorUnidad;
    }

    public void setListaPaxPorUnidad(List<PaxPorUnidad> listaPaxPorUnidad) {
        this.listaPaxPorUnidad = listaPaxPorUnidad;
    }

    public long getReservasPendientes() {
        return reservasPendientes;
    }

    public void setReservasPendientes(long reservasPendientes) {
        this.reservasPendientes = reservasPendientes;
    }

    public List<Reservation> getSelectedReservations() {
        return selectedReservations;
    }

    public void setSelectedReservations(List<Reservation> selectedReservations) {
        this.selectedReservations = selectedReservations;
    }

    public Map<String, List<Reservation>> getMapaReservasAsignadas() {
        return mapaReservasAsignadas;
    }

    public void setMapaReservasAsignadas(Map<String, List<Reservation>> mapaReservasAsignadas) {
        this.mapaReservasAsignadas = mapaReservasAsignadas;
    }

    public Map<String, List<Reservation>> getMapaReservasDropoffs() {
        return mapaReservasDropoffs;
    }

    public void setMapaReservasDropoffs(Map<String, List<Reservation>> mapaReservasDropoffs) {
        this.mapaReservasDropoffs = mapaReservasDropoffs;
    }

    public long getReservasCanceladas() {
        return reservasCanceladas;
    }

    public void setReservasCanceladas(long reservasCanceladas) {
        this.reservasCanceladas = reservasCanceladas;
    }

    public int getPax(List<Reservation> reservations) {
        int pax = 0;
        for (Reservation r : reservations) {
            pax += r.getTotalPax();
        }
        return pax;
    }

}
