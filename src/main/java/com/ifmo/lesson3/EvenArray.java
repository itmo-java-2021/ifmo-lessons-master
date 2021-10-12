package com.ifmo.lesson3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvenArray {
    /*
    Создайте массив из всех чётных чисел от 2 до 20 и выведите элементы массива на экран
    сначала в строку, отделяя один элемент от другого пробелом, а затем в столбик (отделяя один
    элемент от другого началом новой строки). Перед созданием массива подумайте, какого он
    будет размера.2 4 6 … 18 20246…20
     */
    public static void main(String[] args) {
        int[] evenArray = evenArray();

        System.out.println(String.join(" ", Arrays.stream(evenArray).mapToObj(String::valueOf).toArray(String[]::new)));
        System.out.println(String.join("\n", Arrays.stream(evenArray).mapToObj(String::valueOf).toArray(String[]::new)));
    }

    public static int[] evenArray() {
        ArrayList<Integer> vs = new ArrayList<Integer>();
        for (int i = 2; i <= 20; i++) {
            if ((i & 1) == 0){
                vs.add(i);
            }
        }

        return vs.stream().mapToInt(i -> i).toArray();
    }
}
