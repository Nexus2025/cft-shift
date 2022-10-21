package ru.cft.shift.task2.shape;

import org.slf4j.Logger;
import ru.cft.shift.task2.logger.CustomLoggerFactory;

import java.util.Optional;

public class Rectangle extends Shape {
    private final double largeSide;
    private final double smallSide;
    private final double diagonal;

    private Rectangle(double largeSide, double smallSide, double diagonal, double area, double perimeter) {
        super(ShapeType.RECTANGLE, area, perimeter);
        this.largeSide = largeSide;
        this.smallSide = smallSide;
        this.diagonal = diagonal;
    }

    @Override
    public String getContent() {
        return super.getContent() +
                "Длина диагонали: " + decimalFormat.format(diagonal) + " мм" + NEW_LINE +
                "Длина (размер длинной стороны): " + decimalFormat.format(largeSide) + " мм" + NEW_LINE +
                "Ширина (размер короткой стороны): " + decimalFormat.format(smallSide) + " мм";
    }

    public double getLargeSide() {
        return largeSide;
    }

    public double getSmallSide() {
        return smallSide;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public static class RectangleBuilderImpl implements ShapeBuilder {
        private static final Logger log = CustomLoggerFactory.getLogger(Rectangle.RectangleBuilderImpl.class.getName());
        private static final String SEPARATOR = " ";

        private final String params;

        public RectangleBuilderImpl(String params) {
            this.params = params;
        }

        @Override
        public Optional<Shape> build() {
            double oneSide;
            double secondSide;
            try {
                String[] values = params.split(SEPARATOR);
                oneSide = Double.parseDouble(values[0]);
                secondSide = Double.parseDouble(values[1]);
            } catch (NumberFormatException e) {
                log.error("Wrong type of parameters for Rectangle: \"" + params + "\". Must be numbers");
                return Optional.empty();
            } catch (ArrayIndexOutOfBoundsException e) {
                log.error("Wrong amount of parameters for Rectangle: \"" + params + "\". Must be 2 parameters");
                return Optional.empty();
            }

            if (!validate(oneSide, secondSide)) {
                return Optional.empty();
            }

            double diagonal = calculateDiagonal(oneSide, secondSide);
            double area = calculateArea(oneSide, secondSide);
            double perimeter = calculatePerimeter(oneSide, secondSide);

            double largeSide;
            double smallSide;
            if ((oneSide - secondSide) >= 0.01) {
                largeSide = oneSide;
                smallSide = secondSide;
            } else {
                largeSide = secondSide;
                smallSide = oneSide;
            }

            return Optional.of(new Rectangle(largeSide, smallSide, diagonal, area, perimeter));
        }

        static double calculateArea(double firstSide, double secondSide) {
            return firstSide * secondSide;
        }

        static double calculatePerimeter(double firstSide, double secondSide) {
            return 2 * (firstSide + secondSide);
        }

        static double calculateDiagonal(double firstSide, double secondSide) {
            return Math.sqrt((firstSide * firstSide) + (secondSide * secondSide));
        }

        static boolean validate(double firstSide, double secondSide) {
            if (firstSide <= 0 || secondSide <= 0) {
                log.error("Value mast be positive. Your values: " + firstSide + ", " + secondSide);
                return false;
            }

            return true;
        }
    }
}
