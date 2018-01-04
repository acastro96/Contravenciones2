/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivParametros;
import com.contravenciones.tr.persistence.CivSedes;
import java.util.List;

/**
 *
 * @author Miguel Borja
 */
public interface ITParametros {

    /**
     * Retorna el listado de los Parametros correspondientes a un grupo de la
     * base de datos.
     *
     * @param grupo ID único del Grupo en el sistema.
     * @return Lista con los Parametros resultantes de la consulta. Retorna NULL
     * en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivParametros> listParametros(long grupo) throws Exception;

    /**
     * Retorna el listado de todas las Sedes de la base de datos.
     *
     * @return @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivSedes> getSedes() throws Exception;

    /**
     *
     * Método para insertar un Parametro a la base de datos.
     *
     * @param nuevo_parametro El Objeto Parametro a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivParametros nuevo_parametro) throws Exception;

    /**
     * Retorna el listado de todos los Parámetros de la base de datos.
     *
     * @return @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivParametros> listAllParametros() throws Exception;

    /**
     * Método para actualizar un Parametro a la base de datos.
     *
     * @param obj El Objeto Parametro a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivParametros obj) throws Exception;

    /**
     * Retorna el Parametro de la base de datos por medio del ID único.
     *
     * @param id_parametro ID único del Parametro a consultar.
     * @return El Parametro resultante de la consulta. Retorna NULL en caso de
     * no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivParametros getParametro(long id_parametro) throws Exception;

    /**
     * Retorna el Parametro de la base de datos por medio del ID del grupo y el
     * código.
     *
     * @param grupo ID único del Grupo a consultar.
     * @param codigo Código del Parametro?
     * @return El Grupo resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivParametros getParametroByCodeGrupo(int grupo, int codigo) throws Exception;

    /**
     *
     * @param grupo
     * @param subGrupo
     * @return
     * @throws Exception
     */
    public List<CivParametros> listParametros(long grupo, int subGrupo) throws Exception;

    /**
     * Funcion para obtener el parametro por grupo y Nombre corto (Configurado
     * manualmente)
     *
     * @param grupo
     * @param nombreCorto
     * @return
     * @throws Exception
     */
    public CivParametros getParametroByGrupoNombreCorto(long grupo, String nombreCorto) throws Exception;

}
