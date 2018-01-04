/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.singleton;

import com.contravenciones.model.LoginUser;
import com.contravenciones.utility.Log_Handler;
import java.util.HashMap;

/**
 * Esta clase se encarga de manejar los datos de sesión de los usuarios.
 *
 * @author Miguel Borja
 */
public class SessionSingleton {

    private static final SessionSingleton INSTANCIA = new SessionSingleton();

    /**
     * Retorna la instancia única del objeto
     *
     * @return Objeto SessionSingleton
     */
    public static SessionSingleton getInstancia() {
        return INSTANCIA;
    }

    private final HashMap usuarios_logeados;

    private SessionSingleton() {
        usuarios_logeados = new HashMap();
    }

    /**
     * Este método invalida la sesion de un usuario.
     *
     * @param id_usuario
     * @throws Exception
     */
    public void invalidarSesion(int id_usuario) throws Exception {
        LoginUser usr = (LoginUser) this.usuarios_logeados.get(id_usuario);
        try {
            usr.invalidarSesion();
        } catch (IllegalStateException ex) {
            Log_Handler.registrarEvento("Error de Sesión: La sesión ya  se habia invalidado o no estaba en el estado esperado.", null, Log_Handler.WARN, getClass(), id_usuario);
        } finally {
            this.usuarios_logeados.put(id_usuario, null);
        }
    }

    /**
     * Registra en la variable local, la sesión de un usuario en el sistema.
     * (Ver Filtro de Sesión)
     *
     * @param id_usuario ID del usuario registrado en el sistema
     * @param obj Objeto con los datos de sesión del usuario
     */
    public void registrarSesionUsuario(int id_usuario, LoginUser obj) {
        obj.setId_usuario(id_usuario);
        this.usuarios_logeados.put(id_usuario, obj);
    }

    /**
     * Retorna el objeto LoginUser con los datos de sesión del usuario
     *
     * @param id_usuario ID del usuario registrado en el sistema
     * @return
     */
    public LoginUser getUsuarioSesion(int id_usuario) {
        LoginUser user = (LoginUser) this.usuarios_logeados.get(id_usuario);
        return user;
    }

}
