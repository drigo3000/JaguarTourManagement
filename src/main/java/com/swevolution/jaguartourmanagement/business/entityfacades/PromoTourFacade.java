/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.PromoTour;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class PromoTourFacade extends AbstractFacade<PromoTour> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public PromoTourFacade() {
        super(PromoTour.class);
    }

    public List<PromoTour> findByTour(Tour tour) {
        return em.createQuery("select p from PromoTour p where p.tour.id = :tourId")
                .setParameter("tourId", tour.getId())
                .getResultList();
    }
}
