/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jsf.bean;

import com.contravenciones.exception.PersonaException;
import com.contravenciones.tr.bo.GestionPersonaBO;
import com.contravenciones.tr.persistence.CivPersonas;
import com.contravenciones.utility.Log_Handler;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Roymer Camacho
 */
public class BeanGestionPersona implements Serializable {
    
    private BeanLogin loginBean;
    private GestionPersonaBO gestionPersonaBO;
    
    private List<CivPersonas> listPersonas;
    private String buscarPersona;
    private boolean mostrarConsulta=false;
    
     /*Método para consultar todos las personas registradas en la base de datos.*/
    public void listarPersona() {
        impListarPersona();
        //RequestContext.getCurrentInstance().execute("reload()"); // Función para mantener la paginación de la tabla donde se listan los usuarios registrados en la base de datos.
    }

    protected void impListarPersona() {
        try {
            getGestionPersonaBO().listPersona(this);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPersona:messageGeneral");
        } catch (PersonaException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getNivelFacesMessage(), null, e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPersona:messageGeneral");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al listar personas : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPersona:messageGeneral");
        }

    }
    
    /**
     * @return the listPersonas
     */
    public List<CivPersonas> getListPersonas() {
        return listPersonas;
    }

    /**
     * @param listPersonas the listPersonas to set
     */
    public void setListPersonas(List<CivPersonas> listPersonas) {
        this.listPersonas = listPersonas;
    }

    /**
     * @return the loginBean
     */
    public BeanLogin getLoginBean() {
        return loginBean;
    }

    /**
     * @param loginBean the loginBean to set
     */
    public void setLoginBean(BeanLogin loginBean) {
        this.loginBean = loginBean;
    }

    /**
     * @return the gestionPersonaBO
     */
    public GestionPersonaBO getGestionPersonaBO() {
        return gestionPersonaBO;
    }

    /**
     * @param gestionPersonaBO the gestionPersonaBO to set
     */
    public void setGestionPersonaBO(GestionPersonaBO gestionPersonaBO) {
        this.gestionPersonaBO = gestionPersonaBO;
    }

    /**
     * @return the buscarPersona
     */
    public String getBuscarPersona() {
        return buscarPersona;
    }

    /**
     * @param buscarPersona the buscarPersona to set
     */
    public void setBuscarPersona(String buscarPersona) {
        this.buscarPersona = buscarPersona;
    }

    /**
     * @return the mostrarConsulta
     */
    public boolean isMostrarConsulta() {
        return mostrarConsulta;
    }

    /**
     * @param mostrarConsulta the mostrarConsulta to set
     */
    public void setMostrarConsulta(boolean mostrarConsulta) {
        this.mostrarConsulta = mostrarConsulta;
    }
    
    

}
