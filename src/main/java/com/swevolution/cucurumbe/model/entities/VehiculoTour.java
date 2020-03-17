/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.model.entities;

import com.swevolution.cucurumbe.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
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
@Table(name = "VEHICULOS_TOUR", catalog = "CUCURUMBE")
@XmlRootElement
public class VehiculoTour extends PK_Long_Entity implements Serializable {

    @ManyToOne
    private Tour tour;
    @NotNull
    private String name;
    @Lob
    private String description;

    public VehiculoTour() {
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

}
