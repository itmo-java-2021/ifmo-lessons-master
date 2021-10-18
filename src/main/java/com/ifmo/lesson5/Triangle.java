package com.ifmo.lesson5;

public class Triangle extends Shape {
    double ab;
    double bc;
    double cd;

    public Triangle(double ab, double bc, double cd) {
        this.ab = ab;
        this.bc = bc;
        this.cd = cd;
    }

    @Override
    public double square() {
        double p = (ab + bc + cd) / 2;
        double s = Math.sqrt(p * (p - ab) * (p - bc) * (p - cd));
        return s;
    }
}
