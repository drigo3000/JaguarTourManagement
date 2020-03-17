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
@Table(name = "ADDON", catalog = "CUCURUMBE")
@XmlRootElement
public class AddOn extends PK_Long_Entity implements Serializable {

    @NotNull
    private String name;
    @Lob
    private String description;
    @Lob
    private String incluye;
    private BigDecimal precioVenta;
    private boolean esquemaComisionUSD;
    private BigDecimal comisionPesos;
    private BigDecimal comisionUSD;

    public AddOn() {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" - ").append(this.getName());
        return sb.toString();
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public boolean isEsquemaComisionUSD() {
        return esquemaComisionUSD;
    }

    public void setEsquemaComisionUSD(boolean esquemaComisionUSD) {
        this.esquemaComisionUSD = esquemaComisionUSD;
    }

    public BigDecimal getComisionPesos() {
        return comisionPesos;
    }

    public void setComisionPesos(BigDecimal comisionPesos) {
        this.comisionPesos = comisionPesos;
    }

    public BigDecimal getComisionUSD() {
        return comisionUSD;
    }

    public void setComisionUSD(BigDecimal comisionUSD) {
        this.comisionUSD = comisionUSD;
    }

}
