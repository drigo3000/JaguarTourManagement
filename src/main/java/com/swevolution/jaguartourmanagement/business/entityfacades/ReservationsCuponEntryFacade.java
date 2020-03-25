/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.ReservationCuponEntry;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class ReservationsCuponEntryFacade extends AbstractFacade<ReservationCuponEntry> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ReservationsCuponEntryFacade() {
        super(ReservationCuponEntry.class);
    }

    public List<ReservationCuponEntry> findByReservation(Long id) {
        return em.createQuery("select r from ReservationCuponEntry r where r.reservation.id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
