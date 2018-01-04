/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.model;

import java.io.Serializable;

/**
 * Clase que define un recurso del sistema. Por ejemplo una vista que se llama
 * desde el menú principal.
 *
 * @author ing_jefreypadilla
 */
public class Recurso implements Serializable {

    private static final long serialVersionUID = 7526423451548L;

    private int codigo;
    private String nombre;
    private String carpeta;
    private String descripcion;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the carpeta
     */
    public String getCarpeta() {
        return carpeta;
    }

    /**
     * @param carpeta the carpeta to set
     */
    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }
}
