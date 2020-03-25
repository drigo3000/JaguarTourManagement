/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.model.entities;

import com.swevolution.jaguartourmanagement.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "TOUR_SERVICE_PROVIDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TourServiceProvider.findAll", query = "SELECT t FROM TourServiceProvider t")
    ,
    @NamedQuery(name = "TourServiceProvider.findByName", query = "SELECT t FROM TourServiceProvider t WHERE t.name LIKE :name")
    ,
    @NamedQuery(name = "TourServiceProvider.findEqualName", query = "SELECT t FROM TourServiceProvider t WHERE t.name = :name")
})
public class TourServiceProvider extends PK_Long_Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "provider")
    private List<Tour> tours;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public TourServiceProvider() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

}
