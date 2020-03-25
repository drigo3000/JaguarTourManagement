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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "REPRESENTANTE", catalog = "JTM")
@XmlRootElement
public class Representante extends PK_Long_Entity implements Serializable {

    @NotNull
    @Column(name = "NAME", length = 200, nullable = false)
    private String name;
    @Column(name = "EMAIL", length = 200, nullable = true)
    private String email;
    @Column(name = "TELEPHONE")
    private String telephone;
    @ManyToOne
    private Agency agencia;
    private LocalDate fechaNacimiento;

    public Representante() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Agency getAgencia() {
        return agencia;
    }

    public void setAgencia(Agency agencia) {
        this.agencia = agencia;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(this.id).append("] ").append(this.name).append(" ").append(this.agencia.getName());
        return sb.toString();
    }

}
