/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.model.entities;

import com.swevolution.cucurumbe.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "TOUR", catalog = "CUCURUMBE")
@XmlRootElement
public class Tour extends PK_Long_Entity implements Serializable {

    @NotNull
    private String name;
    @Lob
    private String description;
    private boolean buceo;
    private boolean manejaVehiculo;
    private String grupo;

    private BigDecimal imaAdultoUSD;
    private BigDecimal imaNinoUSD;
    private BigDecimal imaAdultoCZM;
    private BigDecimal imaNinoCZM;
    private BigDecimal imaAdultoMXN;
    private BigDecimal imaNinoMXN;

    private BigDecimal costoUnitarioAdulto;
    private BigDecimal costoUnitarioNino;

    private BigDecimal costoUnitarioAdultoCZM;
    private BigDecimal costoUnitarioNinoCZM;

    //fechas de operacion
    private String inicialesConfirma;

    public Tour() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInicialesConfirma() {
        return inicialesConfirma;
    }

    public void setInicialesConfirma(String inicialesConfirma) {
        this.inicialesConfirma = inicialesConfirma;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public boolean isBuceo() {
        return buceo;
    }

    public void setBuceo(boolean buceo) {
        this.buceo = buceo;
    }

    public BigDecimal getImaAdultoUSD() {
        return imaAdultoUSD;
    }

    public void setImaAdultoUSD(BigDecimal imaAdultoUSD) {
        this.imaAdultoUSD = imaAdultoUSD;
    }

    public BigDecimal getImaNinoUSD() {
        return imaNinoUSD;
    }

    public void setImaNinoUSD(BigDecimal imaNinoUSD) {
        this.imaNinoUSD = imaNinoUSD;
    }

    public BigDecimal getImaAdultoMXN() {
        return imaAdultoMXN;
    }

    public void setImaAdultoMXN(BigDecimal imaAdultoMXN) {
        this.imaAdultoMXN = imaAdultoMXN;
    }

    public BigDecimal getImaNinoMXN() {
        return imaNinoMXN;
    }

    public void setImaNinoMXN(BigDecimal imaNinoMXN) {
        this.imaNinoMXN = imaNinoMXN;
    }

    public boolean isManejaVehiculo() {
        return manejaVehiculo;
    }

    public void setManejaVehiculo(boolean manejaVehiculo) {
        this.manejaVehiculo = manejaVehiculo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" - ").append(this.getName());
        return sb.toString();
    }

    public BigDecimal getCostoUnitarioAdulto() {
        return costoUnitarioAdulto;
    }

    public void setCostoUnitarioAdulto(BigDecimal costoUnitarioAdulto) {
        this.costoUnitarioAdulto = costoUnitarioAdulto;
    }

    public BigDecimal getCostoUnitarioNino() {
        return costoUnitarioNino;
    }

    public void setCostoUnitarioNino(BigDecimal costoUnitarioNino) {
        this.costoUnitarioNino = costoUnitarioNino;
    }

    public BigDecimal getCostoUnitarioAdultoCZM() {
        return costoUnitarioAdultoCZM;
    }

    public void setCostoUnitarioAdultoCZM(BigDecimal costoUnitarioAdultoCZM) {
        this.costoUnitarioAdultoCZM = costoUnitarioAdultoCZM;
    }

    public BigDecimal getCostoUnitarioNinoCZM() {
        return costoUnitarioNinoCZM;
    }

    public void setCostoUnitarioNinoCZM(BigDecimal costoUnitarioNinoCZM) {
        this.costoUnitarioNinoCZM = costoUnitarioNinoCZM;
    }

    public BigDecimal getImaAdultoCZM() {
        return imaAdultoCZM;
    }

    public void setImaAdultoCZM(BigDecimal imaAdultoCZM) {
        this.imaAdultoCZM = imaAdultoCZM;
    }

    public BigDecimal getImaNinoCZM() {
        return imaNinoCZM;
    }

    public void setImaNinoCZM(BigDecimal imaNinoCZM) {
        this.imaNinoCZM = imaNinoCZM;
    }

}
