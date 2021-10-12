package com.ifmo.lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class UnevenArray {
    /*
    Создайте массив из всех нечётных чисел от 1 до 99, выведите его на экран в строку, а затем
    этот же массив выведите на экран тоже в строку, но в обратном порядке (99 97 95 93 … 7 5 3
    1)
     */
    public static void main(String[] args) {
        int[] unevenArray = unevenArray();

        System.out.println(String.join(" ", Arrays.stream(unevenArray).mapToObj(String::valueOf).toArray(String[]::new)));

        StringBuilder builder = new StringBuilder();
        for (int i = unevenArray.length - 1; i >= 0; i--) {
            if (i == 0){
                builder.append(unevenArray[i]);
            } else {
                builder.append(unevenArray[i]).append(" ");
            }
        }
        System.out.println(builder.toString());
    }

    public static int[] unevenArray() {
        ArrayList<Integer> vs = new ArrayList<Integer>();
        for (int i = 1; i <= 99; i += 2) {
            vs.add(i);
        }

        return vs.stream().mapToInt(i -> i).toArray();
    }
}
