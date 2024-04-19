package com.ironman.exercises;

import java.util.Arrays;

public class Exercise01 {

    /*
     * Crea un programa que invierta el orden de una cadena de texto
     * sin usar funciones propias del lenguaje que lo hagan de forma automática.
     * - Si le pasamos "Hola mundo" nos retornaría "odnum aloH"
     */

    public String imperativeSolution(String text) {
        String reversed = "";
        /*
        String [] textArray = text.split("");

        for (int i = textArray.length - 1; i >=0; i -- ) {
            reversed += textArray[i];
        }
        */

        for (int i = text.length() - 1; i >= 0 ; i--) {
            reversed += text.charAt(i);
        }

        return reversed;
    }


    public String declarativeSolution(String text) {
        var result = Arrays.stream(text.split(""))
                .reduce("", (acc, current) -> current + acc); //...loH

        // return new StringBuilder(text).reverse().toString();

        return  result;
    }



    public static void main(String[] args) {
        Exercise01 exercise = new Exercise01();

        System.out.println("imperativeSolution: " + exercise.imperativeSolution("Hola mundo"));
        System.out.println("declarativeSolution: " + exercise.declarativeSolution("Hola mundo"));
    }
}
