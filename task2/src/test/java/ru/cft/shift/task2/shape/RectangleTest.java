package ru.cft.shift.task2.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    @DisplayName("Корректно считает площадь прямоугольника")
    public void calculateArea_returnsArea() {
        double expected = 34.59;

        double actual = Rectangle.RectangleBuilderImpl.calculateArea(5.11, 6.77);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Корректно считает периметр прямоугольника")
    public void calculatePerimeter_returnsPerimeter() {
        double expected = 23.76;

        double actual = Rectangle.RectangleBuilderImpl.calculatePerimeter(5.11, 6.77);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Корректно считает диагональ прямоугольника")
    public void calculateDiagonal_returnsDiagonal() {
        double expected = 8.48;

        double actual = Rectangle.RectangleBuilderImpl.calculateDiagonal(5.11, 6.77);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Возвращает false если сторона прямоугольника меньше 1")
    public void validate_returnsFalse_ifSideLessZero() {
        boolean actual = Rectangle.RectangleBuilderImpl.validate(0, 6.77);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Возвращает true если если стороны прямоугольника от 1 и больше")
    public void validate_returnsTrue_ifSideGreaterZero() {
        boolean actual = Rectangle.RectangleBuilderImpl.validate(5.11, 6.77);

        assertTrue(actual);
    }
}