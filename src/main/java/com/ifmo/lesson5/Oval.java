package com.ifmo.lesson5;

public class Oval extends Shape {

    int r1;
    int r2;

    public Oval(int r1, int r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public double square() {

        return r1 * r2 * Math.PI;
    }
}
