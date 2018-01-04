/*
 * To change this template, choose Tools | Templates
 * Copyright 2016.
 */
package com.contravenciones.tr.bo;

import com.contravenciones.exception.LoginException;
import com.contravenciones.jdbc.dao.DaoLogin;
import com.contravenciones.jdbc.dao.ITLogin;
import com.contravenciones.jdbc.dao.ITPersonas;
import com.contravenciones.jdbc.dao.ITUsuarios;
import com.contravenciones.jsf.bean.BeanLogin;
import com.contravenciones.model.Modulo;
import com.contravenciones.model.Recurso;
import com.contravenciones.tr.persistence.CivAttempts;
import com.contravenciones.tr.persistence.CivPersonas;
import com.contravenciones.tr.persistence.CivRecursos;
import com.contravenciones.tr.persistence.CivUsuarios;
import com.contravenciones.utility.DateUtility;
import com.contravenciones.utility.Log_Handler;
import com.contravenciones.utility.ValidacionPassword;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import com.contravenciones.jdbc.dao.ITAttempts;

/**
 *
 * @author Jefrey
 */
public class LoginImplBO implements LoginBO, Serializable {

    private static final long serialVersionUID = 545488145141L;
    private static final int TIEMPO_RESTABLECER_HORAS = 24;

    private ITLogin loginDAO;
    private ITAttempts attemptsDAO;
    private ITPersonas personasDAO;
    private ITUsuarios usuariosDAO;

    @Override
    public void iniciarSesion(BeanLogin obj) throws Exception {
        Date ini = new Date();
        ini.setTime(0);

        CivUsuarios login = new CivUsuarios();
        login.setUsuNombre(obj.getUser().trim().toUpperCase(Locale.ROOT));
        login.setUsuPassword(obj.getPassword());
        CivUsuarios usu = getLoginDAO().getUsuario(login.getUsuNombre()); //Esta variable se llena solamente cuando coincide el usuario. Esto es para el registro de intentos
        if (usu != null) {
            obj.setID_Usuario(usu.getUsuId() + "");
            this.registrarIntento(usu.getUsuId().intValue()); //Registro de intentos
        }
        login = getLoginDAO().validarLogin(login); //Carga de datos por medio de password 
        obj.setPassword("");
        if (login != null) {
            if (login.getUsuEstado().intValue() == 2) { //En caso de que el usuario no se encuentre activo 
                throw new LoginException("Este usuario ha sido deshabilitado. Por favor contáctese con el administrador del sistema.");
            }
            if (login.getUsuEstado().intValue() == 4) { //(Revalidación)En caso de que el usuario no se encuentre activo por bloqueo de intentos
                throw new LoginException("Este usuario ha sido bloqueado por superar el número máximo de intentos de usuario. Por favor contáctese con el administrador del sistema.");
            }
            reestablecerIntentosUsuario(login.getUsuId().intValue());
            Date fecha_pass = getUsuariosDAO().consultarFechaUltimoPassword(login.getUsuId().intValue());
            if (fecha_pass == null) {
                obj.setUserEstado(3);
                login.setUsuEstado(new BigDecimal(3));
                getUsuariosDAO().update(login);
                fecha_pass = new Date(); //Fecha para reestablecer automaticamente si no hay historial 
                Log_Handler.registrarEvento("El usuario ID:" + login.getUsuId().intValue() + " necesita restaurar su contraseña ya que no hay registro de una contraseña anterior.", null, Log_Handler.INFO, getClass(), login.getUsuId().intValue());
            }
            Long dias = DateUtility.getDateDiff(fecha_pass, new Date(), TimeUnit.DAYS); //Usando fecha proceso.
            dias++;
            if (dias > 45) {
                //Se cambia el estado del usuario a estado 3 (Restablecer credenciales)
                login.setUsuEstado(new BigDecimal(3));
                getUsuariosDAO().update(login);
                obj.setUserEstado(3);
                Log_Handler.registrarEvento("El usuario ID:" + login.getUsuId().intValue() + " necesita restaurar su contraseña por superar el límite de días para la restauración de contraseña.", null, Log_Handler.INFO, getClass(), login.getUsuId().intValue());
            }
            if (dias > 35) {
                //CARGA DE MENSAJE: SU CONTRASEÑA ESTÁ POR VENCER
                obj.setDias_vencimiento(46 - dias);
                obj.getNotificationMap().put(login.getUsuId().intValue(), "Su contraseña está por vencer");
            }
            //Se cargan datos básicos de usuario 
            obj.setID_Usuario(login.getUsuId() + "");
            obj.setSede(login.getCivSedes().getSedId().intValue());
            obj.setNombre(login.getUsuNombre());
            obj.setUserEstado(login.getUsuEstado().intValue());
            obj.setIdPersonaUsuario(login.getCivPersonas().getPerId().intValue());
        } else {
            throw new LoginException("Usuario y/o contraseña inválidos");
        }
    }

    //Registrar Intento
    /**
     * Registrar intento en base a un ID de usuario. No se puede usar gestión de
     * transacciones ya que este método debe guardar los cambios inmediatamente.
     *
     * @param usuario Cadena de texto con el nombre de Usuario
     * @throws Exception
     */
    private void registrarIntento(int usuario) throws Exception {
        CivAttempts aut = getAttemptsDAO().consultarIntentos(usuario);

        if (aut == null) {
            aut = new CivAttempts();
            CivUsuarios usu = new CivUsuarios();
            usu.setUsuId(BigDecimal.valueOf(usuario));
            aut.setCivUsuarios(usu);
            aut.setTtpUltimoIntento(new Date());
            aut.setTtpIntentos(1L);
            aut.setTtpId(BigDecimal.valueOf(
                    getAttemptsDAO().insert(aut)));
        } else {
            Long horas = DateUtility.getDateDiff(aut.getTtpUltimoIntento(), new Date(), TimeUnit.HOURS);
            CivUsuarios obj_usuario = getUsuariosDAO().consultarUsuarioBy(usuario);
            if (horas >= TIEMPO_RESTABLECER_HORAS) {
                aut.setTtpIntentos(0L);
                if (obj_usuario.getUsuEstado().intValue() == 4) {
                    obj_usuario.setUsuEstado(new BigDecimal(3)); //Por reestablecer
                    getUsuariosDAO().update(obj_usuario);
                    Log_Handler.registrarEvento("El usuario ID:" + usuario + " debe reestablecer su contraseña ya que hace mas de " + TIEMPO_RESTABLECER_HORAS + " horas se registró un bloqueo por intentos de inicio de sesión.", null, Log_Handler.WARN, getClass(), usuario);
                }
            } else if (aut.getTtpIntentos() >= 6) {
                obj_usuario.setUsuEstado(new BigDecimal(4)); //Bloqueado por intentos
                getUsuariosDAO().update(obj_usuario);
                aut.setTtpIntentos(aut.getTtpIntentos() + 1);
                aut.setTtpUltimoIntento(new Date());
                getAttemptsDAO().update(aut);
                Log_Handler.registrarEvento("El usuario ID:" + usuario + " ha sido bloqueado por superar el número de intentos de inicio de sesión.", null, Log_Handler.WARN, getClass(), usuario);
                throw new LoginException("Ha superado el máximo número de intentos de inicio de sesión. Contáctese con el administrador del sistema.");
            }
            aut.setTtpIntentos(aut.getTtpIntentos() + 1);
            aut.setTtpUltimoIntento(new Date());
            getAttemptsDAO().update(aut);
        }

    }

    /**
     * Reestablece el numero de intentos registrados con un ID de usuario
     * específico
     *
     * @param usuario Cadena de texto con el nombre del usuario
     * @throws Exception
     */
    public void reestablecerIntentosUsuario(int usuario) throws Exception {
        CivAttempts aut = getAttemptsDAO().consultarIntentos(usuario);
        if (aut == null) {
            throw new Exception("Usuario requerido no registrado en el historial de intentos");
        } else {
            aut.setTtpIntentos(Long.parseLong(0 + ""));
            getAttemptsDAO().update(aut);
        }
    }

    @Override
    public List<Modulo> listarModulos(BeanLogin obj) throws Exception {
        List<CivRecursos> listR = getLoginDAO().listarRecursos(Integer.parseInt(obj.getID_Usuario()));
        List<Modulo> listModulo = new LinkedList<>();
        Modulo mod;

        if (listR == null) {
            throw new LoginException("El usuario no tiene ningún perfil asignado. Por favor contáctese con el administrador del sistema.");
        }

        for (int x = 0; x < listR.size(); x++) {
            CivRecursos r = listR.get(x);
            if (listModulo.isEmpty()) {
                mod = new Modulo();
                mod.setCodigo(r.getCivModulos().getModId().intValue());
                mod.setIcon(r.getCivModulos().getIcon());
                mod.setNombre(r.getCivModulos().getModNombre());
                listModulo.add(mod);
            }
            boolean sw = true;
            for (int i = 0; i < listModulo.size(); i++) {
                Modulo m = listModulo.get(i);
                if (r.getCivModulos().getModId().intValue() == m.getCodigo()) {
                    sw = false;
                    break;
                }
            }
            if (sw) {
                mod = new Modulo();
                mod.setCodigo(r.getCivModulos().getModId().intValue());
                mod.setIcon(r.getCivModulos().getIcon());
                mod.setNombre(r.getCivModulos().getModNombre());
                listModulo.add(mod);
            }
        }

        for (int x = 0; x < listModulo.size(); x++) {
            Modulo m = listModulo.get(x);
            List<Recurso> listrec = new LinkedList<>();
            for (int i = 0; i < listR.size(); i++) {
                CivRecursos r = listR.get(i);
                if (r.getCivModulos().getModId().intValue() == m.getCodigo()) {
                    if(r.getRecTipo().intValue() == 1){
                        Recurso rec = new Recurso();
                        rec.setCodigo(r.getRecId().intValue());
                        rec.setNombre(r.getRecNombre());
                        rec.setCarpeta(r.getRecCarpeta());
                        rec.setDescripcion(r.getRecDescripcion());
                        listrec.add(rec);
                    }
                }
            }
            m.setListRecurso(listrec);
        }

        return listModulo;
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String generarContrasena() throws Exception {
        ValidacionPassword val = new ValidacionPassword();
        return val.generarPassword();
    }

    @Override
    public List<String> listarRecursos(BeanLogin obj) throws Exception {
        List<CivRecursos> listR = getLoginDAO().listarRecursos(Integer.parseInt(obj.getID_Usuario()));
        List<String> rec = new LinkedList<>();

        if (listR == null) {
            return null;
        }

        for (CivRecursos list : listR) {
            rec.add(list.getRecDescripcion());
        }

        return rec;
    }

    @Override
    public void consultarDatosUsuario(BeanLogin obj) throws Exception {

        CivPersonas persona = getPersonasDAO().consultarPersonasById(obj.getIdPersonaUsuario());
        if (persona == null) {
            throw new LoginException("No se encontró la persona correspondiente al usuario");
        }
        obj.setNombrePersonaUsuario(persona.getPerNombre1()
                + " "
                + (persona.getPerNombre2() == null
                ? "" : persona.getPerNombre2()) + " "
                + persona.getPerApellido1()
                + " "
                + (persona.getPerApellido2() == null
                ? "" : persona.getPerApellido2()));
        obj.setCedulaPersonaUsuario(persona.getPerDocumento());
        obj.setFechaInicioPersonaUsuario(persona.getPerFechainicial());
    }

    public ITPersonas getPersonasDAO() {
        return personasDAO;
    }

    public void setPersonasDAO(ITPersonas personasDAO) {
        this.personasDAO = personasDAO;
    }

    /**
     * @return the loginDAO
     */
    public ITLogin getLoginDAO() {
        return loginDAO;
    }

    /**
     * @param loginDAO the loginDAO to set
     */
    public void setLoginDAO(DaoLogin loginDAO) {
        this.loginDAO = loginDAO;
    }

    /**
     * @return the attemptsDAO
     */
    public ITAttempts getAttemptsDAO() {
        return attemptsDAO;
    }

    /**
     * @param attemptsDAO the attemptsDAO to set
     */
    public void setAttemptsDAO(ITAttempts attemptsDAO) {
        this.attemptsDAO = attemptsDAO;
    }

    /**
     * @return the usuariosDAO
     */
    public ITUsuarios getUsuariosDAO() {
        return usuariosDAO;
    }

    /**
     * @param usuariosDAO the usuariosDAO to set
     */
    public void setUsuariosDAO(ITUsuarios usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

}
