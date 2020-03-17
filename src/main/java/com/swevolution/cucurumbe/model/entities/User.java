/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.model.entities;

import com.swevolution.cucurumbe.controllers.utility.HashManager;
import com.swevolution.cucurumbe.model.entities.util.PK_String_Entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "USERS", catalog = "CUCURUMBE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    ,
    @NamedQuery(name = "User.getUsers", query = "SELECT u FROM User u WHERE u.role LIKE :role AND u.name LIKE :name AND u.company LIKE :company AND u.operation LIKE :operation AND u.active = :active ORDER BY u.login ASC")
    ,
    @NamedQuery(name = "User.filterRest", query = "SELECT u FROM User u WHERE u.role LIKE :role AND u.name LIKE :name AND u.company LIKE :company AND u.operation LIKE :operation ORDER BY u.login ASC")
    ,
    @NamedQuery(name = "User.getByName", query = "SELECT u FROM User u WHERE u.login LIKE :keywords OR u.name LIKE :keywords ORDER BY u.login ASC")
    ,
    @NamedQuery(name = "User.resetPassword", query = "UPDATE User u SET u.password = :password WHERE u.login = :login")
    ,
    @NamedQuery(name = "User.resetPasswordStatus", query = "UPDATE User u SET u.needsResetPassword = 0 WHERE u.login = :login")
    ,
    @NamedQuery(name = "User.resetAllPasswords", query = "UPDATE User u SET u.password = :password")
})
public class User extends PK_String_Entity implements Serializable {

    @NotNull
    @Column(name = "NAME", length = 200, nullable = false)
    private String name;
    @Column(name = "EMAIL", length = 200, nullable = true)
    private String email;
    @NotNull
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "TELEPHONE2")
    private String telephone2;
    @Column(name = "TELEPHONE3")
    private String telephone3;
    @Column(name = "ADDRESSLINE1")
    private String addressLine1;
    @Column(name = "ADDRESSLINE2")
    private String addressLine2;
    @Column(name = "ZIPCODE")
    private String zipCode;
    @Column(name = "STATENAME")
    private String stateName;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "RESET_PASSWORD")
    @NotNull
    private boolean needsResetPassword;
    @NotNull
    @Column(name = "USER_OPERATION")
    private String operation;
    @NotNull
    @Column(name = "COMPANY")
    private String company;
    @NotNull
    @Column(name = "USER_ROLE")
    private String role;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = HashManager.getEncoded(password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean getNeedsResetPassword() {
        return needsResetPassword;
    }

    public void setNeedsResetPassword(boolean needsResetPassword) {
        this.needsResetPassword = needsResetPassword;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.login).append(" - ").append(this.name);
        return sb.toString();
    }
}
