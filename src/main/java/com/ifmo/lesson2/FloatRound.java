package com.ifmo.lesson2;

public class FloatRound {
    /*
    В переменной n хранится вещественное число с ненулевой дробной частью.
    Создайте программу, округляющую число n до ближайшего целого и выводящую результат на экран.
     */
    public static void main(String[] args) {
        float n = -1.550437F;
        float rounded = round(n);
        System.out.println(rounded);
    }

    public static float round(float n) {
        float x = n%1;
        float result;
        if (x >= 0.5){
            result = (int) n + 1;
        } else if (x <= -0.5){
            result = (int) n - 1;
        } else  {
            result = (int)n;
        }
        return result;
    }
}
