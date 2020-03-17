/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.entityfacades;

import com.swevolution.cucurumbe.model.entities.Hotel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class HotelFacade extends AbstractFacade<Hotel> {

    @PersistenceContext(unitName = "CUCURUMBE_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public HotelFacade() {
        super(Hotel.class);
    }

    public List<Hotel> getAllByOperation(String operation) {
        return em.createQuery("SELECT h FROM Hotel h WHERE h.operation = :operation")
                .setParameter("operation", operation)
                .getResultList();
    }

    public List<Hotel> getByName(String operation, String name, boolean active, int max, int offset) {
        return em.createQuery("SELECT h FROM Hotel h WHERE h.name LIKE :name "
                + "AND h.operation LIKE :operation AND h.active = :active ORDER BY h.operation, h.position ASC")
                .setParameter("active", active)
                .setParameter("operation", "%" + operation + "%")
                .setParameter("name", "%" + name + "%")
                .setFirstResult(offset)
                .setMaxResults(max)
                .getResultList();
    }

    public List<Hotel> findAllActive() {
        return em.createQuery("SELECT h FROM Hotel h WHERE h.active = TRUE")
                .getResultList();
    }

    public Hotel getByName(String string) {
        return (Hotel) em.createQuery("SELECT h FROM Hotel h WHERE h.name = :name")
                .setParameter("name", string)
                .getSingleResult();
    }

    public List<String> getNames() {
        List<String> results = em.createNativeQuery("SELECT DISTINCT(h.NAME) "
                + "FROM HOTEL h ORDER BY h.NAME ASC")
                .getResultList();
        results.remove(null);
        return results;
    }

    public List<String> getZones() {
        List<String> results = em.createNativeQuery("SELECT DISTINCT(h.operation) "
                + "FROM HOTEL h ORDER BY h.position ASC")
                .getResultList();
        results.remove(null);
        return results;
    }

    public List<Hotel> findByPositionInZone(String zona) {
        return em.createQuery("SELECT h FROM Hotel h WHERE h.operation = :operation"
                + " ORDER BY h.position ASC")
                .setParameter("operation", zona)
                .getResultList();
    }

    public List<Hotel> findByPositionInZone(String zona, String subZone, String subUbicacion) {
        return em.createQuery("SELECT h FROM Hotel h WHERE h.operation = :operation and h.subOperation like :subZone and h.subLocation like :subUbicacion"
                + " ORDER BY h.position ASC")
                .setParameter("subZone", "%" + subZone + "%")
                .setParameter("subUbicacion", "%" + subUbicacion + "%")
                .setParameter("operation", zona)
                .getResultList();
    }

    public List<Hotel> findByPosition() {
        return em.createQuery("SELECT h FROM Hotel h"
                + " ORDER BY h.operation, h.position")
                .getResultList();
    }

}
