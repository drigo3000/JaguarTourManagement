/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.Agency;
import com.swevolution.jaguartourmanagement.model.entities.Representante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class RepresentativeFacade extends AbstractFacade<Representante> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public RepresentativeFacade() {
        super(Representante.class);
    }

    public List<Representante> getByName(String name, int limit, int offset) {
        return em.createQuery("SELECT a FROM Representante a WHERE a.name LIKE :name ORDER BY a.name ASC")
                .setParameter("name", "%" + name + "%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Representante> getByName(String name, Agency agency, int limit, int offset) {
        String agencyName;
        if (agency != null) {
            agencyName = agency.getName();
        } else {
            agencyName = "";
        }

        return em.createQuery("SELECT a FROM Representante a WHERE a.name LIKE :name"
                + " AND a.agencia.name LIKE :agency"
                + " ORDER BY a.name ASC")
                .setParameter("name", "%" + name + "%")
                .setParameter("agency", "%" + agencyName + "%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Representante> getByName(String name, int limit, int offset, boolean active) {
        return em.createQuery("SELECT a FROM Representante a WHERE a.name LIKE :name and a.active = :active ORDER BY a.name ASC")
                .setParameter("name", "%" + name + "%")
                .setParameter("active", active)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
