package ru.spbstu.lab2.main.shapes;

/**
 * Представление о треугольнике.
 * <p>
 * Треуго́льник (в евклидовом пространстве) — геометрическая
 * фигура, образованная тремя отрезками, которые соединяют
 * три точки, не лежащие на одной прямой. Указанные три
 * точки называются вершинами треугольника, а отрезки —
 * сторонами треугольника. Часть плоскости, ограниченная
 * сторонами, называется внутренностью треугольника: нередко
 * треугольник рассматривается вместе со своей внутренностью
 * (например, для определения понятия площади).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%A2%D1%80%D0%B5%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Треугольник</a>
 */
public class Triangle implements Polygon {
    private PointXY a;
    private PointXY b;
    private PointXY c;

    private int rotationAngle;

    public Triangle(PointXY a, PointXY b, PointXY c)
    {
        if (isEqualOrGreater(getSide(a, b), getSide(a, c) + getSide(b, c)) ||
                isEqualOrGreater(getSide(a, c) ,getSide(b, c) + getSide(a, b)) ||
                isEqualOrGreater(getSide(b, c), getSide(a, b) + getSide(a, c))) {

            throw new IllegalArgumentException("Invalid points of triangle!");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void rotate(int angle)
    {
        rotationAngle += angle;
        rotationAngle %= 360;

        a = new PointXY(a.getX() * Math.cos(angle) - a.getY() * Math.sin(angle),
                        a.getX() * Math.sin(angle) + a.getY() * Math.cos(angle));
        b = new PointXY(b.getX() * Math.cos(angle) - b.getY() * Math.sin(angle),
                        b.getX() * Math.sin(angle) + b.getY() * Math.cos(angle));
        c = new PointXY(c.getX() * Math.cos(angle) - c.getY() * Math.sin(angle),
                        c.getX() * Math.sin(angle) + c.getY() * Math.cos(angle));
    }

    @Override
    public double getArea()
    {
        double semiPerimeter = getPerimeter() / 2;
        double aSide = getSide(a, b);
        double bSide = getSide(a, c);
        double cSide = getSide(c, b);
        return Math.sqrt(semiPerimeter * (semiPerimeter - aSide) * (semiPerimeter - bSide) * (semiPerimeter - cSide));
    }

    @Override
    public int getRotation()
    {
        return rotationAngle;
    }

    @Override
    public double getPerimeter()
    {
        return getSide(a, b) + getSide(a, c) + getSide(c, b);
    }

    private double getSide(PointXY point1, PointXY point2)
    {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }

    private boolean isEqualOrGreater(double a, double b)
    {
        return (Math.abs(a - b) <= EPSILON | a > b) ;
    }
}
