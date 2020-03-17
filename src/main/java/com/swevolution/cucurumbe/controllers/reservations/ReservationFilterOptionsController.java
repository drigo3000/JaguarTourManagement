/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations;

import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Agency;
import com.swevolution.cucurumbe.model.entities.Hotel;
import com.swevolution.cucurumbe.model.entities.Representante;
import com.swevolution.cucurumbe.model.entities.Tour;
import com.swevolution.cucurumbe.model.entities.User;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class ReservationFilterOptionsController implements Serializable {

    private LocalDate from;
    private LocalDate to;
    private String name = "";
    private String claveHotel = "";
    private String numeroReservation = "";
    private String agency = "";
    private boolean dateOperated;
    private List<String> color;
    private List<String> sucursales;
    private List<String> statuses;
    private Tour servicio;
    private String cupon;
    private List<String> lugarReserva;
    private User reservo;
    private Agency agencia;
    private Representante rep;
    private Hotel hotel;
    private StringBuilder searchQuery;
    private String claveDeConfirma;
    private String grupo = "";
    private int view = 1;

    @PostConstruct
    private void init() {
        from = JsfUtil.getCancunDate().plusDays(1);
        to = JsfUtil.getCancunDate().plusDays(1);
        dateOperated = true;
        color = new ArrayList<>();
        sucursales = new ArrayList<>();
        cupon = "";
        grupo = "";
        claveDeConfirma = "";
        lugarReserva = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClaveHotel() {
        return claveHotel;
    }

    public void setClaveHotel(String claveHotel) {
        this.claveHotel = claveHotel;
    }

    public String getNumeroReservation() {
        return numeroReservation;
    }

    public void setNumeroReservation(String numeroReservation) {
        this.numeroReservation = numeroReservation;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public boolean isDateOperated() {
        return dateOperated;
    }

    public void setDateOperated(boolean dateOperated) {
        this.dateOperated = dateOperated;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<String> sucursales) {
        this.sucursales = sucursales;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    public Tour getServicio() {
        return servicio;
    }

    public void setServicio(Tour servicio) {
        this.servicio = servicio;
    }

    public String getCupon() {
        return cupon;
    }

    public void setCupon(String cupon) {
        this.cupon = cupon;
    }

    public List<String> getLugarReserva() {
        return lugarReserva;
    }

    public void setLugarReserva(List<String> lugarReserva) {
        this.lugarReserva = lugarReserva;
    }

    public User getReservo() {
        return reservo;
    }

    public void setReservo(User reservo) {
        this.reservo = reservo;
    }

    public Agency getAgencia() {
        return agencia;
    }

    public void setAgencia(Agency agencia) {
        this.agencia = agencia;
    }

    public Representante getRep() {
        return rep;
    }

    public void setRep(Representante rep) {
        this.rep = rep;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public StringBuilder getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(StringBuilder searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getClaveDeConfirma() {
        return claveDeConfirma;
    }

    public void setClaveDeConfirma(String claveDeConfirma) {
        this.claveDeConfirma = claveDeConfirma;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String createQueryString(String selectQuery) {
        searchQuery = new StringBuilder();
        searchQuery.append(selectQuery);
        searchQuery.append(" FROM Reservation r WHERE ");
        if (dateOperated) {
            searchQuery.append("(r.fechaOperacion BETWEEN :from AND :to) ");
        } else {
            searchQuery.append("(r.localDateCreated BETWEEN :from AND :to) ");
        }
        searchQuery.append("AND r.noCupon LIKE :cupon ");
        if (grupo != null && !grupo.isEmpty()) {
            searchQuery.append("AND r.servicio.grupo LIKE :grupo ");
        }
        searchQuery.append("AND r.claveConfirmacion LIKE :claveConfirma ");
        if (lugarReserva != null && !lugarReserva.isEmpty()) {
            searchQuery.append("AND (");
            for (int i = 0; i < lugarReserva.size(); i++) {
                searchQuery.append("r.lugarReserva = '");
                searchQuery.append(lugarReserva.get(i));
                searchQuery.append("'");
                if (i < lugarReserva.size() - 1) {
                    searchQuery.append(" OR ");
                }
            }
            searchQuery.append(") ");
        }

        if (color != null && !color.isEmpty()) {
            searchQuery.append("AND (");
            for (int i = 0; i < color.size(); i++) {
                searchQuery.append("r.color = '");
                searchQuery.append(color.get(i));
                searchQuery.append("'");
                if (i < color.size() - 1) {
                    searchQuery.append(" OR ");
                }
            }
            searchQuery.append(") ");
        }

        if (statuses != null && !statuses.isEmpty()) {
            searchQuery.append("AND (");
            int c = 0;
            if (statuses.contains("NO SHOW")) {
                searchQuery.append("r.noShow = true ");
                c++;
            }
            if (statuses.contains("CUPONES PENDIENTES")) {
                if (c > 0) {
                    searchQuery.append("OR ");
                }
                searchQuery.append("r.cuponPendiente = true ");
                c++;
            }
            if (statuses.contains("CUPONES CANCELADOS")) {
                if (c > 0) {
                    searchQuery.append("OR ");
                }
                searchQuery.append("r.cuponCancelado = true ");
                c++;
            }
            if (statuses.contains("CUPONES DEVUELTOS")) {
                if (c > 0) {
                    searchQuery.append("OR ");
                }
                searchQuery.append("r.cuponDevuelto = true ");
            }
            searchQuery.append(") ");
        }

        if (sucursales != null && !sucursales.isEmpty()) {
            searchQuery.append("AND (");
            for (int i = 0; i < sucursales.size(); i++) {
                searchQuery.append("r.sucursales = '");
                searchQuery.append(sucursales.get(i));
                searchQuery.append("'");
                if (i < sucursales.size() - 1) {
                    searchQuery.append(" OR ");
                }
            }
            searchQuery.append(") ");
        }

        if (servicio != null) {
            searchQuery.append("AND r.servicio.id = :servicio ");
        }
        if (agencia != null) {
            searchQuery.append("AND r.agencia.id = :agencia ");
        }
        if (rep != null) {
            searchQuery.append("AND r.representante.id = :representante ");
        }
        if (hotel != null) {
            searchQuery.append("AND r.hotel.id = :hotel ");
        }
        if (reservo != null) {
            searchQuery.append("AND r.quienReserva.login = :quienReserva ");
        }
        searchQuery.append("ORDER BY r.dateCreated DESC");
        return searchQuery.toString();
    }

    public String createPreliminaresQueryString(String selectQuery) {
        searchQuery = new StringBuilder();
        searchQuery.append(selectQuery);
        searchQuery.append(" FROM Reservation r WHERE ");
        searchQuery.append("r.fechaOperacion = :fechaOperacion ");
        if (view > 1) {
            searchQuery.append("AND r.localDateUpdated between :fromLocalDate AND :toLocalDate ");
        } else {
            searchQuery.append("AND r.localDateCreated <= :toLocalDate and r.localDateUpdated <= :toLocalDate ");
        }

        searchQuery.append("AND r.noCupon LIKE :cupon ");
        if (grupo != null && !grupo.isEmpty()) {
            searchQuery.append("AND r.servicio.grupo LIKE :grupo ");
        }
        searchQuery.append("AND r.claveConfirmacion LIKE :claveConfirma ");
        if (lugarReserva != null && !lugarReserva.isEmpty()) {
            searchQuery.append("AND (");
            for (int i = 0; i < lugarReserva.size(); i++) {
                searchQuery.append("r.lugarReserva = '");
                searchQuery.append(lugarReserva.get(i));
                searchQuery.append("'");
                if (i < lugarReserva.size() - 1) {
                    searchQuery.append(" OR ");
                }
            }
            searchQuery.append(") ");
        }

        if (color != null && !color.isEmpty()) {
            searchQuery.append("AND (");
            for (int i = 0; i < color.size(); i++) {
                searchQuery.append("r.color = '");
                searchQuery.append(color.get(i));
                searchQuery.append("'");
                if (i < color.size() - 1) {
                    searchQuery.append(" OR ");
                }
            }
            searchQuery.append(") ");
        }

        if (statuses != null && !statuses.isEmpty()) {
            searchQuery.append("AND (");
            int c = 0;
            if (statuses.contains("NO SHOW")) {
                searchQuery.append("r.noShow = true ");
                c++;
            }
            if (statuses.contains("CUPONES PENDIENTES")) {
                if (c > 0) {
                    searchQuery.append("OR ");
                }
                searchQuery.append("r.cuponPendiente = true ");
                c++;
            }
            if (statuses.contains("CUPONES CANCELADOS")) {
                if (c > 0) {
                    searchQuery.append("OR ");
                }
                searchQuery.append("r.cuponCancelado = true ");
                c++;
            }
            if (statuses.contains("CUPONES DEVUELTOS")) {
                if (c > 0) {
                    searchQuery.append("OR ");
                }
                searchQuery.append("r.cuponDevuelto = true ");
            }
            searchQuery.append(") ");
        }

        if (sucursales != null && !sucursales.isEmpty()) {
            searchQuery.append("AND (");
            for (int i = 0; i < sucursales.size(); i++) {
                searchQuery.append("r.sucursales = '");
                searchQuery.append(sucursales.get(i));
                searchQuery.append("'");
                if (i < sucursales.size() - 1) {
                    searchQuery.append(" OR ");
                }
            }
            searchQuery.append(") ");
        }

        if (servicio != null) {
            searchQuery.append("AND r.servicio.id = :servicio ");
        }
        if (agencia != null) {
            searchQuery.append("AND r.agencia.id = :agencia ");
        }
        if (rep != null) {
            searchQuery.append("AND r.representante.id = :representante ");
        }
        if (hotel != null) {
            searchQuery.append("AND r.hotel.id = :hotel ");
        }
        if (reservo != null) {
            searchQuery.append("AND r.quienReserva.login = :quienReserva ");
        }
        searchQuery.append("ORDER BY r.dateCreated DESC");
        return searchQuery.toString();
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

}
