/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivMunicipios;
import java.util.List;

/**
 *
 * @author Miguel Borja
 */
public interface ITMunicipios {

    /**
     * Método para insertar un Municipio a la base de datos.
     *
     * @param municipio El Objeto Municipio a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivMunicipios municipio) throws Exception;

    /**
     * Método para actualizar un Municipio a la base de datos.
     *
     * @param municipio El Objeto Municipio a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivMunicipios municipio) throws Exception;

    /**
     * Retorna el listado de todos los Municipios de la base de datos.
     *
     * @return @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivMunicipios> listAllMunicipios() throws Exception;

    /**
     * Retorna el Municipio de la base de datos por medio del ID único.
     *
     * @param id_municipio ID único del Municipio a consultar.
     * @return El Municipio resultante de la consulta. Retorna NULL en caso de
     * no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivMunicipios getMunicipio(long id_municipio) throws Exception;

    /**
     * @param departamento
     * @return lista de municipios por departamento
     * @throws Exception
     */
    public List<CivMunicipios> listMunicipiosByDep(long departamento) throws Exception;

}
