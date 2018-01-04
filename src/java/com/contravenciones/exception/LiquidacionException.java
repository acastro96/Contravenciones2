/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.exception;

import javax.faces.application.FacesMessage;

/**
 * Clase para definir los errores del módulo de Liquidación
 *
 * @author Miguel Borja
 */
public class LiquidacionException extends Exception {

    private int nivel;
    
    /*
     * Constructor donde se establece el mensaje de error 
     * @param message Mensaje de error
     */
    public LiquidacionException(String message, int nivel) {
        super(message);
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
    

}
