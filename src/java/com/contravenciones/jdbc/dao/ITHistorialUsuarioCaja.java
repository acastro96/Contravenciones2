/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivHistorialUsuarioCaja;
import java.util.List;

/**
 *
 * @author Roymer Camacho
 */
public interface ITHistorialUsuarioCaja {

    /**
     * Método para insertar un Perfil Recursos a la base de datos.
     *
     * @param usuariocaja El Objeto Perfil Recursos a insertar.
    
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivHistorialUsuarioCaja usuariocaja) throws Exception;

    /**
     * Método para actualizar un Perfil Recursos a la base de datos.
     *
     * @param usuariocaja El Objeto Perfil Recursos a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivHistorialUsuarioCaja usuariocaja) throws Exception;
    
  
}
