/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivModulos;
import java.util.List;

/**
 *
 * @author JefreySistemas
 */
public interface ITModulos {

    /**
     * Retorna el listado de todos los Modulos de la base de datos.
     *
     * @return Lista con los Modulos resultantes de la consulta. Retorna NULL en
     * caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivModulos> getAll() throws Exception;

    /**
     * Retorna el listado de los Modulos correspondientes a un vehículo de la
     * base de datos.
     *
     * @param usu_id ID único del Usuario en el sistema.
     * @return Lista con los Modulos resultantes de la consulta. Retorna NULL en
     * caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivModulos> getModulosByUsuario(int usu_id) throws Exception;
    
    /**
     * Retorna el listado de los Modulos correspondientes a un vehículo de la
     * base de datos.
     *
     * @param id_mod ID único del Usuario en el sistema.
     * @return Lista con los Modulos resultantes de la consulta. Retorna NULL en
     * caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivModulos getModuloID(int id_mod) throws Exception;
}
