/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.tr.bo;

import com.contravenciones.jsf.bean.BeanGestionUsuario;


/**
 *
 * @author Miguel Borja
 */
public interface GestionUsuarioBO {

    /**
     *
     * @param bean
     * @throws Exception
     */
    void listRecursosPerfiles(BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param cod_rec
     * @param bean
     * @throws Exception
     */
    void listDetalleRecursosID(int cod_rec, BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param bean
     * @throws Exception
     */
    void listModulos(BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param cod
     * @param bean
     * @throws Exception
     */
    void listDetalleRecursobyUsu(int cod, BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param bean
     * @throws Exception
     */
    void listUsuario(BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param bean
     * @throws Exception
     */
    void consultarPersona(BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param bean
     * @throws Exception
     */
    void listarPerfiles(BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param bean
     * @throws Exception
     */
    void listarMPagoCaja(BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param bean
     * @throws Exception
     */
    void registrarUsuario(BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param bean
     * @throws Exception
     */
    void registrarPerfil(BeanGestionUsuario bean) throws Exception;
    
    /**
     *
     * @param bean
     * @throws Exception
     */
    void actualizaUsuario(BeanGestionUsuario bean) throws Exception;
    
     /**
     *
     * @param bean
     * @param codigoUsuario
     * @throws Exception
     */
    void detalleUsuario(int codigoUsuario, BeanGestionUsuario bean) throws Exception;
    
    void detalleRecurso(BeanGestionUsuario bean) throws Exception;
    
    void detalleUsuarioActualizacion(int codigoUsuario, BeanGestionUsuario bean) throws Exception;
    
    void listRecursosbyModulo(int modulo, BeanGestionUsuario bean) throws Exception;
    
    public void generarContrasenaAleatoria(BeanGestionUsuario bean) throws Exception;
    
}
