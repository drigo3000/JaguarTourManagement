/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.representative;

import com.swevolution.jaguartourmanagement.business.entityfacades.RepresentativeFacade;
import com.swevolution.jaguartourmanagement.model.entities.Representante;
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
public class RepresentativeAutocompleteController implements Serializable {

    @EJB
    private RepresentativeFacade repFacade;

    public List<Representante> filterRepresentative(String query) {
        return repFacade.getByName(query, 10, 0);
    }
}
