package com.ifmo.lesson6.task1.Operation;

public class AccumulatorArray {
    private int[] value;
    private Operation[] operation;

    public AccumulatorArray(Operation ... operation) {
        this(new int[operation.length], operation);
    }

    public AccumulatorArray(int[] value, Operation ... operation) {
        this.value = value;
        this.operation = operation;
    }

    public int[] getValue(){
        return value;
    }

    public void accumulator(int y){
        for (int i = 0; i < value.length; i++) {
            value[i] = operation[i].calculate(value[i], y);
        }
    }
}
