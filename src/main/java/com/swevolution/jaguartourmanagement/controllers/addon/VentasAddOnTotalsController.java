/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.addon;

import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.User;
import com.swevolution.jaguartourmanagement.model.entities.VentaAddOn;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class VentasAddOnTotalsController implements Serializable {

    private int cantidadTotal;
    private Map<Tour, Integer> cantidadPorTour;
    private Map<User, Integer> cantidadPorGuia;

    public void updateTotals(List<VentaAddOn> ventas) {
        cantidadTotal = 0;
        cantidadPorTour = new HashMap<>();
        cantidadPorGuia = new HashMap<>();
        for (VentaAddOn v : ventas) {
            cantidadTotal += v.getCantidad();
            Tour tourKey = v.getReserva().getTour();
            User userKey = v.getGuia();
            if (cantidadPorTour.containsKey(tourKey)) {
                int valor = cantidadPorTour.get(tourKey);
                valor += v.getCantidad();
                cantidadPorTour.replace(tourKey, valor);
            } else {
                cantidadPorTour.putIfAbsent(tourKey, v.getCantidad());
            }

            if (cantidadPorGuia.containsKey(userKey)) {
                int valor = cantidadPorGuia.get(userKey);
                valor += v.getCantidad();
                cantidadPorGuia.replace(userKey, valor);
            } else {
                cantidadPorGuia.putIfAbsent(userKey, v.getCantidad());
            }
        }
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Map<Tour, Integer> getCantidadPorTour() {
        return cantidadPorTour;
    }

    public void setCantidadPorTour(Map<Tour, Integer> cantidadPorTour) {
        this.cantidadPorTour = cantidadPorTour;
    }

    public Map<User, Integer> getCantidadPorGuia() {
        return cantidadPorGuia;
    }

    public void setCantidadPorGuia(Map<User, Integer> cantidadPorGuia) {
        this.cantidadPorGuia = cantidadPorGuia;
    }

    public List<Map.Entry<Tour, Integer>> getTours() {
        Set<Map.Entry<Tour, Integer>> tourSet
                = cantidadPorTour.entrySet();
        return new ArrayList<>(tourSet);
    }

    public List<Map.Entry<User, Integer>> getGuias() {
        Set<Map.Entry<User, Integer>> guideSet
                = cantidadPorGuia.entrySet();
        return new ArrayList<>(guideSet);
    }

}
