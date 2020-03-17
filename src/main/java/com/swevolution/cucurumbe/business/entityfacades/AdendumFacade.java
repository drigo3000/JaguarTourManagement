/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.entityfacades;

import com.swevolution.cucurumbe.model.entities.Adendum;
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
public class AdendumFacade extends AbstractFacade<Adendum> {

    @PersistenceContext(unitName = "CUCURUMBE_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public AdendumFacade() {
        super(Adendum.class);
    }

    public Adendum find(Agency agencia, String zona, Tour tour) {
        try {
            return (Adendum) em.createQuery("SELECT a FROM Adendum a WHERE a.agencia.id = :agencia "
                    + "AND a.zona = :zona "
                    + "AND a.tour.id =:tour")
                    .setParameter("agencia", agencia.getId())
                    .setParameter("zona", zona)
                    .setParameter("tour", tour.getId())
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public List<Adendum> findByTour(Tour tour) {
        return em.createQuery("SELECT a FROM Adendum a WHERE a.tour.id = :tour "
                + "ORDER BY a.agencia.name, a.promo, a.zona,a.incluyeTransportacion, a.tipoVehiculo, a.buceo  ASC")
                .setParameter("tour", tour.getId())
                .getResultList();
    }

    public List<Adendum> findByAgency(Agency agencia) {
        return em.createQuery("SELECT a FROM Adendum a WHERE a.agencia.id = :agencia "
                + "ORDER BY a.tour.name, a.promo, a.zona,  a.zona,a.incluyeTransportacion, a.tipoVehiculo  ASC")
                .setParameter("agencia", agencia.getId())
                .getResultList();
    }

    public Adendum find(Agency agencia, String operation, Tour tour,
            String promo, boolean transportationIncluded, String vehicleType, boolean buceo) {
        try {
            return (Adendum) em.createQuery("SELECT a FROM Adendum a WHERE a.agencia.id = :agencia "
                    + "AND a.promo = :promo "
                    + "AND a.incluyeTransportacion = :transportationIncluded "
                    + "AND a.tipoVehiculo = :vehicleType "
                    + "AND a.zona = :zona "
                    + "AND a.buceo = :buceo "
                    + "AND a.tour.id =:tour")
                    .setParameter("promo", promo)
                    .setParameter("buceo", buceo)
                    .setParameter("transportationIncluded", transportationIncluded)
                    .setParameter("vehicleType", vehicleType)
                    .setParameter("agencia", agencia.getId())
                    .setParameter("zona", operation)
                    .setParameter("tour", tour.getId())
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
