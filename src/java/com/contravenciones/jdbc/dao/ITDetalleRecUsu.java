/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivDetalleRecUsu;
import java.util.List;

/**
 *
 * @author JefreySistemas
 */
public interface ITDetalleRecUsu {
    
    /**
    
     *
     * @param recursos El Objeto Embargo a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivDetalleRecUsu recursos) throws Exception;

    /**
     
     *
     * @param recursos El Objeto Embargo a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivDetalleRecUsu recursos) throws Exception;
    
    /**
     * Retorna todos los detalle recursos de la base de datos
     *
     * @param id_usuario
     * @return Lista con los Perfiles resultantes de la consulta. Retorna NULL
     * en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivDetalleRecUsu> getDetalleRecursosbyUsu(int id_usuario) throws Exception;
    
    public List<CivDetalleRecUsu> listDetalleRecurso() throws Exception;

    
}
