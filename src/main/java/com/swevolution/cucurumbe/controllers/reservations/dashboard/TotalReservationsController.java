/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations.dashboard;

import com.swevolution.cucurumbe.business.entityfacades.ReservationsFacade;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class TotalReservationsController implements Serializable {

    @Inject
    private DashboardReservationsController dashboard;
    @Inject
    private ReservationsFacade rFacade;

}
