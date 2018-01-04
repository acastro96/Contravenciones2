/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPersonas;

/**
 *
 * @author JefreySistemas
 */
public interface ITPersonas {

    /**
     *
     * @param per
     * @return
     * @throws Exception
     */
    public long insert(CivPersonas per) throws Exception;

    /**
     *
     * @param per
     * @return
     * @throws Exception
     */
    public boolean update(CivPersonas per) throws Exception;

    /**
     * Retorna La Persona de la base de datos por medio del ID único.
     *
     * @param per_id ID único de la Persona a consultar.
     * @return La Persona resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivPersonas consultarPersonasById(int per_id) throws Exception;

    /**
     * Retorna La Persona de la base de datos por medio del documento de
     * identificación.
     *
     * @param tipo Tipo de documento
     * @param Documento Documento de la Persona
     * @return La Persona resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivPersonas consultarPersonasByDocumento(int tipo, String Documento) throws Exception;
    
}
