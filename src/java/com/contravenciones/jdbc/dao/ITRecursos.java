/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivRecursos;
import java.util.List;

/**
 *
 * @author JefreySistemas
 */
public interface ITRecursos {
    
    /**
    
     *
     * @param recursos El Objeto Embargo a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivRecursos recursos) throws Exception;

    /**
     
     *
     * @param recursos El Objeto Embargo a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivRecursos recursos) throws Exception;

    /**
     * Retorna el listado de los Recursos correspondientes a un usuario y módulo
     * de la base de datos.
     *
     
     * @param modulo ID único del Módulo en el sistema.
     * @return Lista con los Recursos resultantes de la consulta. Retorna NULL
     * en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivRecursos> getRecursosByModulo(int modulo) throws Exception;

    /**
     * Retorna el listado de los Recursos correspondientes a un perfil de la
     * base de datos.
     *
     * @param id_perfil ID único del Perfil en el sistema.
     * @return Lista con los Recursos resultantes de la consulta. Retorna NULL
     * en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivRecursos> getRecursosByIdPerfil(int id_perfil) throws Exception;
    
    public CivRecursos getRecursosbyId(int id_recurso) throws Exception;
    
    public List<CivRecursos> getRecursosAll() throws Exception;
}
