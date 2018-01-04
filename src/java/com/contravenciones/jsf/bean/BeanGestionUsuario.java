/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jsf.bean;

import com.contravenciones.exception.PasswordException;
import com.contravenciones.exception.UsuariosException;
import com.contravenciones.tr.bo.GestionUsuarioBO;
import com.contravenciones.tr.persistence.CivDetalleRecUsu;
import com.contravenciones.tr.persistence.CivDetalleRecursos;
import com.contravenciones.tr.persistence.CivPerfilrecurso;
import com.contravenciones.utility.Log_Handler;
import java.io.Serializable;
import static java.lang.Thread.currentThread;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Roymer Camacho
 */
public class BeanGestionUsuario implements Serializable {

    private BeanLogin loginBean;
    private GestionUsuarioBO gestionUsuarioBO;
    private String documento; //documento de la persona al crear usuario.
    private int tipoDocumento; // tipo de documento de la persona al crear usuario.
    private String nombreUsuario; //búsqueda por nombre de usuario.
    private String nombrePersona; //nombre de la persona a quien se le va a crear el usuario.
    private String password; //contraseña del usuario.
    private String confirmarPassword;
    private Date fechainicio; // Fecha de inicio en la cual se vinculó el usuario.
    private Date fechaFin; // Fecha final en la cual se desactivó el usuario.
    private String estado; // nombre del estado del usuario.
    private int codeSede; // código de la sede a la cual pertenece el usuario.
    private int codeDetalleRecurso; // código del detalle recurso.
    private int codeRecursoDetalle; // código del detalle recurso listado por id del recurso.
    private int codeCaja;  //Código de la caja
    private int codeEstado; //Código del estado del usuario 
    private int codePerfil; // código del perfil del usuaroio.
    private int codeRecurso; // codigo de los recursos.
    private int codeUsuario; // codigo del usuario.
    private int codeTipoPago; // codigo de los tipos de pago.
    private int codeModulo; // id de la tabla civ_recursos.
    private int codeModuloCiv; //id de la tabla civ modulos.
    private int codeDetalleRecUsu; // id del detalle rec usu
    private int sw = 0; // Variable para indicar si el usuario hizo alguna selección en detalle recurso, de esto depende si se ejecuta la modal de confirmación
    private int codePerfilSeleccionado; //Codigo del perfil que se seleccionó al actualizar un usuario.
    private boolean checkValue = false; // True o false al seleccionar recursos.
    private boolean FirstcheckValue = false; // Check principal en la sección de recursos, permite seleccionar todos los check secundarios que tiene ese módulo.
    private boolean btnperfilar = true; //Deshabilita o habilita el boton perfilar en la modal crear usuario.
    private boolean btnCrearUsuario = false; //Deshabilita o habilta el boton crear usuario en la modal crear usuario.
    private boolean checkValueTipoPago; //True o False al seleccionar tipos de pagos a asignar.
    private boolean checkValueCaja; // True o False al seleccionar cajas a asignar.
    private boolean checkDetalleRecurso = true; // True O False al seleccionar detalles recursos.
    private boolean checkDetalleRecursoAct = false; // True O False al seleccionar detalles recursos.
    private boolean doc = false; // Al crear un usuario, deshabilita todos los input y select de la modal.
    private boolean tipdoc = false; // Al crear un usuario, deshabilita todos los input y select de la modal.
    private boolean detalleRecurso = false; // Indica que recurso se le puede seleccionar el detalle recurso.
    private boolean mensajeTP = false; // si se desea deshabilitar los tipos de pagos de un usuario, esta variable indica como debe ser el proceso.
    private boolean mensajeC = false;// si se desea deshabilitar las cajas de un usuario, esta variable indica como debe ser el proceso.
    private boolean disableEstado = false; //Si el estado del usuario es 3 deshabilita la opcion de cambiarle de estado
    private String titleEstado = "Estado del usuario";
    private String colorAccordion = "text-black"; // si selecciona un recurso, cambia el color del módulo, para indicar que se ha seleccionado un recurso en ese módulo.
    private String nombreRecurso; // nombre del recurso.
    private String nombrePerfil; // nombre de los perfiles.
    private String nombreTipoPago; // nombre de los tipos de pago.
    private String nombreCaja; // nombre de las cajas.
    private String nombreModulo; // nombre de los módulos.
    private String nombrePerfilUsuario; //Guarda el perfil que tiene el usuario actualmente
    private String nombreUsuarioPerfilar; // Usuario generado aleatoriamente
    private String nombreDetalleRecurso; // nombre de los detalles recursos
    private String tipoDetalleRecurso; // tipo de detalle recurso
    private String usuarioRunt; // Si es usuario runt o no
    private String passwordAleatorio; // contraseña generada aleatoriamente en reestablecer contraseña
    String valorModal; //almacena el id de la modales
    private boolean estadoMensajeMetodoPago = false; //Mensaje de debe seleccionar metodo de pago y caja
    private boolean estadoMensajeCaja = false; // Mensaje de debe seleccionar caja
    private boolean estadoMensajePerfil = true; // Mensaje de debe seleccionar recursos
    private boolean estadoRenderActualizar = false; //Modal actualizar usuario -- selecciona el check principal si todos los check secundario se encuentran seleccionados.
    private boolean estadoRenderActualizarCheck = false; //Variable usada en la modal actualizar usuario para mostrar tipos de pago del usuario.
    private boolean estadoRenderActualizarCheckCaja = false; //Variable usada en la modal actualizar usuario para mostrar cajas del usuario.
    private boolean estadoMensajeSeleccionPago = true; // Activa o desactiva el mensaje de que debe seleccionar por lo menos un tipo de pago y una caja.
    private boolean btnAsignarMetodoPago = true; // Activa o desactiva el botón de asignar en la modal metodo de pago y caja
    private boolean btnAsignarPerfil = true; // Activa o desactiva el botón asignar en la modal perfiles
    private boolean btnAsignarDetalleRecursos = false; // Activa o desactiva el botón de asignar en la modal detalle recursos
    private boolean edcheckcaja = false; // Solo permite que se seleccione una caja
    private boolean estadoMensajeDetalle = false; // Mensaje de alerta en detalle recursos -- Modal Actualizar --
    private boolean estadoDR = false; // Sí tiene detalle recursos sirve para mostrarlos --Modal Actualizar --
    boolean confirmacion = false;
    private List<BeanGestionUsuario> listaRecursosPerfilUsuario; // Lista los recursos asignados a un usuario.
    private List<BeanGestionUsuario> listaRecursosPerfil; // Lista todos los recursos registrados en la base de datos.
    private List<BeanGestionUsuario> listaModulos; // Lista todos los módulos registrados en la base de datos.
    private List<BeanGestionUsuario> lista; //Se usa esta lista para mostrar los perfiles asignados a cada usuario
    private List<BeanGestionUsuario> listUsuario; // Lista los usuarios registrados en la base de datos.
    private List<BeanGestionUsuario> listPerfil; // Lista los perfiles registrados en la base de datos.
    private List<BeanGestionUsuario> listMetodoPago; // Lista los tipos de pagos registrados en la base de datos.
    private List<BeanGestionUsuario> listCajas; // Lista las cajas registradas en la base de datos.
    private List<CivDetalleRecursos> listDetalleRecursos; // Lista los detalle recursos registrados en la base de datos.
    private List<BeanGestionUsuario> listDetalleRecursosbyRec; // almacena los detalle recursos de acuerdo al id del recurso
    private List<BeanGestionUsuario> listDetalleRecursosSeleccionado = new ArrayList<>(); //Lista que almacena los detalles recursos que se le asignaran a un usuario.
    private List<BeanGestionUsuario> listRecursosSeleccionado; //Lista para cargar el select de recursos asignados -- Modal Actualizar Usuario --
    private List<Integer> listRecursosbyModulo; // Lista los recursos por id de módulo.
    private List<CivDetalleRecUsu> detalleRbyRecurso; // 
    private Map<Integer, String> detalleRbyRecurso1; //Lista solo los recursos que contienen detalles recursos por usuario

    @PostConstruct
    public void llenarListPerfiles() {
        impModulos();
    }

    /*Imprime lista de recursos registradas en la base de datos.*/
    public void impListRecursosPerfil() {
        impRecursosPerfil();
    }

    //Método para validar los detalles recursos -- Modal Actualizar --
    public void impListDetalleRecursosbyid(int id_rec, int mod, String modal) {
        int cont = 0; //variable incremental
        if (getDetalleRbyRecurso1() != null) { //Map que contiene los detalles recursos por módulos de un usuario.
            for (Map.Entry<Integer, String> entry : detalleRbyRecurso1.entrySet()) {
                if (entry.getKey() == id_rec) {
                    cont++; //al incrementar esta variable indicará que el usuario ya tiene los detalles recursos
                }
            }
        }

        if (cont == 0) { //En caso de que no existan los detalles recursos...
            valorModal = modal; // Se realiza la asignación a esta variable global.
            modalDetalleRecurso(); //Se llama el método que ejecuta la modal de detalles recursos para asignar.
            impDetalleRecursosbyid(id_rec); // Consulta detalle recursos por codigo del recurso.
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "El usuario ya contiene este detalle recurso, si desea actualizar, desplácese hacia Detalle Recursos"));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:alertaDetalleRecurso" + mod);

        }

    }

    //Método que ejecuta los detalle recursos por recurso asignado a un usuario
    public void detalleRecurso(int id_rec) {
        impDetalleRecursosbyid(id_rec);
    }

    /*Llamado de modales*/
    public void modal() {
        RequestContext.getCurrentInstance().execute("$('#crearUsuario').modal('hide'); $('#perfilesEditar').modal('toggle')");
        impListRecursosPerfil();

    }

    public void modalDetalleRecurso() {
        RequestContext.getCurrentInstance().execute("$('#detalleRecursos').modal('toggle'); $('#" + valorModal + "').modal('toggle')");
    }

    public void modalPagoCaja() {
        RequestContext.getCurrentInstance().execute("$('#cajaypago').modal('toggle'); $('#" + valorModal + "').modal('toggle')");
    }

    public void modalCaja() {
        RequestContext.getCurrentInstance().execute("$('#seleccionC').modal('toggle'); $('#" + valorModal + "').modal('toggle')");
    }

    public void modalConfirmacionNO() {
        RequestContext.getCurrentInstance().execute("$('#modalConfirmacion').modal('toggle'); $('#" + valorModal + "').modal('toggle')");
    }

    public void modalPersona() {
        RequestContext.getCurrentInstance().execute("$('#crearUsuario').modal('toggle'); $('#dg_persona').modal('toggle')");
    }

    /*-----------------------------------------------------------------------------------------*/

 /*Al asignar detalle recursos a un usuario, este método devuelve a la modal anterior correspondiente*/
    public void btnAsignarDetalleR() {
        AsignarDRecursos();
        modalDetalleRecurso();
        valorModal = "";
    }

    /*Método para juntar todos los detalles recursos en una sola lista para guardar los cambios en la base de datos*/
    public void AsignarDRecursos() {
        setSw(getSw() + 1);
        int cont = 0;
        for (BeanGestionUsuario dr : getListDetalleRecursosbyRec()) {
            for (BeanGestionUsuario ds : getListDetalleRecursosSeleccionado()) {
                if (dr.codeDetalleRecurso == ds.codeDetalleRecurso) {
                    ds.setCheckDetalleRecurso(checkDetalleRecurso);
                    cont++;
                }
            }
            if (cont == 0) {
                getListDetalleRecursosSeleccionado().add(dr);
            }

        }
    }

    /*Actualizar detalle recursos*/
    public void actDetalleRecursos(BeanGestionUsuario bean) {
        setSw(getSw() + 1);
        if (bean.checkDetalleRecursoAct) {
            for (CivDetalleRecursos listDetalle : getListDetalleRecursos()) {
                if (listDetalle.getDetRecId().intValue() == bean.codeDetalleRecurso) {
                    listDetalle.setDetRecObjeto("1");
                }
            }
        } else {
            for (CivDetalleRecursos listDetalle : getListDetalleRecursos()) {
                if (listDetalle.getDetRecId().intValue() == bean.codeDetalleRecurso) {
                    listDetalle.setDetRecObjeto("2");
                }
            }
        }

    }

    /*Método que asigna todos los detalle recursos de todos los recursos a un usuario --Modal Confirmación--*/
    public void confirmacionModal() {

        for (BeanGestionUsuario b : getListaRecursosPerfilUsuario()) {
            if (b.codeRecurso == 6 && b.checkValue) {
                for (CivDetalleRecursos dr : getListDetalleRecursos()) {
                    if (dr.getCivRecursos().getRecId().intValue() == 6) {
                        BeanGestionUsuario r = new BeanGestionUsuario();
                        r.setCodeDetalleRecurso(dr.getDetRecId().intValue());
                        r.setNombreDetalleRecurso(dr.getDetRecDescripcion());
                        r.setCodeRecursoDetalle(dr.getCivRecursos().getRecId().intValue());
                        r.setCheckDetalleRecurso(true);
                        getListDetalleRecursosSeleccionado().add(r);
                    }
                }
            }

            if (b.codeRecurso == 8 && b.checkValue) {
                for (CivDetalleRecursos dr : getListDetalleRecursos()) {
                    if (dr.getCivRecursos().getRecId().intValue() == 8) {
                        BeanGestionUsuario r = new BeanGestionUsuario();
                        r.setCodeDetalleRecurso(dr.getDetRecId().intValue());
                        r.setNombreDetalleRecurso(dr.getDetRecDescripcion());
                        r.setCodeRecursoDetalle(dr.getCivRecursos().getRecId().intValue());
                        r.setCheckDetalleRecurso(true);
                        getListDetalleRecursosSeleccionado().add(r);
                    }
                }
            }
        }

        registrarPerfiles();
    }


    /*Consultar los recursos asignados a un usuario y los detalle recursos registrados en el sistema.*/
    protected void impRecursosPerfil() {
        try {
            getGestionUsuarioBO().listRecursosPerfiles(this);
            getGestionUsuarioBO().detalleRecurso(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar listado de Perfiles: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        }
    }

    /*Consulta detalle recursos por codigo del recurso*/
    protected void impDetalleRecursosbyid(int rec_id) {
        try {
            getGestionUsuarioBO().listDetalleRecursosID(rec_id, this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar listado de Perfiles: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        }
    }

    /*Método para consultar todos los módulos registrados en la base de datos.*/
    public void impListModulos() {
        impModulos();
    }

    protected void impModulos() {
        try {
            getGestionUsuarioBO().listModulos(this);

        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar listado de módulos: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        }
    }

    /*Método para consultar todos los perfiles registrados en la base de datos.*/
    public void impListPerfiles() {
        clearModalUsuario();
        impPerfiles();
    }

    protected void impPerfiles() {
        try {
            getGestionUsuarioBO().listarPerfiles(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar listado de Perfiles: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        }
    }

    /*Método para consultar todos los usuarios registrados en la base de datos.*/
    public void listarUsuarios() {
        impListarUsuario();
        RequestContext.getCurrentInstance().execute("reload()"); // Función para mantener la paginación de la tabla donde se listan los usuarios registrados en la base de datos.
    }

    protected void impListarUsuario() {
        try {
            getGestionUsuarioBO().listUsuario(this);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        } catch (UsuariosException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getNivelFacesMessage(), null, e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al listar Usuario : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        }

    }

    /*Lista los tipos de pagos registrados en el sistema y realiza validaciones de modal*/
    public void listarMetodoPagoCaja(String idmodal) {
        if (idmodal.equals("soloCaja")) {
            valorModal = "perfilesActualizarUsuario";
            modalCaja();
        }
        if (idmodal.equals("pagoycaja")) {
            valorModal = "perfilesActualizarUsuario";
            modalPagoCaja();
        }
        if (idmodal.equals("pagoycaja2")) {
            valorModal = "perfilesEditar";
            modalPagoCaja();
        }
        if (idmodal.equals("soloCaja2")) {
            valorModal = "perfilesEditar";
            modalCaja();
        }

        setBtnAsignarMetodoPago(true); // Desactiva el botón asignar en la modal de tipos de pago y caja
        impListarMetodoPagoCaja();

    }

    /*Comprueba los detalles recursos asignados a un usuario*/
    public void checkDetalleRecurso(BeanGestionUsuario bean) throws Exception {
        int cont = 0;
        for (CivDetalleRecUsu dr : getDetalleRbyRecurso()) {
            if (dr.getDetRecId().intValue() == bean.codeDetalleRecurso) {
                if (dr.getPropiedadVisible()) {
                    cont++;
                    bean.setCheckDetalleRecursoAct(true);
                    break;
                }
            }
        }
        if (cont == 0) {
            bean.setCheckDetalleRecursoAct(false);
        }
    }

    //Opción NO de modal confirmación
    public void NOConfirmacion() {
        modalConfirmacionNO();
    }

    protected void impListarMetodoPagoCaja() {
        try {
            getGestionUsuarioBO().listarMPagoCaja(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al listar cajas y métodos de pago : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        }

    }

    /*Método para consultar una persona por número de identificación al crear un usuario.*/
    public void consultarPersona() {
        impConsultaPersona();
    }

    protected void impConsultaPersona() {
        try {
            if (!this.getDocumento().isEmpty()) { // Si el input documento está vacío no realiza ninguna consulta en la base de datos.
                getGestionUsuarioBO().consultarPersona(this);
                setBtnCrearUsuario(false);
                setBtnperfilar(true);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Informacion!", "Consulta exitosa"));
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageErrorCrearUsuario");
            } else {
                setNombreUsuarioPerfilar(""); // Limpiar el input de nombre de usuario
                setNombrePersona("");// Limpiar el input de nombre de la persona encontrada
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageErrorCrearUsuario");
            }

        } catch (UsuariosException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getNivelFacesMessage(), null, e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageErrorCrearUsuario");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al consultar Persona: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        }
    }

    /*Registrar un usuario en la base de datos*/
    public void registrarUsuario() {
        impRegistrarUsuario();
    }

    protected void impRegistrarUsuario() {
        try {
            getGestionUsuarioBO().registrarUsuario(this);
            setBtnperfilar(false); //Activa el boton perfilar en crear usuario.
            setBtnCrearUsuario(true); // Desactiva el boton crear en la modal crear usuario.
            setTipdoc(true); // Deshabilita el select tipo documento.
            setDoc(true); // Deshabilita los select e input restantes en la modal crear usuario.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Informacion!", "Usuario registrado correctamente"));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageErrorCrearUsuario");
        } catch (UsuariosException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getNivelFacesMessage(), null, e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageErrorCrearUsuario");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al registrar Usuario : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        }
    }

    /*Carga los datos básicos del usuario cuando se va a perfilar por primera vez*/
    public void datosUsuario(BeanGestionUsuario bean) {
        try {
            this.setNombreUsuarioPerfilar(bean.getNombreUsuario());
            getGestionUsuarioBO().detalleUsuario(bean.getCodeUsuario(), this);
            impPerfiles();
            impListRecursosPerfil();
        } catch (UsuariosException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getNivelFacesMessage(), null, e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al perfilar usuario : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        }
    }

    /*Carga los datos básicos del usuario cuando se va a actualizar*/
    public void datosUsuarioActualizacion(BeanGestionUsuario bean) {
        try {
            this.setNombreUsuarioPerfilar(bean.getNombreUsuario());
            impListRecursosPerfil();
            impListarMetodoPagoCaja();
            getGestionUsuarioBO().detalleUsuarioActualizacion(bean.getCodeUsuario(), this);
            impPerfiles();
            detalleRecursobyUsu(bean.getCodeUsuario());
            setEstadoRenderActualizar(true);
            setBtnAsignarPerfil(false);
        } catch (UsuariosException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getNivelFacesMessage(), null, e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al perfilar el usuario : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    public void detalleRecursobyUsu(int usuario) throws Exception {
        getGestionUsuarioBO().listDetalleRecursobyUsu(usuario, this);
    }

    public void comprobarModal(String idmodal) {
        int cont = 0;
        valorModal = idmodal;
        for (BeanGestionUsuario list : getListaRecursosPerfilUsuario()) {
            if ((list.getCodeRecurso() == 6 && list.isCheckValue()) || (list.getCodeRecurso() == 8 && list.isCheckValue())) {
                if (getSw() == 0) {
                    cont++;
                    RequestContext.getCurrentInstance().execute("$('#modalConfirmacion').modal('toggle'); $('#" + idmodal + "').modal('toggle')");
                    break;
                } else {
                    registrarPerfiles();
                    cont++;
                    break;
                }

            }
        }

        if (cont == 0) {
            registrarPerfiles();

        }
    }

    /*Registra a un usuario, los perfiles seleccionados en la base de datos.*/
    public void registrarPerfiles() {

        try {
            getGestionUsuarioBO().registrarPerfil(this);
            clearModalPerfilar();
            RequestContext.getCurrentInstance().execute("$('#" + valorModal + "').modal('hide')");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Informacion!", "Recursos asignados correctamente"));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al perfilar el usuario : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }

    }

    /*Actualización de usuario*/
    public void actualizarUsuario() {

        try {
            getGestionUsuarioBO().actualizaUsuario(this);
            clearModalactualizar();
            RequestContext.getCurrentInstance().execute("$('#perfilesActualizarUsuario').modal('hide')");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Informacion!", "El usuario se ha actualizado correctamente"));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al perfilar el usuario : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }

    }

    /*Generar la contraseña aleatoriamente cuando se va a reestablecer la misma.*/
    public void generarContrasena() {
        try {
            getGestionUsuarioBO().generarContrasenaAleatoria(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al generar contraseña del usuario : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    /*Selecciona todos los check secundarios de un módulo al seleccionar el check principal de cada módulo*/
    public void selectAllCombo(BeanGestionUsuario bean) throws Exception {
        if (isFirstcheckValue()) {
            setCheckValue(true);
            getGestionUsuarioBO().listRecursosbyModulo(bean.getCodeModuloCiv(), this);//Lista los recursos por módulo
            setColorAccordion("text-green"); // Cambia de color el título del módulo
            for (Integer listRecursosbyModulo1 : this.getListRecursosbyModulo()) {
                for (BeanGestionUsuario listaRecursosPerfilUsuario1 : this.getListaRecursosPerfilUsuario()) {
                    if (listaRecursosPerfilUsuario1.getCodeRecurso() == listRecursosbyModulo1) {
                        listaRecursosPerfilUsuario1.setCheckValue(true); // Se asignan todos los recursos del módulo a la lista para luego ser almacenados en la base de datos
                    }
                }
            }

            if (bean.codeModuloCiv == 4) { // Si el código del módulo es 4...
                setEstadoMensajeMetodoPago(true); // Realiza una validación
                /*Se usa en la modal de actualizar usuario, con esto indica que los tipos de pagos y las cajas todas fueron deshabilitadas.*/
                setEstadoRenderActualizarCheck(false);
                setEstadoRenderActualizarCheckCaja(false);
                /*-------------------------------------------------------------------------------------------------------------------------*/
            }

        } else {
            setCheckValue(false);
            getGestionUsuarioBO().listRecursosbyModulo(bean.getCodeModuloCiv(), this); //Lista los recursos por módulo
            setColorAccordion("text-black"); // Cambia de color el título del módulo
            for (Integer listRecursosbyModulo1 : this.getListRecursosbyModulo()) {
                for (BeanGestionUsuario listaRecursosPerfilUsuario1 : this.getListaRecursosPerfilUsuario()) {
                    if (listaRecursosPerfilUsuario1.getCodeRecurso() == listRecursosbyModulo1) {
                        listaRecursosPerfilUsuario1.setCheckValue(false); // Se desasignan todos los recursos del módulo a la lista para luego ser almacenados en la base de datos
                    }
                }
            }
            if (bean.codeModuloCiv == 4) { // Si el código del módulo es 4...
                setEstadoMensajeMetodoPago(false);  // Realiza una validación
                /*Se usa en la modal de actualizar usuario, con esto indica que los tipos de pagos y las cajas todas fueron deshabilitadas.*/
                setEstadoRenderActualizarCheck(false);
                setEstadoRenderActualizarCheckCaja(false);
                /*-------------------------------------------------------------------------------------------------------------------------*/
            }
        }

        /*Comprueba si la lista de recursos está vacía o no*/
        int cont = 0; // Indica si en la lista existe o no algún recursos seleccionado.
        for (BeanGestionUsuario listaRecursosPerfilUsuario2 : this.getListaRecursosPerfilUsuario()) {
            if (listaRecursosPerfilUsuario2.isCheckValue()) {
                cont++;
            }
        }
        if (cont > 0) { //La lista no está vacía por tanto...
            setEstadoMensajePerfil(false); //Desactiva el mensaje de que debe seleccionar un recurso.
            setBtnAsignarPerfil(false); // Activa el botón de asignar perfiles.
        } else { // La lista si está vacía por tanto...
            setEstadoMensajePerfil(true); // Activa el mensaje de que debe seleccionar un recurso.
            setBtnAsignarPerfil(true);// Desactiva el botón de asignar perfiles.
        }
        /*Valida si el mensaje de metodo de pago o el de caja aún está activo*/
        if (getEstadoMensajeMetodoPago() || isEstadoMensajeCaja()) {
            setBtnAsignarPerfil(true); // mantiene el botón de asignar desactivado hasta que seleccione un método de pago o caja.
        }

    }

    public void comprobarColor(BeanGestionUsuario bean) throws Exception {

        int cont = 0;
        getGestionUsuarioBO().listRecursosbyModulo(bean.getCodeModulo(), this); // Lista los recursos por módulo.
        for (Integer listRecursosbyModulo1 : this.getListRecursosbyModulo()) {
            for (BeanGestionUsuario listaRecursosPerfilUsuario1 : this.getListaRecursosPerfilUsuario()) {
                if (listRecursosbyModulo1 == listaRecursosPerfilUsuario1.codeRecurso) { // Compara los recursos del módulo listado con todos los recursos registrados en la base de datos.
                    if (listaRecursosPerfilUsuario1.checkValue) {
                        cont++; // si encuentra uno de ellos activos... incrementa esta variable para luego cambiarle de color al módulo.
                    }
                }
            }
        }
        if (cont == 0) { // Si es igual a cero quiere decir que no existe ningún recursos seleccionado en ese módulo por tanto...
            setColorAccordion("text-black"); // el color se mantiene negro.
        } else { // Por el contrario...
            setColorAccordion("text-green"); // El color lo cambia a verde.

        }

    }

    /*Método que llena la lista de recursos a asignar a un usuario.*/
    public void llenarListaPerfilesUsuarios(int codigoRecurso) {
        for (BeanGestionUsuario listaRecursosPerfilUsuario1 : this.getListaRecursosPerfilUsuario()) {
            if (listaRecursosPerfilUsuario1.getCodeRecurso() == codigoRecurso) {
                listaRecursosPerfilUsuario1.setCheckValue(true);

            }

        }
    }

    /*Método que elimina de la lista los recursos que se van a asignar*/
    public void quitarListaPerfilesUsuarios(int codigoRecurso) {
        for (BeanGestionUsuario listaRecursosPerfilUsuario1 : this.getListaRecursosPerfilUsuario()) {
            if (listaRecursosPerfilUsuario1.getCodeRecurso() == codigoRecurso) {
                listaRecursosPerfilUsuario1.setCheckValue(false);
                break;
            }

        }
    }

    /*Al cargar la modal de actualizar usuario comprueba que recursos tiene asignado el usuario.*/
    public void comprobarRecursosUsuario(int codigoRecurso) throws Exception {
        for (BeanGestionUsuario RecursosPerfilUsuario1 : this.getListaRecursosPerfilUsuario()) {
            if (RecursosPerfilUsuario1.codeRecurso == codigoRecurso) {
                if (RecursosPerfilUsuario1.isCheckValue()) {
                    setCheckValue(true);
                    break;
                } else {
                    setCheckValue(false);
                    break;
                }

            }
        }

    }

    public void comprobarDetalleRecursos(int codigoRecurso) throws Exception {
        int cont = 0;
        for (CivDetalleRecursos rec : this.getListDetalleRecursos()) {
            if (rec.getCivRecursos().getRecId().intValue() == codigoRecurso) {
                setDetalleRecurso(false);
                cont++;
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:detRec");
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:detRec2");
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:detRec3");
                break;
            }
        }
        if (cont == 0) {
            setDetalleRecurso(true);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:detRec");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:detRec2");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:detRec3");
        }

    }

    public void actualizarListaCajas(BeanGestionUsuario bean) throws Exception {
        if (bean.checkValueCaja) {
            for (BeanGestionUsuario listCaja : getListCajas()) {
                if (listCaja.getCodeCaja() == bean.codeCaja) {
                    listCaja.setCheckValueCaja(true);
                } else {
                    listCaja.setCheckValueCaja(false);
                }
            }

            deshabilitarCheck(bean);

        } else {
            for (BeanGestionUsuario listCaja : getListCajas()) {
                if (listCaja.getCodeCaja() == bean.codeCaja) {
                    listCaja.setCheckValueCaja(false);
                }
            }
            for (BeanGestionUsuario listaCaja : this.getListCajas()) {
                listaCaja.setEdcheckcaja(false);
            }
        }
        /*Comprueba si la lista de cajas está vacía o no*/
        int cont = 0;
        for (BeanGestionUsuario listCaja : this.getListCajas()) {
            if (listCaja.isCheckValueCaja()) {
                cont++;
            }
        }
        if (cont > 0) {//cont>0 indica que por lo menos se seleccionó un recurso 
            setMensajeC(false);
            setBtnAsignarPerfil(false);
        } else {
            setMensajeC(true);
            setBtnAsignarPerfil(true);
        }

    }

    public void actualizarListaPagos(BeanGestionUsuario bean) throws Exception {
        if (bean.checkValueTipoPago) {
            for (BeanGestionUsuario listPagos : getListMetodoPago()) {
                if (listPagos.getCodeTipoPago() == bean.codeTipoPago) {
                    listPagos.setCheckValueTipoPago(true);
                }
            }
        } else {
            for (BeanGestionUsuario listPagos : getListMetodoPago()) {
                if (listPagos.getCodeTipoPago() == bean.codeTipoPago) {
                    listPagos.setCheckValueTipoPago(false);
                }
            }
        }

        /*Comprueba si la lista de tipos de pagos está vacía o no*/
        int cont = 0;
        for (BeanGestionUsuario listPagos : this.getListMetodoPago()) {
            if (listPagos.isCheckValueTipoPago()) {
                cont++;
            }
        }
        if (cont > 0) {//cont>0 indica que por lo menos se seleccionó un recurso 
            setMensajeTP(false);
            setBtnAsignarPerfil(false);
        } else {
            setMensajeTP(true);
            setBtnAsignarPerfil(true);
        }

    }

    /*Método para activar el check principal de cada módulo si el usuario tiene activo todos los check secundarios del módulo - Modal Actualizar Usuario*/
    public void comprobarRecursosUsuariobyModulo(BeanGestionUsuario bean) throws Exception {
        if (this.getListaRecursosPerfilUsuario() != null) {
            getGestionUsuarioBO().listRecursosbyModulo(bean.getCodeModuloCiv(), this);
            int cantidadRecursos = getListRecursosbyModulo().size();
            int cont = 0;

            for (Integer listRecursosbyModulo1 : getListRecursosbyModulo()) {
                for (BeanGestionUsuario RecursosPerfilUsuario1 : this.getListaRecursosPerfilUsuario()) {
                    if (listRecursosbyModulo1 == RecursosPerfilUsuario1.codeRecurso) {
                        if (RecursosPerfilUsuario1.isCheckValue()) {

                            cont++;
                            break;
                        }

                    }
                }
            }

            if (cont == cantidadRecursos) {
                setFirstcheckValue(true);
            } else {
                setFirstcheckValue(false);
            }
        }

    }

    public void comprobarListas(BeanGestionUsuario bean) throws Exception {

        if (isCheckValue()) {
            if (bean.codeRecurso == 3) {

                int cont = 0;
                if (this.getListCajas() != null) {
                    for (BeanGestionUsuario listCaja : this.getListCajas()) {
                        if (listCaja.isCheckValueCaja()) {
                            cont++;
                        }
                    }
                }
                if (cont == 0) {
                    setEstadoRenderActualizarCheck(false);
                    setEstadoRenderActualizarCheckCaja(false);
                    datosRecursos(bean);

                } else {
                    llenarListaPerfilesUsuarios(bean.codeRecurso);
                }
            } else {
                if (bean.codeRecurso == 8) {

                    int cont = 0;
                    if (this.getListMetodoPago() != null) {
                        for (BeanGestionUsuario listPago : this.getListMetodoPago()) {
                            if (listPago.isCheckValueTipoPago()) {
                                cont++;
                            }
                        }
                    }
                    if (cont == 0) {
                        setEstadoRenderActualizarCheck(false);
                        setEstadoRenderActualizarCheckCaja(false);
                        datosRecursos(bean);

                    } else {
                        llenarListaPerfilesUsuarios(bean.codeRecurso);
                    }
                } else {
                    datosRecursos(bean);
                }
            }

        } else {

            if (bean.codeRecurso == 3 || bean.codeRecurso == 8) {
                setEstadoRenderActualizarCheck(false);
                setEstadoRenderActualizarCheckCaja(false);
                if (getListCajas() != null) {
                    getListCajas().clear();
                }
                if (getListMetodoPago() != null) {
                    getListMetodoPago().clear();
                }

            }
            datosRecursos(bean);
        }

    }

    public void modalAct() {
        RequestContext.getCurrentInstance().execute("$('#modalConfirmacionPago').modal('toggle');$('#perfilesActualizarUsuario').modal('toggle')");
    }

    public void confirmacionSiActualizar(int r) {
        confirmacion = true;

    }

    public void datosRecursos(BeanGestionUsuario bean) throws Exception {
        if (isCheckValue()) {
            setFirstcheckValue(false);
            llenarListaPerfilesUsuarios(bean.codeRecurso);
            for (BeanGestionUsuario listaRecursosPerfilUsuario1 : this.getListaRecursosPerfilUsuario()) {
                if (bean.codeRecurso == 8 && listaRecursosPerfilUsuario1.getCodeRecurso() == 3) {

                    if (listaRecursosPerfilUsuario1.isCheckValue()) {
                        setEstadoMensajeMetodoPago(true);
                        setEstadoMensajeCaja(false);
                        setBtnAsignarPerfil(false);
                        break;
                    } else {
                        setEstadoMensajeMetodoPago(true);
                        setEstadoMensajeCaja(false);
                        setBtnAsignarPerfil(false);
                    }
                }
                if (bean.codeRecurso == 3 && listaRecursosPerfilUsuario1.getCodeRecurso() == 8) {

                    if (listaRecursosPerfilUsuario1.isCheckValue()) {
                        setEstadoMensajeMetodoPago(true);
                        setEstadoMensajeCaja(false);
                        setBtnAsignarPerfil(false);
                        break;
                    } else {
                        setEstadoMensajeMetodoPago(false);
                        setEstadoMensajeCaja(true);
                        setBtnAsignarPerfil(false);
                    }
                }
            }

        } else {
            setFirstcheckValue(false);
            quitarListaPerfilesUsuarios(bean.codeRecurso);

            for (BeanGestionUsuario listaRecursosPerfilUsuario2 : this.getListaRecursosPerfilUsuario()) {
                if (bean.codeRecurso == 8 && listaRecursosPerfilUsuario2.getCodeRecurso() == 3) {

                    if (listaRecursosPerfilUsuario2.isCheckValue()) {
                        setEstadoMensajeMetodoPago(false);
                        setEstadoMensajeCaja(true);
                        setBtnAsignarPerfil(false);
                        break;
                    } else {
                        setEstadoMensajeMetodoPago(false);
                        setEstadoMensajeCaja(false);
                        setBtnAsignarPerfil(false);
                    }
                }
                if (bean.codeRecurso == 3 && listaRecursosPerfilUsuario2.getCodeRecurso() == 8) {

                    if (listaRecursosPerfilUsuario2.isCheckValue()) {
                        setEstadoMensajeMetodoPago(true);
                        setEstadoMensajeCaja(false);
                        setBtnAsignarPerfil(false);
                        break;
                    } else {
                        setEstadoMensajeMetodoPago(false);
                        setEstadoMensajeCaja(false);
                        setBtnAsignarPerfil(false);
                    }
                }
            }
        }
        comprobarColor(bean);
        /*Comprueba si la lista de recursos está vacía o no*/
        int cont = 0;
        for (BeanGestionUsuario listaRecursosPerfilUsuario2 : this.getListaRecursosPerfilUsuario()) {
            if (listaRecursosPerfilUsuario2.isCheckValue()) {
                cont++;
            }
        }
        if (cont > 0) {//cont>0 indica que por lo menos se seleccionó un recurso 
            setEstadoMensajePerfil(false);
            setBtnAsignarPerfil(false);
        } else {
            setEstadoMensajePerfil(true);
            setBtnAsignarPerfil(true);
        }
        if (getEstadoMensajeMetodoPago() || isEstadoMensajeCaja()) {
            setBtnAsignarPerfil(true);
        }

    }

    public void datosMetodoPago(BeanGestionUsuario bean) throws Exception {
        if (bean.checkValueTipoPago) {

            for (BeanGestionUsuario listaPago : this.getListMetodoPago()) {
                if (listaPago.getCodeTipoPago() == bean.codeTipoPago) {
                    listaPago.setCheckValueTipoPago(true);
                    break;
                }

            }
        } else {
            for (BeanGestionUsuario listaPago : this.getListMetodoPago()) {
                if (listaPago.getCodeTipoPago() == bean.codeTipoPago) {
                    listaPago.setCheckValueTipoPago(false);
                    break;
                }

            }
        }

        btnAsig();

    }

    public void btnAsig() {
        /*Comprueba si la lista de tipos de pago y caja está vacía o no*/
        int cont = 0, con = 0;
        for (BeanGestionUsuario listPago : this.getListMetodoPago()) {
            if (listPago.isCheckValueTipoPago()) {
                cont++;
            }
        }
        for (BeanGestionUsuario listCaja : this.getListCajas()) {
            if (listCaja.isCheckValueCaja()) {
                con++;
            }
        }

        if (cont > 0 && con > 0) {
            setBtnAsignarMetodoPago(false);
            setEstadoMensajeSeleccionPago(false);

        } else {
            setBtnAsignarMetodoPago(true);
            setEstadoMensajeSeleccionPago(true);

        }

    }

    /*Método que deshabilita los check de las cajas cuando ya se ha seleccionado una caja*/
    public void deshabilitarCheck(BeanGestionUsuario bean) throws Exception {
        for (BeanGestionUsuario listaCaja : this.getListCajas()) {
            if (listaCaja.isCheckValueCaja()) {
                listaCaja.setEdcheckcaja(false);
            } else {
                listaCaja.setEdcheckcaja(true);
            }
        }

    }

    public void datosCajas(BeanGestionUsuario bean, String modal) throws Exception {
        if (bean.checkValueCaja) {

            for (BeanGestionUsuario listaCaja : this.getListCajas()) {
                if (listaCaja.getCodeCaja() == bean.codeCaja) {

                    listaCaja.setCheckValueCaja(true);

                    break;
                }

            }
            deshabilitarCheck(bean);

        } else {
            for (BeanGestionUsuario listaCaja : this.getListCajas()) {
                if (listaCaja.getCodeCaja() == bean.codeCaja) {
                    listaCaja.setCheckValueCaja(false);
                    break;
                }

            }
            for (BeanGestionUsuario listaCaja : this.getListCajas()) {
                setBtnAsignarMetodoPago(true);
                setEstadoMensajeSeleccionPago(true);
                listaCaja.setEdcheckcaja(false);
            }

        }
        if (modal.equals("cajaypago")) {
            btnAsig();
        } else {
            int cont = 0;
            for (BeanGestionUsuario listCaja : this.getListCajas()) {
                if (listCaja.isCheckValueCaja()) {
                    cont++;
                }
            }

            if (cont > 0) {
                setBtnAsignarMetodoPago(false);
                setEstadoMensajeSeleccionPago(false);

            } else {
                setBtnAsignarMetodoPago(true);
                setEstadoMensajeSeleccionPago(true);

            }
        }

    }

    public void datosDetalleRecursos(BeanGestionUsuario bean) throws Exception {
        if (bean.checkDetalleRecurso) {

            for (BeanGestionUsuario listaRecursosDet : this.getListDetalleRecursosbyRec()) {
                if (listaRecursosDet.getCodeDetalleRecurso() == bean.codeDetalleRecurso) {
                    listaRecursosDet.setCheckDetalleRecurso(true);
                    break;
                }

            }
        } else {
            for (BeanGestionUsuario listaRecursosDet : this.getListDetalleRecursosbyRec()) {
                if (listaRecursosDet.getCodeDetalleRecurso() == bean.codeDetalleRecurso) {
                    listaRecursosDet.setCheckDetalleRecurso(false);
                    break;
                }

            }
        }

        /*Comprueba si la lista de detalle recursos está vacía o no*/
        int cont = 0;
        for (BeanGestionUsuario listaRecursosDet : this.getListDetalleRecursosbyRec()) {
            if (listaRecursosDet.isCheckDetalleRecurso()) {
                cont++;
            }
        }
        if (cont > 0) {
            setBtnAsignarDetalleRecursos(false);

        } else {
            setBtnAsignarDetalleRecursos(true);

        }
    }

    public void btnAsignarCajasPagos() throws Exception {
        limpiarPagoCaja();
        setEstadoMensajeMetodoPago(false);
        setBtnAsignarPerfil(false);
        setEstadoRenderActualizarCheck(false);
        setEstadoRenderActualizarCheckCaja(false);
    }

    public void limpiarPagoCaja() {
        modalPagoCaja();
        valorModal = "";
    }

    public void limpiarCaja() {
        modalCaja();
        valorModal = "";
    }

    public void btnAsignarCajas() throws Exception {
        limpiarCaja();
        setEstadoMensajeCaja(false);
        setBtnAsignarPerfil(false);
    }

    public void clearModalUsuario() {//limpiar modal crear usuario
        setNombreUsuarioPerfilar("");
        setNombreUsuario("");
        setDocumento("");
        setNombrePersona("");
        setBtnperfilar(true);
        setDetalleRecurso(true);
        setBtnCrearUsuario(false);
        setDoc(false);
        setTipdoc(false);
        setColorAccordion("text-black");
        if (getListPerfil() != null) {
            getListPerfil().clear();
        }
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:modalbodyCrearUsuario");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageErrorCrearUsuario");
    }

    public void clearModalPerfilar() {//limpiar modal perfilar usuario
        setNombreUsuarioPerfilar("");
        setCheckValue(false);
        setDetalleRecurso(true);
        setCheckValueTipoPago(false);
        setFirstcheckValue(false);
        setEstadoMensajeMetodoPago(false);
        setEstadoMensajeCaja(false);
        setEstadoMensajePerfil(true);
        setBtnAsignarPerfil(true);
        setColorAccordion("text-black");
        setSw(0);
        if (getListMetodoPago() != null) {
            getListMetodoPago().clear();
        }
        if (getListCajas() != null) {
            getListCajas().clear();
        }
        if (getListDetalleRecursos() != null) {
            getListDetalleRecursos().clear();
        }
        if (getListDetalleRecursosSeleccionado() != null) {
            getListDetalleRecursosSeleccionado().clear();
        }
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:pnlaccordion2");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
    }

    public void clearModalactualizar() {//limpiar modal actualizar usuario
        setNombreUsuarioPerfilar("");
        setEstadoDR(false);
        setPasswordAleatorio("");
        setCheckValue(false);
        setDetalleRecurso(true);
        setCheckDetalleRecursoAct(false);
        setCheckValueTipoPago(false);
        setCheckValueCaja(false);
        setFirstcheckValue(false);
        setEstadoMensajeMetodoPago(false);
        setDisableEstado(false);
        setTitleEstado("Estado del usuario");
        setEstadoMensajeCaja(false);
        setEstadoMensajePerfil(true);
        setEstadoRenderActualizar(false);
        setBtnAsignarPerfil(true);
        setMensajeC(false);
        setMensajeTP(false);
        setEstadoRenderActualizarCheck(false);
        setEstadoRenderActualizarCheckCaja(false);
        setColorAccordion("text-black");
        setSw(0);
        if (getListMetodoPago() != null) {
            getListMetodoPago().clear();
        }
        if (getListCajas() != null) {
            getListCajas().clear();
        }
        if (getListDetalleRecursos() != null) {
            getListDetalleRecursos().clear();
        }
        if (getListDetalleRecursosSeleccionado() != null) {
            getListDetalleRecursosSeleccionado().clear();
        }
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:pnlaccordion3");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmConUsuario:messageGeneral");
    }

    public void clearModalDetalleRecurso() {
        setBtnAsignarDetalleRecursos(false);
        if (getListDetalleRecursosbyRec() != null) {
            getListDetalleRecursosbyRec().clear();
        }
        modalDetalleRecurso();
        valorModal = "";
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
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the tipoDocumento
     */
    public int getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the nombrePersona
     */
    public String getNombrePersona() {
        return nombrePersona;
    }

    /**
     * @param nombrePersona the nombrePersona to set
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
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
     * @return the confirmarPassword
     */
    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    /**
     * @param confirmarPassword the confirmarPassword to set
     */
    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }

    /**
     * @return the listaRecursosPerfil
     */
    public List<BeanGestionUsuario> getListaRecursosPerfilUsuario() {
        return listaRecursosPerfilUsuario;
    }

    /**
     * @param listaRecursosPerfil the listaRecursosPerfil to set
     */
    public void setListaRecursosPerfilUsuario(List<BeanGestionUsuario> listaRecursosPerfil) {
        this.listaRecursosPerfilUsuario = listaRecursosPerfil;
    }

    /**
     * @return the gestionUsuarioBO
     */
    public GestionUsuarioBO getGestionUsuarioBO() {
        return gestionUsuarioBO;
    }

    /**
     * @param gestionUsuarioBO the gestionUsuarioBO to set
     */
    public void setGestionUsuarioBO(GestionUsuarioBO gestionUsuarioBO) {
        this.gestionUsuarioBO = gestionUsuarioBO;
    }

    /**
     * @return the fechainicio
     */
    public Date getFechainicio() {
        return fechainicio;
    }

    /**
     * @param fechainicio the fechainicio to set
     */
    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the codeSede
     */
    public int getCodeSede() {
        return codeSede;
    }

    /**
     * @param codeSede the codeSede to set
     */
    public void setCodeSede(int codeSede) {
        this.codeSede = codeSede;
    }

    /**
     * @return the codeCaja
     */
    public int getCodeCaja() {
        return codeCaja;
    }

    /**
     * @param codeCaja the codeCaja to set
     */
    public void setCodeCaja(int codeCaja) {
        this.codeCaja = codeCaja;
    }

    /**
     * @return the codeEstado
     */
    public int getCodeEstado() {
        return codeEstado;
    }

    /**
     * @param codeEstado the codeEstado to set
     */
    public void setCodeEstado(int codeEstado) {
        this.codeEstado = codeEstado;
    }

    /**
     * @return the codePerfil
     */
    public int getCodePerfil() {
        return codePerfil;
    }

    /**
     * @param codePerfil the codePerfil to set
     */
    public void setCodePerfil(int codePerfil) {
        this.codePerfil = codePerfil;
    }

    /**
     * @return the codeRecurso
     */
    public int getCodeRecurso() {
        return codeRecurso;
    }

    /**
     * @param codeRecurso the codeRecurso to set
     */
    public void setCodeRecurso(int codeRecurso) {
        this.codeRecurso = codeRecurso;
    }

    /**
     * @return the codeUsuario
     */
    public int getCodeUsuario() {
        return codeUsuario;
    }

    /**
     * @param codeUsuario the codeUsuario to set
     */
    public void setCodeUsuario(int codeUsuario) {
        this.codeUsuario = codeUsuario;
    }

    /**
     * @return the codeTipoPago
     */
    public int getCodeTipoPago() {
        return codeTipoPago;
    }

    /**
     * @param codeTipoPago the codeTipoPago to set
     */
    public void setCodeTipoPago(int codeTipoPago) {
        this.codeTipoPago = codeTipoPago;
    }

    /**
     * @return the checkValue
     */
    public boolean isCheckValue() {
        return checkValue;
    }

    /**
     * @param checkValue the checkValue to set
     */
    public void setCheckValue(boolean checkValue) {
        this.checkValue = checkValue;
    }

    /**
     * @return the nombreRecurso
     */
    public String getNombreRecurso() {
        return nombreRecurso;
    }

    /**
     * @param nombreRecurso the nombreRecurso to set
     */
    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    /**
     * @return the nombrePerfil
     */
    public String getNombrePerfil() {
        return nombrePerfil;
    }

    /**
     * @param nombrePerfil the nombrePerfil to set
     */
    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    /**
     * @return the nombreTipoPago
     */
    public String getNombreTipoPago() {
        return nombreTipoPago;
    }

    /**
     * @param nombreTipoPago the nombreTipoPago to set
     */
    public void setNombreTipoPago(String nombreTipoPago) {
        this.nombreTipoPago = nombreTipoPago;
    }

    /**
     * @return the nombreCaja
     */
    public String getNombreCaja() {
        return nombreCaja;
    }

    /**
     * @param nombreCaja the nombreCaja to set
     */
    public void setNombreCaja(String nombreCaja) {
        this.nombreCaja = nombreCaja;
    }

    /**
     * @return the usuarioRunt
     */
    public String getUsuarioRunt() {
        return usuarioRunt;
    }

    /**
     * @param usuarioRunt the usuarioRunt to set
     */
    public void setUsuarioRunt(String usuarioRunt) {
        this.usuarioRunt = usuarioRunt;
    }

    /**
     * @return the passwordAleatorio
     */
    public String getPasswordAleatorio() {
        return passwordAleatorio;
    }

    /**
     * @param passwordAleatorio the passwordAleatorio to set
     */
    public void setPasswordAleatorio(String passwordAleatorio) {
        this.passwordAleatorio = passwordAleatorio;
    }

    /**
     * @return the codeModulo
     */
    public int getCodeModulo() {
        return codeModulo;
    }

    /**
     * @param codeModulo the codeModulo to set
     */
    public void setCodeModulo(int codeModulo) {
        this.codeModulo = codeModulo;
    }

    /**
     * @return the listaModulos
     */
    public List<BeanGestionUsuario> getListaModulos() {
        return listaModulos;
    }

    /**
     * @param listaModulos the listaModulos to set
     */
    public void setListaModulos(List<BeanGestionUsuario> listaModulos) {
        this.listaModulos = listaModulos;
    }

    /**
     * @return the nombreModulo
     */
    public String getNombreModulo() {
        return nombreModulo;
    }

    /**
     * @param nombreModulo the nombreModulo to set
     */
    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    /**
     * @return the codeModuloCiv
     */
    public int getCodeModuloCiv() {
        return codeModuloCiv;
    }

    /**
     * @param codeModuloCiv the codeModuloCiv to set
     */
    public void setCodeModuloCiv(int codeModuloCiv) {
        this.codeModuloCiv = codeModuloCiv;
    }

    /**
     * @return the lista
     */
    public List<BeanGestionUsuario> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<BeanGestionUsuario> lista) {
        this.lista = lista;
    }

    /**
     * @return the listUsuario
     */
    public List<BeanGestionUsuario> getListUsuario() {
        return listUsuario;
    }

    /**
     * @param listUsuario the listUsuario to set
     */
    public void setListUsuario(List<BeanGestionUsuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    /**
     * @return the listPerfil
     */
    public List<BeanGestionUsuario> getListPerfil() {
        return listPerfil;
    }

    /**
     * @param listPerfil the listPerfil to set
     */
    public void setListPerfil(List<BeanGestionUsuario> listPerfil) {
        this.listPerfil = listPerfil;
    }

    /**
     * @return the codePerfilSeleccionado
     */
    public int getCodePerfilSeleccionado() {
        return codePerfilSeleccionado;
    }

    /**
     * @param codePerfilSeleccionado the codePerfilSeleccionado to set
     */
    public void setCodePerfilSeleccionado(int codePerfilSeleccionado) {
        this.codePerfilSeleccionado = codePerfilSeleccionado;
    }

    /**
     * @return the btnperfilar
     */
    public boolean isBtnperfilar() {
        return btnperfilar;
    }

    /**
     * @param btnperfilar the btnperfilar to set
     */
    public void setBtnperfilar(boolean btnperfilar) {
        this.btnperfilar = btnperfilar;
    }

    /**
     * @return the btnCrearUsuario
     */
    public boolean isBtnCrearUsuario() {
        return btnCrearUsuario;
    }

    /**
     * @param btnCrearUsuario the btnCrearUsuario to set
     */
    public void setBtnCrearUsuario(boolean btnCrearUsuario) {
        this.btnCrearUsuario = btnCrearUsuario;
    }

    /**
     * @return the nombrePerfilUsuario
     */
    public String getNombrePerfilUsuario() {
        return nombrePerfilUsuario;
    }

    /**
     * @param nombrePerfilUsuario the nombrePerfilUsuario to set
     */
    public void setNombrePerfilUsuario(String nombrePerfilUsuario) {
        this.nombrePerfilUsuario = nombrePerfilUsuario;
    }

    /**
     * @return the nombreUsuarioPerfilar
     */
    public String getNombreUsuarioPerfilar() {
        return nombreUsuarioPerfilar;
    }

    /**
     * @param nombreUsuarioPerfilar the nombreUsuarioPerfilar to set
     */
    public void setNombreUsuarioPerfilar(String nombreUsuarioPerfilar) {
        this.nombreUsuarioPerfilar = nombreUsuarioPerfilar;
    }

    /**
     * @return the FirstcheckValue
     */
    public boolean isFirstcheckValue() {
        return FirstcheckValue;
    }

    /**
     * @param FirstcheckValue the FirstcheckValue to set
     */
    public void setFirstcheckValue(boolean FirstcheckValue) {
        this.FirstcheckValue = FirstcheckValue;
    }

    /**
     * @return the estadoMensajeMetodoPago
     */
    public boolean getEstadoMensajeMetodoPago() {
        return estadoMensajeMetodoPago;
    }

    /**
     * @param estadoMensajeMetodoPago the estadoMensajeMetodoPago to set
     */
    public void setEstadoMensajeMetodoPago(boolean estadoMensajeMetodoPago) {
        this.estadoMensajeMetodoPago = estadoMensajeMetodoPago;
    }

    /**
     * @return the listMetodoPago
     */
    public List<BeanGestionUsuario> getListMetodoPago() {
        return listMetodoPago;
    }

    /**
     * @param listMetodoPago the listMetodoPago to set
     */
    public void setListMetodoPago(List<BeanGestionUsuario> listMetodoPago) {
        this.listMetodoPago = listMetodoPago;
    }

    /**
     * @return the listCajas
     */
    public List<BeanGestionUsuario> getListCajas() {
        return listCajas;
    }

    /**
     * @param listCajas the listCajas to set
     */
    public void setListCajas(List<BeanGestionUsuario> listCajas) {
        this.listCajas = listCajas;
    }

    /**
     * @return the checkValueTipoPago
     */
    public boolean isCheckValueTipoPago() {
        return checkValueTipoPago;
    }

    /**
     * @param checkValueTipoPago the checkValueTipoPago to set
     */
    public void setCheckValueTipoPago(boolean checkValueTipoPago) {
        this.checkValueTipoPago = checkValueTipoPago;
    }

    /**
     * @return the listaRecursosPerfil
     */
    public List<BeanGestionUsuario> getListaRecursosPerfil() {
        return listaRecursosPerfil;
    }

    /**
     * @param listaRecursosPerfil the listaRecursosPerfil to set
     */
    public void setListaRecursosPerfil(List<BeanGestionUsuario> listaRecursosPerfil) {
        this.listaRecursosPerfil = listaRecursosPerfil;
    }

    /**
     * @return the estadoMensajePerfil
     */
    public boolean isEstadoMensajePerfil() {
        return estadoMensajePerfil;
    }

    /**
     * @param estadoMensajePerfil the estadoMensajePerfil to set
     */
    public void setEstadoMensajePerfil(boolean estadoMensajePerfil) {
        this.estadoMensajePerfil = estadoMensajePerfil;
    }

    /**
     * @return the listRecursosbyModulo
     */
    public List<Integer> getListRecursosbyModulo() {
        return listRecursosbyModulo;
    }

    /**
     * @param listRecursosbyModulo the listRecursosbyModulo to set
     */
    public void setListRecursosbyModulo(List<Integer> listRecursosbyModulo) {
        this.listRecursosbyModulo = listRecursosbyModulo;
    }

    /**
     * @return the btnAsignarMetodoPago
     */
    public boolean isBtnAsignarMetodoPago() {
        return btnAsignarMetodoPago;
    }

    /**
     * @param btnAsignarMetodoPago the btnAsignarMetodoPago to set
     */
    public void setBtnAsignarMetodoPago(boolean btnAsignarMetodoPago) {
        this.btnAsignarMetodoPago = btnAsignarMetodoPago;
    }

    /**
     * @return the checkValueCaja
     */
    public boolean isCheckValueCaja() {
        return checkValueCaja;
    }

    /**
     * @param checkValueCaja the checkValueCaja to set
     */
    public void setCheckValueCaja(boolean checkValueCaja) {
        this.checkValueCaja = checkValueCaja;
    }

    /**
     * @return the btnAsignarPerfil
     */
    public boolean isBtnAsignarPerfil() {
        return btnAsignarPerfil;
    }

    /**
     * @param btnAsignarPerfil the btnAsignarPerfil to set
     */
    public void setBtnAsignarPerfil(boolean btnAsignarPerfil) {
        this.btnAsignarPerfil = btnAsignarPerfil;
    }

    /**
     * @return the estadoMensajeCaja
     */
    public boolean isEstadoMensajeCaja() {
        return estadoMensajeCaja;
    }

    /**
     * @param estadoMensajeCaja the estadoMensajeCaja to set
     */
    public void setEstadoMensajeCaja(boolean estadoMensajeCaja) {
        this.estadoMensajeCaja = estadoMensajeCaja;
    }

    /**
     * @return the estadoRenderActualizar
     */
    public boolean isEstadoRenderActualizar() {
        return estadoRenderActualizar;
    }

    /**
     * @param estadoRenderActualizar the estadoRenderActualizar to set
     */
    public void setEstadoRenderActualizar(boolean estadoRenderActualizar) {
        this.estadoRenderActualizar = estadoRenderActualizar;
    }

    /**
     * @return the estadoRenderActualizarCheck
     */
    public boolean isEstadoRenderActualizarCheck() {
        return estadoRenderActualizarCheck;
    }

    /**
     * @param estadoRenderActualizarCheck the estadoRenderActualizarCheck to set
     */
    public void setEstadoRenderActualizarCheck(boolean estadoRenderActualizarCheck) {
        this.estadoRenderActualizarCheck = estadoRenderActualizarCheck;
    }

    /**
     * @return the estadoRenderActualizarCheckCaja
     */
    public boolean isEstadoRenderActualizarCheckCaja() {
        return estadoRenderActualizarCheckCaja;
    }

    /**
     * @param estadoRenderActualizarCheckCaja the
     * estadoRenderActualizarCheckCaja to set
     */
    public void setEstadoRenderActualizarCheckCaja(boolean estadoRenderActualizarCheckCaja) {
        this.estadoRenderActualizarCheckCaja = estadoRenderActualizarCheckCaja;
    }

    /**
     * @return the estadoMensajeSeleccionPago
     */
    public boolean isEstadoMensajeSeleccionPago() {
        return estadoMensajeSeleccionPago;
    }

    /**
     * @param estadoMensajeSeleccionPago the estadoMensajeSeleccionPago to set
     */
    public void setEstadoMensajeSeleccionPago(boolean estadoMensajeSeleccionPago) {
        this.estadoMensajeSeleccionPago = estadoMensajeSeleccionPago;
    }

    /**
     * @return the doc
     */
    public boolean isDoc() {
        return doc;
    }

    /**
     * @param doc the doc to set
     */
    public void setDoc(boolean doc) {
        this.doc = doc;
    }

    /**
     * @return the tipdoc
     */
    public boolean isTipdoc() {
        return tipdoc;
    }

    /**
     * @param tipdoc the tipdoc to set
     */
    public void setTipdoc(boolean tipdoc) {
        this.tipdoc = tipdoc;
    }

    /**
     * @return the detalleRecurso
     */
    public boolean isDetalleRecurso() {
        return detalleRecurso;
    }

    /**
     * @param detalleRecurso the detalleRecurso to set
     */
    public void setDetalleRecurso(boolean detalleRecurso) {
        this.detalleRecurso = detalleRecurso;
    }

    /**
     * @return the listDetalleRecursos
     */
    public List<CivDetalleRecursos> getListDetalleRecursos() {
        return listDetalleRecursos;
    }

    /**
     * @param listDetalleRecursos the listDetalleRecursos to set
     */
    public void setListDetalleRecursos(List<CivDetalleRecursos> listDetalleRecursos) {
        this.listDetalleRecursos = listDetalleRecursos;
    }

    /**
     * @return the listDetalleRecursosbyRec
     */
    public List<BeanGestionUsuario> getListDetalleRecursosbyRec() {
        return listDetalleRecursosbyRec;
    }

    /**
     * @param listDetalleRecursosbyRec the listDetalleRecursosbyRec to set
     */
    public void setListDetalleRecursosbyRec(List<BeanGestionUsuario> listDetalleRecursosbyRec) {
        this.listDetalleRecursosbyRec = listDetalleRecursosbyRec;
    }

    /**
     * @return the checkDetalleRecurso
     */
    public boolean isCheckDetalleRecurso() {
        return checkDetalleRecurso;
    }

    /**
     * @param checkDetalleRecurso the checkDetalleRecurso to set
     */
    public void setCheckDetalleRecurso(boolean checkDetalleRecurso) {
        this.checkDetalleRecurso = checkDetalleRecurso;
    }

    /**
     * @return the nombreDetalleRecurso
     */
    public String getNombreDetalleRecurso() {
        return nombreDetalleRecurso;
    }

    /**
     * @param nombreDetalleRecurso the nombreDetalleRecurso to set
     */
    public void setNombreDetalleRecurso(String nombreDetalleRecurso) {
        this.nombreDetalleRecurso = nombreDetalleRecurso;
    }

    /**
     * @return the tipoDetalleRecurso
     */
    public String getTipoDetalleRecurso() {
        return tipoDetalleRecurso;
    }

    /**
     * @param tipoDetalleRecurso the tipoDetalleRecurso to set
     */
    public void setTipoDetalleRecurso(String tipoDetalleRecurso) {
        this.tipoDetalleRecurso = tipoDetalleRecurso;
    }

    /**
     * @return the codeDetalleRecurso
     */
    public int getCodeDetalleRecurso() {
        return codeDetalleRecurso;
    }

    /**
     * @param codeDetalleRecurso the codeDetalleRecurso to set
     */
    public void setCodeDetalleRecurso(int codeDetalleRecurso) {
        this.codeDetalleRecurso = codeDetalleRecurso;
    }

    /**
     * @return the codeRecursoDetalle
     */
    public int getCodeRecursoDetalle() {
        return codeRecursoDetalle;
    }

    /**
     * @param codeRecursoDetalle the codeRecursoDetalle to set
     */
    public void setCodeRecursoDetalle(int codeRecursoDetalle) {
        this.codeRecursoDetalle = codeRecursoDetalle;
    }

    /**
     * @return the btnAsignarDetalleRecursos
     */
    public boolean isBtnAsignarDetalleRecursos() {
        return btnAsignarDetalleRecursos;
    }

    /**
     * @param btnAsignarDetalleRecursos the btnAsignarDetalleRecursos to set
     */
    public void setBtnAsignarDetalleRecursos(boolean btnAsignarDetalleRecursos) {
        this.btnAsignarDetalleRecursos = btnAsignarDetalleRecursos;
    }

    /**
     * @return the listDetalleRecursosSeleccionado
     */
    public List<BeanGestionUsuario> getListDetalleRecursosSeleccionado() {
        return listDetalleRecursosSeleccionado;
    }

    /**
     * @param listDetalleRecursosSeleccionado the
     * listDetalleRecursosSeleccionado to set
     */
    public void setListDetalleRecursosSeleccionado(List<BeanGestionUsuario> listDetalleRecursosSeleccionado) {
        this.listDetalleRecursosSeleccionado = listDetalleRecursosSeleccionado;
    }

    /**
     * @return the colorAccordion
     */
    public String getColorAccordion() {
        return colorAccordion;
    }

    /**
     * @param colorAccordion the colorAccordion to set
     */
    public void setColorAccordion(String colorAccordion) {
        this.colorAccordion = colorAccordion;
    }

    /**
     * @return the mensajeTP
     */
    public boolean isMensajeTP() {
        return mensajeTP;
    }

    /**
     * @param mensajeTP the mensajeTP to set
     */
    public void setMensajeTP(boolean mensajeTP) {
        this.mensajeTP = mensajeTP;
    }

    /**
     * @return the mensajeC
     */
    public boolean isMensajeC() {
        return mensajeC;
    }

    /**
     * @param mensajeC the mensajeC to set
     */
    public void setMensajeC(boolean mensajeC) {
        this.mensajeC = mensajeC;
    }

    /**
     * @return the edcheckcaja
     */
    public boolean isEdcheckcaja() {
        return edcheckcaja;
    }

    /**
     * @param edcheckcaja the edcheckcaja to set
     */
    public void setEdcheckcaja(boolean edcheckcaja) {
        this.edcheckcaja = edcheckcaja;
    }

    /**
     * @return the estadoMensajeDetalle
     */
    public boolean isEstadoMensajeDetalle() {
        return estadoMensajeDetalle;
    }

    /**
     * @param estadoMensajeDetalle the estadoMensajeDetalle to set
     */
    public void setEstadoMensajeDetalle(boolean estadoMensajeDetalle) {
        this.estadoMensajeDetalle = estadoMensajeDetalle;
    }

    /**
     * @return the detalleRbyRecurso1
     */
    public Map<Integer, String> getDetalleRbyRecurso1() {
        return detalleRbyRecurso1;
    }

    /**
     * @param detalleRbyRecurso1 the detalleRbyRecurso1 to set
     */
    public void setDetalleRbyRecurso1(Map<Integer, String> detalleRbyRecurso1) {
        this.detalleRbyRecurso1 = detalleRbyRecurso1;
    }

    /**
     * @return the detalleRbyRecurso
     */
    public List<CivDetalleRecUsu> getDetalleRbyRecurso() {
        return detalleRbyRecurso;
    }

    /**
     * @param detalleRbyRecurso the detalleRbyRecurso to set
     */
    public void setDetalleRbyRecurso(List<CivDetalleRecUsu> detalleRbyRecurso) {
        this.detalleRbyRecurso = detalleRbyRecurso;
    }

    /**
     * @return the checkDetalleRecursoAct
     */
    public boolean isCheckDetalleRecursoAct() {
        return checkDetalleRecursoAct;
    }

    /**
     * @param checkDetalleRecursoAct the checkDetalleRecursoAct to set
     */
    public void setCheckDetalleRecursoAct(boolean checkDetalleRecursoAct) {
        this.checkDetalleRecursoAct = checkDetalleRecursoAct;
    }

    /**
     * @return the estadoDR
     */
    public boolean isEstadoDR() {
        return estadoDR;
    }

    /**
     * @param estadoDR the estadoDR to set
     */
    public void setEstadoDR(boolean estadoDR) {
        this.estadoDR = estadoDR;
    }

    /**
     * @return the codeDetalleRecUsu
     */
    public int getCodeDetalleRecUsu() {
        return codeDetalleRecUsu;
    }

    /**
     * @param codeDetalleRecUsu the codeDetalleRecUsu to set
     */
    public void setCodeDetalleRecUsu(int codeDetalleRecUsu) {
        this.codeDetalleRecUsu = codeDetalleRecUsu;
    }

    /**
     * @return the listRecursosSeleccionado
     */
    public List<BeanGestionUsuario> getListRecursosSeleccionado() {
        return listRecursosSeleccionado;
    }

    /**
     * @param listRecursosSeleccionado the listRecursosSeleccionado to set
     */
    public void setListRecursosSeleccionado(List<BeanGestionUsuario> listRecursosSeleccionado) {
        this.listRecursosSeleccionado = listRecursosSeleccionado;
    }

    /**
     * @return the sw
     */
    public int getSw() {
        return sw;
    }

    /**
     * @param sw the sw to set
     */
    public void setSw(int sw) {
        this.sw = sw;
    }

    /**
     * @return the disableEstado
     */
    public boolean isDisableEstado() {
        return disableEstado;
    }

    /**
     * @param disableEstado the disableEstado to set
     */
    public void setDisableEstado(boolean disableEstado) {
        this.disableEstado = disableEstado;
    }

    /**
     * @return the titleEstado
     */
    public String getTitleEstado() {
        return titleEstado;
    }

    /**
     * @param titleEstado the titleEstado to set
     */
    public void setTitleEstado(String titleEstado) {
        this.titleEstado = titleEstado;
    }

}
