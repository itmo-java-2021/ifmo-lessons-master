package com.ifmo.lesson2;

import java.util.Arrays;

public class Fibonacci {
    /*
     Выведите на экран первые 11 членов последовательности Фибоначчи. Напоминаем, что
     первый и второй члены последовательности равны единицам, а каждый следующий — сумме
     двух предыдущих.
     */
    public static void main(String[] args) {
        int[] vs = new int[11];
        for (int i = 0; i < 11; i++) {
            switch (i){
                case 0:
                case 1:
                    vs[i] = 1;
                    break;
                default:
                    vs[i] = vs[i - 1] + vs[i - 2];
            }
        }
        for (int item: vs) {
            System.out.println(item);
        }
    }
}
