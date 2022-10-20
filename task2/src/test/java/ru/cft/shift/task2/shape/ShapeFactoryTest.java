package ru.cft.shift.task2.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShapeFactoryTest {
    @Test
    @DisplayName("Возвращает пустой Optional если передали null")
    public void create_returnsEmpty_ifPassNull() {
        Optional<Shape> actual = ShapeFactory.create(null);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если shapeType не валидный")
    public void create_returnsEmpty_ifNotValidShapeType() {
        List<String> data = new ArrayList<>();
        data.add("not valid shape type");
        data.add("5 5 5");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если размер data не равен 2")
    public void create_returnsEmpty_ifDataSizeNotEqualTwo() {
        Optional<Shape> actual = ShapeFactory.create(new ArrayList<>());

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если не валидное число для круга")
    public void create_returnsEmpty_ifNotValidNumberForCircle() {
        List<String> data = new ArrayList<>();
        data.add("CIRCLE");
        data.add("not valid number");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если число меньше 1 для круга")
    public void create_returnsEmpty_ifNumberLessZeroForCircle() {
        List<String> data = new ArrayList<>();
        data.add("CIRCLE");
        data.add("0");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если не валидные числа для прямоугольника")
    public void create_returnsEmpty_ifNotValidNumbersForRectangle() {
        List<String> data = new ArrayList<>();
        data.add("RECTANGLE");
        data.add("not valid numbers");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если число меньше 1 для прямоугольника")
    public void create_returnsEmpty_ifNumberLessZeroForRectangle() {
        List<String> data = new ArrayList<>();
        data.add("RECTANGLE");
        data.add("10 0");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если нет всех чисел для прямоугольника")
    public void create_returnsEmpty_ifAbsentNumbersForRectangle() {
        List<String> data = new ArrayList<>();
        data.add("RECTANGLE");
        data.add("0");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если не валидные числа для треугольника")
    public void create_returnsEmpty_ifNotValidNumbersForTriangle() {
        List<String> data = new ArrayList<>();
        data.add("TRIANGLE");
        data.add("not valid numbers");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если нет всех чисел для треугольника")
    public void create_returnsEmpty_ifAbsentNumbersForTriangle() {
        List<String> data = new ArrayList<>();
        data.add("TRIANGLE");
        data.add("0");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если есть число меньше 1 для треугольника")
    public void create_returnsEmpty_ifNumberLessZeroForTriangle() {
        List<String> data = new ArrayList<>();
        data.add("TRIANGLE");
        data.add("10 10 0");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если нарушается теорема о неравенстве треугольника")
    public void create_returnsEmpty_ifNumberInequalityViolatedForTriangle() {
        List<String> data = new ArrayList<>();
        data.add("TRIANGLE");
        data.add("10 10 21");

        Optional<Shape> actual = ShapeFactory.create(data);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает корректный круг")
    public void create_returnCircle_ifCorrectParams() {
        List<String> data = new ArrayList<>();
        data.add("CIRCLE");
        data.add("5.2");

        Circle actual = (Circle) ShapeFactory.create(data).get();

        assertEquals(ShapeType.CIRCLE, actual.getType());
        assertEquals(84.95, actual.getArea(), 0.01);
        assertEquals(32.67, actual.getPerimeter(), 0.01);
        assertEquals(5.2, actual.getRadius(), 0.01);
        assertEquals(10.4, actual.getDiameter(), 0.01);
    }

    @Test
    @DisplayName("Возвращает корректный прямоугольник")
    public void create_returnRectangle_ifCorrectParams() {
        List<String> data = new ArrayList<>();
        data.add("RECTANGLE");
        data.add("10.2 5.3");

        Rectangle actual = (Rectangle) ShapeFactory.create(data).get();

        assertEquals(ShapeType.RECTANGLE, actual.getType());
        assertEquals(54.06, actual.getArea(), 0.01);
        assertEquals(31, actual.getPerimeter(), 0.01);
        assertEquals(11.49, actual.getDiagonal(), 0.01);
        assertEquals(10.2, actual.getLargeSide(), 0.01);
        assertEquals(5.3, actual.getSmallSide(), 0.01);
    }

    @Test
    @DisplayName("Возвращает корректный треугольник")
    public void create_returnTriangle_ifCorrectParams() {
        List<String> data = new ArrayList<>();
        data.add("TRIANGLE");
        data.add("10.2 5.3 11.3");

        Triangle actual = (Triangle) ShapeFactory.create(data).get();

        assertEquals(ShapeType.TRIANGLE, actual.getType());
        assertEquals(27.01, actual.getArea(), 0.01);
        assertEquals(26.8, actual.getPerimeter(), 0.01);
        assertEquals(10.2, actual.getFirstSide(), 0.01);
        assertEquals(64.41, actual.getFirstSideCorner(), 0.01);
        assertEquals(5.3, actual.getSecondSide(), 0.01);
        assertEquals(27.95, actual.getSecondSideCorner(), 0.01);
        assertEquals(11.3, actual.getThirdSide(), 0.01);
        assertEquals(87.65, actual.getThirdSideCorner(), 0.01);
    }
}