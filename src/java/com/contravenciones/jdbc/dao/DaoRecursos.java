/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivRecursos;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JefreySistemas
 */
public class DaoRecursos extends HibernateDaoSupport implements ITRecursos {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivRecursos recursos) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(recursos).toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivRecursos recursos) throws Exception {
        getHibernateTemplate().update(recursos);
        return true;
    }
    
    @Override

    public List<CivRecursos> getRecursosByModulo(int modulo) throws Exception {
            
        String hql = "from CivRecursos where mod_id =:modulo and rec_fechafin is null";
        List list = getHibernateTemplate().findByNamedParam(hql, "modulo", modulo);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override

    public List<CivRecursos> getRecursosByIdPerfil(int id_perfil) throws Exception {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        String hql = "select cr.* "
                + "from civ_perfiles cp "
                + "inner join CIV_PERFILRECURSO cpr on cpr.PERF_ID = cp.PERF_ID "
                + "inner join CIV_RECURSOS cr on cr.rec_id = cpr.REC_ID "
                + "where cp.PERF_ID=:idperfil order by 1 asc";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(CivRecursos.class);
        query.setInteger("idperfil", id_perfil);

        return query.list();
    }
    
    @Override

    public CivRecursos getRecursosbyId(int id_recurso) throws Exception {

        String hql = "from CivRecursos where rec_id=:id_recurso";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_recurso", id_recurso);
        if (list.size() > 0) {
            return (CivRecursos) list.get(0);
        }
        return null;
    }
    
    @Override

    public List<CivRecursos> getRecursosAll() throws Exception {
        String hql = "from CivRecursos where rec_fechafin is null order by mod_id asc";
        List list = getHibernateTemplate().find(hql);
        return list;
    }
}
