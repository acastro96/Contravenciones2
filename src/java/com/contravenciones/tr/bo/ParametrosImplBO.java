/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.tr.bo;

import com.contravenciones.jdbc.dao.ITCajas;
import com.contravenciones.jdbc.dao.ITDepartamentos;
import com.contravenciones.jdbc.dao.ITMunicipios;
import com.contravenciones.jdbc.dao.ITPaises;
import com.contravenciones.jdbc.dao.ITParametros;
import com.contravenciones.jdbc.dao.ITPerfiles;
import com.contravenciones.jdbc.dao.ITPersonas;
import com.contravenciones.jdbc.dao.ITSedes;
import com.contravenciones.jdbc.dao.ITUsuarios;
import com.contravenciones.jsf.bean.BeanParametros;
import com.contravenciones.tr.persistence.CivCajas;
import com.contravenciones.tr.persistence.CivDepartamentos;
import com.contravenciones.tr.persistence.CivMunicipios;
import com.contravenciones.tr.persistence.CivPais;
import com.contravenciones.tr.persistence.CivParametros;
import com.contravenciones.tr.persistence.CivPerfiles;
import com.contravenciones.tr.persistence.CivPersonas;
import com.contravenciones.tr.persistence.CivSedes;
import com.contravenciones.tr.persistence.CivUsuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ing_jefreypadilla
 */
public class ParametrosImplBO implements ParametrosBO, Serializable {

    private static final long serialVersionUID = 752642895448L;
    private ITUsuarios usuariosDAO;
    private ITParametros parametrosDAO;
    private ITDepartamentos departamentosDAO;
    private ITMunicipios municipiosDAO;
    private ITPaises paisDAO;
    private ITPerfiles perfilesDAO;
    private ITPersonas personasDAO;
    private ITSedes sedesDAO;
    private ITCajas cajasDAO;

    @Override
    public List<BeanParametros> listParametros(BeanParametros beanPar) throws Exception {
        List<BeanParametros> lista = new ArrayList<>();
        for (CivParametros p : getParametrosDAO().listParametros(beanPar.getGrupo())) {
            BeanParametros beanP = new BeanParametros();
            beanP.setCode(p.getParCodigo().toString());
            beanP.setGrupo(p.getCivGrupos().getGruId().intValue());
            beanP.setNombreP(p.getParNombre());
            beanP.setSubGrupo(p.getParNombrecorto());
            lista.add(beanP);
        }
        return lista;
    }

    @Override
    public String consultarParametro(BeanParametros beanParametros) throws Exception {
        return getParametrosDAO().getParametroByCodeGrupo(beanParametros.getGrupo(), Integer.parseInt(beanParametros.getCode())).getParNombre();
    }

       /**
     *
     * @param beanParametros
     * @return
     * @throws Exception
     */
    @Override
    public List<BeanParametros> listPaises(BeanParametros beanParametros) throws Exception {
        List<BeanParametros> list = new ArrayList<>();
        List<CivPais> listPais = getPaisDAO().listAllPaises();
        BeanParametros bparametros;
        for (CivPais pais : listPais) {
            bparametros = new BeanParametros();
            bparametros.setCode(pais.getPaiId().intValue() + "");
            bparametros.setNombreP(pais.getPaiNombre());
            list.add(bparametros);
        }
        return list;
    }

    @Override
    public List<BeanParametros> listDepartamentos(BeanParametros beanParametros) throws Exception {
        List<BeanParametros> list = new ArrayList<>();
        List<CivDepartamentos> listDepartamentos = getDepartamentosDAO().listAllDepartamentos();
        BeanParametros bparametros;
        for (CivDepartamentos dep : listDepartamentos) {
            bparametros = new BeanParametros();
            bparametros.setCode(dep.getDepId().longValue() + "");
            bparametros.setNombreP(dep.getDepNombre());
            list.add(bparametros);
        }
        return list;
    }

    /**
     *
     * @param beanParametros
     * @return
     * @throws Exception
     */
    @Override
    public List<BeanParametros> listDepartamentosByPais(BeanParametros beanParametros) throws Exception {
        List<BeanParametros> list = new ArrayList<>();
        List<CivDepartamentos> listDepartamentos = getDepartamentosDAO().listDepartamentosByPais(beanParametros.getGrupo());
        BeanParametros bparametros;
        for (CivDepartamentos dep : listDepartamentos) {
            bparametros = new BeanParametros();
            bparametros.setCode(dep.getDepId().longValue() + "");
            bparametros.setNombreP(dep.getDepNombre());
            list.add(bparametros);
        }
        return list;
    }

    @Override
    public List<BeanParametros> listMunicipios(BeanParametros beanParametros) throws Exception {
        List<BeanParametros> list = new ArrayList<>();
        List<CivMunicipios> listMunicipios = getMunicipiosDAO().listMunicipiosByDep(beanParametros.getGrupo());
        BeanParametros bparametros;
        for (CivMunicipios mun : listMunicipios) {
            bparametros = new BeanParametros();
            bparametros.setCode(mun.getMunId().intValue() + "");
            bparametros.setNombreP(mun.getMunNombre());
            list.add(bparametros);
        }
        return list;
    }

    @Override
    public List<BeanParametros> listParametrosBySubCodigo(BeanParametros beanPar) throws Exception {
        List<BeanParametros> lista = new ArrayList<>();
        for (CivParametros p : getParametrosDAO().listParametros(beanPar.getGrupo(), Integer.parseInt(beanPar.getSubGrupo()))) {
            BeanParametros beanP = new BeanParametros();
            beanP.setCode(p.getParCodigo().toString());
            beanP.setGrupo(p.getCivGrupos().getGruId().intValue());
            beanP.setNombreP(p.getParNombre());
            lista.add(beanP);
        }
        return lista;
    }
    
        @Override
    public void listSedes(BeanParametros bean) throws Exception {
        List<CivSedes> sede = getSedesDAO().listSedesByOrganismo(Long.parseLong(bean.getCode()));
        bean.setLista(new ArrayList<>());
        for (CivSedes civSedes : sede) {
            BeanParametros bp = new BeanParametros();
            bp.setNombreP(civSedes.getSedNombrecorto());
            bp.setCode(civSedes.getSedId().toString());
            bean.getLista().add(bp);
        }
    }
    
    /**
     *Método para listar cajas
     * @param bean
     * @throws Exception
     */
    @Override
    public void listCajas(BeanParametros bean) throws Exception {
        List<CivCajas> Cajas = getCajasDAO().listCajasByEstado(Integer.parseInt(bean.getCodigoCaja()));//getCodigoCaja = estado de la caja
        bean.setLista(new ArrayList<>());
        for (CivCajas civCajas : Cajas) {
            BeanParametros bp = new BeanParametros();
            bp.setNombreP(civCajas.getCajNombre());
            bp.setCode(civCajas.getCajId().toString());
            bean.getLista().add(bp);
        }
    }
        
 
    /**
     * @return the parametrosDAO
     */
    public ITParametros getParametrosDAO() {
        return parametrosDAO;
    }

    /**
     * @param parametrosDAO the parametrosDAO to set
     */
    public void setParametrosDAO(ITParametros parametrosDAO) {
        this.parametrosDAO = parametrosDAO;
    }

    /**
     * @return the departamentosDAO
     */
    public ITDepartamentos getDepartamentosDAO() {
        return departamentosDAO;
    }

    /**
     * @param departamentosDAO the departamentosDAO to set
     */
    public void setDepartamentosDAO(ITDepartamentos departamentosDAO) {
        this.departamentosDAO = departamentosDAO;
    }

    /**
     * @return the municipiosDAO
     */
    public ITMunicipios getMunicipiosDAO() {
        return municipiosDAO;
    }

    /**
     * @param municipiosDAO the municipiosDAO to set
     */
    public void setMunicipiosDAO(ITMunicipios municipiosDAO) {
        this.municipiosDAO = municipiosDAO;
    }

    /**
     * @return the paisDAO
     */
    public ITPaises getPaisDAO() {
        return paisDAO;
    }

    /**
     * @param paisDAO the paisDAO to set
     */
    public void setPaisDAO(ITPaises paisDAO) {
        this.paisDAO = paisDAO;
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
    /**
     * @return the sedesDAO
     */
    public ITSedes getSedesDAO() {
        return sedesDAO;
    }

    /**
     * @param sedesDAO the sedesDAO to set
     */
    public void setSedesDAO(ITSedes sedesDAO) {
        this.sedesDAO = sedesDAO;
    }
    /**
     * @return the perfilesDAO
     */
    public ITPerfiles getPerfilesDAO() {
        return perfilesDAO;
    }

    /**
     * @param perfilesDAO the perfilesDAO to set
     */
    public void setPerfilesDAO(ITPerfiles perfilesDAO) {
        this.perfilesDAO = perfilesDAO;
    }

    /**
     * @return the cajasDAO
     */
    public ITCajas getCajasDAO() {
        return cajasDAO;
    }

    /**
     * @param cajasDAO the cajasDAO to set
     */
    public void setCajasDAO(ITCajas cajasDAO) {
        this.cajasDAO = cajasDAO;
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
