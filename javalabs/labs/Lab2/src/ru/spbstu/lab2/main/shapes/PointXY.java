package ru.spbstu.lab2.main.shapes;

public class PointXY implements Point {

    private double x;
    private double y;

    public PointXY() {
        this(0, 0);
    }

    public PointXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getArea() {
        return 0;
    }

}
