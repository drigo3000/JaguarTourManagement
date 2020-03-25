/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.agency;

import com.swevolution.jaguartourmanagement.business.entityfacades.AgencyFacade;
import com.swevolution.jaguartourmanagement.model.entities.Agency;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class AgencyAutocompleteController implements Serializable {

    @EJB
    private AgencyFacade aFacade;

    public List<Agency> filterAgency(String query) {
        return aFacade.getByName(query, 10, 0);
    }
}
