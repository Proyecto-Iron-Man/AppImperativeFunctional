package com.ironman.exercises;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;

public class Exercise03 {
    /*
     * Crea un programa que calcule quien gana más partidas al piedra,
     * papel, tijera.
     * - El resultado puede ser: "Player 1", "Player 2", "Tie" (empate)
     * - La función recibe un listado que contiene pares, representando cada jugada.
     * - El par puede contener combinaciones de "R" (piedra), "P" (papel)
     *   o "S" (tijera).
     * - Ejemplo. Entrada: [("R","S"), ("S","R"), ("P","S")]. Resultado: "Player 2".
     */

    private static final String ROCK = "R";
    private static final String PAPER = "P";
    private static final String SCISSORS = "S";

    private static final String PLAYER_1 = "Player 1";
    private static final String PLAYER_2 = "Player 2";
    private static final String TIE = "Tie";

    public String imperativeSolution(String[][] plays) {
        int player1Wins = 0;
        int player2Wins = 0;
        int playsLength = plays.length;

        for (int i = 0; i < playsLength; i++) {
            String player1 = plays[i][0];
            String player2 = plays[i][1];

            if (player1.equals(player2)) {
                continue;
            }

            if (player1.equals(ROCK)) {
                if (player2.equals(SCISSORS)) {
                    player1Wins++;
                } else {
                    player2Wins++;
                }
            } else if (player1.equals(SCISSORS)) {
                if (player2.equals(PAPER)) {
                    player1Wins++;
                } else {
                    player2Wins++;
                }
            } else {
                if (player2.equals(ROCK)) {
                    player1Wins++;
                } else {
                    player2Wins++;
                }
            }
        }

        if (player1Wins > player2Wins) {
            return PLAYER_1;
        } else if (player1Wins < player2Wins) {
            return PLAYER_2;
        } else {
            return TIE;
        }

    }

    public String declarativeSolution(String[][] plays) {

        /*
        var filter = Arrays.stream(plays)
                .filter(play -> !play[0].equals(play[1]));

        // filter.forEach(item -> System.out.println(item[0] + " " + item[1]));

        var map = filter
                .map(play -> {
                    String player1 = play[0];
                    String player2 = play[1];

                    if(player1.equals(ROCK)) {
                        return player2.equals(SCISSORS) ? PLAYER_1 : PLAYER_2;
                    } else if (player1.equals(SCISSORS)) {
                        return player2.equals(PAPER) ? PLAYER_1 : PLAYER_2;
                    } else  {
                        return   player2.equals(ROCK) ? PLAYER_1 : PLAYER_2;
                    }
                });

        // map.forEach(player -> System.out.println("map: " + player));

        var group = map
                .collect(groupingBy(winner -> winner, counting()));

        group.entrySet().stream()
                .forEach(name -> System.out.println(name.getKey() + " " + name.getValue()));
        */

        var result = Arrays.stream(plays)
                .filter(play -> !play[0].equals(play[1]))
                .map(play -> {
                    String player1 = play[0];
                    String player2 = play[1];

                    if(player1.equals(ROCK)) {
                        return player2.equals(SCISSORS) ? PLAYER_1 : PLAYER_2;
                    } else if (player1.equals(SCISSORS)) {
                        return player2.equals(PAPER) ? PLAYER_1 : PLAYER_2;
                    } else  {
                      return   player2.equals(ROCK) ? PLAYER_1 : PLAYER_2;
                    }
                })
                .collect(groupingBy(winner -> winner, counting()))
                .entrySet()
                .stream()
                // .max(Map.Entry.comparingByValue()) // el max en caso todos los valores sean iguales no resuelve el empate
                .reduce((entry1, entry2) -> {
                    if (entry1.getValue().equals(entry2.getValue())) {
                        return Map.entry(TIE, entry1.getValue());
                    } else {
                        return (entry1.getValue() > entry2.getValue()) ? entry1 : entry2;
                    }
                })
                .map(Map.Entry::getKey)
                .orElse(TIE);


        return result;
    }


    public static void main(String[] args) {
        Exercise03 exercise = new Exercise03();

        //String[][] plays = {{ROCK, SCISSORS}, {SCISSORS, ROCK}, {PAPER, SCISSORS}};
        //String[][] plays = {{ROCK, SCISSORS}, {SCISSORS, ROCK}, {PAPER, ROCK}};
        //String[][] plays = {{ROCK, ROCK}, {SCISSORS, SCISSORS}, {PAPER, PAPER}};
        String[][] plays = {{ROCK, SCISSORS}, {PAPER, ROCK}, {SCISSORS, ROCK}};
        // String[][] plays = {{ROCK, SCISSORS}, {PAPER, PAPER}, {SCISSORS, ROCK}};
        System.out.println(exercise.imperativeSolution(plays));
        System.out.println(exercise.declarativeSolution(plays));
    }
}
