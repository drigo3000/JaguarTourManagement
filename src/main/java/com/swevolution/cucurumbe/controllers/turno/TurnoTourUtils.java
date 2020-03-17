/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.turno;

import com.swevolution.cucurumbe.model.entities.HorarioTurnoTour;
import java.io.Serializable;
import java.time.LocalDate;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class TurnoTourUtils implements Serializable {

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
}
