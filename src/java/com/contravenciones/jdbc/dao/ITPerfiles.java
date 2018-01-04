/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPerfiles;
import java.util.List;

/**
 *
 * @author JefreySistemas
 */
public interface ITPerfiles {

    /**
     * Retorna todos los perfiles de la base de datos
     *
     * @return Lista con los Perfiles resultantes de la consulta. Retorna NULL
     * en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivPerfiles> getAllPerfiles() throws Exception;

    /**
     * Retorna el Perfil de la base de datos por medio del ID único.
     *
     * @param perfil ID único del Perfil en el sistema.
     * @return El Perfil resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivPerfiles consultarPerfilById(int perfil) throws Exception;
    
    public CivPerfiles consultarPerfilByName(String name) throws Exception;
}
