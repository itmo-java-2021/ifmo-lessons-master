package com.ifmo.lesson6.task1.Operation;

public class Accumulator {
    private int value;
    private Operation operation;

    public Accumulator(Operation operation) {
        this(0, operation);
    }

    public Accumulator(int value, Operation operation) {
        this.value = value;
        this.operation = operation;
    }

    public int getValue(){
        return value;
    }

    public void accumulator(int y){
        value = operation.calculate(value, y);
    }
}
