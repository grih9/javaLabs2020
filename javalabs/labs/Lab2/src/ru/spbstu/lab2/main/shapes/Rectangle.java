package ru.spbstu.lab2.main.shapes;

/**
 * Представление о прямоугольнике.
 * <p>
 * Прямоугольник — четырехугольник, у которого все углы
 * прямые (равны 90 градусам).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D1%8F%D0%BC%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Прямоугольник</a>
 */
public class Rectangle implements Polygon  {

    private PointXY centre;

    private double width;
    private double height;

    private int rotationAngle;

    public Rectangle(PointXY centre, double width, double height)
    {
        if ((width < 0) || (height < 0)) {
            throw new IllegalArgumentException("Width and height must be positive numbers!");
        }

        this.centre = centre;
        this.width = width;
        this.height = height;
        rotationAngle = 0;
    }

    public Rectangle(PointXY centre, double side)
    {
       this(centre, side, side);
    }

    public Rectangle(double width, double height)
    {
        this(new PointXY(), width, height);
    }

    public Rectangle(double side)
    {
        this(new PointXY(), side, side);
    }

    @Override
    public void rotate(int angle)
    {
        rotationAngle += angle;
        rotationAngle %= 360;
    }

    @Override
    public double getPerimeter()
    {
        return 2 * width + 2 * height;
    }

    @Override
    public double getArea()
    {
        return width * height;
    }

    @Override
    public int getRotation()
    {
        return rotationAngle;
    }

    public PointXY getCentre()
    {
        return centre;
    }

    /*
     * TODO: Реализовать класс 'Rectangle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */
}
