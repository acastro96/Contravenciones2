/*
 * To change this template, choose Tools | Templates
 * Copyright 2016.
 */
package com.contravenciones.tr.bo;

import com.contravenciones.jsf.bean.BeanLogin;
import com.contravenciones.model.Modulo;
import java.util.List;

/**
 *
 * @author Jefrey Padilla
 */
public interface LoginBO {

    /**
     *
     * @param obj
     * @throws java.lang.Exception
     */
     void iniciarSesion(BeanLogin obj) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws java.lang.Exception
     */
    List<Modulo> listarModulos(BeanLogin obj) throws Exception;

    /**
     *
     * @return @throws Exception
     */
    public String generarContrasena() throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    List<String> listarRecursos(BeanLogin obj) throws Exception;
    
    
    void consultarDatosUsuario(BeanLogin obj) throws Exception;
}
