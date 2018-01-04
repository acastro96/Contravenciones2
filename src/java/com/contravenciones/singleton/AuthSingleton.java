/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.singleton;

import com.contravenciones.exception.AuthenticationException;
import com.contravenciones.exception.CivitransWebServiceException;
import com.contravenciones.model.AutenticacionUser;
import com.contravenciones.utility.DateUtility;
import com.contravenciones.utility.Log_Handler;
import static com.contravenciones.utility.Log_Handler.registrarEvento;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 *
 * Esta clase se encarga del manejo de tokens y proceso general de autenticación
 * con el servicio de RUNT
 *
 * @author Miguel Borja
 *
 */
public class AuthSingleton {

    private static final AuthSingleton INSTANCIA = new AuthSingleton();

    /**
     * Trae la instancia única de este objeto
     *
     * @return
     */
    public static AuthSingleton getInstancia() {
        return INSTANCIA;
    }

    private final HashMap token_usuarios;
    private final HashMap token_ciudadanos;

    private AuthSingleton() {
        token_usuarios = new HashMap();
        token_ciudadanos = new HashMap();
    }

    /**
     * Se elimina los datos de autenticación del ciudadano
     *
     * @param id_usuario ID del ciudadano registrado en el sistema
     */
    public void reestablecerCiudadano(int id_usuario) {
        token_ciudadanos.remove(id_usuario);
    }

    /**
     * Se elimina los datos de autenticación del funcionario
     *
     * @param id_usuario ID del funcionario registrado en el sistema.
     */
    public void reesstablecerFuncionario(int id_usuario) {
        token_usuarios.remove(id_usuario);
    }

    /**
     * Retorna el objeto AutenticacionUser usando el id del usuario
     *
     * @param id_usuario ID del funcionario registrado en el sistema.
     * @return
     */
    public AutenticacionUser getFuncionarioAuthObj(int id_usuario) {
        return (AutenticacionUser) token_usuarios.get(id_usuario);
    }

    /**
     * Retorna el objeto AutenticacionUser usando el id de la persona
     *
     * @param id_persona ID del ciudadano registrado en el sistema.
     * @return
     */
    public AutenticacionUser getCiudadanoAuthObj(int id_persona) {
        return (AutenticacionUser) token_ciudadanos.get(id_persona);
    }

    /**
     * Se valida si el usuario posee los datos de autenticación provenientes del
     * RUNT
     *
     * @param id_usuario ID del funcionario registrado en el sistema.
     * @return Si el usuario tiene datos de autenticación
     * @throws Exception Exception java
     */
    public boolean isAutenticado(int id_usuario) throws Exception {

        if (getFuncionarioAuthObj(id_usuario) == null) {
            return false;
        } else if (getFuncionarioAuthObj(id_usuario).getUltimo_acceso() == null
                || (DateUtility.getDateDiff(
                        getFuncionarioAuthObj(id_usuario).getUltimo_acceso(), new Date(), TimeUnit.MINUTES) > 40)) {
            return false;
        } else if (getFuncionariosAuthID(id_usuario) == null || getFuncionariosAuthID(id_usuario).isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Se valida si el ciudadano posee los datos de autenticación provenientes
     * del RUNT
     *
     * @param id_persona ID del ciudadano registrado en el sistema.
     * @return Si el ciudadano tiene datos de autenticación
     * @throws Exception Exception java
     */
    public boolean isAutenticadoCiudadano(int id_persona) throws Exception {
        if (getCiudadanoAuthObj(id_persona) == null) {
            return false;
        } else if (getCiudadanoAuthObj(id_persona).getUltimo_acceso() == null
                || (DateUtility.getDateDiff(
                        new Date(), getCiudadanoAuthObj(id_persona).getUltimo_acceso(), TimeUnit.MINUTES) > 30)) {
            return false;
        } else if (getCiudadanoAuthID(id_persona) == null || getCiudadanoAuthID(id_persona).isEmpty()) {
            return false;
        }
        return true;
    }


    /**
     * Establece el Token de un Ciudadano
     *
     * @param id_persona ID del ciudadano registrado en el sistema.
     * @param auth_id Token proveniente del sistema de autenticación RUNT
     * @throws Exception
     */
    public void setCiudadanoAuthID(int id_persona, String auth_id) throws Exception {
        AutenticacionUser aut = (AutenticacionUser) token_ciudadanos.get(id_persona);
        if (aut == null) {
            throw new AuthenticationException("No se ha encontrado la persona en el listado de tokens"); //Revisar
        }
        aut.setUserAuthID(auth_id);
        aut.setUltimo_acceso(new Date());
        token_ciudadanos.put(id_persona, aut);
    }

    /**
     *
     * @param id_persona
     * @param auth_id
     * @throws Exception
     */
    public void setCiudadanoAuthIDBiometrico(int id_persona, String auth_id) throws Exception {
        AutenticacionUser aut = (AutenticacionUser) token_ciudadanos.get(id_persona);
        if (aut == null) {
            throw new AuthenticationException("No se ha encontrado la persona en el listado de tokens"); //Revisar
        }
        aut.setUserAuthIDBiometrico(auth_id);
        aut.setUltimo_acceso(new Date());
        token_ciudadanos.put(id_persona, aut);
    }

    /**
     * Retorna el Token de un Ciudadano
     *
     * @param id_persona ID del ciudadano registrado en el sistema.
     * @return Token
     * @throws Exception
     */
    public String getCiudadanoAuthID(int id_persona) throws Exception {
        AutenticacionUser aut = (AutenticacionUser) token_ciudadanos.get(id_persona);
        if (aut != null) {
            return aut.getUserAuthID();
        } else {
            return "";
        }
    }

    /**
     *
     * @param id_persona
     * @return
     * @throws Exception
     */
    public String getCiudadanoAuthIDBiometrico(int id_persona) throws Exception {
        AutenticacionUser aut = (AutenticacionUser) token_ciudadanos.get(id_persona);
        if (aut != null) {
            return aut.getUserAuthIDBiometrico();
        } else {
            return "";
        }
    }

    /**
     * Establece el Token de un Funcionario
     *
     * @param id_usuario ID del funcionario registrado en el sistema.
     * @param auth_id Token proveniente del sistema de autenticación RUNT
     * @throws Exception
     */
    public void setFuncionariosAuthID(int id_usuario, String auth_id) throws Exception {
        AutenticacionUser aut = (AutenticacionUser) token_usuarios.get(id_usuario);
        if (aut == null) {
            throw new AuthenticationException("No se ha encontrado el usuario en el listado de tokens"); //Revisar
        }
        aut.setUserAuthID(auth_id);
        aut.setUltimo_acceso(new Date());
        token_usuarios.put(id_usuario, aut);
    }

    /**
     * Retorna el Token de un Funcionarios
     *
     * @param id_usuario ID del ciudadano registrado en el sistema.
     * @return Token
     * @throws Exception
     */
    public String getFuncionariosAuthID(int id_usuario) throws Exception {
        AutenticacionUser aut = (AutenticacionUser) token_usuarios.get(id_usuario);
        if (aut != null) {
            return aut.getUserAuthID();
        } else {
            return "";
        }
    }




}
