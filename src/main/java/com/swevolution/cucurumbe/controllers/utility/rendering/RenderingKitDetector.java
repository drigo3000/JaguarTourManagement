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

import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class RenderingKitDetector implements Serializable {

    private String renderingKit;

    @PostConstruct
    public void init(){
        getRenderingKit();
    }
    
    public String getRenderingKit() {
        String requestParameter = JsfUtil.getRequestParameter("display");
        if (requestParameter != null && requestParameter.equals("mb")) {
            return renderingKit = "PRIMEFACES_MOBILE";
        } else if(requestParameter != null && requestParameter.equals("dt")) {
            return null;
        }
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String userAgent = request.getHeader("User-Agent");
        String httpAccept = request.getHeader("Accept");
        UserAgentInfo detector = new UserAgentInfo(userAgent, httpAccept);
        if (detector.isMobileDevice()) {
            renderingKit = "PRIMEFACES_MOBILE";
        } else {
            renderingKit = null;
        }
        return renderingKit;
    }

    public void setRenderingKit(String renderingKit) {
        this.renderingKit = renderingKit;
    }

}
