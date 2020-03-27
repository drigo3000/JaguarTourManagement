/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.CustomerContact;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class CustomerContactFacade extends AbstractFacade<CustomerContact> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public CustomerContactFacade() {
        super(CustomerContact.class);
    }

    public List<CustomerContact> find(LocalDate from, LocalDate to) {
        return em.createQuery("select c from CustomerContact c where c.localDateCreated between :from and :to")
                .setParameter("from", from.atStartOfDay())
                .setParameter("to", to.atTime(23, 59, 59))
                .getResultList();
    }
}
