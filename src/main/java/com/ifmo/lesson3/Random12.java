package com.ifmo.lesson3;

import java.util.Arrays;

public class Random12 {
    /*
    Создайте массив из 12 случайных целых чисел из отрезка [-15;15]. Определите какой
    элемент является в этом массиве максимальным и сообщите индекс его последнего
    вхождения в массив.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();


        int max = max(randomNumbers);
        int maxLastIndex = lastIndexOf(randomNumbers, max);

        System.out.format("max: %d, index: %d", max, maxLastIndex);
    }

    public static int[] randomNumbers() {

        return object.Random.getArray(12, -15, 15);
    }

    public static int max(int[] randomNumbers) {
        int max = Arrays.stream(randomNumbers).max().getAsInt();

        return max;
    }

    public static int lastIndexOf(int[] randomNumbers, int max) {
        for (int i = randomNumbers.length - 1; i >= 0; i--) {
            if (randomNumbers[i] == max){
                return i;
            }
        }

        return 0;
    }
}
