/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.entityfacades;

import com.swevolution.jaguartourmanagement.model.entities.HorarioTurnoTour;
import com.swevolution.jaguartourmanagement.model.entities.Hotel;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rxkx
 */
@Stateless
public class HorarioTurnoTourFacade extends AbstractFacade<HorarioTurnoTour> {

    @PersistenceContext(unitName = "JAGUAR_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public HorarioTurnoTourFacade() {
        super(HorarioTurnoTour.class);
    }

    public List<HorarioTurnoTour> findByTurno(TurnoTour turno) {
        return em.createQuery("SELECT h FROM HorarioTurnoTour h WHERE h.turno.id = :turno"
                + " ORDER BY h.hotel.position ASC")
                .setParameter("turno", turno.getId())
                .getResultList();
    }

    public HorarioTurnoTour findHorarioTurnoTour(TurnoTour turno, Hotel hotel) {
        try {
            return (HorarioTurnoTour) em.createQuery("SELECT h FROM HorarioTurnoTour h WHERE h.hotel.id = :hotel AND h.turno.id = :turno")
                    .setParameter("hotel", hotel.getId())
                    .setParameter("turno", turno.getId())
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public List<HorarioTurnoTour> findHorariosTurnoTourHotel(Tour servicio, Hotel hotel) {
        try {
            return em.createQuery("select h from HorarioTurnoTour h where h.hotel.id = :hotel "
                    + "and h.turno.tour.id = :servicio and "
                    + "h.active = true and h.turno.active = true")
                    .setParameter("hotel", hotel.getId())
                    .setParameter("servicio", servicio.getId())
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> findHorariosTourHotel(Tour servicio, Hotel hotel) {
        try {
            return em.createQuery("select distinct(h.pickup) from HorarioTurnoTour h where h.hotel.id = :hotel and h.turno.tour.id = :servicio")
                    .setParameter("hotel", hotel.getId())
                    .setParameter("servicio", servicio.getId())
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public void removeTurno(TurnoTour turno) {
        em.createQuery("delete from HorarioTurnoTour h where h.turno.id = :turno")
                .setParameter("turno", turno.getId())
                .executeUpdate();
    }

    public HorarioTurnoTour find(Hotel hotel, TurnoTour turno) {
        return (HorarioTurnoTour) em.createQuery("select h from HorarioTurnoTour h where h.hotel.id = :hotelId and h.turno.id = :turnoId")
                .setParameter("hotelId", hotel.getId())
                .setParameter("turnoId", turno.getId())
                .getSingleResult();
    }

    public List<HorarioTurnoTour> findByHotel(Hotel hotel) {
        return em.createQuery("select h from HorarioTurnoTour h where h.hotel.id = :hotel order by h.turno.tour.name asc")
                .setParameter("hotel", hotel.getId())
                .getResultList();
    }

}
