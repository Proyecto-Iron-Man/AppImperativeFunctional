package com.ironman;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

public class Main {

    void streamCreation() {
        Integer [] numbers = {1,2,3};
        Stream<Integer> stream01 =Arrays.stream(numbers);
        stream01.forEach(item -> System.out.println(item));


        Stream<String> stream02 = Stream.of("Java", "C#", "PHP");
        stream02.forEach(System.out::println);

        List<Integer> listNumbers = Arrays.asList(2,4,6);
        Stream<Integer> stream03 = listNumbers.stream();

        List<String> names = List.of("Abel", "Jose", "Maria");
        var stream04 = names.stream();
    }

    void predicateFunctionalInterface() {
        Predicate<String> lengthPredicate = str -> str.length() > 4;
        boolean resultLength = lengthPredicate.test("Hola Mundo"); // true
        System.out.println("lengthPredicate: " + resultLength);


        BiPredicate<String, String> equalspredicate = (str1, str2) -> str1.equals(str2);
        System.out.println("equalspredicate: " + equalspredicate.test("Hola", "hola"));
    }

    void consumerFunctionalInterface() {
        Consumer<String> printConsumer = text -> System.out.println(text);
        printConsumer.accept("Hello World");

        BiConsumer<Integer, String> printBiConsumer = (anio, text) -> System.out.println("Tengo " + anio + " " + text);
        printBiConsumer.accept(34, "años");
    }

    void functionFunctionalInterface(){
        Function<String, Integer> lengthFunction = text -> text.length();
        System.out.println("lengthFunction: " + lengthFunction.apply("Hola"));

        BiFunction<String, String, String> concatBiFunction = (text1, text2) -> text1 + text2;
        System.out.println("concatBiFunction: " + concatBiFunction.apply("Hola", " Mundo"));
    }


    void supplierFunctionalInterface() {
        Supplier<String> stringSupplier = () -> "Hola Mundo";

        System.out.println("stringSupplier: " + stringSupplier.get());
    }


    public static void main(String[] args) {
        Main main =  new Main();
        // main.streamCreation();

        // main.predicateFunctionalInterface();

        // main.consumerFunctionalInterface();

        // main.functionFunctionalInterface();

        main.supplierFunctionalInterface();
    }
}