package com.smartpump.utils;

import java.util.Random;

/**
 * Clase responsable de generar un token aleatorio para los diferentes usuarios.
 * 
 * @author Franco Ariel Salonia
 *
 */
public class TokenGenerator {

    /** Posibles caracteres sobre los que se pueden generar los tokens. */
    private static final String TOKEN_POSSIBLE_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /** Longitud del token a generar. */
    private static final int TOKEN_LENGTH = 20;

    /**
     * Método que genera el token para el usuario.
     * 
     * @return el token generado.
     */
    public String generateToken() {
        Random rand = new Random();
        String possibleLetters = TOKEN_POSSIBLE_CHARACTERS;
        StringBuilder stringBuilder = new StringBuilder(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++)
            stringBuilder.append(possibleLetters.charAt(rand
                    .nextInt(possibleLetters.length())));
        return stringBuilder.toString();
    }
}
