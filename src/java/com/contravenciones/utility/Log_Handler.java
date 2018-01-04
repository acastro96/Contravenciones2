package com.contravenciones.utility;

import com.contravenciones.model.LoginUser;
import com.contravenciones.singleton.SessionSingleton;
import com.contravenciones.tr.persistence.CivErrores;
import com.contravenciones.tr.persistence.LogEventos;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



/**
 *
 * @author Miguel Borja
 */
public class Log_Handler {

    private static final org.apache.log4j.Logger ROOT_LOG = org.apache.log4j.Logger.getRootLogger(); // Log4j

    private static SessionFactory SESSIONFACTORY;

    public static final int DEBUG = 1;

    public static final int ERROR = 2;

    public static final int TRACE = 4;

    public static final int FATAL = 6;

    public static final int WARN = 5;

    public static final int INFO = 3;

    /**
     * Registra los mensajes generados por diferentes componentes del sistema.
     *
     * @author Miguel Borja
     * @param mensaje Mensaje a registrar
     * @param error Objeto Throwable del mensaje (Disponible solamente en
     * niveles FATAL, ERROR).
     * @param nivel Nivel del mensaje (ver constantes)
     * @param clase La clase desde donde se genera el mensaje PE: getClass()
     * @param id_usuario
     */
    public static void registrarEvento(String mensaje, Throwable error, int nivel, Class clase, int id_usuario) {
        org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(clase); // Log4j
        try {
//            String clean = mensaje.replace("\n", "_").replace("\r", "_");
//            mensaje = ESAPI.encoder().encodeForHTML(mensaje);
//            if(!mensaje.endsWith(clean))
//                clean += mensaje;
//            
//            mensaje = new String(clean);
            
            Logger logger = Logger.getLogger(clase.getName());
            Throwable result = null;
            if (error != null) {
                Throwable cause = null;
                result = error;
                while (null != (cause = result.getCause()) && (result != cause)) {
                    result = cause;
                }
            }

            switch (nivel) {
                case DEBUG: //DEBUG
                    log.debug(mensaje);
                    System.out.println("[DEBUG] [" + clase.getName() + "] " + mensaje);
                    logger.config(mensaje);
                    break;
                case ERROR: //ERROR
                    log.error(mensaje + " Causado por: " + result.toString() + " Error Localizado:" + error.getLocalizedMessage());
                    error.printStackTrace();
                    System.out.println("[ERROR] [" + clase.getName() + "] " + mensaje + " Causado por: " + result.toString() + " Error Localizado:" + error.getLocalizedMessage());
                    logger.severe(mensaje);
                    break;
                case INFO: //INFO
                    log.info(mensaje);
                    System.out.println("[INFO] [" + clase.getName() + "] " + mensaje);
                    logger.info(mensaje);
                    break;
                case TRACE: //TRACE
                    log.trace(mensaje);
                    System.out.println("[TRACE] [" + clase.getName() + "] " + mensaje);
                    logger.fine(mensaje);
                    break;
                case WARN: //WARN
                    log.warn(mensaje);
                    System.out.println("[WARN] [" + clase.getName() + "] " + mensaje);
                    logger.warning(mensaje);
                    break;
                case FATAL: //FATAL
                    log.fatal(mensaje + " Causado por: " + result.toString() + "Error Localizado:" + error.getLocalizedMessage());
                    error.printStackTrace();
                    System.out.println("[FATAL] [" + clase.getName() + "] " + mensaje + " Causado por: " + result.toString() + "Error Localizado:" + error.getLocalizedMessage());
                    logger.severe(mensaje);
                    break;

            }
            LogEventos logEventos = new LogEventos();
            LoginUser login_user = SessionSingleton.getInstancia().getUsuarioSesion(id_usuario);
            if (login_user != null) {
                String ip = login_user.getIP();
                logEventos.setEvIp(ip);
            }
            if (error != null) {
                logEventos.setEvCausa((result != null) ? result.toString() : "");
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement element : error.getStackTrace()) {
                    sb.append(element.toString());
                    sb.append("\n");
                    if (sb.toString().length() >= 3500) {
                        break;
                    }
                }
                logEventos.setEvStacktrace(sb.toString());
                logEventos.setEvExcpetion(error.getClass().getCanonicalName());
                logEventos.setEvMensaje(mensaje + " - " + error.getMessage());
            } else {
                logEventos.setEvMensaje(mensaje);
            }
            logEventos.setUsuId(new BigDecimal(id_usuario));
            logEventos.setEvFecha(new Date());
            logEventos.setEvClaseOrigen(clase.getCanonicalName());
            logEventos.setEvNivel(new BigDecimal(nivel));

            insertarLog(logEventos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ingresa a la Base de Datos el registro del error.
     *
     * @param logEvento Pojo con los datos a ingresar
     * @return ID único del registro
     */
    private static long insertarLog(LogEventos logEvento) {
        if (SESSIONFACTORY == null) {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties());
            SESSIONFACTORY = configuration.buildSessionFactory(builder.build());
        }
        Session session = SESSIONFACTORY.openSession();
        long id = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            id = Long.parseLong(session.save(logEvento).toString());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.close();
            }
        }
        return id;
    }

    /**
     *
     * @param exx Excepcion generada
     * @return Texto para posible solución del error.
     */
    public static String solucionError(Throwable exx) {
        String excepcion = exx.getClass().getCanonicalName();
        if (SESSIONFACTORY == null) {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties());
            SESSIONFACTORY = configuration.buildSessionFactory(builder.build());
        }
        Session session = SESSIONFACTORY.openSession();
        long id = 0;
        Transaction tx = null;
        CivErrores error = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from CivErrores where ERR_EXCEPTION = :excepcion");
            query.setParameter("excepcion", excepcion);
            error = (CivErrores) query.list().get(0);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.close();
            }
        }
        if (error != null) {
            return error.getErrCausa() + " " + error.getErrSolucion();
        } else {
            return "Se ha registrado un error en el sistema. Por favor intente de nuevo o contacte al administrador del sistema.";
        }
    }

    private Log_Handler() {

    }

}
