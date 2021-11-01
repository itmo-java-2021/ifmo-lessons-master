package com.ifmo.lesson6;

import com.ifmo.lesson6.task1.Operation.Accumulator;
import com.ifmo.lesson6.task1.Operation.Plus;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        Accumulator accumulator = new Accumulator(new Plus());

        System.out.println(accumulator.getValue());

        accumulator.accumulator(10);

        System.out.println(accumulator.getValue());




        int[] vs = new int[10];
    }
}
