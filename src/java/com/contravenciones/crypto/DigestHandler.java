/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.crypto;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Miguel Borja
 */
public class DigestHandler {

    /**
     *
     * @param cadena Cadena a Hashear
     * @return Cadena Digest SHA-1 Hexadecimal
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public static String encryptSHA2(String cadena) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        java.security.MessageDigest d;
        d = java.security.MessageDigest.getInstance("SHA-512");
        d.reset();
        d.update(cadena.getBytes("UTF-8"));
        return bytesToHex(d.digest());
    }

    /**
     * Covnierte un Array de Bytes a formato Hexadecimal
     *
     * @param in Array de Bytes
     * @return cadena Cadena de texto Hexadecimal
     */
    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    private DigestHandler() {
    }
}
