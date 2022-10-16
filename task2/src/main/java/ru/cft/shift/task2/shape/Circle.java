package ru.cft.shift.task2.shape;

import org.slf4j.Logger;
import ru.cft.shift.task2.logger.CustomLoggerFactory;

import java.util.Optional;

public class Circle extends Shape {
    private final double radius;
    private final double diameter;

    private Circle(double radius, double diameter, double area, double perimeter) {
        super(ShapeType.CIRCLE, area, perimeter);
        this.radius = radius;
        this.diameter = diameter;
    }

    @Override
    public String getContent() {
        return super.getContent() +
                "Радиус: " + decimalFormat.format(radius) + " мм" + NEW_LINE +
                "Диаметр: " + decimalFormat.format(diameter) + " мм";
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return diameter;
    }

    public static class CircleBuilderImpl implements ShapeBuilder {
        private static final Logger log = CustomLoggerFactory.getLogger(Circle.CircleBuilderImpl.class.getName());
        private final double radius;

        public CircleBuilderImpl(double radius) {
            this.radius = radius;
        }

        @Override
        public Optional<Shape> build() {
            if (!validate(radius)) {
                return Optional.empty();
            }

            double diameter = calculateDiameter(radius);
            double area = calculateArea(radius);
            double perimeter = calculatePerimeter(radius);

            return Optional.of(new Circle(radius, diameter, area, perimeter));
        }

        static double calculateArea(double radius) {
            return Math.PI * radius * radius;
        }

        static double calculatePerimeter(double radius) {
            return 2 * Math.PI * radius;
        }

        static double calculateDiameter(double radius) {
            return radius * 2;
        }

        static boolean validate(double radius) {
            if (radius <= 0) {
                log.error("Radius must be greater than 0. Your value: " + radius);
                return false;
            }

            return true;
        }
    }
}