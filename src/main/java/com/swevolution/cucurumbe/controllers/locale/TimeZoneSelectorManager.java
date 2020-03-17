package com.swevolution.cucurumbe.controllers.locale;

/*
 * Copyright 2014 Rxkx.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class TimeZoneSelectorManager implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(TimeZoneSelectorManager.class.getName());

    private TimeZone timeZone;

    public TimeZone getTimeZone() {
        if (timeZone == null) {
            timeZone = JsfUtil.getCancunTimeZone();
        }
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public SelectItem[] getAvailableTimeZone() {
        try {
            return JsfUtil.getSelectItems(Arrays.asList(TimeZone.getAvailableIDs()), true);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
            return null;
        }
    }
}
