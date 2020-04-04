package ru.spbstu.lab2.main.shapes;

/**
 * Абстрактное представление о геометрической фигуре.
 * <p>
 * Фигура (от лат. figura) — термин, формально применимый к
 * произвольному множеству точек; тем не менее обычно
 * фигурой называют замкнутые множества на плоскости,
 * которые ограничены конечным числом линий. При этом
 * допускаются вырождения, например: угол, луч и точка
 * считаются геометрическими фигурами.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%A4%D0%B8%D0%B3%D1%83%D1%80%D0%B0_(%D0%B3%D0%B5%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D1%8F)">Фигура (геометрия)</a>
 */
public interface Shape {

    static final double EPSILON = 1e-5;

    default void rotate(int angle)
    {
        // do nothing if rotation is unavailable;
    }

    double getArea();

    default int getRotation()
    {
        return 0;
    }

}
