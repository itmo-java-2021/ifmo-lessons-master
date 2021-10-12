package com.ifmo.lesson3;

import java.util.Arrays;

public class Random8 {
    /*
    Создайте массив из 8 случайных целых чисел из отрезка [1;10]. Выведите массив на экран
    в строку. Замените каждый элемент с нечётным индексом на ноль. Снова выведете массив на
    экран на отдельной строке.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();

        System.out.println(String.join(" ", Arrays.stream(randomNumbers).mapToObj(String::valueOf).toArray(String[]::new)));

        int[] replacedWithZeros = replaceWithZeros(randomNumbers);

        System.out.println(String.join(" ", Arrays.stream(replacedWithZeros).mapToObj(String::valueOf).toArray(String[]::new)));
    }

    public static int[] randomNumbers() {

        return object.Random.getArray(8, 1, 10);
    }

    public static int[] replaceWithZeros(int[] randomNumbers) {
        int[] vs = randomNumbers.clone();
        for (int i = 1; i < vs.length; i += 2) {
            vs[i] = 0;
        }

        return vs;
    }
}
