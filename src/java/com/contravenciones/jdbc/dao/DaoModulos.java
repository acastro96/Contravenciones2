/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivModulos;
import java.util.LinkedList;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 *
 * @author JefreySistemas
 */
public class DaoModulos extends HibernateDaoSupport implements ITModulos {

    @Override

    public List<CivModulos> getAll() throws Exception {
        List list = getHibernateTemplate().find("from CivModulos where mod_fechafin is null");
        return list;
    }

    @Override

    public List<CivModulos> getModulosByUsuario(int usu_id) throws Exception {

        List<CivModulos> listModulos = new LinkedList<>();

        return listModulos;
    }
        
    @Override

    public CivModulos getModuloID(int modulo) throws Exception {

        String hql = "from CivModulos where mod_id=:modulo and mod_fechafin is null";
        List list = getHibernateTemplate().findByNamedParam(hql, "modulo", modulo);
        if (list.size() > 0) {
            return (CivModulos) list.get(0);
        }
        return null;
    }

}
