/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.session;

import com.swevolution.jsf.webutils.JsfUtil;
import java.util.TimeZone;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@RequestScoped
public class TimeZoneInfo {

    private TimeZone timeZone;

    public TimeZone getTimeZone() {
        timeZone = JsfUtil.getCancunTimeZone();
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

}
