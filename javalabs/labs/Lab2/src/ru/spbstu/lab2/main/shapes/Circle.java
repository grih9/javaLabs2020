package ru.spbstu.lab2.main.shapes;

/**
 * Представление об окружности.
 * <p>
 * Окру́жность — замкнутая плоская кривая, которая состоит из
 * всех точек на плоскости, равноудалённых от заданной точки.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9E%D0%BA%D1%80%D1%83%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D1%8C">Окружность</a>
 */
public class Circle implements Ellipse {

    private PointXY centre;
    private double radius;

    public Circle(double radius)
    {
        this(new PointXY(), radius);
    }

    public Circle(PointXY centre, double radius)
    {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be a positive number!");
        }

        this.centre = centre;
        this.radius = radius;
    }

    @Override
    public double getLength()
    {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea()
    {
        return Math.PI * Math.pow(radius, 2);
    }

    public PointXY getCentre()
    {
        return centre;
    }

    public double getRadius()
    {
        return radius;
    }
}
