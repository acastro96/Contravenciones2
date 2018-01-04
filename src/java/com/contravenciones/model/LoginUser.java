/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.model;

import com.contravenciones.jsf.bean.BeanLogin;
import com.contravenciones.singleton.AuthSingleton;
import java.io.Serializable;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 * Esta clase maneja los datos de sesión de los usuarios en el sistema.
 *
 * @author Miguel Borja
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 752642345145875L;

    private int id_usuario;
    private HttpSession session;
    private String IP;
    private Date ultimo_acceso;

    /**
     * Invalida la sesión y elimina los datos de autenticación RUNT
     *
     * @throws java.lang.Exception
     */
    public void invalidarSesion() throws Exception {
        BeanLogin userInSession = (BeanLogin) getSession().getAttribute("loginBean");
        AuthSingleton.getInstancia().reesstablecerFuncionario(Integer.parseInt(userInSession.getID_Usuario()));
        this.setIP("");
        this.setUltimo_acceso(null);
        this.getSession().invalidate();
        this.setSession(null);
        //this.intentos = 0;
    }

    /**
     * @return the session
     */
    public HttpSession getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(HttpSession session) {
        this.session = session;
    }

    /**
     * @return the IP
     */
    public String getIP() {
        return IP;
    }

    /**
     * @param IP the IP to set
     */
    public void setIP(String IP) {
        this.IP = IP;
    }

    /**
     * @return the ultimo_acceso
     */
    public Date getUltimo_acceso() {
        return ultimo_acceso;
    }

    /**
     * @param ultimo_acceso the ultimo_acceso to set
     */
    public void setUltimo_acceso(Date ultimo_acceso) {
        this.ultimo_acceso = ultimo_acceso;
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

}
