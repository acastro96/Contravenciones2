/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.tr.bo;

import com.contravenciones.jsf.bean.BeanGestionPersona;


/**
 *
 * @author Roymer Camacho
 */
public interface GestionPersonaBO {

    /**
     *
     * @param bean
     * @throws Exception
     */
    void listPersona(BeanGestionPersona bean) throws Exception;
    
}
