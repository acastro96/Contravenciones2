/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.model;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que define los módulos y recursos a los cuales los usuarios tienen
 * acceso.
 *
 * @author ing_jefreypadilla
 */
public class Modulo implements Serializable {

    private static final long serialVersionUID = 752642345145887L;

    private int codigo;
    private String nombre;
    private String icon;
    private List<Recurso> listRecurso;

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
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the listRecurso
     */
    public List<Recurso> getListRecurso() {
        return listRecurso;
    }

    /**
     * @param listRecurso the listRecurso to set
     */
    public void setListRecurso(List<Recurso> listRecurso) {
        this.listRecurso = listRecurso;
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

}
