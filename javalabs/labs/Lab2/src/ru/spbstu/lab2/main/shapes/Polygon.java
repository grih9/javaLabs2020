package ru.spbstu.lab2.main.shapes;

/**
 * Абстрактное представление о многоугольнике.
 * <p>
 * Многоуго́льник — это геометрическая фигура, обычно
 * определяемая как часть плоскости, ограниченная замкнутой
 * ломаной.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9C%D0%BD%D0%BE%D0%B3%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Многоугольник</a>
 */
public interface Polygon extends Shape {

    double getPerimeter();

}
