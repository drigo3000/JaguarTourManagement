/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class TurnoTourFacade extends AbstractFacade<TurnoTour> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public TurnoTourFacade() {
        super(TurnoTour.class);
    }

    public List<TurnoTour> findByTour(Tour tour) {
        return em.createQuery("SELECT t FROM TurnoTour t WHERE t.tour.id = :tour")
                .setParameter("tour", tour.getId())
                .getResultList();
    }

    public List<TurnoTour> findByTourName(String tourName) {
        return em.createQuery("select t from TurnoTour t where t.tour.name like :tourName")
                .setMaxResults(10)
                .setParameter("tourName", "%" + tourName + "%")
                .getResultList();
    }

    public List<TurnoTour> findAllByTourName() {
        return em.createQuery("select t from TurnoTour t order by t.tour.name ASC")
                .getResultList();
    }

}
