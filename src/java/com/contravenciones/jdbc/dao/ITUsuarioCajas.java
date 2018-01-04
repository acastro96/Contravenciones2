/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivUsuarioCajas;
import java.util.List;

/**
 *
 * @author ing_jefreypadilla
 */
public interface ITUsuarioCajas {

    /**
     * Insertar Usuario Caja
     *
     * @param usuarioCajas
     * @return id de inserción
     * @throws Exception
     */
    public void insert(CivUsuarioCajas usuarioCajas) throws Exception;

    /**
     * Actualizacion de usuarioCaja
     *
     * @param usuarioCajas
     * @return true en el caso de actualizar correctamente
     * @throws Exception
     */
    public boolean update(CivUsuarioCajas usuarioCajas) throws Exception;

      /**
     * Listado de Cajas asociadas al usuario
     *
     * @param usuario
     * @return listado de usuario Cajas
     * @throws Exception
     */
    public List<CivUsuarioCajas> listUsuarioCajasByUsuario(long usuario) throws Exception;
    
     /**
     * Listado de Cajas asociadas al usuario
     *
     * @param usuario
     * @return listado de usuario Cajas
     * @throws Exception
     */
    public List<CivUsuarioCajas> listUsuarioCajasByUsuarioFechaFin(long usuario) throws Exception;
    
     /**
     * Listado de Cajas asociadas al usuario
     *
     * @param usuario
     * @return listado de usuario Cajas
     * @throws Exception
     */
    public List<CivUsuarioCajas> getAll() throws Exception;
    
     /**
     * Listado de Cajas asociadas al usuario
     *
     * @param usuario
     * @return listado de usuario Cajas
     * @throws Exception
     */
    public List<CivUsuarioCajas> listUsuarioCajasByCaja(long caja) throws Exception;
    
     /**
     * Listado de Cajas asociadas al usuario
     *
     * @param usuario
     * @return caja que tiene asignado un usuario
     * @throws Exception
     */
    public CivUsuarioCajas getCajaUsuario(long usuario) throws Exception;

}
