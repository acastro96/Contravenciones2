/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jsf.bean;

import com.contravenciones.tr.bo.ParametrosBO;
import com.contravenciones.utility.Log_Handler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ing_jefreypadilla
 */
public class BeanParametros implements Serializable {

    private static final long serialVersionUID = 752642345125233L;
    
    private BeanLogin loginBean;

    private String code;
    private String codigoCaja;
    private String nombreP;
    private String nombreUsuario; // esta variable me almacena el nombre del usuario
    private String codigoUsu; // esta variable me almacena codigo del usuario
    private String fechaTaquilla; // esta variable me almacena el nombre del usuario
    private int codeTaquilla;
    
    private int grupo;
    private String subGrupo;
    private List<BeanParametros> lista;
    private List<BeanParametros> listaUsuario;//llena los usuarios del sistema
    private ParametrosBO parametroBO;
    private List<Integer> perfiles = new ArrayList<>();

    private List<BeanParametros> radicaciones;
    private List<BeanParametros> tipo_documentos;
    private List<BeanParametros> clases_vehiculo;
    private List<BeanParametros> servicios;
    private List<BeanParametros> categoria_licencia;
    private List<BeanParametros> sexos;
    private List<BeanParametros> tipo_guarismo;
    private List<BeanParametros> listTaquillas; // Listar taquillas por usuario

    /**
     *
     */
    @PostConstruct
    public void init() {
        //radicaciones = consultarParametros(18); //Radicacion ---Desarrollo
        radicaciones = consultarParametros(71); //Tipo de Tramites 
        tipo_documentos = consultarParametros(5); //Tipo de Documentos
        clases_vehiculo = consultarParametros(1); //Clase Vehículo
        servicios = consultarParametros(3); //Servicios
//        categoria_licencia = consultarParametros(19); //Categoría Licencia ---Desarrollo
        categoria_licencia = consultarParametros(221); //Categoría Licencia
        //sexos = consultarParametros(17); //Sexo ---Desarrollo
        sexos = consultarParametros(373); //Sexo
//        tipo_guarismo = consultarParametros(53); // Tipo Guarismo ---Desarrollo
        tipo_guarismo = consultarParametros(374); // Tipo Guarismo
    }

    /**
     *
     * @param group
     * @return
     */
    public List<BeanParametros> consultarParametros(int group) {
        setGrupo(group);
        listaParametros();
        return getLista();
    }

    /**
     * Consulta de parametros por grupo y subgrupo
     *
     * @param grupo
     * @param subGrupo
     * @return Listado de parametros por grupo y subgrupo
     */
    public List<BeanParametros> consultarParametros(int grupo, String subGrupo) {
        setGrupo(grupo);
        setSubGrupo(subGrupo);
        listarParametrosBySubCodigo();
        return getLista();
    }

   
    private void listaParametros() {
        try {
            setLista(getParametroBO().listParametros(this));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error listando parametro: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    private void listarParametrosBySubCodigo() {
        try {
            setLista(getParametroBO().listParametrosBySubCodigo(this));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error listando parámetros por subcodigo: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }
 /**
     *
     * @param query
     * @return
     */
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(query + i);
        }

        return results;
    }

    /**
     *
     * @param group
     * @param code
     * @return
     */
    public String consultarParametro(int group, String code) {
        setGrupo(group);
        setCode(code);
        consultarParametro();
        return getNombreP();
    }

    private void consultarParametro() {
        try {
            setNombreP(getParametroBO().consultarParametro(this));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error consultando parámetros: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }
    /**
     *
     * @return
     */
    public List<BeanParametros> listarPaises() {
        listaPaises();
        return getLista();
    }

    /**
     *
     */
    protected void listaPaises() {
        try {
            setLista(getParametroBO().listPaises(this));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error listando países: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    /**
     *
     * @return
     */
    public List<BeanParametros> listarDepartamentos() {
        listaDepartamentos();
        return getLista();
    }

    private void listaDepartamentos() {
        try {
            setLista(getParametroBO().listDepartamentos(this));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error listando departamentos: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    private void listaDepartamentosByPais() {
        try {
            setLista(getParametroBO().listDepartamentosByPais(this));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error listando departamentos por pais: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    /**
     *
     * @param dep
     * @return
     */
    public List<BeanParametros> listarMunicipios(int dep) {
        setGrupo(dep);
        listaMunicipios();
        return getLista();
    }

    /**
     *
     * @param pais
     * @return
     */
    public List<BeanParametros> listarDepartamentosByPais(int pais) {
        setGrupo(pais);
        listaDepartamentosByPais();
        return getLista();
    }

    private void listaMunicipios() {
        try {
            setLista(getParametroBO().listMunicipios(this));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error listando municipios: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    /**
     *
     * @param organismo
     * @return
     */
    public List<BeanParametros> listSedes(int organismo) {
        setCode(organismo + "");
        impListSedes();
        return getLista();
    }

    /**
     *
     */
    protected void impListSedes() {
        try {
            getParametroBO().listSedes(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error listando sedes: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }
    
    public List<BeanParametros> listCajas(int estado) {//Listar cajas
        setCodigoCaja(estado+"");//Envia el estado de la caja
        impListCajas();
        return getLista();
    }
    
    /**
     *
     */
    protected void impListCajas() {// Imprimir cajas
        try {
            getParametroBO().listCajas(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error listando cajas: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBean().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }
    

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the nombreP
     */
    public String getNombreP() {
        return nombreP;
    }

    /**
     * @param NombreP the nombreP to set
     */
    public void setNombreP(String NombreP) {
        this.nombreP = NombreP;
    }

    /**
     * @return the lista
     */
    public List<BeanParametros> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<BeanParametros> lista) {
        this.lista = lista;
    }

    /**
     * @return the parametroBO
     */
    public ParametrosBO getParametroBO() {
        return parametroBO;
    }

    /**
     * @param parametroBO the parametroBO to set
     */
    public void setParametroBO(ParametrosBO parametroBO) {
        this.parametroBO = parametroBO;
    }

    /**
     * @return the grupo
     */
    public int getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the subGrupo
     */
    public String getSubGrupo() {
        return subGrupo;
    }

    /**
     * @param subGrupo the subGrupo to set
     */
    public void setSubGrupo(String subGrupo) {
        this.subGrupo = subGrupo;
    }

    /**
     * @return the perfiles
     */
    public List<Integer> getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(List<Integer> perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * @return the radicaciones
     */
    public List<BeanParametros> getRadicaciones() {
        return radicaciones;
    }

    /**
     * @return the tipo_documentos
     */
    public List<BeanParametros> getTipo_documentos() {
        return tipo_documentos;
    }

    /**
     * @return the clases_vehiculo
     */
    public List<BeanParametros> getClases_vehiculo() {
        return clases_vehiculo;
    }

    /**
     * @return the servicios
     */
    public List<BeanParametros> getServicios() {
        return servicios;
    }

    /**
     * @return the categoria_licencia
     */
    public List<BeanParametros> getCategoria_licencia() {
        return categoria_licencia;
    }

    /**
     * @return the sexos
     */
    public List<BeanParametros> getSexos() {
        return sexos;
    }

    /**
     * @return the guarismos
     */
    public List<BeanParametros> getTipo_guarismo() {
        return tipo_guarismo;
    }

    /**
     * @param guarismos the guarismos to set
     */
    public void setTipo_guarismo(List<BeanParametros> guarismos) {
        this.tipo_guarismo = guarismos;
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
     * @return the codigoCaja
     */
    public String getCodigoCaja() {
        return codigoCaja;
    }

    /**
     * @param codigoCaja the codigoCaja to set
     */
    public void setCodigoCaja(String codigoCaja) {
        this.codigoCaja = codigoCaja;
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
     * @return the codigoUsuario
     */
    public String getCodigoUsu() {
        return codigoUsu;
    }

    /**
     * @param codigoUsuario the codigoUsuario to set
     */
    public void setCodigoUsu(String codigoUsuario) {
        this.codigoUsu = codigoUsuario;
    }

    /**
     * @return the listaUsuario
     */
    public List<BeanParametros> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(List<BeanParametros> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    /**
     * @return the listTaquillas
     */
    public List<BeanParametros> getListTaquillas() {
        return listTaquillas;
    }

    /**
     * @param listTaquillas the listTaquillas to set
     */
    public void setListTaquillas(List<BeanParametros> listTaquillas) {
        this.listTaquillas = listTaquillas;
    }

    /**
     * @return the fechaTaquilla
     */
    public String getFechaTaquilla() {
        return fechaTaquilla;
    }

    /**
     * @param fechaTaquilla the fechaTaquilla to set
     */
    public void setFechaTaquilla(String fechaTaquilla) {
        this.fechaTaquilla = fechaTaquilla;
    }

    /**
     * @return the codeTaquilla
     */
    public int getCodeTaquilla() {
        return codeTaquilla;
    }

    /**
     * @param codeTaquilla the codeTaquilla to set
     */
    public void setCodeTaquilla(int codeTaquilla) {
        this.codeTaquilla = codeTaquilla;
    }

}
