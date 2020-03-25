/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations;

import com.swevolution.jaguartourmanagement.business.entityfacades.ReservationsFacade;
import com.swevolution.jaguartourmanagement.business.entityfacades.TourFacade;
import com.swevolution.jaguartourmanagement.business.session.SessionInfo;
import com.swevolution.jsf.webutils.JsfUtil;
import com.swevolution.jaguartourmanagement.model.entities.Reservation;
import com.swevolution.jaguartourmanagement.model.entities.Tour;
import com.swevolution.jaguartourmanagement.model.entities.TurnoTour;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class ReservationsUtilityController implements Serializable {

    @EJB
    private ReservationsFacade rFacade;
    @EJB
    private TourFacade tFacade;
    @EJB
    private SessionInfo session;

    public String getTotalReservaciones(LocalDate from, LocalDate to, String tipoVehiculo) {
        long countAdults = rFacade.countAdults(from, to, tipoVehiculo);
        long countNinos = rFacade.countNinos(from, to, tipoVehiculo);
        long countInfants = rFacade.countInfants(from, to, tipoVehiculo);
        String total = countAdults + "." + countNinos + "." + countInfants;
        return total;
    }

    public String getConfirma(Reservation reservacion) {

        StringBuilder sb = new StringBuilder();
        if (null != reservacion.getFechaOperacion().getDayOfWeek()) {
            switch (reservacion.getFechaOperacion().getDayOfWeek()) {
                case MONDAY:
                    sb.append("L");
                    break;
                case TUESDAY:
                    sb.append("M");
                    break;
                case WEDNESDAY:
                    sb.append("M");
                    break;
                case THURSDAY:
                    sb.append("J");
                    break;
                case FRIDAY:
                    sb.append("V");
                    break;
                case SATURDAY:
                    sb.append("S");
                    break;
                case SUNDAY:
                    sb.append("D");
                    break;
            }
        }
        sb.append(reservacion.getFechaOperacion().getDayOfMonth() < 10 ? "0" + reservacion.getFechaOperacion().getDayOfMonth() : reservacion.getFechaOperacion().getDayOfMonth());
        sb.append(reservacion.getFechaOperacion().getMonthValue() < 10 ? "0" + reservacion.getFechaOperacion().getMonthValue() : reservacion.getFechaOperacion().getMonthValue());
        sb.append(reservacion.getFechaOperacion().getYear() - 2000);
        sb.append(" ");
        int totalPax = reservacion.getAdulto() + reservacion.getNino() + reservacion.getInfante();
        if (totalPax < 10) {
            sb.append("0");
            sb.append(totalPax);
        } else {
            sb.append(totalPax);
        }
        sb.append(" ");
        sb.append(reservacion.getServicio().getInicialesConfirma());
        sb.append(" ");
        if (reservacion.getIdioma().equals("Inglés")) {
            sb.append("IN");
        } else if (reservacion.getIdioma().equals("Español")) {
            sb.append("ES");
        } else if (reservacion.getIdioma().equals("Francés")) {
            sb.append("FR");
        } else if (reservacion.getIdioma().equals("Francés")) {
            sb.append("AL");
        } else if (reservacion.getIdioma().equals("Ruso")) {
            sb.append("RU");
        }
        sb.append(" ");
        sb.append(session.getCurrentUser().getName().charAt(0));
        sb.append(rFacade.countFromUser(session.getCurrentUser(), reservacion.getFechaOperacion()) + 1);
        return sb.toString();
    }

    public String getConfirma(Reservation original, Reservation reservacion) {
        boolean needsChange = false;
        if (!original.getFechaOperacion().isEqual(reservacion.getFechaOperacion())) {
            needsChange = true;
        }
        if (!original.getIdioma().equals(original.getIdioma())) {
            needsChange = true;
        }
        if (original.getAdulto() != original.getAdulto()) {
            needsChange = true;
        }
        if (original.getNino() != original.getNino()) {
            needsChange = true;
        }
        if (original.getInfante() != original.getInfante()) {
            needsChange = true;
        }
        if (!original.getServicio().getId().equals(reservacion.getServicio().getId())) {
            needsChange = true;
        }

        if (needsChange) {
            StringBuilder sb = new StringBuilder();
            if (null != reservacion.getFechaOperacion().getDayOfWeek()) {
                switch (reservacion.getFechaOperacion().getDayOfWeek()) {
                    case MONDAY:
                        sb.append("L");
                        break;
                    case TUESDAY:
                        sb.append("M");
                        break;
                    case WEDNESDAY:
                        sb.append("M");
                        break;
                    case THURSDAY:
                        sb.append("J");
                        break;
                    case FRIDAY:
                        sb.append("V");
                        break;
                    case SATURDAY:
                        sb.append("S");
                        break;
                    case SUNDAY:
                        sb.append("D");
                        break;
                }
            }
            sb.append(reservacion.getFechaOperacion().getDayOfMonth() < 10 ? "0" + reservacion.getFechaOperacion().getDayOfMonth() : reservacion.getFechaOperacion().getDayOfMonth());
            sb.append(reservacion.getFechaOperacion().getMonthValue() < 10 ? "0" + reservacion.getFechaOperacion().getMonthValue() : reservacion.getFechaOperacion().getMonthValue());
            sb.append(reservacion.getFechaOperacion().getYear() - 2000);
            sb.append(" ");
            int totalPax = reservacion.getAdulto() + reservacion.getNino() + reservacion.getInfante();
            if (totalPax < 10) {
                sb.append("0");
                sb.append(totalPax);
            } else {
                sb.append(totalPax);
            }
            sb.append(" ");
            sb.append(reservacion.getServicio().getInicialesConfirma());
            sb.append(" ");
            if (reservacion.getIdioma().equals("Inglés")) {
                sb.append("IN");
            } else if (reservacion.getIdioma().equals("Español")) {
                sb.append("ES");
            } else if (reservacion.getIdioma().equals("Francés")) {
                sb.append("FR");
            } else if (reservacion.getIdioma().equals("Francés")) {
                sb.append("AL");
            } else if (reservacion.getIdioma().equals("Ruso")) {
                sb.append("RU");
            }
            sb.append(" ");
            sb.append(session.getCurrentUser().getName().charAt(0));
            sb.append(rFacade.countFromUser(session.getCurrentUser(), reservacion.getFechaOperacion()) + 1);
            return sb.toString();
        } else {
            return reservacion.getClaveConfirmacion();
        }

    }

    public String getStringTotalPaxFecha(LocalDate from, LocalDate to) {
        long countAdults = rFacade.countAdults(from, to);
        long countNinos = rFacade.countNinos(from, to);
        long countInfants = rFacade.countInfants(from, to);
        String total = countAdults + "." + countNinos + "." + countInfants;
        return total;
    }

    public long getNumberTotalPaxFecha(LocalDate from, LocalDate to) {
        long countAdults = rFacade.countAdults(from, to);
        long countNinos = rFacade.countNinos(from, to);
        long countInfants = rFacade.countInfants(from, to);
        return countAdults + countNinos + countInfants;
    }

    public long countTotalReservas(LocalDate from, LocalDate to) {
        return rFacade.countReservas(from, to);
    }

    public String getPaxBuceo(LocalDate from, LocalDate to) {
        long countAdults = rFacade.countAdultsBuceo(from, to);
        long countNinos = rFacade.countNinosBuceo(from, to);
        return countAdults + "." + countNinos;
    }

    public String getPaxCatamaranDeluxe(LocalDate from, LocalDate to) {
        Tour tour = tFacade.find(7l);
        long countAdults = rFacade.countAdultsTour(from, to, tour);
        long countNinos = rFacade.countNinosTour(from, to, tour);
        long countInfants = rFacade.countInfantesTour(from, to, tour);
        String total = countAdults + "." + countNinos + "." + countInfants;
        return total;
    }

    public String getPaxCatamaranSunset(LocalDate from, LocalDate to) {
        Tour tour = tFacade.find(8l);
        long countAdults = rFacade.countAdultsTour(from, to, tour);
        long countNinos = rFacade.countNinosTour(from, to, tour);
        long countInfants = rFacade.countInfantesTour(from, to, tour);
        String total = countAdults + "." + countNinos + "." + countInfants;
        return total;
    }

    public String getStringPaxPorTurno(LocalDate from, LocalDate to, TurnoTour turno) {
        long countAdults = rFacade.countAdultsTurnoTour(from, to, turno);
        long countNinos = rFacade.countNinosTurnoTour(from, to, turno);
        long countInfants = rFacade.countInfantesTurnoTour(from, to, turno);
        String total = countAdults + "." + countNinos + "." + countInfants;
        return total;
    }

    public long getNumeroPorTurno(LocalDate from, LocalDate to, TurnoTour turno) {
        long countAdults = rFacade.countAdultsTurnoTour(from, to, turno);
        long countNinos = rFacade.countNinosTurnoTour(from, to, turno);
        long countInfants = rFacade.countInfantesTurnoTour(from, to, turno);
        return countAdults + countNinos + countInfants;
    }

    public String getOperationRowClass(Reservation r) {
        if (r.isCuponCancelado()) {
            return "renglonReservaCancelada";
        }
        if (r.getNoShow()) {
            return "reglonReservaNoShow";
        }
        return "";
    }

    public String getNoShow(Reservation r) {
        if (r.getNoShow()) {
            return "No Show";
        } else {
            return "";
        }
    }

    public String isReservationCancelled(Reservation r) {
        if (r != null) {
            if (r.isCuponCancelado()) {
                return "red";
            } else {
                return "green";
            }
        } else {
            return "gray";
        }
    }

    public String getRowClass(Reservation r) {
        try {
            switch (r.getColor()) {
                case "FAM TRIP":
                    return "famTrip";
                case "DIRECTOS":
                    return "directos";
                case "CORTESIAS":
                    return "cortesias";
                case "INCENTIVOS":
                    return "incentivos";
                default:
                    return "";
            }
        } catch (Exception e) {
            return "";
        }

    }

    public String getRowClassPreliminar(Reservation r) {
        LocalDate today = JsfUtil.getCancunDate();
        LocalDate resUpdateDate = r.getLocalDateUpdated().toLocalDate();
        if (today.equals(resUpdateDate)) {
            if (r.getLocalDateUpdated().isAfter(r.getLocalDateCreated())) {
                return "pink";
            }
        }
        return "";
    }

    public boolean verifyIfNeedsReview(Reservation reservacionOriginal, Reservation reservacion) {
        boolean review = false;
        LocalDate today = JsfUtil.getCancunDate();
        LocalDate fechaOperacionOriginal = reservacionOriginal.getFechaOperacion();
        if (today.plusDays(1).isEqual(fechaOperacionOriginal)) {
            review = true;
        }
        return review;
    }

    public LocalDate getMinDate() {
        return JsfUtil.getCancunDate();
    }

    public String getDay(LocalDate date) {
        DayOfWeek dayOfWeek = DayOfWeek.from(date);
        switch (dayOfWeek.name()) {
            case "MONDAY":
                return "Lunes";
            case "TUESDAY":
                return "Martes";
            case "WEDNESDAY":
                return "Miércoles";
            case "THURSDAY":
                return "Jueves";
            case "FRIDAY":
                return "Viernes";
            case "SATURDAY":
                return "Sábado";
            case "SUNDAY":
                return "Domingo";
        }
        return "";
    }

}
