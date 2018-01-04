/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.jsf.bean;

import com.contravenciones.exception.PasswordException;
import com.contravenciones.tr.bo.RenovarPasswordBO;
import com.contravenciones.utility.Log_Handler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author JgCarreno
 */
public class BeanRenovarPassword {

    private String password;
    private String newPassword;
    private String verificarPaswword;
    private int option;
    private BeanLogin loginBean;
    private String oldPassClass = "btn-defaut";
    private String newPassClass = "btn-defaut";
    private String vrfPassClass = "btn-defaut";
    private String oldFaClass;
    private String newFaClass;
    private String vrfFaClass;
    private RenovarPasswordBO renovarPasswordBO;

    /**
     *
     * @param opt
     */
    public void validarPassword(int opt) {
        setOption(opt);
        implValidarPassword();
    }

    /**
     *
     */
    public void renovar() {
        implRenovar();
    }

    public String renovarInicioSesion() {
        return implRenovarInicioSesion();
    }

    /**
     *
     * @return 
     */
    protected String implRenovarInicioSesion() {
        try {
            if (getRenovarPasswordBO().updatePassword(this)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Informacion!", "La contraseña fue renovada correctamente"));
                getLoginBean().validarAcceso();
                if (getLoginBean().isAut_RUNT()) {
                    return "content/autenticacionRunt?faces-redirect=true"; //Redirect=true obligatorio para validaciones de filtro
                } else {
                    return "content/home?faces-redirect=true"; //Redirect=true obligatorio para validaciones de filtro
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Validacion!", "Verifique que toda la informacion sea correcta"));
            }
        } catch (PasswordException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Validacion!", ex.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al renovar contraseña: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Error!", Log_Handler.solucionError(e)));
        }
        return "";
    }

    /**
     *
     */
    protected void implRenovar() {
        try {
            if (getRenovarPasswordBO().updatePassword(this)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Informacion!", "La contraseña fue renovada correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Validacion!", "Verifique que toda la informacion sea correcta"));
            }
        } catch (PasswordException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Validacion!", ex.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al renovar contraseña: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Error!", Log_Handler.solucionError(e)));
        }
    }

    /**
     *
     */
    protected void implValidarPassword() {
        try {
            String fa = "";
            String btn = "";
            if (getRenovarPasswordBO().validarPassword(this)) {
                btn = "btn-success";
                fa = "fa-exclamation-circle";
                switch (getOption()) {
                    case 1:
                        setOldPassClass(btn);
                        setOldFaClass(fa);
                        break;
                    case 2:
                        setNewPassClass(btn);
                        setNewFaClass(fa);
                        break;
                    case 3:
                        setVrfFaClass(fa);
                        setVrfPassClass(btn);
                        break;
                    default:
                        break;
                }
            } else {
                fa = "fa-exclamation-triangle";
                btn = "btn-danger";
                switch (getOption()) {
                    case 1:
                        setOldPassClass(btn);
                        setOldFaClass(fa);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Validacion!", "Contraseña Incorrecta."));
                        break;
                    case 3:
                        setVrfPassClass(btn);
                        setVrfFaClass(fa);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Validacion!", "Las Contraseñas no Coinciden."));
                        break;
                }
            }
        } catch (PasswordException ex) {
            setNewFaClass("fa-exclamation-triangle");
            setNewPassClass("btn-warning");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Validacion!", ex.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error validando la contraseña: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(loginBean.getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
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
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the verificarPaswword
     */
    public String getVerificarPaswword() {
        return verificarPaswword;
    }

    /**
     * @param verificarPaswword the verificarPaswword to set
     */
    public void setVerificarPaswword(String verificarPaswword) {
        this.verificarPaswword = verificarPaswword;
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
     * @return the renovarPasswordBO
     */
    public RenovarPasswordBO getRenovarPasswordBO() {
        return renovarPasswordBO;
    }

    /**
     * @param renovarPasswordBO the renovarPasswordBO to set
     */
    public void setRenovarPasswordBO(RenovarPasswordBO renovarPasswordBO) {
        this.renovarPasswordBO = renovarPasswordBO;
    }

    /**
     * @return the option
     */
    public int getOption() {
        return option;
    }

    /**
     * @param option the option to set
     */
    public void setOption(int option) {
        this.option = option;
    }

    /**
     * @return the oldPassClass
     */
    public String getOldPassClass() {
        return oldPassClass;
    }

    /**
     * @param oldPassClass the oldPassClass to set
     */
    public void setOldPassClass(String oldPassClass) {
        this.oldPassClass = oldPassClass;
    }

    /**
     * @return the newPassClass
     */
    public String getNewPassClass() {
        return newPassClass;
    }

    /**
     * @param newPassClass the newPassClass to set
     */
    public void setNewPassClass(String newPassClass) {
        this.newPassClass = newPassClass;
    }

    /**
     * @return the vrfPassClass
     */
    public String getVrfPassClass() {
        return vrfPassClass;
    }

    /**
     * @param vrfPassClass the vrfPassClass to set
     */
    public void setVrfPassClass(String vrfPassClass) {
        this.vrfPassClass = vrfPassClass;
    }

    /**
     * @return the oldFaClass
     */
    public String getOldFaClass() {
        return oldFaClass;
    }

    /**
     * @param oldFaClass the oldFaClass to set
     */
    public void setOldFaClass(String oldFaClass) {
        this.oldFaClass = oldFaClass;
    }

    /**
     * @return the newFaClass
     */
    public String getNewFaClass() {
        return newFaClass;
    }

    /**
     * @param newFaClass the newFaClass to set
     */
    public void setNewFaClass(String newFaClass) {
        this.newFaClass = newFaClass;
    }

    /**
     * @return the vrfFaClass
     */
    public String getVrfFaClass() {
        return vrfFaClass;
    }

    /**
     * @param vrfFaClass the vrfFaClass to set
     */
    public void setVrfFaClass(String vrfFaClass) {
        this.vrfFaClass = vrfFaClass;
    }

}
