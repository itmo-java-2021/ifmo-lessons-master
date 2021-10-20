package com.ifmo.lesson6.task1.Operation;

public class Power implements Operation{
    @Override
    public int calculate(int x, int y) {
        return (int)Math.pow(x, y);
    }
}
