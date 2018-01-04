/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.model;

import java.util.Date;

/**
 * Clase que define los datos de autenticación RUNT y enrolamiento de una
 * persona.
 *
 * @author Miguel Borja
 */
public class AutenticacionUser {

    private char tipo_documento;
    private String documento;
    private String password;
    private String userAuthID;
    private String userAuthIDBiometrico;
    private String userPreAuthID;
    private int id_usuario;
    private Date ultimo_acceso;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the tipo_documento
     */
    public char getTipo_documento() {
        return tipo_documento;
    }

    /**
     * @param tipo_documento the tipo_documento to set
     */
    public void setTipo_documento(char tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    /**
     * @return the userAuthID
     */
    public String getUserAuthID() {
        return userAuthID;
    }

    /**
     * @param userAuthID the userAuthID to set
     */
    public void setUserAuthID(String userAuthID) {
        this.userAuthID = userAuthID;
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the ultimo_acceso
     */
    public Date getUltimo_acceso() {
        return ultimo_acceso;
    }

    /**
     * @param ultimo_acceso the ultimo_acceso to set
     */
    public void setUltimo_acceso(Date ultimo_acceso) {
        this.ultimo_acceso = ultimo_acceso;
    }

    /**
     * @return the userPreAuthID
     */
    public String getUserPreAuthID() {
        return userPreAuthID;
    }

    /**
     * @param userPreAuthID the userPreAuthID to set
     */
    public void setUserPreAuthID(String userPreAuthID) {
        this.userPreAuthID = userPreAuthID;
    }

    /**
     * @return the userAuthIDBiometrico
     */
    public String getUserAuthIDBiometrico() {
        return userAuthIDBiometrico;
    }

    /**
     * @param userAuthIDBiometrico the userAuthIDBiometrico to set
     */
    public void setUserAuthIDBiometrico(String userAuthIDBiometrico) {
        this.userAuthIDBiometrico = userAuthIDBiometrico;
    }

}
