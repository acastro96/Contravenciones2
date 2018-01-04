package com.contravenciones.jdbc.dao;

import com.contravenciones.crypto.DigestHandler;
import com.contravenciones.tr.persistence.CivRecursos;
import com.contravenciones.tr.persistence.CivUsuarios;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 *
 * @author Miguel Borja
 */
public class DaoLogin extends HibernateDaoSupport implements ITLogin, Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public CivUsuarios validarLogin(CivUsuarios obj) throws Exception {
        List list = getHibernateTemplate().findByNamedParam("select us from CivUsuarios us where usu_nombre = :usuario and usu_password = :passwd",
                new String[]{"usuario", "passwd"},
                new Object[]{obj.getUsuNombre(),
                    DigestHandler.encryptSHA2(obj.getUsuPassword())});
        if (list.size() > 0) {
            return (CivUsuarios) list.get(0);
        }
        return null;
    }

    /**
     *
     * @param usu_id
     * @return
     */
    @Override
    public List<CivRecursos> listarRecursos(int usu_id) throws Exception {

        Session session = getHibernateTemplate().getSessionFactory().openSession();

//        String sql = "Select r.* from CIV_RECURSOS r\n"
//                + "inner join CIV_PERFILRECURSO pr\n"
//                + "on pr.rec_id = r.rec_id\n"
//                + "inner join CIV_USUPERFIL up\n"
//                + "on up.PERF_ID = pr.PERF_ID and up.USU_ID =:usu_id";
        String sql = "SELECT *\n" +
"FROM CIV_PERFILRECURSO PR INNER JOIN CIV_RECURSOS R ON R.REC_ID=PR.REC_ID\n" +
"INNER JOIN CIV_MODULOS M ON M.MOD_ID=R.MOD_ID\n" +
"WHERE PR.USU_ID=:usu_id AND PERREC_FECHAFIN IS NULL AND R.REC_FECHAFIN IS NULL";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivRecursos.class);
        query.setInteger("usu_id", usu_id);

        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    /**
     *
     * @param nombre_usu
     * @return
     * @throws Exception
     */
    @Override
    public CivUsuarios getUsuario(String nombre_usu) throws Exception {
        List list = getHibernateTemplate().findByNamedParam("from CivUsuarios where usu_nombre = :nombre AND USU_FECHAFINAL is null", "nombre", nombre_usu);
        if (list.size() > 0) {
            return (CivUsuarios) list.get(0);
        }
        return null;
    }
}
