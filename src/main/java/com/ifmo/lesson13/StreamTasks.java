package com.ifmo.lesson13;

import object.Random;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTasks {

    static class Person {
        final String name;
        final int age;
        final String country;

        public Person(String name, int age, String country) {
            this.name = name;
            this.age = age;
            this.country = country;
        }
    }

    static class RandomPerson extends Person {
        private static final List<String> COUNTRIES = Arrays.asList("Germany", "Russia", "Mexico", "Ghana", "Japan");
        private static final List<String> NAMES = Arrays.asList("Bob", "Alice", "Nick", "Ted", "Quentin", "David", "Zack", "Stacy", "Helen", "Julia");

        public RandomPerson() {
            super(NAMES.get(Random.getRandom(0, NAMES.size())), Random.getRandom(0, 99), COUNTRIES.get(Random.getRandom(0, COUNTRIES.size())));
        }
    }

    public static void main(String[] args) {
        List<Person> people = generatePeople(100).collect(Collectors.toList());

        List<String> countries = countriesSortedByTheirPopulationDescending(people.stream());
        String countryThatHasMostKids = countryThatHasMostKids(people.stream());
        Map<String, Long> populationByCountry = populationByCountry(people.stream());

        System.out.println(countries);
        System.out.println(countryThatHasMostKids);
        System.out.println(populationByCountry);

        List<String> words = List.of("the", "hello", "approximation", "greetings", "java", "war");

        Map<Integer, Set<String>> wordsByLength = groupWordsByLength(words);
        int averageWordLength = averageWordLength(words);
        Set<String> longestWords = longestWords(words);

        System.out.println(wordsByLength);
        System.out.println(averageWordLength);
        System.out.println(longestWords);
    }

    // Метод возвращает страны в порядке убывания их населения.
    public static List<String> countriesSortedByTheirPopulationDescending(Stream<Person> people) {

        return people
                .collect(Collectors.groupingBy(o -> o.country, Collectors.counting()))
                .entrySet().parallelStream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // Метод находит страну (или одну из стран), в которых больше всего человек в возрасте
    // до 18 лет.
    public static String countryThatHasMostKids(Stream<Person> people) {

        Optional<String> s = people
                .filter(person -> person.age < 18)
                .collect(Collectors.groupingBy(o -> o.country, Collectors.counting()))
                .entrySet().parallelStream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();

        return s.orElse(null);
    }

    // Метод возвращает карту стран их населения.
    public static Map<String, Long> populationByCountry(Stream<Person> people) {

        return people
                .collect(Collectors.groupingBy(o -> o.country, Collectors.counting()));

    }

    // Метод генерирует случайных людей в ограниченном наборе стран.
    // number - число желаемых людей.
    public static Stream<Person> generatePeople(int number) {

        return Stream.generate(RandomPerson::new).limit(number).map(randomPerson -> (Person) randomPerson);
    }

    // Метод возвращает карту сгруппированных слов по их длине. Например, для
    // трёхбуквенных слов будет:
    // 3 -> "the", "map", "got", "war"...
    public static Map<Integer, Set<String>> groupWordsByLength(List<String> words) {

        return words
                .parallelStream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
    }

    // Метод находит среднюю длину слов в списке.
    public static int averageWordLength(List<String> words) {

        return words
                .parallelStream()
                .map(String::length)
                .reduce(0, (integer, integer2) -> integer += integer2) / words.size();
    }

    // Метод находит самое длинное слово или слова, если таких несколько.
    public static Set<String> longestWords(List<String> words) {

        var vs = words
                .parallelStream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()))
                .entrySet().parallelStream()
                .max(Map.Entry.comparingByKey())
                .stream()
                .flatMap(integerSetEntry -> integerSetEntry.getValue().stream())
                .collect(Collectors.toSet());

        return vs;
    }
}
