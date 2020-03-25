/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.addon;

import com.swevolution.jaguartourmanagement.business.entityfacades.AddOnFacade;
import com.swevolution.jaguartourmanagement.model.entities.AddOn;
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
public class AddOnAutocompleteController implements Serializable {

    @EJB
    private AddOnFacade aFacade;

    public List<AddOn> filterAddOn(String nombre) {
        return aFacade.findByName(nombre);
    }
}
