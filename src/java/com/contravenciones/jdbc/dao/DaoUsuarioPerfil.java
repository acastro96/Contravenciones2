/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivUsuperfil;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jcarreno
 */
public class DaoUsuarioPerfil extends HibernateDaoSupport implements ITUsuarioPerfil {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BigDecimal  registrarUsuarioPerfil(CivUsuperfil usuperfil) throws Exception {
        return (BigDecimal ) getHibernateTemplate().save(usuperfil);
    }

    /**
     *
     * @param usuperfil
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUsuarioPerfil(CivUsuperfil usuperfil) throws Exception {
        getHibernateTemplate().update(usuperfil);
        return true;
    }

    /**
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    @Override
    public List<CivUsuperfil> listUsuperfil(long usuario) throws Exception {
        String hql = "from CivUsuperfil where usu_id=:usuario";
        List list = getHibernateTemplate().findByNamedParam(hql, "usuario", BigDecimal.valueOf(usuario));
        return list;
    }

    /**
     *
     * @param usuario
     * @param perfil
     * @return
     * @throws Exception
     */
    @Override
    public CivUsuperfil getUsuperfilUsuario(long usuario, long perfil) throws Exception {
        String hql = "from CivUsuperfil where usu_Id=:usuario and perf_Id=:perfil and usuper_fechafin is null ORDER BY 1 asc";
        List list = getHibernateTemplate().findByNamedParam(hql, new String[]{"usuario", "perfil"}, new Object[]{BigDecimal.valueOf(usuario), BigDecimal.valueOf(perfil)});
        if (list.isEmpty()) {
            return null;
        }
        return (CivUsuperfil) list.get(0);
    }
    
    @Override
    public CivUsuperfil getPerfilUsuario(long usuario) throws Exception {
         String hql = "from CivUsuperfil where usu_Id=:usuario and usuper_fechafin is null";
        List list = getHibernateTemplate().findByNamedParam(hql, "usuario", BigDecimal.valueOf(usuario));
        if (list.isEmpty()) {
            return null;
        }
        return (CivUsuperfil) list.get(0);
    }

}
