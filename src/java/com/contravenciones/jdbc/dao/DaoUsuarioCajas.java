/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivUsuarioCajas;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ing_jefreypadilla
 */
public class DaoUsuarioCajas extends HibernateDaoSupport implements ITUsuarioCajas {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(CivUsuarioCajas usuarioCajas) throws Exception {
        getHibernateTemplate().save(usuarioCajas).toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivUsuarioCajas usuarioCajas) throws Exception {
        getHibernateTemplate().update(usuarioCajas);
        return true;
    }

    @Override
    public List<CivUsuarioCajas> getAll() throws Exception {
        String hql = "from CivUsuarioCajas";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<CivUsuarioCajas> listUsuarioCajasByUsuario(long usuario) throws Exception {
        String hql = "from CivUsuarioCajas where usu_id=:usuario";
        List list = getHibernateTemplate().findByNamedParam(hql, "usuario", usuario);
        return list;
    }
    
    @Override
    public List<CivUsuarioCajas> listUsuarioCajasByUsuarioFechaFin(long usuario) throws Exception {
        String hql = "from CivUsuarioCajas where usu_id=:usuario and usucaj_fecha_fin is null";
        List list = getHibernateTemplate().findByNamedParam(hql, "usuario", usuario);
        return list;
    }

    @Override
    public List<CivUsuarioCajas> listUsuarioCajasByCaja(long caja) throws Exception {
        String hql = "from CivUsuarioCajas where caj_id=:caja";
        List list = getHibernateTemplate().findByNamedParam(hql, "caja", caja);
        return list;
    }
    
    @Override
    public CivUsuarioCajas getCajaUsuario(long usuario) throws Exception {
        String hql = "from CivUsuarioCajas where usu_id=:usuario and USUCAJ_FECHA_FIN is null";
        List list = getHibernateTemplate().findByNamedParam(hql, new String[]{"usuario"}, new Object[]{BigDecimal.valueOf(usuario)});
        if (list.isEmpty()) {
            return null;
        }
        return (CivUsuarioCajas) list.get(0);
    }
    

}
