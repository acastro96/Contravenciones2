/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivAttempts;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Borja
 */
public class DaoAttempts extends HibernateDaoSupport implements ITAttempts {

    /**
     *
     * @param attp
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivAttempts attp) throws Exception {
        long id = Long.parseLong(getHibernateTemplate().save(attp).toString());
        return id;
    }

    /**
     *
     * @param attp
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivAttempts attp) throws Exception {
        getHibernateTemplate().merge(attp);
        return true;
    }

    /**
     *
     * @param id_usuario
     * @return
     * @throws Exception
     */
    @Override
    public CivAttempts consultarIntentos(int id_usuario) throws Exception {
        List list = getHibernateTemplate().findByNamedParam("FROM CivAttempts WHERE USU_ID= :idusuario", "idusuario", id_usuario);
        if (list.size() > 0) {
            return (CivAttempts) list.get(0);
        }
        return null;
    }
}
