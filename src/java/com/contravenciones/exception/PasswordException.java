/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.exception;

/**
 * Clase que define una Excepcion en el módulo de Password
 *
 * @author Miguel Borja
 */
public class PasswordException extends Exception {

    /**
     * Constructor donde se establece el mensaje de error
     *
     * @param message Mensaje de error
     */
    public PasswordException(String message) {
        super(message);
    }

}
