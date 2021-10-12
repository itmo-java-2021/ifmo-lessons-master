package com.ifmo.lesson3;

public class Random15 {
    /*
     Создайте массив из 15 случайных целых чисел из отрезка [0;9]. Выведите массив на экран.
     Подсчитайте сколько в массиве чётных элементов и выведете это количество на экран на
     отдельной строке.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();

        for (int i = 0; i < randomNumbers.length; i++) {
            System.out.println(randomNumbers[i]);
        }

        int evens = evens(randomNumbers);

        System.out.println(evens);
    }

    public static int[] randomNumbers() {

        return object.Random.getArray(15, 0, 9);
    }

    private static int evens(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) == 0){
                count++;
            }
        }

        return count;
    }
}
