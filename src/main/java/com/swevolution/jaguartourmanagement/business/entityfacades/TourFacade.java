/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

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
public class TourFacade extends AbstractFacade<Tour> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public TourFacade() {
        super(Tour.class);
    }

    public List<Tour> getByName(String name, int limit, int offset) {
        return em.createQuery("SELECT t FROM Tour t WHERE t.name LIKE :name ORDER BY t.name ASC")
                .setParameter("name", "%" + name + "%")
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    public List<Tour> getByName(String name, int limit, int offset, boolean active) {
        return em.createQuery("SELECT t FROM Tour t WHERE t.name LIKE :name and t.active = :active ORDER BY t.name ASC")
                .setParameter("name", "%" + name + "%")
                .setParameter("active", active)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    public List<Tour> findByInitials(String initials) {
        return em.createQuery("select t from Tour t where t.inicialesConfirma = :initials")
                .setParameter("initials", initials)
                .getResultList();
    }

    public List<Tour> findByExactName(String name) {
        return em.createQuery("select t from Tour t where t.name = :name")
                .setParameter("name", name)
                .getResultList();
    }

    public List<String> getGrupos(String query, int limit, int offset) {
        return em.createQuery("select distinct(t.grupo) from Tour t where t.grupo like :grupo order by t.grupo asc")
                .setParameter("grupo", query + "%")
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    public List<Tour> findAllByName() {
        return em.createQuery("select t from Tour t order by t.name asc")
                .getResultList();
    }

    public List<Tour> findByGroup(String group) {
        return em.createQuery("select t from Tour t where t.grupo = :grupo order by t.name asc")
                .setParameter("grupo", group)
                .getResultList();
    }
}
