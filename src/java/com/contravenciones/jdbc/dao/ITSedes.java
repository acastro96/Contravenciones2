/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivSedes;
import java.util.List;

/**
 *
 * @author ing_jefreypadilla
 */
public interface ITSedes {

    /**
     * Método para insertar una Sede a la base de datos.
     *
     * @param sedes El Objeto Sede a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivSedes sedes) throws Exception;

    /**
     * Método para actualizar una Sede a la base de datos.
     *
     * @param sedes El Objeto Sede a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivSedes sedes) throws Exception;

    /**
     * Retorna la Sede de la base de datos por medio del ID único.
     *
     * @param idsede ID único de la Sede en el sistema.
     * @return La sede resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivSedes getSedeById(long idsede) throws Exception;

    /**
     * Retorna el listado de las Sedes correspondientes a un organismo de la
     * base de datos.
     *
     * @param idorganismo ID único del Organismo en el sistema.
     * @return Lista con las Sedes resultantes de la consulta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivSedes> listSedesByOrganismo(long idorganismo) throws Exception;
}
