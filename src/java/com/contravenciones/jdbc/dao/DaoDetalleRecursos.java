/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivDetalleRecursos;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JefreySistemas
 */
public class DaoDetalleRecursos extends HibernateDaoSupport implements ITDetalleRecursos {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivDetalleRecursos recursos) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(recursos).toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivDetalleRecursos recursos) throws Exception {
        getHibernateTemplate().update(recursos);
        return true;
    }
    
   @Override

    public List<CivDetalleRecursos> getAllDetalleRecursos() throws Exception {
        String hql = "from CivDetalleRecursos order by rec_id asc";
        List list = getHibernateTemplate().find(hql);
        return list;
    }
    
    @Override

    public List<CivDetalleRecursos> getDetalleRecursosbyRec(int id_recurso) throws Exception {
            
        String hql = "from CivDetalleRecursos where rec_id =:id_recurso";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_recurso", id_recurso);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    
}
