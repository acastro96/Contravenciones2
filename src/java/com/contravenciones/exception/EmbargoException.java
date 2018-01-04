/*
 * La C�vica Impresores S.A.S
 * Copyright 2016  * 
 */
package com.contravenciones.exception;

/**
 *
 * @author Acastro
 */
public class EmbargoException extends Exception{
    
    
    /*
     * Constructor donde se establece el mensaje de error 
     * @param message Mensaje de error
     */
    public EmbargoException(String message) {
        super(message);
    }
}
