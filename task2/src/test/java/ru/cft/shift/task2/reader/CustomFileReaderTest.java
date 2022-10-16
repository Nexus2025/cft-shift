package ru.cft.shift.task2.reader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cft.shift.task2.shape.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {

    @Test
    @DisplayName("Возвращает пустой Optional если передали null")
    public void createShape_returnsEmpty_ifPassNull() {
        Optional<Shape> actual = CustomFileReader.createShape(null, null);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Бросает ислючение если shapeType не валидный")
    public void createShape_throwsIllegalArgumentException_ifNotValidShapeType() {
        assertThrows(IllegalArgumentException.class,
                () -> CustomFileReader.createShape("not valid value", "params"));
    }

    @Test
    @DisplayName("Бросает ислючение если не валидное число для круга")
    public void createShape_throwsNumberFormatException_ifNotValidNumberForCircle() {
        assertThrows(NumberFormatException.class,
                () -> CustomFileReader.createShape("CIRCLE", "not valid number"));
    }

    @Test
    @DisplayName("Возвращает пустой Optional если число меньше 1 для круга")
    public void createShape_returnsEmpty_ifNumberLessZeroForCircle() {
        Optional<Shape> actual = CustomFileReader.createShape("CIRCLE", "0");
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Бросает ислючение если не валидные числа для прямоугольника")
    public void createShape_throwsIllegalArgumentException_ifNotValidNumbersForRectangle() {
        assertThrows(IllegalArgumentException.class,
                () -> CustomFileReader.createShape("RECTANGLE", "not valid numbers"));
    }

    @Test
    @DisplayName("Возвращает пустой Optional если число меньше 1 для прямоугольника")
    public void createShape_returnsEmpty_ifNumberLessZeroForRectangle() {
        Optional<Shape> actual = CustomFileReader.createShape("RECTANGLE", "10 0");

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Бросает ислючение если нет всех чисел для прямоугольника")
    public void createShape_throwsArrayIndexOutOfBoundsException_ifAbsentNumbersForRectangle() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> CustomFileReader.createShape("RECTANGLE", "0"));
    }

    @Test
    @DisplayName("Бросает ислючение если не валидные числа для треугольника")
    public void createShape_throwsIllegalArgumentException_ifNotValidNumbersForTriangle() {
        assertThrows(IllegalArgumentException.class,
                () -> CustomFileReader.createShape("TRIANGLE", "not valid numbers"));
    }

    @Test
    @DisplayName("Бросает ислючение если нет всех чисел для треугольника")
    public void createShape_throwsArrayIndexOutOfBoundsException_ifAbsentNumbersForTriangle() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> CustomFileReader.createShape("TRIANGLE", "0"));
    }

    @Test
    @DisplayName("Возвращает пустой Optional если число меньше 1 для треугольника")
    public void createShape_returnsEmpty_ifNumberLessZeroForTriangle() {
        Optional<Shape> actual = CustomFileReader.createShape("TRIANGLE", "10 10 0");

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает пустой Optional если нарушается теорема о неравенстве треугольника")
    public void createShape_returnsEmpty_ifNumberInequalityViolatedForTriangle() {
        Optional<Shape> actual = CustomFileReader.createShape("TRIANGLE", "10 10 21");

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Возвращает корректный круг")
    public void createShape_returnCircle_ifCorrectParams() {
        Circle actual = (Circle) CustomFileReader.createShape("CIRCLE", "5.2").get();

        assertEquals(ShapeType.CIRCLE, actual.getType());
        assertEquals(84.95, actual.getArea(), 0.01);
        assertEquals(32.67, actual.getPerimeter(), 0.01);
        assertEquals(5.2, actual.getRadius(), 0.01);
        assertEquals(10.4, actual.getDiameter(), 0.01);
    }

    @Test
    @DisplayName("Возвращает корректный прямоугольник")
    public void createShape_returnRectangle_ifCorrectParams() {
        Rectangle actual = (Rectangle) CustomFileReader.createShape("RECTANGLE", "10.2 5.3").get();

        assertEquals(ShapeType.RECTANGLE, actual.getType());
        assertEquals(54.06, actual.getArea(), 0.01);
        assertEquals(31, actual.getPerimeter(), 0.01);
        assertEquals(11.49, actual.getDiagonal(), 0.01);
        assertEquals(10.2, actual.getLargeSide(), 0.01);
        assertEquals(5.3, actual.getSmallSide(), 0.01);
    }

    @Test
    @DisplayName("Возвращает корректный треугольник")
    public void createShape_returnTriangle_ifCorrectParams() {
        Triangle actual = (Triangle) CustomFileReader.createShape("TRIANGLE", "10.2 5.3 11.3").get();

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