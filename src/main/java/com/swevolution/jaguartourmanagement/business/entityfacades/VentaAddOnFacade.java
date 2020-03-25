/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.AddOn;
import com.swevolution.jaguartourmanagement.model.entities.Reservation;
import com.swevolution.jaguartourmanagement.model.entities.User;
import com.swevolution.jaguartourmanagement.model.entities.VentaAddOn;
import java.time.LocalDate;
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
public class VentaAddOnFacade extends AbstractFacade<VentaAddOn> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public VentaAddOnFacade() {
        super(VentaAddOn.class);
    }

    public List<VentaAddOn> search(AddOn addOn, User guia, Reservation reserva,
            User creador, LocalDate from, LocalDate to) {
        String query = createSearchQuery(addOn, guia, reserva,
                creador, from, to);
        Query createQuery = em.createQuery(query);
        addQueryParams(createQuery, addOn, guia, reserva,
                creador, from, to);
        return createQuery.getResultList();
    }

    private String createSearchQuery(AddOn addOn, User guia, Reservation reserva, User creador, LocalDate from, LocalDate to) {
        StringBuilder sb = new StringBuilder();
        sb.append("select v from VentaAddOn v where ");
        if (addOn != null) {
            sb.append("v.addOn.id = :addOn AND ");
        }
        if (guia != null) {
            sb.append("v.guia.login = :guia AND ");
        }
        if (reserva != null) {
            sb.append("v.reserva.id = :reserva AND ");
        }
        if (creador != null) {
            sb.append("v.creador.login = :creador AND ");
        }
        sb.append("v.localDateCreated between :from and :to");
        return sb.toString();
    }

    private void addQueryParams(Query createQuery, AddOn addOn, User guia, Reservation reserva, User creador, LocalDate from, LocalDate to) {
        if (addOn != null) {
            createQuery.setParameter("addOn", addOn.getId());
        }
        if (guia != null) {
            createQuery.setParameter("guia", guia.getLocalDateCreated());
        }
        if (reserva != null) {
            createQuery.setParameter("reserva", reserva.getId());
        }
        if (creador != null) {
            createQuery.setParameter("creador", creador.getLogin());
        }
        createQuery.setParameter("from", from.atStartOfDay());
        createQuery.setParameter("to", to.atTime(23, 59, 59));
    }
}
