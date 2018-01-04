/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.tr.bo;

import com.contravenciones.jsf.bean.BeanRenovarPassword;

/**
 *
 * @author JgCarreno
 */
public interface RenovarPasswordBO {

    /**
     *
     * @param bean
     * @return
     * @throws Exception
     */
    boolean validarPassword(BeanRenovarPassword bean) throws Exception;

    /**
     *
     * @param bean
     * @return
     * @throws Exception
     */
    boolean updatePassword(BeanRenovarPassword bean) throws Exception;

}
