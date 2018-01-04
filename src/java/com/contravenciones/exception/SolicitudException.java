/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.exception;

import javax.faces.application.FacesMessage;

/**
 *
 * @author Miguel Borja
 */
public class SolicitudException extends Exception {

    private int nivel;

    /**
     * Constructor donde se establece el mensaje de error y el nivel de error.
     *
     * @param message Mensaje de error
     * @param nivel Nivel de error
     */
    public SolicitudException(String message, int nivel) {
        super(message);
        this.nivel = nivel;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * Returna nivel de Severidad de mensajes de Faces
     *
     * @return Nivel del mensaje Severity
     */
    public FacesMessage.Severity getNivelFacesMessage() {
        FacesMessage.Severity sev;
        switch (this.getNivel()) {
            case 1:
                sev = FacesMessage.SEVERITY_WARN;
                break;
            case 2:
                sev = FacesMessage.SEVERITY_INFO;
                break;
            default:
                sev = FacesMessage.SEVERITY_ERROR;
                break;
        }
        return sev;
    }
    @Override
    public String getMessage() {
        switch (this.getNivel()) {
            case 1:
            case 2:
                return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
            default:
                return "Se ha registrado un error. Por favor intente de nuevo. "+super.getMessage(); //To change body of generated methods, choose Tools | Templates.

        }
    }
}
