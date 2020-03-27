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
import javax.persistence.Query;

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

    public List<Tour> getForSiteMap(String lang) {
        return em.createQuery("SELECT t FROM Tour t WHERE t.lang LIKE :name AND t.active = true ORDER BY t.name ASC")
                .setParameter("name", "%" + lang + "%")
                .getResultList();
    }

    public long countByKeywords(String keywords, String lang, int limit, int offset, boolean active) {
        StringBuilder sb = new StringBuilder("SELECT Count(t) FROM Tour t WHERE ");
        if (keywords != null && !keywords.isEmpty()) {
            sb.append("(t.name LIKE :keywords OR t.searchKeywords LIKE :keywords) AND ");
        }
        sb.append("t.active = :active AND t.descriptionLang = :lang ORDER BY t.adultPrice ASC");
        Query createQuery = em.createQuery(sb.toString());
        if (keywords != null && !keywords.isEmpty()) {
            createQuery.setParameter("keywords", "%" + keywords + "%");
        }
        createQuery.setParameter("lang", lang);
        createQuery.setParameter("active", active);
        createQuery.setMaxResults(limit);
        createQuery.setFirstResult(offset);
        return (long) createQuery.getSingleResult();
    }

    public List<Tour> getByKeywords(String keywords, String lang, int limit, int offset, boolean active) {
        StringBuilder sb = new StringBuilder("SELECT t FROM Tour t WHERE ");
        if (keywords != null && !keywords.isEmpty()) {
            sb.append("(t.name LIKE :keywords OR t.searchKeywords LIKE :keywords) AND ");
        }
        sb.append("t.active = :active AND t.descriptionLang = :lang ORDER BY t.adultPrice ASC");
        Query createQuery = em.createQuery(sb.toString());
        if (keywords != null && !keywords.isEmpty()) {
            createQuery.setParameter("keywords", "%" + keywords + "%");
        }
        createQuery.setParameter("lang", lang);
        createQuery.setParameter("active", active);
        createQuery.setMaxResults(limit);
        createQuery.setFirstResult(offset);
        return createQuery.getResultList();
    }

    public List<Tour> findTopTours(String langCode, int max) {
        return em.createQuery("SELECT t FROM Tour t WHERE t.topTour = true AND t.descriptionLang = :lang AND t.active = true")
                .setParameter("lang", langCode)
                .setMaxResults(max)
                .getResultList();

    }
}
