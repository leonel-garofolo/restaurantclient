package com.restaurant.app.utils;

public class UtilString {

    /*
     * Obtener cadena de texto hasta cierto numero. Si la longitud es menor al limite, completa con 0.
     * @param String cadena a cortar
     * @param limit longitud de la cadena a obtener
     * @ return String
     */
    public static String hardString(String cadena, int limit){
        if(cadena.length() > limit)
            return cadena.substring(0, limit);
        else {
            String newString = "";
            for(int i = 0; i < cadena.length(); i++){
                newString += String.valueOf(cadena.charAt(i));
            }
            int longCeros = limit - cadena.length();
            for(int i = 0; i < longCeros; i++){
                newString += " ";
            }
            return newString;
        }
    }
}
