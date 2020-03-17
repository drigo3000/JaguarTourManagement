/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.timers;

import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Reservation;
import com.swevolution.zonemanager.business.messaging.EmailManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Rxkx
 */
@Stateless
public class PreliminaresReservasTimer {

    @EJB
    private EmailManager eManager;
    @EJB
    private ReservationsFacade rFacade;

    public void sendPreliminares() {
        LocalDateTime fromDateTime = JsfUtil.getCancunNow();
        LocalDateTime from = LocalDateTime.of(fromDateTime.getYear(),
                fromDateTime.getMonthValue(),
                fromDateTime.getDayOfMonth(),
                12, 0);
        LocalDate operationDate = JsfUtil.getCancunDate().plusDays(1);
        List<Reservation> reservations = rFacade.findByDateTime(from, operationDate);
    }

}
