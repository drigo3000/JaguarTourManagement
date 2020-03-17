/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.entityfacades;

import com.swevolution.cucurumbe.model.entities.IMA;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class IMAFacade extends AbstractFacade<IMA> {

    @PersistenceContext(unitName = "CUCURUMBE_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public IMAFacade() {
        super(IMA.class);
    }

    public List<IMA> findByTourID(long id) {
        return em.createQuery("select i from IMA i where i.tour.id = :id order by i.agencia.name asc")
                .setParameter("id", id)
                .getResultList();
    }

    public IMA findByTourAgency(Long tourid, Long agencyid) {
        try {
            return (IMA) em.createQuery("select i from IMA i where i.tour.id = :tourid and i.agencia.id = :agencyid")
                    .setParameter("tourid", tourid)
                    .setParameter("agencyid", agencyid)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

}
