/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivDepartamentos;
import java.util.List;

/**
 *
 * @author Miguel Borja
 */
public interface ITDepartamentos {

    /**
     * Método para insertar un Departamento a la base de datos.
     *
     * @param departamento El Objeto Departamento a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivDepartamentos departamento) throws Exception;

    /**
     * Método para insertar un Departamento a la base de datos.
     *
     * @param departamento El Objeto Departamento a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivDepartamentos departamento) throws Exception;

    /**
     * Retorna el Departamento de la base de datos por medio del ID único.
     *
     * @param id_departamento ID único del Departamento en el sistema.
     * @return El Departamento resultante de la consulta. Retorna NULL en caso
     * de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivDepartamentos getDepartamento(long id_departamento) throws Exception;

    /**
     * Retorna el listado de todos los Departamentos de la base de datos.
     *
     * @return Lista con los Departamentos resultantes de la consulta. Retorna
     * NULL en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivDepartamentos> listAllDepartamentos() throws Exception;

    /**
     *
     * @param pais
     * @return
     * @throws Exception
     */
    public List<CivDepartamentos> listDepartamentosByPais(int pais) throws Exception;

    /**
     * Retorna el Departamento de la base de datos por medio del Codigo único.
     *
     * @param coddepartamento Codigo único del Departamento en el sistema.
     * @return El Departamento resultante de la consulta. Retorna NULL en caso
     * de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivDepartamentos getDepartamentoByCodigoDepartamento(long coddepartamento) throws Exception;

}
