/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.tr.bo;

import com.contravenciones.jsf.bean.BeanParametros;
import java.util.List;

/**
 *
 * @author ing_jefreypadilla
 */
public interface ParametrosBO {

    /**
     *
     * @param beanPar
     * @return
     * @throws Exception
     */
    List<BeanParametros> listParametros(BeanParametros beanPar) throws Exception;

    /**
     *
     * @param beanParametros
     * @return
     * @throws Exception
     */
    String consultarParametro(BeanParametros beanParametros) throws Exception;

    /**
     *
     * @param beanParametros
     * @return
     * @throws Exception
     */
    List<BeanParametros> listDepartamentos(BeanParametros beanParametros) throws Exception;

    /**
     *
     * @param beanParametros
     * @return
     * @throws Exception
     */
    List<BeanParametros> listDepartamentosByPais(BeanParametros beanParametros) throws Exception;

    /**
     *
     * @param beanParametros
     * @return
     * @throws Exception
     */
    List<BeanParametros> listPaises(BeanParametros beanParametros) throws Exception;

    /**
     *
     * @param beanParametros
     * @return
     * @throws Exception
     */
    List<BeanParametros> listMunicipios(BeanParametros beanParametros) throws Exception;

     /**
     *
     * @param beanPar
     * @return
     * @throws Exception
     */
    List<BeanParametros> listParametrosBySubCodigo(BeanParametros beanPar) throws Exception;

    
    void listSedes(BeanParametros bean) throws Exception;
    
    /** Lista las cajas en estado 1
     *
     * @param bean
     * @throws Exception
     */
    void listCajas(BeanParametros bean) throws Exception;

    
}
