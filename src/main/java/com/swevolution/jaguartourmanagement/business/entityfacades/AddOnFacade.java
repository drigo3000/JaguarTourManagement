/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.AddOn;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class AddOnFacade extends AbstractFacade<AddOn> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public AddOnFacade() {
        super(AddOn.class);
    }

    public List<AddOn> all() {
        return em.createQuery("select a from AddOn a order by a.name asc")
                .getResultList();
    }

    public List<AddOn> findByName(String nombre) {
        return em.createQuery("select a from AddOn a where a.name like :name order by a.name asc")
                .setParameter("name", "%" + nombre + "%")
                .getResultList();
    }
}
