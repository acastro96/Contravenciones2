/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPersonas;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JefreySistemas
 */
public class DaoPersonas extends HibernateDaoSupport implements ITPersonas {

    @Override
    public CivPersonas consultarPersonasById(int per_id) throws Exception {
        String hql = "from CivPersonas where per_id =:per_id";
        List list = getHibernateTemplate().findByNamedParam(hql, "per_id", per_id);
        if (list.size() > 0) {
            return (CivPersonas) list.get(0);
        }
        return null;
    }

    @Override
    public CivPersonas consultarPersonasByDocumento(int tipo, String nro_documento) throws Exception {
        String hql = "from CivPersonas where per_tipodocumento =:tipo and per_documento=:nro_documento";
        List list = getHibernateTemplate().findByNamedParam(hql, new String[]{"tipo", "nro_documento"}, new Object[]{tipo, nro_documento});
        if (list.size() > 0) {
            return (CivPersonas) list.get(0);
        }
        return null;
    }
    
    
    
    /**
     *
     * @param per
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivPersonas per) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(per).toString());
    }

    /**
     *
     * @param per
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivPersonas per) throws Exception {
        getHibernateTemplate().merge(per);
        return true;
    }

}
