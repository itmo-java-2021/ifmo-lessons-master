package com.ifmo.lesson5;

public class Rectangle extends Shape {
    int ab;
    int bc;
    int cd;
    int da;

    public Rectangle(int ab, int bc) {
        this.ab = ab;
        this.bc = bc;
        this.cd = ab;
        this.da = bc;
    }

    @Override
    public double square() {
        return ab * bc;
    }
}
