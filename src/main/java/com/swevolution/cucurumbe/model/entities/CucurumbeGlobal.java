/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.cucurumbe.model.entities;

import com.swevolution.cucurumbe.model.entities.util.PK_String_Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "CUBURUMBE_GLOBAL", catalog = "CUCURUMBE")
@XmlRootElement
public class CucurumbeGlobal extends PK_String_Entity implements Serializable {

    private BigDecimal tipoDeCambio;
    private String appName;
    private String appDescription;
    private int maxJeep;
    private int maxBuggy;

    public CucurumbeGlobal() {

    }

    public BigDecimal getTipoDeCambio() {
        return tipoDeCambio;
    }

    public void setTipoDeCambio(BigDecimal tipoDeCambio) {
        this.tipoDeCambio = tipoDeCambio;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public int getMaxJeep() {
        return maxJeep;
    }

    public void setMaxJeep(int maxJeep) {
        this.maxJeep = maxJeep;
    }

    public int getMaxBuggy() {
        return maxBuggy;
    }

    public void setMaxBuggy(int maxBuggy) {
        this.maxBuggy = maxBuggy;
    }

}
