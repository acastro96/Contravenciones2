/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.exception;

/**
 * Clase para definir los errores del módulo de Liquidación
 *
 * @author Miguel Borja
 */
public class LoginException extends Exception {

    /**
     * Constuctor
     */
    public LoginException() {
    }

    /**
     * Constructor donde se establece el mensaje de error
     *
     * @param message Mensaje de error
     */
    public LoginException(String message) {
        super(message);
    }

}
