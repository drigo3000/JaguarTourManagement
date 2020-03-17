/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.entityfacades;

import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import com.swevolution.cucurumbe.model.entities.Agency;
import com.swevolution.cucurumbe.model.entities.Hotel;
import com.swevolution.cucurumbe.model.entities.Representante;
import com.swevolution.cucurumbe.model.entities.Reservation;
import com.swevolution.cucurumbe.model.entities.Tour;
import com.swevolution.cucurumbe.model.entities.TurnoTour;
import com.swevolution.cucurumbe.model.entities.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class ReservationsFacade extends AbstractFacade<Reservation> {

    @PersistenceContext(unitName = "CUCURUMBE_PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ReservationsFacade() {
        super(Reservation.class);
    }

    public List<Reservation> findByDateTime(LocalDateTime from, LocalDate operation) {
        return em.createQuery("select r from Reservation r where r.localDateCreated >= :from and r.fechaOperacion = :fechaOperacion")
                .setParameter("from", from)
                .setParameter("fechaOperacion", operation)
                .getResultList();
    }

    public long countFromUser(User user, LocalDate date) {
        return (long) em.createQuery("SELECT COUNT(r) FROM Reservation r WHERE r.quienReserva.login = :login AND r.fechaOperacion = :fechaOperacion")
                .setParameter("fechaOperacion", date)
                .setParameter("login", user.getLogin())
                .getSingleResult();
    }

    public List<Reservation> findDuplicateCupon(String cupon, Agency agency) {
        try {
            return em.createQuery("select r from Reservation r where r.noCupon = :cupon and r.agencia.id = :agencia")
                    .setParameter("agencia", agency.getId())
                    .setParameter("cupon", cupon)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    public List<Reservation> findByCupon(String cupon) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.noCupon = :cupon")
                .setParameter("cupon", cupon)
                .getResultList();
    }

    public List<Reservation> find(String query,
            LocalDate from,
            LocalDate to,
            String cupon,
            String claveConfirma,
            Tour servicio,
            String grupo,
            Agency agencia,
            Representante rep,
            Hotel hotel,
            User reservo,
            boolean dateOperated) {
        Query createQuery = em.createQuery(query);
        addQueryParams(createQuery, from, to, cupon, claveConfirma, servicio, grupo,
                agencia, rep, hotel, reservo, dateOperated);
        return createQuery.getResultList();
    }

    public List<Reservation> findPreliminar(String query,
            LocalDate operacion,
            LocalDateTime fromLocalDateTime,
            LocalDateTime toLocalDateTime,
            String cupon,
            String claveConfirma,
            Tour servicio,
            String grupo,
            Agency agencia,
            Representante rep,
            Hotel hotel,
            User reservo, int reporte) {
        Query createQuery = em.createQuery(query);
        addQueryParamsPreliminar(createQuery, operacion, fromLocalDateTime, toLocalDateTime,
                cupon, claveConfirma, servicio, grupo,
                agencia, rep, hotel, reservo, reporte);
        return createQuery.getResultList();
    }

    private void addQueryParamsPreliminar(Query createQuery,
            LocalDate fechaOperacion,
            LocalDateTime fromLocalDateTime,
            LocalDateTime toLocalDateTime,
            String cupon,
            String claveConfirma,
            Tour servicio,
            String grupo,
            Agency agencia,
            Representante rep,
            Hotel hotel,
            User reservo,
            int reporte) {

        if (reporte != 1) {
            createQuery.setParameter("fechaOperacion", fechaOperacion);
            createQuery.setParameter("fromLocalDate", fromLocalDateTime);
            createQuery.setParameter("toLocalDate", toLocalDateTime);
        } else {
            createQuery.setParameter("fechaOperacion", fechaOperacion);
            createQuery.setParameter("toLocalDate", toLocalDateTime);
        }

        if (reservo != null) {
            createQuery.setParameter("quienReserva", reservo.getLogin());
        }
        if (agencia != null) {
            createQuery.setParameter("agencia", agencia.getId());
        }
        if (rep != null) {
            createQuery.setParameter("representante", rep.getId());
        }
        if (hotel != null) {
            createQuery.setParameter("hotel", hotel.getId());
        }
        createQuery.setParameter("cupon", "%" + cupon + "%");
        createQuery.setParameter("claveConfirma", "%" + claveConfirma + "%");
        if (servicio != null) {
            createQuery.setParameter("servicio", servicio.getId());
        }
        if (grupo != null && !grupo.isEmpty()) {
            createQuery.setParameter("grupo", grupo);
        }

    }

    private void addQueryParams(Query createQuery,
            LocalDate from,
            LocalDate to,
            String cupon,
            String claveConfirma,
            Tour servicio,
            String grupo,
            Agency agencia,
            Representante rep,
            Hotel hotel,
            User reservo,
            boolean dateOperated) {

        if (dateOperated) {
            createQuery.setParameter("from", from);
            createQuery.setParameter("to", to);
        } else {
            createQuery.setParameter("from", from.atStartOfDay());
            createQuery.setParameter("to", to.atTime(23, 59, 59));
        }

        if (reservo != null) {
            createQuery.setParameter("quienReserva", reservo.getLogin());
        }
        if (agencia != null) {
            createQuery.setParameter("agencia", agencia.getId());
        }
        if (rep != null) {
            createQuery.setParameter("representante", rep.getId());
        }
        if (hotel != null) {
            createQuery.setParameter("hotel", hotel.getId());
        }
        createQuery.setParameter("cupon", "%" + cupon + "%");
        createQuery.setParameter("claveConfirma", "%" + claveConfirma + "%");
        if (servicio != null) {
            createQuery.setParameter("servicio", servicio.getId());
        }
        if (grupo != null && !grupo.isEmpty()) {
            createQuery.setParameter("grupo", grupo);
        }

    }

    public List<Reservation> findByUser(User user, int limit, int offset) {
        return em.createQuery("select r from Reservation r where r.quienReserva.login = :user ORDER BY r.id DESC")
                .setParameter("user", user.getLogin())
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    public List<Reservation> findByRep(Representante representante, int limit, int offset) {
        return em.createQuery("select r from Reservation r where r.representante.id = :representante ORDER BY r.id DESC")
                .setParameter("representante", representante.getId())
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    public List<Reservation> findRangeOperation(LocalDate from, LocalDate to) {
        return em.createQuery("select r from Reservation r where r.cuponCancelado = false and r.noShow = false "
                + "and r.fechaOperacion between :from and :to order by r.servicio.grupo, r.servicio.name, r.hotel.position asc")
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    public long countAdults(LocalDate from, LocalDate to, Agency agency) {
        try {
            return (long) em.createQuery("select sum(r.adulto) from Reservation r where r.adulto is not null and r.noShow = false and r.agencia.id = :agency and r.cuponCancelado = false and r.fechaOperacion between :from and :to")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("agency", agency.getId())
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countNinos(LocalDate from, LocalDate to, Agency agency) {
        try {
            return (long) em.createQuery("select sum(r.nino) from Reservation r where r.nino is not null and r.agencia.id = :agency and r.cuponCancelado = false and r.noShow = false and r.fechaOperacion between :from and :to")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("agency", agency.getId())
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countInfante(LocalDate from, LocalDate to, Agency agency) {
        try {
            return (long) em.createQuery("select sum(r.infante) from Reservation r where r.infante is not null and r.agencia.id = :agency and r.cuponCancelado = false and r.noShow = false and r.fechaOperacion between :from and :to")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("agency", agency.getId())
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countAdults(LocalDate from, LocalDate to, String tipoVehiculo) {
        try {
            return (long) em.createQuery("select sum(r.adulto) from Reservation r where r.adulto is not null and r.fechaOperacion between :from and :to and r.tipoVehiculo = :tipoVehiculo  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("tipoVehiculo", tipoVehiculo)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countNinos(LocalDate from, LocalDate to, String tipoVehiculo) {
        try {
            return (long) em.createQuery("select sum(r.nino) from Reservation r where r.nino is not null and r.fechaOperacion between :from and :to and r.tipoVehiculo = :tipoVehiculo  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("tipoVehiculo", tipoVehiculo)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }

    }

    public long countInfants(LocalDate from, LocalDate to, String tipoVehiculo) {
        try {
            return (long) em.createQuery("select sum(r.infante) from Reservation r where r.infante is not null and r.fechaOperacion between :from and :to and r.tipoVehiculo = :tipoVehiculo  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("tipoVehiculo", tipoVehiculo)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }

    }

    public long countIncentivo(LocalDate from, LocalDate to, String tipoVehiculo) {
        try {
            return (long) em.createQuery("select sum(r.adultoCortesia) from Reservation r where r.adultoCortesia is not null and r.fechaOperacion between :from and :to and r.tipoVehiculo = :tipoVehiculo  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("tipoVehiculo", tipoVehiculo)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countAdults(LocalDate from, LocalDate to) {
        try {
            return (long) em.createQuery("select sum(r.adulto) from Reservation r where r.adulto is not null and r.fechaOperacion between :from and :to and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countAdults(LocalDate from, LocalDate to, Tour tour) {
        try {
            return (long) em.createQuery("select sum(r.adulto) from Reservation r where r.adulto is not null and r.servicio.id = :tour and r.fechaOperacion between :from and :to and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("tour", tour.getId())
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countNinos(LocalDate from, LocalDate to) {
        try {
            return (long) em.createQuery("select sum(r.nino) from Reservation r where r.nino is not null and r.fechaOperacion between :from and :to  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countNinos(LocalDate from, LocalDate to, Tour tour) {
        try {
            return (long) em.createQuery("select sum(r.nino) from Reservation r where r.nino is not null and r.servicio.id = :tour and r.fechaOperacion between :from and :to  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("tour", tour.getId())
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countInfants(LocalDate from, LocalDate to) {
        try {
            return (long) em.createQuery("select sum(r.infante) from Reservation r where r.infante is not null and r.fechaOperacion between :from and :to  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countInfants(LocalDate from, LocalDate to, Tour tour) {
        try {
            return (long) em.createQuery("select sum(r.infante) from Reservation r where r.infante is not null and r.servicio.id = :tour and r.fechaOperacion between :from and :to  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("tour", tour.getId())
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countReservas(LocalDate from, LocalDate to) {
        try {
            return (long) em.createQuery("select count(r) from Reservation r where r.fechaOperacion between :from and :to  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countIncentivos(LocalDate from, LocalDate to) {
        try {
            return (long) em.createQuery("select sum(r.adultoCortesia) from Reservation r where r.adultoCortesia is not null and r.fechaOperacion between :from and :to  and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countAdultsBuceo(LocalDate from, LocalDate to) {
        try {
            return (long) em.createQuery("select sum(r.buceoAdultos) from Reservation r where r.buceoAdultos is not null and r.fechaOperacion between :from and :to and r.cuponCancelado = false and  r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countNinosBuceo(LocalDate from, LocalDate to) {
        try {
            return (long) em.createQuery("select sum(r.buceoNinos) from Reservation r where r.buceoNinos is not null and r.fechaOperacion between :from and :to and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countAdultsTour(LocalDate from, LocalDate to, Tour tour) {
        try {
            return (long) em.createQuery("select sum(r.adulto) from Reservation r where r.adulto is not null and r.fechaOperacion between :from and :to and r.servicio.id = :tour and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("tour", tour.getId())
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countNinosTour(LocalDate from, LocalDate to, Tour tour) {
        try {
            return (long) em.createQuery("select sum(r.nino) from Reservation r where r.nino is not null and r.fechaOperacion between :from and :to and r.servicio.id = :tour and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("tour", tour.getId())
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countInfantesTour(LocalDate from, LocalDate to, Tour tour) {
        try {
            return (long) em.createQuery("select sum(r.infante) from Reservation r where r.infante is not null and r.fechaOperacion between :from and :to and r.servicio.id = :tour and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("tour", tour.getId())
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countIncentivosTour(LocalDate from, LocalDate to, Tour tour) {
        try {
            return (long) em.createQuery("select sum(r.adultoCortesia) from Reservation r where r.adultoCortesia is not null and r.fechaOperacion between :from and :to and r.servicio.id = :tour and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("tour", tour.getId())
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countAdultsTurnoTour(LocalDate from, LocalDate to, TurnoTour turno) {
        try {
            return (long) em.createQuery("select sum(r.adulto) from Reservation r where r.adulto is not null and r.fechaOperacion between :from and :to and r.horario.turno.id = :turno and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("turno", turno.getId())
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countNinosTurnoTour(LocalDate from, LocalDate to, TurnoTour turno) {
        try {
            return (long) em.createQuery("select sum(r.nino) from Reservation r where r.nino is not null and r.fechaOperacion between :from and :to and r.horario.turno.id = :turno and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("turno", turno.getId())
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countInfantesTurnoTour(LocalDate from, LocalDate to, TurnoTour turno) {
        try {
            return (long) em.createQuery("select sum(r.infante) from Reservation r where r.infante is not null and r.fechaOperacion between :from and :to and r.horario.turno.id = :turno and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("turno", turno.getId())
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public long countIcentivosTurnoTour(LocalDate from, LocalDate to, TurnoTour turno) {
        try {
            return (long) em.createQuery("select sum(r.adultoCortesia) from Reservation r where r.adultoCortesia is not null and r.fechaOperacion between :from and :to and r.horario.turno.id = :turno and r.cuponCancelado = false and r.noShow = false")
                    .setParameter("turno", turno.getId())
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Reservation> getForReview() {
        return em.createQuery("select r from Reservation r where r.review = true")
                .getResultList();
    }

    public void resetReservationReview() {
        em.createQuery("update Reservation r set r.review = false").executeUpdate();
    }

    public List<Reservation> findEfectivas(Agency agency, String sucursal, LocalDate from, LocalDate to) {

        return em.createQuery("select r from Reservation r where r.agencia.id = :agencia AND "
                + "r.sucursales = :sucursal AND "
                + "(r.color = 'DIRECTOS' or r.color = 'FACTURABLE') AND "
                + "(r.cuponCancelado = false AND r.noShow = false) AND "
                + "r.fechaOperacion between :from and :to")
                .setParameter("from", from)
                .setParameter("to", to)
                .setParameter("sucursal", sucursal)
                .setParameter("agencia", agency.getId())
                .getResultList();
    }

    public List<Reservation> findEfectivas(Agency agency, LocalDate from, LocalDate to) {

        return em.createQuery("select r from Reservation r where r.agencia.id = :agencia AND "
                + "(r.color = 'DIRECTOS' or r.color = 'FACTURABLE') AND "
                + "(r.cuponCancelado = false AND r.noShow = false) AND "
                + "r.fechaOperacion between :from and :to")
                .setParameter("from", from)
                .setParameter("to", to)
                .setParameter("agencia", agency.getId())
                .getResultList();
    }

    public List<Reservation> findEfectivas(LocalDate from, LocalDate to) {

        return em.createQuery("select r from Reservation r where r.cuponCancelado = false and r.noShow = false AND "
                + "r.fechaOperacion between :from and :to")
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    public List<Reservation> findForPickups(LocalDate fechaOperacion) {
        return em.createQuery("select r from Reservation r where r.fechaOperacion = :fechaOperacion order by r.horario.turno.horariodDeCruce, r.hotel.position")
                .setParameter("fechaOperacion", fechaOperacion)
                .getResultList();
    }

    public List<Reservation> findByCustomerName(String name) {
        LocalDateTime from = JsfUtil.getCancunDate().minusDays(15).atStartOfDay();
        return em.createQuery("select r from Reservation r where "
                + "r.nombreCliente like :nombreCliente and r.localDateCreated >= :from order by r.localDateCreated desc")
                .setMaxResults(10)
                .setParameter("from", from)
                .setParameter("nombreCliente", "%" + name + "%")
                .getResultList();
    }

}
