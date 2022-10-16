package ru.cft.shift.task2.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    @DisplayName("Корректно считает площадь треугольника")
    public void calculateArea_returnsArea() {
        double expected = 15.55;

        double actual = Triangle.TriangleBuilderImpl.calculateArea(5.11, 6.77, 10.11, 21.99);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Корректно считает периметр треугольника")
    public void calculatePerimeter_returnsPerimeter() {
        double expected = 21.99;

        double actual = Triangle.TriangleBuilderImpl.calculatePerimeter(5.11, 6.77, 10.11);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Корректно считает угол треугольника")
    public void calculateCorner_returnsCorner() {
        double expected = 27.03;

        double actual = Triangle.TriangleBuilderImpl.calculateCorner(5.11, 6.77, 10.11);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Возвращает false если сторона треугольника меньше 1")
    public void validate_returnsFalse_ifSideLessZero() {
        boolean actual = Triangle.TriangleBuilderImpl.validate(5.11, 6.77, 0);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Возвращает true если если стороны треугольника от 1 и больше")
    public void validate_returnsTrue_ifSideGreaterZero() {
        boolean actual = Triangle.TriangleBuilderImpl.validate(5.11, 6.77, 10.11);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Возвращает false если треугольник нарушает теорему неравенства")
    public void checkTriangleEquality_returnsFalse() {
        boolean actual = Triangle.TriangleBuilderImpl.checkTriangleEquality(22.11, 5.11, 6.77);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Возвращает true если треугольник не нарушает теорему неравенства")
    public void checkTriangleEquality_returnsTrue() {
        boolean actual = Triangle.TriangleBuilderImpl.checkTriangleEquality(5.11, 6.77, 10.11);

        assertTrue(actual);
    }
}