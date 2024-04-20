package com.ironman.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;

public class Exercise02 {
    /*
     * Crea un programa que cuente cuantas veces se repite cada palabra
     * y que muestre el recuento final de todas ellas.
     * - Los signos de puntuación no forman parte de la palabra.
     * - Una palabra es la misma aunque aparezca en mayúsculas y minúsculas.
     * - No se pueden utilizar funciones propias del lenguaje que
     *   lo resuelvan automáticamente.
     */

    private final String REGEX_ONLY_LETTERS = "[^a-zA-Z ]";

    public void imperativeSolution(String text) {
        Map<String, Integer> wordsCount = new HashMap<>();

        String textProcessed = text.replaceAll(REGEX_ONLY_LETTERS, "");
        String [] words = textProcessed.split(" ");

        for (int i = 0; i < words.length ; i++) {
            String keyName = words[i].toLowerCase();
            if(wordsCount.containsKey(keyName)) {
                int valueCount = wordsCount.get(keyName) + 1;
                wordsCount.put(keyName, valueCount);
            } else {
                wordsCount.put(keyName, 1);
            }
        }


        for (String word : wordsCount.keySet()) {
            System.out.println("imperativeSolution: " + word + " [repite] " + wordsCount.get(word));
        }

    }

    public void declarativeSolution(String text) {

        String textProcessed = text.replaceAll(REGEX_ONLY_LETTERS, "");
        String [] words = textProcessed.split(" ");

        var result = Arrays.stream(words)
                .map(word -> word.toLowerCase())
                .collect(groupingBy(x -> x, counting()));

        result.forEach((word,count) -> System.out.println("declarativeSolution: " + word + " [repite] " + count));


    }

    public static void main(String[] args) {
        Exercise02 exercise = new Exercise02();

        exercise.imperativeSolution("Hola, soy un dev, un dev backend, hola dev frontend");
        exercise.declarativeSolution("Hola, soy un dev, un dev backend, hola dev frontend");
    }


}
