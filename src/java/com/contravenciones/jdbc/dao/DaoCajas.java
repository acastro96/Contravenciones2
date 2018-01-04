package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivCajas;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Borja
 */
public class DaoCajas extends HibernateDaoSupport implements ITCajas {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivCajas caja) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(caja).toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivCajas caja) throws Exception {
        getHibernateTemplate().merge(caja);
        return true;
    }

    @Override
    public CivCajas getCajaById(long id_caja) throws Exception {

        String hql = "from CivCajas where caj_id= :id_caja";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_caja", id_caja);
        if (list.size() > 0) {
            return (CivCajas) list.get(0);
        }
        return null;
    }

    @Override
    public List<CivCajas> listCajasByEstado(int estado) throws Exception {

        String hql = "from CivCajas where caj_estado=:estado";
        List list = getHibernateTemplate().findByNamedParam(hql, "estado", estado);
        return list;
    }
}
