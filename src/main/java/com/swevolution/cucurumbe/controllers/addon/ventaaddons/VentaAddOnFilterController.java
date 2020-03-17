/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.controllers.addon.ventaaddons;

import com.swevolution.cucurumbe.business.entityfacades.VentaAddOnFacade;
import com.swevolution.cucurumbe.controllers.addon.VentasAddOnTotalsController;
import com.swevolution.cucurumbe.model.entities.VentaAddOn;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class VentaAddOnFilterController implements Serializable {

    @EJB
    private VentaAddOnFacade vFacade;
    @Inject
    private VentaAddOnFilterOptionsController optionsController;
    @Inject
    VentasAddOnTotalsController totalsController;
    private List<VentaAddOn> ventas;

    public void search() {
        ventas = vFacade.search(optionsController.getAddOn(), optionsController.getGuia(),
                optionsController.getReserva(), optionsController.getCreador(),
                optionsController.getFrom(), optionsController.getTo());
        totalsController.updateTotals(ventas);
    }

    public List<VentaAddOn> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaAddOn> ventas) {
        this.ventas = ventas;
    }

}
