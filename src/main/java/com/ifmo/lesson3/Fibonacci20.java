package com.ifmo.lesson3;

public class Fibonacci20 {
    /*
    Создайте массив из 20-ти первых чисел Фибоначчи и выведите его на экран. Напоминаем,
    что первый и второй члены последовательности равны единицам, а каждый следующий —
    сумме двух предыдущих.
     */
    public static void main(String[] args) {
        int[] fibonacciNumbers = fibonacciNumbers();

        for (int item: fibonacciNumbers) {
            System.out.println(item);
        }
    }

    public static int[] fibonacciNumbers() {
        int[] vs = new int[20];
        vs[0] = 1;
        vs[1] = 1;
        for (int i = 2; i < 20; i++) {
            vs[i] = vs[i - 1] + vs[i - 2];
        }

        return vs;
    }

}
