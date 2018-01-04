/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.exception;

/**
 * Clase para definir los errores de autenticación en el sistema.
 *
 * @author Miguel Borja
 */
public class AuthenticationException extends Exception {

    /**
     *
     * Constructor donde se establece el mensaje de error.
     *
     * @param message Mensaje de error
     */
    public AuthenticationException(String message) {
        super(message);
    }

}
