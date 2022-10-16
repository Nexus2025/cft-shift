package ru.cft.shift.task2.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    @DisplayName("Корректно считает площадь круга")
    public void calculateArea_returnsArea() {
        double expected = 82.03;

        double actual = Circle.CircleBuilderImpl.calculateArea(5.11);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Корректно считает периметр круга")
    public void calculatePerimeter_returnsPerimeter() {
        double expected = 32.11;

        double actual = Circle.CircleBuilderImpl.calculatePerimeter(5.11);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Корректно считает диаметр круга")
    public void calculatePerimeter_returnsDiameter() {
        double expected = 10.22;

        double actual = Circle.CircleBuilderImpl.calculateDiameter(5.11);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Возвращает false если радиус круга меньше 1")
    public void validate_returnsFalse_ifRadiusLessZero() {
        boolean actual = Circle.CircleBuilderImpl.validate(0);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Возвращает true если радиус круга от 1 и больше")
    public void validate_returnsTrue_ifRadiusGreaterZero() {
        boolean actual = Circle.CircleBuilderImpl.validate(1);

        assertTrue(actual);
    }
}