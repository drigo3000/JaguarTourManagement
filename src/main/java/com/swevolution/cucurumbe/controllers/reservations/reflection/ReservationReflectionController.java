/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.reservations.reflection;

import com.swevolution.cucurumbe.business.session.SessionInfo;
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Reservation;
import java.lang.reflect.Field;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@RequestScoped
public class ReservationReflectionController {

    @EJB
    private SessionInfo session;

    public String getDifferences(Reservation oldRes, Reservation newRes) throws IllegalArgumentException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("<br/>*********************************<br/>");
        sb.append(JsfUtil.getCancunNow()).append("<br/>");
        sb.append(session.getCurrentUser().getLogin())
                .append(" - ")
                .append(session.getCurrentUser().getName())
                .append("<br/>");
        Field[] fields = Reservation.class.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Object value1 = f.get(oldRes);
            Object value2 = f.get(newRes);
            if (value1 != null && value2 != null && !value1.equals(value2)) {
                sb.append("Cambió campo ")
                        .append(f.getName())
                        .append((": "))
                        .append(value1.toString())
                        .append(" a ")
                        .append(value2.toString());
                sb.append("<br/>");
            }
        }
        return sb.toString();
    }
}
