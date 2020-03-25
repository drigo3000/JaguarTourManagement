/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.model.entities;

import com.swevolution.jaguartourmanagement.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "TOUR_PHOTO")
@NamedQueries({
    @NamedQuery(name = "TourPhoto.findByTourId", query = "SELECT c FROM TourPhoto c WHERE c.tour.id = :id")
})
@XmlRootElement
public class TourPhoto extends PK_Long_Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "TOUR_ID")
    private Tour tour;

    @Size(min = 0, max = 500)
    @Column(nullable = false, length = 255, name = "PHOTO_LINK")
    protected String link;

    @Size(min = 0, max = 500)
    @Column(nullable = false, length = 255, name = "PHOTO_DESCRIPTION")
    protected String description;

    public TourPhoto() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
