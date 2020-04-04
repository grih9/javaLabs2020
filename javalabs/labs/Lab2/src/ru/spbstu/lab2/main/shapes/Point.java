package ru.spbstu.lab2.main.shapes;

/**
 * Абстрактное представление о точке.
 * <p>
 * То́чка — абстрактный объект в пространстве, не имеющий
 * никаких измеримых характеристик (нульмерный объект).
 * Точка является одним из фундаментальных понятий в
 * математике.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%A2%D0%BE%D1%87%D0%BA%D0%B0_(%D0%B3%D0%B5%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D1%8F)">Точка</a>
 */
interface Point extends Shape {

    double getX();
    double getY();

}
