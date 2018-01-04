/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivDetalleRecUsu;
import com.contravenciones.tr.persistence.CivRecursos;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roymer Camacho
 */
public class DaoDetalleRecUsu extends HibernateDaoSupport implements ITDetalleRecUsu {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivDetalleRecUsu recursos) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(recursos).toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivDetalleRecUsu recursos) throws Exception {
        getHibernateTemplate().update(recursos);
        return true;
    }

    @Override
    public List<CivDetalleRecUsu> getDetalleRecursosbyUsu(int id_usuario) throws Exception {
        String hql = "from CivDetalleRecUsu where usu_id =:id_usuario";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_usuario", id_usuario);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override

    public List<CivDetalleRecUsu> listDetalleRecurso() throws Exception {

        String hql = "from CivDetalleRecUsu order by rec_id asc";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

}
