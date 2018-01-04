/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivAttempts;

/**
 *
 * @author Miguel Borja
 */
public interface ITAttempts {

    /**
     *
     * @param attp
     * @return
     * @throws Exception
     */
    public long insert(CivAttempts attp) throws Exception;

    /**
     *
     * @param attp
     * @return
     * @throws Exception
     */
    public boolean update(CivAttempts attp) throws Exception;

    /**
     *
     * @param id_usuario
     * @return
     * @throws Exception
     */
    public CivAttempts consultarIntentos(int id_usuario) throws Exception;
}
