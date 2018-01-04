package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivCajas;
import java.util.List;

/**
 *
 * @author Miguel Borja
 */
public interface ITCajas {

    /**
     * Método para insertar una Caja en la base de datos.
     *
     * @param caja El Objeto Caja a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivCajas caja) throws Exception;

    /**
     * Método para actualizar una Caja en la base de datos.
     *
     * @param caja El Objeto Caja a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivCajas caja) throws Exception;

    /**
     * Retorna la Caja de la base de datos por medio del ID único.
     *
     * @param id_caja ID único de la Caja a consultar.
     * @return La Caja resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivCajas getCajaById(long id_caja) throws Exception;

    /**
     * Retorna el listado de las Cajas correspondientes a un Estado.
     *
     * @param estado Estado de la Caja
     * @return Lista con las Cajas resultantes de la consulta. Retorna NULL en
     * caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivCajas> listCajasByEstado(int estado) throws Exception;

}
