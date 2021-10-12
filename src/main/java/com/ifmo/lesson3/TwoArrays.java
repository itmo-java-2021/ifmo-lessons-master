package com.ifmo.lesson3;

import java.util.Arrays;

public class TwoArrays {
    /*
     Создайте 2 массива из 5 случайных целых чисел из отрезка [0;5] каждый, выведите
     массивы на экран в двух отдельных строках. Посчитайте среднее арифметическое элементов
     каждого массива и сообщите, для какого из массивов это значение оказалось больше (либо
     сообщите, что их средние арифметические равны).
     */
    public static void main(String[] args) {
        int[] randomNumbers1 = randomNumbers();
        int[] randomNumbers2 = randomNumbers();

        System.out.println(String.join(" ", Arrays.stream(randomNumbers1).mapToObj(String::valueOf).toArray(String[]::new)));
        System.out.println(String.join(" ", Arrays.stream(randomNumbers2).mapToObj(String::valueOf).toArray(String[]::new)));

        int average1 = average(randomNumbers1);
        int average2 = average(randomNumbers2);

        if (average1 == average2) {
            System.out.println("Равны");
        } else if (average1 > average2) {
            System.out.println("average1 больше average2");

        } else {
            System.out.println("average2 больше average1");
        }
    }
    public static int[] randomNumbers() {

        return object.Random.getArray(5, 0, 5);
    }

    public static int average(int[] randomNumbers) {
        int sum = 0;

        for (int i = 0; i < randomNumbers.length; i++) {
            sum += randomNumbers[i];
        }
        sum = sum / randomNumbers.length;

        return sum;
    }
}
