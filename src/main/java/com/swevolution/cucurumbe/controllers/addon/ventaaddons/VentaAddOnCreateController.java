/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.addon.ventaaddons;

import com.swevolution.cucurumbe.business.entityfacades.VentaAddOnFacade;
import com.swevolution.cucurumbe.business.session.SessionInfo;
import com.swevolution.cucurumbe.controllers.utility.EntityCrudController;
import com.swevolution.cucurumbe.model.entities.VentaAddOn;
import java.io.Serializable;
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
public class VentaAddOnCreateController extends EntityCrudController<VentaAddOn> implements Serializable {

    @EJB
    private SessionInfo session;
    @EJB
    private VentaAddOnFacade vFacade;

    @Override
    @PostConstruct
    public void init() {
        reset();
    }

    @Override
    public void reset() {
        entity = new VentaAddOn();
        entity.setActive(true);
        entity.setCantidad(0);
        entity.setAddOn(null);
        entity.setCreador(session.getCurrentUser());
        entity.setGuia(null);
        entity.setReserva(null);
    }

    @Override
    public void action() {
        vFacade.create(entity);
        reset();
    }

}
