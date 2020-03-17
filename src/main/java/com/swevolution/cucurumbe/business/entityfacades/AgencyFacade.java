/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.entityfacades;

import com.swevolution.cucurumbe.model.entities.Agency;
import com.swevolution.cucurumbe.model.entities.Tour;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class AgencyFacade extends AbstractFacade<Agency> {

    @PersistenceContext(unitName = "CUCURUMBE_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public AgencyFacade() {
        super(Agency.class);
    }

    public List<Agency> getByName(String name, int limit, int offset) {
        return em.createQuery("SELECT a FROM Agency a WHERE a.name LIKE :name ORDER BY a.name ASC")
                .setParameter("name", "%" + name + "%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Agency> getByName(String name, int limit, int offset, boolean active) {
        return em.createQuery("SELECT a FROM Agency a WHERE a.name LIKE :name and a.active = :active ORDER BY a.name ASC")
                .setParameter("name", "%" + name + "%")
                .setParameter("active", active)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Tour> findByExactName(String name) {
        return em.createQuery("select a from Agency a where a.name = :name")
                .setParameter("name", name)
                .getResultList();
    }

    public List<Agency> findAllByName() {
        return em.createQuery("select a from Agency a order by a.name asc")
                .getResultList();
    }
}
