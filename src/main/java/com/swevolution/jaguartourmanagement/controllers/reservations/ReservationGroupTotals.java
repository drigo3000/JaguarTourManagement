/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations;

import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class ReservationGroupTotals implements Serializable {

    @EJB
    private ReservationsFacade rFacade;
    @EJB
    private TourFacade tourFacade;
    private Map<Tour, String> toursPax;
    private long totalAdultos;
    private long totalNinos;
    private long totalInfantes;

    public void updateTotals(String group, LocalDate fechaOperacion) {
        totalAdultos = 0;
        totalNinos = 0;
        totalInfantes = 0;
        toursPax = new HashMap<>();
        if (group != null && fechaOperacion != null) {
            try {
                List<Tour> toursGrupo = tourFacade.findByGroup(group);
                for (Tour t : toursGrupo) {
                    long adultos = rFacade.countAdults(fechaOperacion, fechaOperacion, t);
                    totalAdultos += adultos;
                    long ninos = rFacade.countNinos(fechaOperacion, fechaOperacion, t);
                    totalNinos += ninos;
                    long infantes = rFacade.countInfants(fechaOperacion, fechaOperacion, t);
                    totalInfantes += infantes;
                    toursPax.putIfAbsent(t, adultos + "." + ninos + "." + infantes);
                }
            } catch (Exception e) {
                JsfUtil.addErrorMessage("Error");
            }
        }

    }

    public Map<Tour, String> getToursPax() {
        return toursPax;
    }

    public void setToursPax(Map<Tour, String> toursPax) {
        this.toursPax = toursPax;
    }

    public long getTotalAdultos() {
        return totalAdultos;
    }

    public void setTotalAdultos(long totalAdultos) {
        this.totalAdultos = totalAdultos;
    }

    public long getTotalNinos() {
        return totalNinos;
    }

    public void setTotalNinos(long totalNinos) {
        this.totalNinos = totalNinos;
    }

    public long getTotalInfantes() {
        return totalInfantes;
    }

    public void setTotalInfantes(long totalInfantes) {
        this.totalInfantes = totalInfantes;
    }

    public List<Map.Entry<Tour, String>> getTours() {
        Set<Map.Entry<Tour, String>> tourSet
                = toursPax.entrySet();
        return new ArrayList<>(tourSet);
    }

}
