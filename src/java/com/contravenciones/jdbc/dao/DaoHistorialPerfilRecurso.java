/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivHistorialPerfilRecurso;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roymer Camacho
 */
public class DaoHistorialPerfilRecurso extends HibernateDaoSupport implements ITHistorialPerfilRecurso {

    @Override

    @Transactional(rollbackFor = Exception.class)
    public long insert(CivHistorialPerfilRecurso perfilrecurso) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(perfilrecurso).toString());
    }

    @Override

    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivHistorialPerfilRecurso perfilrecurso) throws Exception {
        getHibernateTemplate().update(perfilrecurso);
        return true;
    }
 
    @Override

    public List<CivHistorialPerfilRecurso> listPerfilRecursoByIDUsuario(long idusuario) throws Exception {

        String hql = "from CivHistorialPerfilRecurso where usu_id=:idusuario";
        List list = getHibernateTemplate().findByNamedParam(hql, "idusuario", idusuario);
        return list;
    }
    
  

}
