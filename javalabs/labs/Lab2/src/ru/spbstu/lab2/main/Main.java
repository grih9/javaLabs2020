package ru.spbstu.lab2.main;

import ru.spbstu.lab2.main.shapes.*;


public class Main {
    private static Shape findMaxShape(Shape[] shapes)
    {
        if (shapes.length == 0) {
            throw new IllegalCallerException("No shapes in array of Shape");
        }

        Shape maxShape = shapes[0];

        for (int i = 1; i < shapes.length; ++i) {
            if (shapes[i].getArea() > maxShape.getArea()) {
                maxShape = shapes[i];
            }
        }
        return maxShape;
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[10];

        try {
            shapes[0] = new Circle(new PointXY(2, 3), 2);
            shapes[1] = new Rectangle(new PointXY(3, 11), 11, 22);
            shapes[2] = new Rectangle(new PointXY(5, 2), 11);
            shapes[3] = new Rectangle(14);
            shapes[4] = new Circle(2);
            shapes[5] = new Triangle(new PointXY(1, 1), new PointXY(4, 5), new PointXY(7, 1));
            shapes[6] = new Circle(new PointXY(41,12), 41);
            shapes[7] = new Rectangle(1,3);
            shapes[8] = new Triangle(new PointXY(5, -2), new PointXY(-11, 4), new PointXY(18, 32));
            shapes[9] = new Triangle(new PointXY(0, 4), new PointXY(2, 3), new PointXY(1, 1));

            System.out.println("Area of max shape = " + findMaxShape(shapes).getArea());
            System.out.println("This shape is an instance of " + findMaxShape(shapes).getClass());

        } catch (IllegalArgumentException | IllegalCallerException p) {
            p.printStackTrace();
        }
    }
}
