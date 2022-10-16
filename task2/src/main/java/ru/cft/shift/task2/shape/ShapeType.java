package ru.cft.shift.task2.shape;

public enum ShapeType {
    TRIANGLE("Треугольник"), CIRCLE("Круг"), RECTANGLE("Прямоугольник");

    private final String name;

    ShapeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
