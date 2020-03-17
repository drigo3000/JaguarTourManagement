/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.model.entities;

import com.swevolution.cucurumbe.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "AGENCY", catalog = "CUCURUMBE")
@XmlRootElement
public class Agency extends PK_Long_Entity implements Serializable {

    @NotNull
    @Column(name = "NAME")
    private String name;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    private int metaMensual;
    private String clasificacion;

    public Agency() {
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

    public int getMetaMensual() {
        return metaMensual;
    }

    public void setMetaMensual(int metaMensual) {
        this.metaMensual = metaMensual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" - ").append(this.getName());
        return sb.toString();
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

}
