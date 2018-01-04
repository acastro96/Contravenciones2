/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivParametros;
import com.contravenciones.tr.persistence.CivSedes;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Borja
 */
public class DaoParametros extends HibernateDaoSupport implements ITParametros, Serializable {

    private static final long serialVersionUID = 752642223562244446L;

    @Override
    public List<CivParametros> listAllParametros() throws Exception {

        String hql = "FROM CivParametros where PAR_FECHAFINAL is null";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<CivParametros> listParametros(long grupo) throws Exception {

        String hql = "FROM CivParametros WHERE GRU_ID = :grupo order by par_codigo,PAR_NOMBRE";
        List list = getHibernateTemplate().findByNamedParam(hql, "grupo", grupo);
        return list;
    }

    @Override

    public CivParametros getParametro(long id_parametro) throws Exception {

        String hql = "FROM CivParametros WHERE PAR_ID = :id_parametro";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_parametro", id_parametro);
        if (list.size() > 0) {
            return (CivParametros) list.get(0);
        }
        return null;
    }

    @Override

    @Transactional(rollbackFor = Exception.class)
    public long insert(CivParametros parametro) throws Exception {
        long id = Long.parseLong(getHibernateTemplate().save(parametro).toString());
        return id;
    }

    @Override

    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivParametros parametro) throws Exception {
        getHibernateTemplate().merge(parametro);
        return true;
    }

    @Override

    public List<CivSedes> getSedes() {

        String hql = "FROM CivSedes";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public CivParametros getParametroByCodeGrupo(int grupo, int codigo) throws Exception {
        String hql = "from CivParametros where gru_id=:grupo and par_codigo=:codigo";
        List list = getHibernateTemplate().findByNamedParam(hql, new String[]{"grupo", "codigo"}, new Object[]{grupo, codigo});
        if (list.size() > 0) {
            return (CivParametros) list.get(0);
        }
        return null;
    }

    @Override
    public List<CivParametros> listParametros(long grupo, int subGrupo) throws Exception {
        String hql = "FROM CivParametros WHERE GRU_ID = :grupo AND PAR_SUBCODIGO = :subGrupo order by par_codigo,PAR_NOMBRE asc";
        List list = getHibernateTemplate().findByNamedParam(hql, new String[]{"grupo", "subGrupo"}, new Object[]{grupo, subGrupo});
        return list;
    }

    @Override
    public CivParametros getParametroByGrupoNombreCorto(long grupo, String nombreCorto) throws Exception {
        String hql = "from CivParametros where gru_id=:grupo and par_nombrecorto=:nombreCorto ";
        List list = getHibernateTemplate().findByNamedParam(hql, new String[]{"grupo", "nombreCorto"}, new Object[]{grupo, nombreCorto});
        if (list.size() > 0) {
            return (CivParametros) list.get(0);
        }
        return null;
    }

}
