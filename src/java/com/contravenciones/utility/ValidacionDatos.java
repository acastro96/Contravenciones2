/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package com.contravenciones.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Miguel Borja
 */
public class ValidacionDatos {

    private static final String ORIGINAL = "ÁáÉéÍíÓóÚúÑñÜü";
    private static final String REPLACEMENT = "AaEeIiOoUuNnUu";

    /**
     *
     * @param str
     * @return
     */
    public static String quitarTildes(String str) {
        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int index = 0; index < array.length; index++) {
            int pos = ORIGINAL.indexOf(array[index]);
            if (pos > -1) {
                array[index] = REPLACEMENT.charAt(pos);
            }
        }
        return new String(array);
    }

    /**
     *
     * @param cadena
     * @return
     */
    public boolean isNumeric(String cadena) {
        try {
            Long.parseLong(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     *
     * @param pattern
     * @param campo
     * @return
     */
    public boolean validarFormatoDeCampos(String pattern, String campo) {
        boolean sw;
//        Pattern pat = Pattern.compile(".*1(?!2).*");
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(campo);
        sw = mat.matches(); //System.out.println("SI");
        //System.out.println("NO");
        return sw;
    }

    /**
     *
     * @param campo
     * @return
     */
    public boolean validarSolonumeros(String campo) {
        boolean sw;
//        Pattern pat = Pattern.compile("[0-9]*");
//        Matcher mat = pat.matcher("123456");
        sw = campo.matches("[0-9]*"); //System.out.println("SI");
        //System.out.println("NO");
        return sw;
    }

    /**
     * valida la seguridad del nombre del usuario - Se debe empezar y terminar
     * con un dígito o carácter. - Debe ser exactamente 4 a 10 caracteres de
     * largo. - permiten caracteres especiales son _.-
     *
     * @param campo
     * @return
     */
    public boolean validarNombreUsuario(String campo) {
        boolean sw;
        sw = campo.matches("^[a-zA-Z0-9][a-zA-Z0-9-_.]{2,8}[a-zA-Z0-9]$");
        return sw;
    }
}
