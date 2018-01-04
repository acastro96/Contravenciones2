/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivHistorialPerfilRecurso;
import java.util.List;

/**
 *
 * @author Roymer Camacho
 */
public interface ITHistorialPerfilRecurso {

    /**
     * Método para insertar un Perfil Recursos a la base de datos.
     *
     * @param perfilrecurso El Objeto Perfil Recursos a insertar.
    
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivHistorialPerfilRecurso perfilrecurso) throws Exception;

    /**
     * Método para actualizar un Perfil Recursos a la base de datos.
     *
     * @param perfilrecurso El Objeto Perfil Recursos a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivHistorialPerfilRecurso perfilrecurso) throws Exception;

    public List<CivHistorialPerfilRecurso> listPerfilRecursoByIDUsuario(long idusuario) throws Exception;
    
  
}
