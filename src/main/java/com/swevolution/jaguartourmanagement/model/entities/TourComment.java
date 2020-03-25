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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "TOUR_COMMENT")
@XmlRootElement
public class TourComment extends PK_Long_Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "TOUR_ID")
    private Tour tour;

    @Size(min = 0, max = 500)
    @Column(length = 500, name = "COMMENT")
    private String comment;

    @Size(min = 0, max = 100)
    @Column(length = 100, name = "USER_NAME")
    private String userName;

    @Size(min = 0, max = 100)
    @Column(length = 100, name = "EMAIL")
    private String email;

    @Size(min = 0, max = 100)
    @Column(length = 100, name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "SCORE")
    private int score;

    public TourComment() {
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
