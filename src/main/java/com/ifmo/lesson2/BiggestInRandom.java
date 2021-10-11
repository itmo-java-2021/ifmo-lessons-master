package com.ifmo.lesson2;

import java.util.Random;

public class BiggestInRandom {
    /*
     Создать программу, выводящую на экран случайно сгенерированное трёхзначное
     натуральное число и его наибольшую цифру.Примеры работы программы:
     В числе 208 наибольшая цифра 8.
     В числе 774 наибольшая цифра 7.
     В числе 613 наибольшая цифра 6.
     */
    public static void main(String[] args) {
        int rnd = threeDigitRandom();

        String largestDigit = largestDigit(rnd);

        System.out.println(largestDigit);
    }

    public static int threeDigitRandom() {
        Random random = new Random();
        return random.nextInt(899) + 100;
    }

    public static String largestDigit(int rnd) {
        int max = 0;
        String str = Integer.toString(rnd);

        for (int i = 0; i < str.length(); i++){
            int x = Character.getNumericValue(str.charAt(i));
            if (x > max) max = x;
        }

        return "В числе " + rnd + " наибольшая цифра " + max + ".";
    }
}
