/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.entityfacades;

import com.swevolution.cucurumbe.model.entities.EscenarioComercial;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class EscenariosComercialesFacade extends AbstractFacade<EscenarioComercial> {

    @PersistenceContext(unitName = "CUCURUMBE_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public EscenariosComercialesFacade() {
        super(EscenarioComercial.class);
    }

}
