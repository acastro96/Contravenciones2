package com.contravenciones.utility;

import com.contravenciones.exception.PasswordException;
import edu.vt.middleware.crypt.util.Base64Converter;
import edu.vt.middleware.password.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres Moreno
 */
public class ValidacionPassword {

    /**
     * metodo que permite relizar validacion al nombre del usuario segun los
     * parametros del RUNT<br>
     * - validaciones del runt<br>
     * 1. Debe contener por lo menos 2 digitos.<br>
     * 2. Debe contener por lo menos 5 caracteres en minuscula.<br>
     * 3. Debe contener por lo menos 1 caracter en mayuscula.<br>
     * 4. La longitud minima es de 8 caracteres.<br>
     * 5. No se permiten caracteres especiales.<br>
     * - Validaciones del sistema<br>
     * 1. No se permiten espacios en blanco<br>
     * 2. No puede contener secuencias numericas de mas de 2 caracteres<br>
     * 3. No puede contener secuencias alfabeticas de mas de 3 caracteres<br>
     * 4. no puede se permite el mismo caracter mas de 2 veces <br>
     * 5. No puede contener una secuencias de QUERTY<br>
     *
     * @param password recibo en un texto el nombre del usuario a validar
     * @param nombre_usuario String con nombre del usuario (usado durante la
     * validación)
     * @param historial Array de String que tiene el historial de contraseñas.
     * @return Retorna verdadero si la contraseña cumple con los requerimientos.
     * @throws java.lang.Exception
     */
    public static boolean validarPassword(String password, String nombre_usuario, ArrayList<String> historial) throws Exception {

        //EL PARAMETRO PUEDE SER NULL
        if (historial == null) {
            historial = new ArrayList<>();
        }

        //  AGREGAR HISTORIAL DE CONTRASEÑAS DE CADA USUARIO (REQUISITO DEL RUNT)
        List<Rule> ruleList = new ArrayList<>();

        // validaciones de seguridad criterio Civitrans-RUNT
        ruleList.add(new WhitespaceRule());//no permite espacion en blanco
        ruleList.add(new NumericalSequenceRule(3, true));//no puede contener una secuencia de mas de 3 digitos (123)
        ruleList.add(new QwertySequenceRule(4, true));// no puede contener una secuencia (QWERTY)
        ruleList.add(new AlphabeticalSequenceRule(4, true));//no puede contener una secuencia alfabetica de mas de 4 caracteres (abcd)
        ruleList.add(new RepeatCharacterRegexRule(3));// no permite repetir 3 caracteres (aaa)
        // fin reglas
        // Reglas que debe contener Segun Documento Del runt
        ruleList.add(new LengthRule(8, 20));//esteble la longitud de la contraseña (min,max)
        ruleList.add(new UppercaseCharacterRule(1));// debe contener como minimo una mayuscula
        ruleList.add(new DigitCharacterRule(2));// debe contener como minimo 2 digitos 
        ruleList.add(new LowercaseCharacterRule(5));// debe contener como minimo 5 letras en minusculas
        ruleList.add(new UsernameRule(true, true)); // Valida si el nombre de usuario se encuentra en la frase de contraseña.
        HistoryRule historyRule = new HistoryRule();
        historyRule.setDigest("SHA-1", new Base64Converter()); //Conversiòn y algoritmo
        ruleList.add(historyRule); //Regla de historial de contraseña
        // fin reglas del runt

        PasswordValidator validator = new PasswordValidator(ruleList);
        PasswordData passwordData = new PasswordData();
        //ArrayList<String> historial = new ArrayList(); //Agregar historial de contraseñas
        passwordData.setPasswordHistory(historial);
        passwordData.setPassword(new Password(password));
        passwordData.setUsername(nombre_usuario);
        RuleResult result = validator.validate(passwordData);
        if (!result.isValid()) {
            throw new PasswordException(getErroValidacion(validator.validate(passwordData).getDetails()));
        }
        return true;
    }

    /**
     * Retorna los mensajes de error correspondiente a la validación de la
     * contraseña
     *
     * @param listaDeErrores Listado con los resultados de la validación
     * @return
     */
    private static String getErroValidacion(List<RuleResultDetail> listaDeErrores) {
        String Cadenamensaje = "";
        for (RuleResultDetail detalleError : listaDeErrores) {
            switch (detalleError.getErrorCode()) {
                case "HISTORY_VIOLATION":
                    Cadenamensaje += "La contraseña concuerda con una de las contraseñas anteriores.\n\r";
                    break;
                case "ILLEGAL_WORD":
                    Cadenamensaje += "La contraseña contiene una palabra no válida.\n\r";
                    break;
                case "ILLEGAL_WORD_REVERSED":
                    Cadenamensaje += "La contraseña contiene una palabra no válida de forma inversa.\n\r";
                    break;
                case "ILLEGAL_MATCH":
                    Cadenamensaje += "La contraseña contiene caracteres repetidos.(Ej: aaa) o (Ej:111) \n\r";
                    break;
                case "ILLEGAL_CHAR":
                    Cadenamensaje += "La contraseña contiene la contiene un carácter inválido.\n\r";
                    break;
                case "ILLEGAL_SEQUENCE":
                    Cadenamensaje += "La contraseña contiene una secuencía inválida.(Ej: 123 o abcd)\n\r";
                    break;
                case "ILLEGAL_USERNAME":
                    Cadenamensaje += "La contraseña contiene el nombre de usuario.\n\r";
                    break;
                case "ILLEGAL_USERNAME_REVERSED":
                    Cadenamensaje += "La contraseña contiene el nombre de usuario en sentido inverso.\n\r";
                    break;
                case "ILLEGAL_WHITESPACE":
                    Cadenamensaje += "La contraseña no puede contener caracteres en blanco.\n\r";
                    break;
                case "INSUFFICIENT_CHARACTERS":

                    switch (detalleError.getParameters().get("characterType").toString()) {
                        case "digit":
                            Cadenamensaje += "La Contraseña debe tener por lo menos 2 (Dos) digitos (Ej: 53).\n\r";
                            break;
                        case "lowercase":
                            Cadenamensaje += "La contraseña debe contener por lo menos 5 minusculas (Ej: pedro).\n\r";
                            break;

                        case "uppercase":
                            Cadenamensaje += "La contraseña debe contener por lo menos una mayuscula (Ej: A). \n\r";
                            break;
                    }

                    break;
                case "INSUFFICIENT_CHARACTERISTICS":
                    Cadenamensaje += "La contraseña no cumple con las caracteristicas suficientes. \n\r";
                    break;
                case "SOURCE_VIOLATION":
                    Cadenamensaje += "La contraseña no puede ser la misma a la contraseña anterior. \n\r";
                    break;
                case "TOO_LONG":
                    Cadenamensaje += "La contraseña es demasiado larga. \n\r";
                    break;
                case "TOO_SHORT":
                    Cadenamensaje += "La contraseña es demasiado corta (minimo 8). \n\r";
                    break;
                default:
                    Cadenamensaje += "";
                    break;
            }
        }
        return Cadenamensaje;
    }

    /**
     *
     * @return @throws Exception
     */
    public String generarPassword() throws Exception {
        List<CharacterRule> ruleList = new ArrayList<>();
        ruleList.add(new UppercaseCharacterRule(1));// debe contener como minimo una mayuscula
        ruleList.add(new DigitCharacterRule(2));// debe contener como minimo 2 digitos
        ruleList.add(new LowercaseCharacterRule(5));// debe contener como minimo 5 letras en minusculas
        // fin reglas del runt
        PasswordGenerator generator = new PasswordGenerator();
        return generator.generatePassword(12, ruleList);
    }
}
