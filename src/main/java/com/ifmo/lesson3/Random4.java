package com.ifmo.lesson3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Random4 {
    /*
    Создайте массив из 4 случайных целых чисел из отрезка [10;99], выведите его на экран в
    строку. Определить и вывести на экран сообщение о том, является ли массив строго
    возрастающей последовательностью.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();

        System.out.println(String.join(" ", Arrays.stream(randomNumbers).mapToObj(String::valueOf).toArray(String[]::new)));

        if (isIncreasingSequence(randomNumbers)){
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    public static int[] randomNumbers() {

        return object.Random.getArray(4, 10, 99);
    }

    public static boolean isIncreasingSequence(int[] randomNumbers) {
        for (int i = 0; i < randomNumbers.length - 1; i++) {
            if (randomNumbers[i] > randomNumbers[i + 1]){
                return false;
            }
        }

        return true;
    }
}
