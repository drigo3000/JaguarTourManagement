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

package com.swevolution.cucurumbe.controllers.utility.rendering;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rxkx
 */
public class MyViewHandlerWrapper extends ViewHandlerWrapper{
    private ViewHandler wrapped;

    public MyViewHandlerWrapper(ViewHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ViewHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public String calculateRenderKitId(FacesContext context) {
        
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        String userAgent = req.getHeader("user-agent");
        String accept = req.getHeader("Accept");

        if (userAgent != null && accept != null) {
            UserAgentInfo agent = new UserAgentInfo(userAgent, accept);
            if (agent.isMobileDevice()) {
                return "PRIMEFACES_MOBILE";
            }
        }

        return this.wrapped.calculateRenderKitId(context);
    }
}
