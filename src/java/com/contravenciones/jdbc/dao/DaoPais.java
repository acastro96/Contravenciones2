/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPais;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Borja
 */
public class DaoPais extends HibernateDaoSupport implements ITPaises, Serializable {

    private static final long serialVersionUID = 558922235622776147L;

    @Override
    public List<CivPais> listAllPaises() throws Exception {

        String hql = "FROM CivPais";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public CivPais getPais(long id_pais) throws Exception {

        String hql = "FROM CivPais WHERE pai_codpais = :id_pais";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_pais", id_pais);
        if (list.size() > 0) {
            return (CivPais) list.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivPais pais) throws Exception {
        long id = Long.parseLong(getHibernateTemplate().save(pais).toString());
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivPais pais) throws Exception {
        getHibernateTemplate().merge(pais);
        return true;

    }
}
