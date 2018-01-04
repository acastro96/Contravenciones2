/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPais;
import java.util.List;

/**
 *
 * @author Miguel Borja
 */
public interface ITPaises {

    /**
     * Método para insertar un País a la base de datos.
     *
     * @param Pais El Objeto País a actualizar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivPais Pais) throws Exception;

    /**
     * Método para actualizar un País a la base de datos.
     *
     * @param Pais El Objeto País a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivPais Pais) throws Exception;

    /**
     * Retorna el País de la base de datos por medio del ID único.
     *
     * @param id_Pais ID único del País a consultar.
     * @return El País resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivPais getPais(long id_Pais) throws Exception;

    /**
     * Retorna el listado de todos los Paises de la base de datos.
     *
     * @return @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivPais> listAllPaises() throws Exception;

}
