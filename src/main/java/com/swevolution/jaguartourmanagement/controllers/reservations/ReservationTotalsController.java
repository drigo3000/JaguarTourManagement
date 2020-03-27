/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.controllers.reservations;

import com.swevolution.jaguartourmanagement.model.entities.Reservation;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@ViewScoped
public class ReservationTotalsController implements Serializable {

    private long adultos;
    private long ninos;
    private long infantes;
    private long totalPax;
    private long totalPaxEfectivos;
    private long cortesias;
    private long buceoAdultos;
    private long buceoNinos;
    private long adultosCancelados;
    private long ninosCancelados;
    private long infantesCancelados;
    private long adultosNoShow;
    private long ninosNoShow;
    private long infantesNoShow;
    private int reservasSize;

    public long getAdultos() {
        return adultos;
    }

    public void setAdultos(long adultos) {
        this.adultos = adultos;
    }

    public long getNinos() {
        return ninos;
    }

    public void setNinos(long ninos) {
        this.ninos = ninos;
    }

    public long getInfantes() {
        return infantes;
    }

    public void setInfantes(long infantes) {
        this.infantes = infantes;
    }

    public void getPaxInformation(List<Reservation> reservations) {
        adultos = 0;
        ninos = 0;
        infantes = 0;
        cortesias = 0;
        buceoNinos = 0;
        buceoAdultos = 0;
        adultosCancelados = 0;
        ninosCancelados = 0;
        infantesCancelados = 0;
        adultosNoShow = 0;
        ninosNoShow = 0;
        infantesNoShow = 0;

        if (reservations != null && !reservations.isEmpty()) {
            reservasSize = reservations.size();
        }

        for (Reservation r : reservations) {
            adultos += r.getAdulto();
            ninos += r.getNino();
            infantes += r.getInfante();
            cortesias += r.getAdultoCortesia();

            if (r.isCuponCancelado()) {
                adultosCancelados += r.getAdulto();
                ninosCancelados += r.getNino();
                infantesCancelados += r.getInfante();
            }
            if (r.getNoShow()) {
                adultosNoShow += r.getAdulto();
                ninosNoShow += r.getNino();
                infantesNoShow += r.getInfante();
            }
        }
        totalPax = adultos + ninos + infantes;
        totalPaxEfectivos = totalPax - adultosCancelados - ninosCancelados - infantesCancelados;
    }

    public long getTotalPax() {
        return totalPax;
    }

    public void setTotalPax(long totalPax) {
        this.totalPax = totalPax;
    }

    public long getTotalPaxCancelados() {
        return adultosCancelados + ninosCancelados + infantesCancelados;
    }

    /*public BigDecimal getPronosticoUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getPronosticoUSD());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getPronosticoMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getPronosticoMXN());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getRealUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getRealUSD());
            } catch (Exception e) {
                total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getRealMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getRealMXN());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getRealTCUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getRealTCUSD());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getRealTCMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getRealTCMXN());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getDTVUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getDtvUSD());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getDTVMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getDtvMXN());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getTotalGeneralUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getTotalGeneralUSD());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getTotalCashUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getTotalCashUSD());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getTotalCashMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getTotalCashMXN());
            } catch (Exception e) {
                total = total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getDiferenciaPesos() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getDiferenciaPesos());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getTotalGeneralMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getTotalGeneralMXN());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public long buceoAdultos() {
        long count = 0;
        for (Reservation r : reservations) {
            count += r.getBuceoAdultos();
        }
        return count;
    }

    public long buceoNinos() {
        long count = 0;
        for (Reservation r : reservations) {
            count += r.getBuceoNinos();
        }
        return count;
    }

    public long adultosReales() {
        long count = 0;
        for (Reservation r : reservations) {
            count += r.getAdultosReales();
        }
        return count;
    }

    public long ninosReales() {
        long count = 0;
        for (Reservation r : reservations) {
            count += r.getNinosReales();
        }
        return count;
    }

    public long infantesReales() {
        long count = 0;
        for (Reservation r : reservations) {
            count += r.getInfantesReales();
        }
        return count;
    }

    public BigDecimal getFacturacionUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getFacturacionUSD());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getFacturacionMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            try {
                total = total.add(r.getFacturacionMXN());
            } catch (Exception e) {
                total = total.add(BigDecimal.ZERO);
            }
        }
        return total;
    }

    public BigDecimal getNetoUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            total = total.add(r.getNetoUSD());
        }
        return total;
    }

    public BigDecimal getNetoMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            total = total.add(r.getNetoMXN());
        }
        return total;
    }

    public BigDecimal getNetoAdultoUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            total = total.add(r.getNetoAdultosUSD());
        }
        return total;
    }

    public BigDecimal getNetoAdultoMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            total = total.add(r.getNetoAdultosMXN());
        }
        return total;
    }

    public BigDecimal getNetoNinoMXN() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            total = total.add(r.getNetoNinosMXN());
        }
        return total;
    }

    public BigDecimal getNetoNinoUSD() {
        BigDecimal total = BigDecimal.ZERO;
        for (Reservation r : reservations) {
            total = total.add(r.getNetoNinosUSD());
        }
        return total;
    }*/
    public long getCortesias() {
        return cortesias;
    }

    public void setCortesias(long cortesias) {
        this.cortesias = cortesias;
    }

    public long getBuceoAdultos() {
        return buceoAdultos;
    }

    public void setBuceoAdultos(long buceoAdultos) {
        this.buceoAdultos = buceoAdultos;
    }

    public long getBuceoNinos() {
        return buceoNinos;
    }

    public void setBuceoNinos(long buceoNinos) {
        this.buceoNinos = buceoNinos;
    }

    public long getTotalPaxEfectivos() {
        return totalPaxEfectivos;
    }

    public void setTotalPaxEfectivos(long totalPaxEfectivos) {
        this.totalPaxEfectivos = totalPaxEfectivos;
    }

    public long getAdultosCancelados() {
        return adultosCancelados;
    }

    public void setAdultosCancelados(long adultosCancelados) {
        this.adultosCancelados = adultosCancelados;
    }

    public long getNinosCancelados() {
        return ninosCancelados;
    }

    public void setNinosCancelados(long ninosCancelados) {
        this.ninosCancelados = ninosCancelados;
    }

    public long getInfantesCancelados() {
        return infantesCancelados;
    }

    public void setInfantesCancelados(long infantesCancelados) {
        this.infantesCancelados = infantesCancelados;
    }

    public long getAdultosNoShow() {
        return adultosNoShow;
    }

    public void setAdultosNoShow(long adultosNoShow) {
        this.adultosNoShow = adultosNoShow;
    }

    public long getNinosNoShow() {
        return ninosNoShow;
    }

    public void setNinosNoShow(long ninosNoShow) {
        this.ninosNoShow = ninosNoShow;
    }

    public long getInfantesNoShow() {
        return infantesNoShow;
    }

    public void setInfantesNoShow(long infantesNoShow) {
        this.infantesNoShow = infantesNoShow;
    }

    public int getReservasSize() {
        return reservasSize;
    }

    public void setReservasSize(int reservasSize) {
        this.reservasSize = reservasSize;
    }

}
