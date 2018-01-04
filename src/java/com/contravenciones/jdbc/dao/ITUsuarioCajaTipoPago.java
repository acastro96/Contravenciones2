/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jdbc.dao;

import com.contravenciones.tr.persistence.CivUsuariosCajasTipopagos;
import java.util.List;

/**
 *
 * @author ing_jefreypadilla
 */
public interface ITUsuarioCajaTipoPago {

    /**
     * Insertar usuario caja tipo pagos
     *
     * @param usuariosCajasTipopagos
     * @return id de insercion
     * @throws Exception
     */
    public long insert(CivUsuariosCajasTipopagos usuariosCajasTipopagos) throws Exception;

    /**
     * Actualiza usuario caja tipo pago
     *
     * @param usuariosCajasTipopagos
     * @return true en el caso de ser exitosa la actualización
     * @throws Exception
     */
    public boolean update(CivUsuariosCajasTipopagos usuariosCajasTipopagos) throws Exception;

    /**
     * Listado de los tipos de pago asociados a una caja, usuario y estado
     *
     * @param caja
     * @param usuario
     * @param estado
     * @return Listado
     * @throws Exception
     */
    public List<CivUsuariosCajasTipopagos> listTipoPagosByCajaUsuarioEst(long caja, long usuario, int estado) throws Exception;
    
    /**
     * Listado de los tipos de pago asociados a una caja, usuario y estado
     *
     
     * @param usuario
    
     * @return Listado
     * @throws Exception
     */
    public List<CivUsuariosCajasTipopagos> listTipoPagosByUsuario(long usuario) throws Exception;
    
    public List<CivUsuariosCajasTipopagos> listTipoPagosByUsuarioFechaFin(long usuario) throws Exception;
    
    /**
     *
     * @param usuario
     * @param tipopago
     * @return
     * @throws Exception
     */
    public CivUsuariosCajasTipopagos getTipoCajaUsuario(long usuario, long tipopago) throws Exception;
    
    /**
     *
     * @param caja
     * @param usuario
     * @param tipopago
     * Éste método retorna todos los tipos de pago según el id de la caja
     * @return
     * @throws Exception
     */
    public CivUsuariosCajasTipopagos getTipoPagobyCaja(long caja, long usuario, long tipopago) throws Exception;
}
