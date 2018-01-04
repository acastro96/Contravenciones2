/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.tr.bo;

import com.contravenciones.exception.PasswordException;
import com.contravenciones.jdbc.dao.ITUsuarios;
import com.contravenciones.jsf.bean.BeanRenovarPassword;
import com.contravenciones.tr.persistence.CivUspHistoria;
import com.contravenciones.tr.persistence.CivUsuarios;
import com.contravenciones.utility.Log_Handler;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JgCarreno
 */
public class RenovarPasswordImplBO implements RenovarPasswordBO {

    private ITUsuarios usuariosDAO;

    /**
     *
     * @param bean
     * @return
     * @throws Exception
     */
    @Override
    public boolean validarPassword(BeanRenovarPassword bean) throws Exception {

        switch (bean.getOption()) {
            case 1:

                String oldPass = com.contravenciones.crypto.DigestHandler.encryptSHA2(bean.getPassword());
                return getUsuariosDAO().consultarUsuarioBy(Integer.parseInt(bean.getLoginBean().getID_Usuario())).getUsuPassword().equals(oldPass);
            case 2:
                if (bean.getPassword().equals(bean.getNewPassword())) {//Válida si la nueva contraseña es igual a la actual
                    throw new PasswordException("La contraseña debe ser diferente a la actual.");
                } else { //Sino es igual, simplemente sigue ejecutando el código
                    ArrayList<String> list = getUsuariosDAO().consultar_HPAS(Integer.parseInt(bean.getLoginBean().getID_Usuario()));
                    return com.contravenciones.utility.ValidacionPassword.validarPassword(bean.getNewPassword(), bean.getLoginBean().getUser(), list);
                }
            case 3:
                return bean.getNewPassword().equals(bean.getVerificarPaswword());
            default:
                return false;
        }//Switch
    }//End

    /**
     *
     * @param bean
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(BeanRenovarPassword bean) throws Exception {
        bean.setOption(1);
        if (validarPassword(bean)) {
            bean.setOption(2);
            if (validarPassword(bean)) {
                bean.setOption(3);
                if (validarPassword(bean)) {
                    List list = getUsuariosDAO().consultar_HPAS(Integer.parseInt(bean.getLoginBean().getID_Usuario()));
                    String crypto = com.contravenciones.crypto.DigestHandler.encryptSHA2(bean.getVerificarPaswword());
                    if (list.contains(crypto)) {
                        throw new PasswordException("Por seguridad debe usar una contraseña no registrada con anterioridad en el sistema");
                    }
                    CivUsuarios civUsuarios = getUsuariosDAO().consultarUsuarioBy(Integer.parseInt(bean.getLoginBean().getID_Usuario()));
                    CivUspHistoria uspHistoria = new CivUspHistoria(null, civUsuarios, crypto, new Date(), new BigDecimal(1));

                    /*se actualizan todas las historias de contraseñas en estado 2*/
                    List<CivUspHistoria> com = getUsuariosDAO().consultarEstado_HPAS(Integer.parseInt(bean.getLoginBean().getID_Usuario()));
                    if (com != null) {
                        for (CivUspHistoria cu : getUsuariosDAO().consultarEstado_HPAS(Integer.parseInt(bean.getLoginBean().getID_Usuario()))) {
                            if (cu.getEstado().intValue() == 1) {
                                cu.setEstado(BigDecimal.valueOf(2));
                                getUsuariosDAO().updateHisPass(cu);
                            }
                        }
                    }
                    /*---------------------------------------------------------------*/

                    getUsuariosDAO().insertHisPass(uspHistoria);
                    civUsuarios.setUsuPassword(crypto);
                    civUsuarios.setUsuEstado(BigDecimal.ONE);
                    bean.getLoginBean().setUserEstado(1);
                    getUsuariosDAO().update(civUsuarios);
                    Log_Handler.registrarEvento("El usuario:" + bean.getLoginBean().getNombre() + " ha restaurado su contraseña correctamente.", null, Log_Handler.INFO, getClass(), Integer.parseInt(bean.getLoginBean().getID_Usuario()));
                    return true;
                }
            }
        }
        return false;
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
