/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivDepartamentos;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Borja
 */
public class DaoDepartamentos extends HibernateDaoSupport implements ITDepartamentos, Serializable {

    private static final long serialVersionUID = 2365472295622776524L;  // unique id

    @Override
    public List<CivDepartamentos> listAllDepartamentos() throws Exception {

        String hql = "FROM CivDepartamentos";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    /**
     *
     * @param pais
     * @return
     * @throws Exception
     */
    @Override
    public List<CivDepartamentos> listDepartamentosByPais(int pais) throws Exception {
        String hql = "FROM CivDepartamentos where pai_id = :pais";
        List list = getHibernateTemplate().findByNamedParam(hql, "pais", pais);
        return list;
    }

    @Override
    public CivDepartamentos getDepartamento(long iddepartamento) throws Exception {

        String hql = "FROM CivDepartamentos WHERE DEP_ID=:iddepartamento";
        List list = getHibernateTemplate().findByNamedParam(hql, "iddepartamento", iddepartamento);
        if (list.size() > 0) {
            return (CivDepartamentos) list.get(0);
        }
        return null;
    }

    @Override
    public CivDepartamentos getDepartamentoByCodigoDepartamento(long coddepartamento) throws Exception {

        String hql = "FROM CivDepartamentos WHERE DEP_CODDEPARTAMENTO=:coddepartamento";
        List list = getHibernateTemplate().findByNamedParam(hql, "coddepartamento", coddepartamento);
        if (list.size() > 0) {
            return (CivDepartamentos) list.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivDepartamentos departamento) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(departamento).toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivDepartamentos departamento) throws Exception {
        getHibernateTemplate().merge(departamento);
        return true;
    }

}
