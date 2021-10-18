package com.ifmo.lesson5;

public class SquareTriangle extends Triangle{

    public SquareTriangle(double bc, double cd) {

        super(Math.sqrt(bc * bc + cd * cd), bc, cd);
    }

    @Override
    public double square() {
        return  0.5 * bc * cd;
    }
}
