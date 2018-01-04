/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivUsuariosCajasTipopagos;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ing_jefreypadilla
 */
public class DaoUsuariosCajasTipoPagos extends HibernateDaoSupport implements ITUsuarioCajaTipoPago {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivUsuariosCajasTipopagos usuariosCajasTipopagos) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(usuariosCajasTipopagos).toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivUsuariosCajasTipopagos usuariosCajasTipopagos) throws Exception {
        getHibernateTemplate().update(usuariosCajasTipopagos);
        return true;
    }

    @Override
    public List<CivUsuariosCajasTipopagos> listTipoPagosByCajaUsuarioEst(long caja, long usuario, int estado) throws Exception {
        String hql = "from CivUsuariosCajasTipopagos where caj_id=:caja and usu_id=:usuario and ustip_estado=:estado";
        List list = getHibernateTemplate().findByNamedParam(hql, new String[]{"caja", "usuario", "estado"}, new Object[]{caja, usuario, estado});
        return list;
    }

    @Override
    public List<CivUsuariosCajasTipopagos> listTipoPagosByUsuario(long usuario) throws Exception {
        String hql = "from CivUsuariosCajasTipopagos where usu_id=:usuario";
        List list = getHibernateTemplate().findByNamedParam(hql, "usuario", usuario);
        return list;

    }
    
    @Override
    public List<CivUsuariosCajasTipopagos> listTipoPagosByUsuarioFechaFin(long usuario) throws Exception {
        String hql = "from CivUsuariosCajasTipopagos where usu_id=:usuario and fechafin is null";
        List list = getHibernateTemplate().findByNamedParam(hql, "usuario", usuario);
        if (list.isEmpty()) {
            return null;
        }
        return list;

    }
    
    @Override
    public CivUsuariosCajasTipopagos getTipoCajaUsuario(long usuario, long tipopago) throws Exception {
        String hql = "from CivUsuariosCajasTipopagos where usu_id=:usuario and ustip_tipo_pago=:tipopago and fechafin is null order by 1 asc";
        List list = getHibernateTemplate().findByNamedParam(hql, new String[]{"usuario", "tipopago"}, new Object[]{BigDecimal.valueOf(usuario), BigDecimal.valueOf(tipopago)});
        if (list.isEmpty()) {
            return null;
        }
        return (CivUsuariosCajasTipopagos) list.get(0);
    }

    @Override
    public CivUsuariosCajasTipopagos getTipoPagobyCaja(long caja, long usuario, long tipopago) throws Exception {
        String hql = "from CivUsuariosCajasTipopagos where caj_id=:caja and usu_id=:usuario and ustip_tipo_pago=:tipopago and fechafin is null";
        List list = getHibernateTemplate().findByNamedParam(hql, new String[]{"caja", "usuario", "tipopago"}, new Object[]{BigDecimal.valueOf(caja), BigDecimal.valueOf(usuario), BigDecimal.valueOf(tipopago)});
        if (list.isEmpty()) {
            return null;
        }
        return (CivUsuariosCajasTipopagos) list.get(0);
    }

}
