package com.ifmo.lesson2;

public class IntsOrdering {
    /*
     В три переменные a, b и c явно записаны программистом три целых попарно неравных
     между собой числа. Создать программу, которая переставит числа в переменных таким
     образом, чтобы при выводе на экран последовательность a, b и c оказалась строго
     возрастающей.

     Числа в переменных a, b и c: 3, 9, -1
     Возрастающая последовательность: -1, 3, 9

     Числа в переменных a, b и c: 2, 4, 3
     Возрастающая последовательность: 2, 3, 4

     Числа в переменных a, b и c: 7, 0, -5
     Возрастающая последовательность: -5, 0, 7
     */
    public static void main(String[] args) {
        int a = 3, b = 9, c = -1;

        String ordering = ordering(a, b, c);

        System.out.println(ordering);
    }

    public static String ordering(int a, int b, int c) {
        int[] vs = new int[3];
        vs[0] = a;
        vs[1] = b;
        vs[2] = c;
        for (int i = 0; i < vs.length; i++) {
            for (int j = 0; j < vs.length - i - 1; j++) {
                if (vs[j] > vs[j + 1]) {
                    int x = vs[j];
                    vs[j] = vs[j + 1];
                    vs[j + 1] = x;
                }
            }
        }

        /*
        String str = "";
        for (int i = 0; i < vs.length; i++) {
            if (i == 0){
                str += vs[i];
            } else {
                str += ", " + vs[i];
            }
        }
        */

        return "Числа в переменных a, b и c: " + a + ", " + b + ", " + c + "\n" +
                "Возрастающая последовательность: " + vs[0] + ", " + vs[1] + ", " + vs[2];
    }
}
