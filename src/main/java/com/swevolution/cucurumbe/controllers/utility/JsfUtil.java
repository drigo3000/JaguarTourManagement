package com.swevolution.cucurumbe.controllers.utility;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JsfUtil {

    public static void addCookie(Cookie cookie) {
        getHttpServletResponse().addCookie(cookie);
    }

    public static Cookie getCookie(String cookieName) {
        try {
            Cookie[] cookies = getHttpServletRequest().getCookies();
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

    public static String getResourceString(String resource, Locale locale) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String messageBundleName = facesContext.getApplication().getMessageBundle();
        ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
        return bundle.getString(resource);
    }

    public static Object getPreservedFlashObject(String key) {
        return FacesContext.getCurrentInstance().
                getExternalContext().getFlash().get(key);
    }

    public static void setPreservedFlashObject(String key, Object object) {
        Flash flash = FacesContext.getCurrentInstance().
                getExternalContext().getFlash();
        flash.put(key, object);
        flash.keep(key);
    }

    public static String getFlashInput() {
        Flash flash = FacesContext.getCurrentInstance().
                getExternalContext().getFlash();
        String input = (String) flash.get("inputText");
        flash.keep("inputText");
        return input;
    }

    /**
     * Regresa una lista de elementos que pueden ser utilizados en un SelectOne
     * o un SelectMany
     *
     * @param entities La lista generica de las entidades que seran incluidas en
     * el UIComponent (SelectOne o SelectMany)
     * @param selectOne - Si se selecciona esta opcion la primera opcion del
     * SelectOne o SelectMany sera un String Internacionalizado que indique que
     * se tiene que seleccionar una opcion.
     * @return
     */
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static List<Locale> getLocaleConstants() {
        return Arrays.asList(Locale.getAvailableLocales());
    }

    public static void redirect(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(url);
    }

    public static void setCurrentLocale(Locale locale) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public static TimeZone getCancunTimeZone() {
        return TimeZone.getTimeZone("America/Cancun");
    }

    public static Locale getCurrentLocale() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        if (locale == null) {
            return new Locale.Builder().setLanguage("es").setRegion("MX").build();
        }
        return locale;
    }

    public static Locale getDefaultLocale() {
        return new Locale.Builder().setLanguage("es").setRegion("MX").build();
    }

    public static Iterator<Locale> getSupportedLocales() {
        return FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addErrorMessage(String summary, String detail) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", detail);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addWarnMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING", msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addWarnMessage(String clientID, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING", msg);
        FacesContext.getCurrentInstance().addMessage(clientID, facesMsg);
    }

    public static void addInfoMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addInfoMessage(String msg, String detail) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detail);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void navigateTo(String fromOutcome, String fromAction) {
        FacesContext fc = FacesContext.getCurrentInstance();
        NavigationHandler nh = fc.getApplication().getNavigationHandler();
        nh.handleNavigation(fc, fromOutcome, fromAction);
    }

    /**
     * Get parameter value from web.xml file
     *
     * @param parameter name to look up
     * @return the value of the parameter
     */
    public static String getParameterValue(String parameter) {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return sc.getInitParameter(parameter);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    /**
     * <hr/> <h1>English</h1> Get request paramenter from request scope <hr/>
     * <h1>Español</h1> Regresa el valor del parametro
     *
     * @param key / Clave
     * @return the parameter value <br/> el valor del parametro
     */
    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static UIComponent getContainingForm(UIComponent component) {
        if (!(component.getParent() instanceof UIForm)) {
            return (getContainingForm(component.getParent()));
        } else {
            return component.getParent();
        }
    }

    public static void clearEditableValueHolders(UIComponent form) {
        Iterator<UIComponent> iterator = form.getFacetsAndChildren();
        while (iterator.hasNext()) {
            UIComponent component = iterator.next();
            if (component instanceof EditableValueHolder) {
                ((EditableValueHolder) component).resetValue();
            }
            clearEditableValueHolders(component);
        }
    }

    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    public static ExternalContext getExternalContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext();
    }

    public static HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public static HttpSession getHttpSession(boolean create) {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(create);
    }

    public static Object getManagedBean(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ELContext elc = fc.getELContext();
        ExpressionFactory ef = fc.getApplication().getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(elc, getJsfEl(name), Object.class);
        return ve.getValue(elc);
    }

    public static void resetManagedBean(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ELContext elc = fc.getELContext();
        ExpressionFactory ef = fc.getApplication().getExpressionFactory();
        ef.createValueExpression(elc, getJsfEl(name), Object.class).setValue(elc, null);
    }

    public static String getJsfEl(String value) {
        return "#{" + value + "}";
    }

    public static UIComponent findUIComponent(UIComponent c, String id) {
        if (id.equals(c.getId())) {
            return c;
        }
        Iterator<UIComponent> iterator = c.getFacetsAndChildren();
        while (iterator.hasNext()) {
            UIComponent found = findUIComponent(iterator.next(), id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public static LocalDateTime getCancunNow() {
        ZonedDateTime nowCancun = ZonedDateTime.now(ZoneId.of("America/Cancun"));
        LocalDateTime ldt = nowCancun.toLocalDateTime();
        return ldt;
    }

    public static LocalDate getCancunDate() {
        LocalDateTime ldt = JsfUtil.getCancunNow();
        return LocalDate.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
    }

    public static LocalDate getInicioMes() {
        YearMonth month = YearMonth.from(getCancunDate());
        return month.atDay(1);
    }

    public static LocalDate getFinMes() {
        YearMonth month = YearMonth.from(getCancunDate());
        return month.atEndOfMonth();
    }
}
