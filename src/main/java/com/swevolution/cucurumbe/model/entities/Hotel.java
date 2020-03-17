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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "HOTEL", catalog = "CUCURUMBE")
@XmlRootElement
public class Hotel extends PK_Long_Entity implements Serializable {

    @Column(name = "NAME", nullable = false, length = 255)
    private String name;
    @Column(name = "HOTEL_OPERATION")
    private String operation;
    @Lob
    @Column(name = "NOTES", nullable = true)
    private String notes;
    private String lugarPickup;
    private int position;
    private String subOperation;
    private String subLocation;

    public Hotel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLugarPickup() {
        return lugarPickup;
    }

    public void setLugarPickup(String lugarPickup) {
        this.lugarPickup = lugarPickup;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSubOperation() {
        return subOperation;
    }

    public void setSubOperation(String subOperation) {
        this.subOperation = subOperation;
    }

    public int getOrderPosition() {
        int pos = 0;
        switch (getOperation()) {
            case "RVM":
                pos = 1000;
                pos += getPosition();
                break;
            case "CUN":
                pos = 10000;
                pos += getPosition();
                break;
            case "CZM":
                pos = 100000;
                pos += getPosition();
                break;
            default:
                pos = 0;
        }
        return pos;
    }

    public String getSubLocation() {
        return subLocation;
    }

    public void setSubLocation(String subLocation) {
        this.subLocation = subLocation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" - ").append(this.getName());
        return sb.toString();
    }

}
