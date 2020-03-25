/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.TourPhoto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class TourPhotoFacade extends AbstractFacade<TourPhoto> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public TourPhotoFacade() {
        super(TourPhoto.class);
    }

    public List<TourPhoto> findByTour(Long id) {
        return em.createQuery("select p from TourPhoto p where p.tour.id =:id")
                .setParameter("id", id)
                .getResultList();
    }
}
