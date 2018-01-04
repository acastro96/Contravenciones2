/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPersonas;
import com.contravenciones.tr.persistence.CivUspHistoria;
import com.contravenciones.tr.persistence.CivUsuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JefreySistemas
 */
public interface ITUsuarios {

    /**
     * Valida el usuario y la contraseña. Usado para el inicio de sesión
     *
     * @param usuario Nombre de Usuario.
     * @param password El Objeto Usuario a insertar.
     * @return El Usuario resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
//    public CivUsuarios consultarUsuario(String usuario, String password) throws Exception;
    /**
     * Retorna la Configuración de la base de datos por medio del ID único.
     *
     * @param id ID único de la Persona en el sistema.
     * @return El Usuario resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivUsuarios consultarUsuarioBy(int id) throws Exception;

    /**
     * Retorna el listado de todos los Usuarios de la base de datos.
     *
     * @return @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivUsuarios> getAll() throws Exception;

    /**
     * Retorna el listado de los Datos de Vehículos correspondientes a un
     * vehiculo de la base de datos.
     *
     * @param Usuario Nombre de usuario. 'ALL' o vacío trae todos los usuarios.
     * @return Lista con los Usuarios resultantes de la consulta. Retorna NULL
     * en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivUsuarios> listarUsuarios(String Usuario) throws Exception;

    /**
     * Método para insertar un Usuario de la base de datos.
     *
     * @param civUsuario El Objeto Usuario a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivUsuarios civUsuario) throws Exception;

    /**
     * Método para actualizar un Usuario de la base de datos.
     *
     * @param civUsuario El Objeto Usuario a actualizar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivUsuarios civUsuario) throws Exception;

    /**
     * Retorna el listado de los Datos de Vehículos correspondientes a un
     * vehiculo de la base de datos.
     *
     * @param Nombre Nombre de usuario.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivUsuarios consultarUsuarioByNombre(String Nombre) throws Exception;

    /**
     *
     * @param id_usuario
     * @return
     * @throws Exception
     */
    public ArrayList<String> consultar_HPAS(int id_usuario) throws Exception;
    
    /**
     *
     * @param id_usuario
     * @return
     * @throws Exception
     */
    public List<CivUspHistoria> consultarEstado_HPAS(int id_usuario) throws Exception;

    /**
     *
     * @param uspHistoria
     * @return
     * @throws Exception
     */
    public long insertHisPass(CivUspHistoria uspHistoria) throws Exception;
    
    /**
     *
     * @param uspHistoria
     * @return
     * @throws Exception
     */
    public boolean updateHisPass(CivUspHistoria uspHistoria) throws Exception;
    
    public Date consultarFechaUltimoPassword(int id_usuario) throws Exception;
    
    /**
     * Esta funcion se utiliza para retornar el id del usuario
     * @param id
     * @return
     * @throws Exception 
     */
    public CivUsuarios consultarIdPer(int id) throws Exception;
  
}
