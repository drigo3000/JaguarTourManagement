/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.business.adendum;

import com.swevolution.cucurumbe.business.entityfacades.AdendumFacade;
import com.swevolution.cucurumbe.business.entityfacades.AgencyFacade;
import com.swevolution.cucurumbe.business.entityfacades.TourFacade;
import com.swevolution.cucurumbe.model.entities.Adendum;
import com.swevolution.cucurumbe.model.entities.Agency;
import com.swevolution.cucurumbe.model.entities.Tour;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Rxkx
 */
@Named
@RequestScoped
public class AdendumUtil {

    @EJB
    private AdendumFacade adendumFacade;
    @EJB
    private AgencyFacade agencyFacade;
    @EJB
    private TourFacade tourFacade;

    public void initAdendums() {
        for (Tour tour : tourFacade.findAll()) {
            crearAdendumsTour(tour);
        }
    }

    private Adendum createAdendum(Agency agencia, String zona, Tour tour) {
        Adendum adendum = new Adendum();
        //SET IMPORTANT
        adendum.setAgencia(agencia);
        adendum.setZona(zona);
        adendum.setTour(tour);
        //SET DEFAULTS
        adendum.setActive(true);
        adendum.setComisionAdultoMXN(BigDecimal.ZERO);
        adendum.setComisionAdultoUSD(BigDecimal.ZERO);
        adendum.setComisionNinoMXN(BigDecimal.ZERO);
        adendum.setComisionNinoUSD(BigDecimal.ZERO);
        adendum.setPrecioNinoMXN(BigDecimal.ZERO);
        adendum.setPrecioAdultoMXN(BigDecimal.ZERO);
        adendum.setPrecioAdultoUSD(BigDecimal.ZERO);
        adendum.setPrecioNinoUSD(BigDecimal.ZERO);
        adendum.setImpuestoUSD(BigDecimal.ZERO);
        return adendum;
    }

    public void crearAdendumsTour(Tour tour) {
        for (Agency a : agencyFacade.findAll()) {
            crearAdendumsAgencia(a, tour);
        }
    }

    private void crearAdendumsAgencia(Agency a, Tour tour) {
        for (String zona : getZonas()) {
            Adendum ad = adendumFacade.find(a, zona, tour);
            if (ad == null) {
                ad = createAdendum(a, zona, tour);
                adendumFacade.create(ad);
            }
        }
    }

    public void crearAdendumsAgencia(Agency agency) {
        for (Tour tour : tourFacade.findAll()) {
            for (String zona : getZonas()) {
                Adendum adendum = adendumFacade.find(agency, zona, tour);
                if (adendum == null) {
                    adendum = createAdendum(agency, zona, tour);
                    adendumFacade.create(adendum);
                }
            }
        }
    }

    public List<String> getZonas() {
        List<String> zonas = new ArrayList<>();
        zonas.add("CZM");
        zonas.add("RVM");
        zonas.add("CUN");
        return zonas;
    }
}
