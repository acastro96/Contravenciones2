/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivPersonas;
import com.contravenciones.tr.persistence.CivUspHistoria;
import com.contravenciones.tr.persistence.CivUsuarios;
import java.util.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JefreySistemas
 */
public class DaoUsuarios extends HibernateDaoSupport implements ITUsuarios {

//    @Override
//    public CivUsuarios consultarUsuario(String usuario, String password) throws Exception {
//
//        String hql = "from CivUsuarios where usu_nombre =? and usu_password =?";
//        List list = getHibernateTemplate().findByNamedParam(hql, usuario, password);
//        if (list.size() > 0) {
//            return (CivUsuarios) list.get(0);
//        }
//        return null;
//    }
    @Override

    public List<CivUsuarios> getAll() throws Exception {
        String hql = "from CivUsuarios order by usu_nombre asc";
        List list = getHibernateTemplate().find(hql);
        return list;
    }

    @Override

    public CivUsuarios consultarUsuarioBy(int id_usuario) throws Exception {

        String hql = "from CivUsuarios where usu_id =:id_usuario";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_usuario", id_usuario);
        if (list.size() >= 0) {
            return (CivUsuarios) list.get(0);
        }
        return null;
    }

    @Override
    public CivUsuarios consultarIdPer(int id) throws Exception {
        String hql = "from CivUsuarios where per_id =:id and usu_fechafinal is null";
        List list = getHibernateTemplate().findByNamedParam(hql, "id", id);
        if (list.size() > 0) {
            return (CivUsuarios) list.get(0);
        }
        return null;
    }

    @Override

    public CivUsuarios consultarUsuarioByNombre(String nombre_usuario) throws Exception {

        String hql = "from CivUsuarios where (usu_nombre=:nombre_usuario)";
        List list = getHibernateTemplate().findByNamedParam(hql, "nombre_usuario", nombre_usuario);
        if (list.size() > 0) {
            return (CivUsuarios) list.get(0);
        }
        return null;
    }

    @Override

    public List<CivUsuarios> listarUsuarios(String nombre_usuario) throws Exception {

        String hql = "from CivUsuarios where usu_nombre like :nombre_usuario ORDER BY 1 asc";
        List list = getHibernateTemplate().findByNamedParam(hql, "nombre_usuario", "%" + nombre_usuario + "%");
        return list;
    }

    /**
     *
     * @param id_usuario
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<String> consultar_HPAS(int id_usuario) throws Exception {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        String sql = "select P_DATA from CIV_USP_HISTORIA where USU_ID =:usu_id";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger("usu_id", id_usuario);

        if (query.list().size() > 0) {
            if (query.list() instanceof java.util.ArrayList) {
                return (ArrayList<String>) query.list();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<CivUspHistoria> consultarEstado_HPAS(int id_usuario) throws Exception {
        String hql = "from CivUspHistoria where USU_ID =:id_usuario";
        List list = getHibernateTemplate().findByNamedParam(hql, "id_usuario", id_usuario);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public Date consultarFechaUltimoPassword(int id_usuario) throws Exception {
        Date fecha = null;
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        String sql = "select FECHA_PROCESO from CIV_USP_HISTORIA where USU_ID =:usu_id and ESTADO=1 order by FECHA_PROCESO DESC";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger("usu_id", id_usuario);

        if (query.list().size() > 0) {
            fecha = (Date) query.list().get(0);
        }
        if (session.isOpen()) {
            session.flush();
            session.close();
        }
        if (fecha != null) {
            return fecha;
        } else {
            return null;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivUsuarios civUsuario) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(civUsuario).toString());
    }

    /**
     *
     * @param uspHistoria
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insertHisPass(CivUspHistoria uspHistoria) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(uspHistoria).toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateHisPass(CivUspHistoria uspHistoria) throws Exception {
        getHibernateTemplate().update(uspHistoria);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivUsuarios civUsuario) throws Exception {
        getHibernateTemplate().update(civUsuario);
        return true;
    }

}
