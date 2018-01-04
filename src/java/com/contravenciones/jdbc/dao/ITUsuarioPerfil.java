/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivUsuperfil;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jcarreno
 */
public interface ITUsuarioPerfil {

    /**
     * Método para insertar un Usuario Perfil a la base de datos.
     *
     * @param usuperfil El Objeto Usuario Perfil a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public BigDecimal  registrarUsuarioPerfil(CivUsuperfil usuperfil) throws Exception;

    /**
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    public List<CivUsuperfil> listUsuperfil(long usuario) throws Exception;

    /**
     *
     * @param usuperfil
     * @return
     * @throws Exception
     */
    public boolean updateUsuarioPerfil(CivUsuperfil usuperfil) throws Exception;

    /**
     *
     * @param usuario
     * @param perfil
     * @return
     * @throws Exception
     */
    public CivUsuperfil getUsuperfilUsuario(long usuario, long perfil) throws Exception;
    
    /**
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    public CivUsuperfil getPerfilUsuario(long usuario) throws Exception;
}
