/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.user.login;

import com.swevolution.cucurumbe.model.entities.User;
import com.swevolution.cucurumbe.business.entityfacades.UserFacade;
import com.swevolution.cucurumbe.business.session.SessionInfo;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rxkx
 */
@WebFilter(filterName = "RememberMeFilter", urlPatterns = {"/index.xhtml"})
public class RememberMeFilter implements Filter {

    @EJB
    private UserFacade userFacade;
    @EJB
    private SessionInfo session;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;

    public RememberMeFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest r = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            if (r.getParameter("logout") != null && r.getParameter("logout").equals("true")) {
                r.logout();

                Cookie cookie = getCookie(r, "bdtuser");
                if (cookie != null) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    res.addCookie(cookie);
                }

                Cookie cookiep = getCookie(r, "bdtpass");
                if (cookiep != null) {
                    cookiep.setValue(null);
                    cookiep.setMaxAge(0);
                    res.addCookie(cookiep);
                }

            }
            if (r.getUserPrincipal() == null) {
                Cookie cookie = getCookie(r, "bdtuser");
                Cookie passcookie = getCookie(r, "bdtpass");
                if (cookie != null && passcookie != null && cookie.getValue() != null) {
                    User user = userFacade.find(cookie.getValue());
                    r.login(user.getLogin(), passcookie.getValue());
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public Cookie getCookie(HttpServletRequest request, String cookieName) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(cookieName)) {
                        return cookie;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
