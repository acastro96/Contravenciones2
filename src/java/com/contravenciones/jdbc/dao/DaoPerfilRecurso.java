/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPerfilrecurso;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ing_jefreypadilla
 */
public class DaoPerfilRecurso extends HibernateDaoSupport implements ITPerfilRecursos {

    @Override

    @Transactional(rollbackFor = Exception.class)
    public void insert(CivPerfilrecurso perfilrecurso) throws Exception {
        getHibernateTemplate().save(perfilrecurso).toString();
    }

    @Override

    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivPerfilrecurso perfilrecurso) throws Exception {
        getHibernateTemplate().update(perfilrecurso);
        return true;
    }

    @Override

    public List<CivPerfilrecurso> listPerfilRecursoByPerfil(long perfil) throws Exception {

        String hql = "from CivPerfilrecurso where perf_id=:perfil";
        List list = getHibernateTemplate().findByNamedParam(hql, "perfil", perfil);
        return list;
    }
    
    @Override

    public List<CivPerfilrecurso> listPerfilRecursoByIDUsuario(long idusuario) throws Exception {

        String hql = "from CivPerfilrecurso where usu_id=:idusuario";
        List list = getHibernateTemplate().findByNamedParam(hql, "idusuario", idusuario);
        return list;
    }
    
    @Override

    public List<CivPerfilrecurso> listPerfilRecursoByIDUsuarioFechaFin(long idusuario) throws Exception {

        String hql = "from CivPerfilrecurso where usu_id=:idusuario and PERREC_FECHAFIN is null";
        List list = getHibernateTemplate().findByNamedParam(hql, "idusuario", idusuario);
        return list;
    }
    
    @Override

    public List<CivPerfilrecurso> listPerfilRecurso() throws Exception {

        String hql = "from CivPerfilrecurso order by perf_id asc";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

}
