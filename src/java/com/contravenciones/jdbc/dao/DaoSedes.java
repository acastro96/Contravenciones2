/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivSedes;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ing_jefreypadilla
 */
public class DaoSedes extends HibernateDaoSupport implements ITSedes {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivSedes sedes) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(sedes).toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivSedes sedes) throws Exception {
        getHibernateTemplate().merge(sedes);
        return true;
    }

    @Override
    public CivSedes getSedeById(long id_sede) throws Exception {

        String hql = "from CivSedes where sed_id=:id_sede";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_sede", id_sede);
        if (list.size() > 00) {
            return (CivSedes) list.get(0);
        }
        return null;
    }

    @Override
    public List<CivSedes> listSedesByOrganismo(long id_organismo) throws Exception {

        String hql = "from CivSedes where org_id=:id_organismo";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_organismo", id_organismo);
        return list;
    }

}
