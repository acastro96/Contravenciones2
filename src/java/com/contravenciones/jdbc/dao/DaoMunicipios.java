/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivMunicipios;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Borja
 */
public class DaoMunicipios extends HibernateDaoSupport implements ITMunicipios, Serializable {

    private static final long serialVersionUID = 58584222356227747L;

    @Override
    public List<CivMunicipios> listAllMunicipios() throws Exception {

        String hql = "FROM CivMunicipios";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public CivMunicipios getMunicipio(long id_municipio) throws Exception {

        String hql = "FROM CivMunicipios WHERE MUN_ID=:id_municipio ";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_municipio", id_municipio);
        if (list.size() > 0) {
            return (CivMunicipios) list.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivMunicipios municipio) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(municipio).toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivMunicipios municipio) throws Exception {
        getHibernateTemplate().merge(municipio);
        return true;
    }

    @Override
    public List<CivMunicipios> listMunicipiosByDep(long departamento) throws Exception {
        String hql = "FROM CivMunicipios where dep_id=:departamento";
        List list = getHibernateTemplate().findByNamedParam(hql, "departamento", departamento);
        return list;
    }
}
