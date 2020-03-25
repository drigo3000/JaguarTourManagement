/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.VehiculoTour;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class VehiculoTourFacade extends AbstractFacade<VehiculoTour> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public VehiculoTourFacade() {
        super(VehiculoTour.class);
    }

    public List<VehiculoTour> findByTour(Tour tour) {
        return em.createQuery("select v from VehiculoTour v where v.tour.id = :tourId")
                .setParameter("tourId", tour.getId())
                .getResultList();
    }
}
