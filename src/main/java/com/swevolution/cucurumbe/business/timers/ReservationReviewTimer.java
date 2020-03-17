/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.timers;

import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author Rxkx
 */
@Stateless
public class ReservationReviewTimer {

    @EJB
    private ReservationsFacade rFacade;

    @Schedule(dayOfWeek = "*",
            month = "*",
            hour = "5",
            dayOfMonth = "*",
            year = "*",
            minute = "0",
            second = "0",
            persistent = false)
    private void resetReservations() {
        rFacade.resetReservationReview();
    }

}
