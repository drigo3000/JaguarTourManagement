/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations.operaciones;

import com.swevolution.jaguartourmanagement.model.entities.Reservation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class ListaPaxPorUnidadRetornoController implements Serializable {

    @Inject
    private OperacionesReservationFilterController rfController;
    private List<PaxPorUnidad> listaPaxPorUnidad;
    private long reservasPendientes = 0;

    @PostConstruct
    private void init() {
        updatePaxPorUnidad();
    }

    public void updatePaxPorUnidad() {
        Map<String, PaxPorUnidad> mapaPaxPorUnidad = new HashMap<>();
        List<Reservation> reservations = rfController.getReservations();
        listaPaxPorUnidad = new ArrayList<>();
        reservasPendientes = 0;
        if (reservations != null) {
            for (Reservation r : reservations) {
                if (r.getUnidadRetorno() != null && !r.getUnidadRetorno().isEmpty()) {
                    String unidad = r.getUnidadRetorno();
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
                        paxPorUnidad.setGuia(r.getGuia());
                        paxPorUnidad.setTarjeta(r.getTarjeta());
                        mapaPaxPorUnidad.putIfAbsent(unidad, paxPorUnidad);
                    }
                } else {
                    reservasPendientes++;
                }
            }
            listaPaxPorUnidad = new ArrayList<>(mapaPaxPorUnidad.values());
        }

    }

    public class PaxPorUnidad {

        private String unidad;
        private String operador;
        private long pax;
        private String tarjeta;
        private String guia;

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

        public String getTarjeta() {
            return tarjeta;
        }

        public void setTarjeta(String tarjeta) {
            this.tarjeta = tarjeta;
        }

        public String getGuia() {
            return guia;
        }

        public void setGuia(String guia) {
            this.guia = guia;
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

}
