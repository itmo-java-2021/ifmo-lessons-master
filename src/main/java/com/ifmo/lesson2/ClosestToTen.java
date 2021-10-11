package com.ifmo.lesson2;

public class ClosestToTen {
    /*
     Создать программу, выводящую на экран ближайшее к 10 из двух чисел, записанных в
     переменные m и n. Например, среди чисел 8,5 и 11,45 ближайшее к десяти 11,45.
     */
    public static void main(String[] args) {
        float m = 8.5f;
        float n = 11.45f;

        float closestToTen = closestToTen(m, n);

        System.out.println(closestToTen);
    }

    public static float closestToTen(float m, float n) {
        // m = -5, n = 16

        float m1 = Math.abs(10 - m); // 1.5
        float n1 = Math.abs(10 - n); // -1.45

        if (m1 <= n1){
            return m;
        } else if (m1 > n1){
            return  n;
        } else {
            return 0;
        }
    }
}
