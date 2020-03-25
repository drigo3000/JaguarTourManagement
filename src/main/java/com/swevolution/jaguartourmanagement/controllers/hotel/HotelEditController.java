package com.swevolution.jaguartourmanagement.controllers.hotel;

import com.swevolution.jaguartourmanagement.business.entityfacades.HotelFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.UserFacade;
import com.swevolution.jaguartourmanagement.business.session.SessionInfo;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.Hotel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class HotelEditController implements Serializable {

    @EJB
    private UserFacade userFacade;
    @EJB
    private SessionInfo session;
    @EJB
    private HotelFacade hotelController;
    private Hotel hotel;
    private long id;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HotelEditController() {
    }

    public void edit() {
        try {
            hotelController.edit(hotel);
            hotel = hotelController.find(id);
            JsfUtil.addInfoMessage("Éxito");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error");
        }
    }

    public String remove() {
        try {
            hotelController.remove(hotel);
            return "index?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Unable to delete hotel");
            return null;
        }

    }

    @PostConstruct
    private void init() {
        id = Long.parseLong(JsfUtil.getRequestParameter("id"));
        hotel = hotelController.find(id);
    }

    public List<String> getOperations() {
        return userFacade.getOperations(session.getCurrentUser().getCompany());
    }
}
