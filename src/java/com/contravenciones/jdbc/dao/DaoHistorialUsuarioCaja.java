/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivHistorialUsuarioCaja;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roymer Camacho
 */
public class DaoHistorialUsuarioCaja extends HibernateDaoSupport implements ITHistorialUsuarioCaja {

    @Override

    @Transactional(rollbackFor = Exception.class)
    public long insert(CivHistorialUsuarioCaja usuariocaja) throws Exception {
        return Long.parseLong(getHibernateTemplate().save(usuariocaja).toString());
    }

    @Override

    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivHistorialUsuarioCaja usuariocaja) throws Exception {
        getHibernateTemplate().update(usuariocaja);
        return true;
    }
 
   
    
  

}
