package com.harel.practise.inteviews.evercompliant.model;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceFrom(Coordinate coordinate) {
        return Math.sqrt(
                Math.pow(this.x - coordinate.getX(), 2) +Math.pow(this.y - coordinate.getY(), 2));
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
