package com.ifmo.lesson6.task1.Operation;

public class Mod implements Operation{
    @Override
    public int calculate(int x, int y) {
        return x % y;
    }
}
