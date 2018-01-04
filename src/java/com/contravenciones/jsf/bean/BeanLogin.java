package com.contravenciones.jsf.bean;

import com.contravenciones.exception.LoginException;
import com.contravenciones.model.Modulo;
import com.contravenciones.singleton.AuthSingleton;
import com.contravenciones.tr.bo.LoginBO;
import com.contravenciones.utility.Log_Handler;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Miguel Borja
 */
public class BeanLogin implements Serializable {

    private static final long serialVersionUID = 752642333333L;
    private static final String REGEX = "^[a-zA-Z0-9/-@ ]*$";
    private static final String REGEXNUMERO = "^[0-9]*$";
    private static final String REGEXPASSWORD = "^[a-zA-Z0-9]*$";
//    private static final String REGEX_MAIL = "^[a-zA-Z0-9/-@. ]*$";
    private static final String REGEX_MAIL = "^$|^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
    private static final String REGEX_DIRECCION = "^[a-zA-Z0-9-@.#_ ]*$";
    private static final String VALIDATOR_MENSAJE = "inválido/a. Revise que el campo no tenga caracteres especiales como tildes, puntos o numerales.";

    

    private String id_usuario;
    private String user = "";
    private String password;
    private String nombre;
    private char tipoDocumentoRUNT;
    private List<BeanLogin> listaUsuarios;
    private List<Modulo> listModulos;
    private LoginBO loginBO;
    private int sede;
    private long dias_vencimiento = -1;
    private boolean aut_RUNT;
    private Date fecha_;
    private String root;
    private Map<Integer, String> notificationMap;
    private List<String> listRecursos;
    private int userEstado;
    private boolean hasAccess;

    private int idPersonaUsuario;
    private String nombrePersonaUsuario;
    private String cedulaPersonaUsuario;
    private Date fechaInicioPersonaUsuario;

    /**
     *
     * @return
     */
    public String autenticarUsuario() {
        try {
//            Log_Handler.registrarEvento("Tiempo fuera de lugar del reloj: " + NTPClient.retrasoReloj(), null, Log_Handler.INFO, getClass(),Integer.parseInt(loginBean.getID_Usuario()));
            setNotificationMap(new LinkedHashMap<>());
            getLoginBO().iniciarSesion(this); //Se cargan datos y ejecutan validaciones de usuario.
            setListModulos(getLoginBO().listarModulos(this)); //Se carga el menu correspondiente al usuario
            setListRecursos(getLoginBO().listarRecursos(this)); // Se cargan los recursos correspondientes al usuario
            AuthSingleton.getInstancia().reesstablecerFuncionario(Integer.parseInt(id_usuario)); //Se reestablecen credenciales de RUNT
            setNombre(getNombre().toUpperCase(Locale.ROOT));
            validarAcceso(); //Revisar Estado del Usuario
            getLoginBO().consultarDatosUsuario(this);

           // setRoot("/resources/dist/img/" + (getID_Usuario().equals("5") ? "avatar3.png" : "user2-160x160.jpg"));
           // setRoot("/resources/images/transito_avatar.png");

            return ejecutarDestino();
        } catch (LoginException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", e.getMessage()));
            return "";
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error iniciando sesion: ", e, Log_Handler.ERROR, getClass(), (getID_Usuario() != null) ? Integer.parseInt(getID_Usuario()) : 0);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            return "";
        }
    }

    private String ejecutarDestino() {
        if (getUserEstado() == 3) { //Usuario Por reestablecer credenciales
            return "/reestablecer.civ";
        }else
        {
            return "/inicio?faces-redirect=true"; //Redirect=true obligatorio para validaciones de filtro
        }
         
        
    }

    public void validarAcceso() {

        switch (getUserEstado()) {
            case 0: //Desactivada
            case 3: //Por reestablecer
            case 4: //Bloqueado
            case 2: //Deshabilitado
                this.hasAccess = false;
                break;
            case 1: //Normal
                this.hasAccess = true;
                break;
            default:
                this.hasAccess = false;
                break;
        }
    }

    public String getCedulaPersonaUsuario() {
        return cedulaPersonaUsuario;
    }

    /**
     * @param cedulaPersonaUsuario
     */
    public void setCedulaPersonaUsuario(String cedulaPersonaUsuario) {
        this.cedulaPersonaUsuario = cedulaPersonaUsuario;
    }

    public String getNombrePersonaUsuario() {
        return nombrePersonaUsuario;
    }

    /**
     * @param user the user to set
     */
    public void setNombrePersonaUsuario(String user) {
        this.nombrePersonaUsuario = user;
    }

    public Date getFechaInicioPersonaUsuario() {
        return fechaInicioPersonaUsuario;
    }

    public void setFechaInicioPersonaUsuario(Date fechaInicioPersonaUsuario) {
        this.fechaInicioPersonaUsuario = fechaInicioPersonaUsuario;
    }

    public int getIdPersonaUsuario() {
        return idPersonaUsuario;
    }

    /**
     * @param user the user to set
     */
    public void setIdPersonaUsuario(int user) {
        this.idPersonaUsuario = user;
    }

    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the listaUsuarios
     */
    public List<BeanLogin> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<BeanLogin> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * @return the loginBO
     */
    public LoginBO getLoginBO() {
        return loginBO;
    }

    /**
     * @param loginBO the loginBO to set
     */
    public void setLoginBO(LoginBO loginBO) {
        this.loginBO = loginBO;
    }

    /**
     * @return the listModulos
     */
    public List<Modulo> getListModulos() {
        return listModulos;
    }

    /**
     * @param listModulos the listModulos to set
     */
    public void setListModulos(List<Modulo> listModulos) {
        this.listModulos = listModulos;
    }

    /**
     * @return the id_usuario
     */
    public String getID_Usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario
     */
    public void setID_Usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the sede
     */
    public int getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(int sede) {
        this.sede = sede;
    }

    /**
     * @return the TipoDocumentoRUNT
     */
    public char getTipo_documento_runt() {
        return tipoDocumentoRUNT;
    }

    /**
     * @param tipo_documento_runt the TipoDocumentoRUNT to set
     */
    public void setTipo_documento_runt(char tipo_documento_runt) {
        this.tipoDocumentoRUNT = tipo_documento_runt;
    }

    /**
     * @return the aut_RUNT
     */
    public boolean isAut_RUNT() {
        return aut_RUNT;
    }

    /**
     * @param aut_RUNT the aut_RUNT to set
     */
    public void setAut_RUNT(boolean aut_RUNT) {
        this.aut_RUNT = aut_RUNT;
    }

    /**
     *
     * @return
     */
    public String getRegex() {
        return REGEX;
    }

    /**
     * @return the fecha_
     */
    public Date getFecha_() {
        return fecha_;
    }

    /**
     * @param fecha_ the fecha_ to set
     */
    public void setFecha_(Date fecha_) {
        this.fecha_ = fecha_;
    }

    /**
     * @return the dias_vencimiento
     */
    public long getDias_vencimiento() {
        return dias_vencimiento;
    }

    /**
     * @param dias_vencimiento the dias_vencimiento to set
     */
    public void setDias_vencimiento(long dias_vencimiento) {
        this.dias_vencimiento = dias_vencimiento;
    }

    /**
     * @return the root
     */
    public String getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(String root) {
        this.root = root;
    }

    /**
     * @return the notificationMap
     */
    public Map<Integer, String> getNotificationMap() {
        return notificationMap;
    }

    /**
     * @param notificationMap the notificationMap to set
     */
    public void setNotificationMap(Map<Integer, String> notificationMap) {
        this.notificationMap = notificationMap;
    }

    /**
     * @return the listRecursos
     */
    public List<String> getListRecursos() {
        return listRecursos;
    }

    /**
     * @param listRecursos the listRecursos to set
     */
    public void setListRecursos(List<String> listRecursos) {
        this.listRecursos = listRecursos;
    }

    /**
     * @return the VALIDATOR_MENSAJE
     */
    public String getVALIDATOR_MENSAJE() {
        return VALIDATOR_MENSAJE;
    }

    public String getREGEXDIRECCION() {
        return REGEX_DIRECCION;
    }

    /**
     * @return the REGEXPASSWORD
     */
    public String getREGEXPASSWORD() {
        return REGEXPASSWORD;
    }

    /**
     * @return the userEstado
     */
    public int getUserEstado() {
        return userEstado;
    }

    /**
     * @param userEstado the userEstado to set
     */
    public void setUserEstado(int userEstado) {
        this.userEstado = userEstado;
    }

    /**
     * @return the hasAccess
     */
    public boolean isHasAccess() {
        return hasAccess;
    }

    /**
     * @param hasAccess the hasAccess to set
     */
    public void setHasAccess(boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    /**
     * @return the REGEX_MAIL
     */
    public String getREGEX_MAIL() {
        return REGEX_MAIL;
    }
    
    /**
     * @return the REGEXNUMERO
     */
    public String getREGEXNUMERO() {
        return REGEXNUMERO;
    }

}
