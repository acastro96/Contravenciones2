/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPerfilrecurso;
import java.util.List;

/**
 *
 * @author ing_jefreypadilla
 */
public interface ITPerfilRecursos {

    /**
     * Método para insertar un Perfil Recursos a la base de datos.
     *
     * @param perfilrecurso El Objeto Perfil Recursos a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public void insert(CivPerfilrecurso perfilrecurso) throws Exception;

    /**
     * Método para actualizar un Perfil Recursos a la base de datos.
     *
     * @param perfilrecurso El Objeto Perfil Recursos a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivPerfilrecurso perfilrecurso) throws Exception;

    /**
     * Retorna el listado de los Perfieles de Recursos correspondientes a un
     * formulario de la base de datos.
     *
     * @param perfil Perfil del Recurso.
     * @return Lista con los Perfil Recursos resultantes de la consulta. Retorna
     * NULL en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivPerfilrecurso> listPerfilRecursoByPerfil(long perfil) throws Exception;
    
    public List<CivPerfilrecurso> listPerfilRecursoByIDUsuario(long idusuario) throws Exception;
    
    public List<CivPerfilrecurso> listPerfilRecursoByIDUsuarioFechaFin(long idusuario) throws Exception;
    
    public List<CivPerfilrecurso> listPerfilRecurso() throws Exception;
}
