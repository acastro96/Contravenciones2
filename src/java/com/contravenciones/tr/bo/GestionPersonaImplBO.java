/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.tr.bo;

import com.contravenciones.exception.PersonaException;
import com.contravenciones.jdbc.dao.ITPersonas;
import com.contravenciones.jsf.bean.BeanGestionPersona;
import com.contravenciones.tr.persistence.CivPersonas;
import java.util.ArrayList;



/**
 *
 * @author Miguel Borja
 */
public class GestionPersonaImplBO implements GestionPersonaBO {

    private ITPersonas personasDAO;
    
@Override
    public void listPersona(BeanGestionPersona bean) throws Exception {
        bean.setListPersonas(new ArrayList<>());
        
        for (CivPersonas per : getPersonasDAO().listarPersonas(bean.getBuscarPersona().toUpperCase())){
            bean.getListPersonas().add(per);
        }
        if (bean.getListPersonas().isEmpty()) {
            throw new PersonaException("No se encontraron coincidencias.", 2);
        }
        bean.setMostrarConsulta(true);
    }

    /**
     * @return the personasDAO
     */
    public ITPersonas getPersonasDAO() {
        return personasDAO;
    }

    /**
     * @param personasDAO the personasDAO to set
     */
    public void setPersonasDAO(ITPersonas personasDAO) {
        this.personasDAO = personasDAO;
    }
    
}
