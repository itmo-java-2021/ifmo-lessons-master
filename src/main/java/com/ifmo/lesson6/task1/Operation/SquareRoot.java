package com.ifmo.lesson6.task1.Operation;

public class SquareRoot implements Operation{
    @Override
    public int calculate(int x, int y) {
        return (int)Math.sqrt(x);
    }
}
