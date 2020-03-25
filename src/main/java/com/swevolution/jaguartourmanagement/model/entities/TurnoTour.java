/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.model.entities;

import com.swevolution.jaguartourmanagement.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "TURNOTOUR", catalog = "JTM")
@XmlRootElement
public class TurnoTour extends PK_Long_Entity implements Serializable {

    @NotNull
    @Column(name = "NAME")
    private String name;
    private int maxCapacity;
    private String horario;
    private String horariodDeCruce;
    private LocalDate fechaDeCambio;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToOne
    private Tour tour;

    public TurnoTour() {
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

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHorariodDeCruce() {
        return horariodDeCruce;
    }

    public void setHorariodDeCruce(String horariodDeCruce) {
        this.horariodDeCruce = horariodDeCruce;
    }

    public LocalDate getFechaDeCambio() {
        return fechaDeCambio;
    }

    public void setFechaDeCambio(LocalDate fechaDeCambio) {
        this.fechaDeCambio = fechaDeCambio;
    }

}
