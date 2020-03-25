/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.business.session;

/**
 *
 * @author Rxkx
 */
public enum SecurityClearance {
    //GUEST ACCOUNT
    GUEST(0),
    GUIA(50),
    //OPERACIONES

    //RESERVACIONES
    RESERVACIONES(200),
    SUPERVISOR_RESERVACIONES(210),
    GERENTE_RESERVACIONES(230),
    //OPERATIVO
    GERENTE_DE_TRANSPORTACION(225),
    COORDINADORA_DE_LOGISTICA(225),
    LOGISTICA_TRANSPORTACION(225),
    GERENTE_DE_TRAFICO(230),
    GERENTE_OPERATIVO(230),
    DIRECTOR_ADDONS(230),
    DIRECTOR_OPERATIVO(250),
    //SUPERVISORS
    SUPERVISOR(600),
    MANAGER(700),
    DIRECTOR(800),
    WEBADMIN(1000),
    //web master
    WEBMASTER(10000);
    private final int clearance;

    private SecurityClearance(int clearance) {
        this.clearance = clearance;
    }

    public int getClearance() {
        return clearance;
    }
}
