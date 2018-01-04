/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPerfiles;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 *
 * @author JefreySistemas
 */
public class DaoPerfiles extends HibernateDaoSupport implements ITPerfiles {

    @Override

    public List<CivPerfiles> getAllPerfiles() throws Exception {
        String hql = "from CivPerfiles order by 1 asc";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    @Override

    public CivPerfiles consultarPerfilById(int id_perfil) throws Exception {

        String hql = "from CivPerfiles where perf_id=:id_perfil";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_perfil", id_perfil);
        if (list.size() > 0) {
            return (CivPerfiles) list.get(0);
        }
        return null;
    }
    
    @Override

    public CivPerfiles consultarPerfilByName(String name) throws Exception {

        String hql = "from CivPerfiles where perf_nombre=:name";
        List list = getHibernateTemplate().findByNamedParam(hql, "name", name);
        if (list.size() > 0) {
            return (CivPerfiles) list.get(0);
        }
        return null;
    }
}
