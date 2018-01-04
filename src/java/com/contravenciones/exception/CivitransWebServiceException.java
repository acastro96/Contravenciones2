/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.exception;

/**
 * Objecto para manejar los errores de Web Service
 *
 * @author Miguel Borja
 */
public class CivitransWebServiceException extends Exception {

    private String message;

    /**
     * Constructor
     */
    public CivitransWebServiceException() {
    }

    /**
     * Constructor donde se establece el mensaje de error
     *
     * @param message Mensaje de error
     */
    public CivitransWebServiceException(String message) {
        this.message = message;
        this.message = this.message.replace("Client received SOAP Fault from server: ", "");
        this.message = this.message.replace(" Please see the server log to find more detail regarding exact cause of the failure.", "");
//        super(message);
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return this.message;
    }

}
