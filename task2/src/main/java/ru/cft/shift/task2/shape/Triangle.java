package ru.cft.shift.task2.shape;

import org.slf4j.Logger;
import ru.cft.shift.task2.logger.CustomLoggerFactory;

import java.util.Optional;

public class Triangle extends Shape {
    private final double firstSide;
    private final double secondSide;
    private final double thirdSide;
    private final double firstSideCorner;
    private final double secondSideCorner;
    private final double thirdSideCorner;

    private Triangle(double firstSide, double secondSide, double thirdSide, double firstSideCorner,
                    double secondSideCorner, double thirdSideCorner, double area, double perimeter) {

        super(ShapeType.TRIANGLE, area, perimeter);

        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
        this.firstSideCorner = firstSideCorner;
        this.secondSideCorner = secondSideCorner;
        this.thirdSideCorner = thirdSideCorner;
    }

    @Override
    public String getContent() {
        return super.getContent() +
                "Сторона 1: " + decimalFormat.format(firstSide) + " мм" + NEW_LINE +
                "Противолежащий угол стороны 1: " + decimalFormat.format(firstSideCorner) + " гр" + NEW_LINE +
                "Сторона 2: " + decimalFormat.format(secondSide) + " мм" + NEW_LINE +
                "Противолежащий угол стороны 2: " + decimalFormat.format(secondSideCorner) + " гр" + NEW_LINE +
                "Сторона 3: " + decimalFormat.format(thirdSide) + " мм" + NEW_LINE +
                "Противолежащий угол стороны 3: " + decimalFormat.format(thirdSideCorner) + " гр";
    }

    public double getFirstSide() {
        return firstSide;
    }

    public double getSecondSide() {
        return secondSide;
    }

    public double getThirdSide() {
        return thirdSide;
    }

    public double getFirstSideCorner() {
        return firstSideCorner;
    }

    public double getSecondSideCorner() {
        return secondSideCorner;
    }

    public double getThirdSideCorner() {
        return thirdSideCorner;
    }

    public static class TriangleBuilderImpl implements ShapeBuilder {
        private static final Logger log = CustomLoggerFactory.getLogger(Triangle.TriangleBuilderImpl.class.getName());
        private final double firstSide;
        private final double secondSide;
        private final double thirdSide;

        public TriangleBuilderImpl(double firstSide, double secondSide, double thirdSide) {
            this.firstSide = firstSide;
            this.secondSide = secondSide;
            this.thirdSide = thirdSide;
        }

        @Override
        public Optional<Shape> build() {
            if (!validate(firstSide, secondSide, thirdSide)) {
                return Optional.empty();
            }

            double firstSideCorner = calculateCorner(firstSide, secondSide, thirdSide);
            double secondSideCorner = calculateCorner(secondSide, firstSide, thirdSide);
            double thirdSideCorner = calculateCorner(thirdSide, secondSide, firstSide);
            double perimeter = calculatePerimeter(firstSide, secondSide, thirdSide);
            double area = calculateArea(firstSide, secondSide, thirdSide, perimeter);

            return Optional.of(new Triangle(firstSide, secondSide, thirdSide, firstSideCorner, secondSideCorner,
                    thirdSideCorner, area, perimeter));
        }

        static double calculateArea(double firstSide, double secondSide, double thirdSide, double perimeter) {
            double semiPerimeter = perimeter / 2;
            return Math.sqrt(semiPerimeter * (semiPerimeter - firstSide) * (semiPerimeter - secondSide)
                    * (semiPerimeter - thirdSide));
        }

        static double calculatePerimeter(double firstSide, double secondSide, double thirdSide) {
            return firstSide + secondSide + thirdSide;
        }

        static double calculateCorner(double oppositeSide, double leftSide, double rightSide) {
            return Math.toDegrees(Math.acos(((leftSide * leftSide) + (rightSide * rightSide) -
                    (oppositeSide * oppositeSide)) / (2 * leftSide * rightSide)));
        }

        static boolean validate(double firstSide, double secondSide, double thirdSide) {
            if (firstSide <= 0 || secondSide <= 0 || thirdSide <= 0) {
                log.error("All value mast be positive. Your values: " + secondSide + ","
                        + firstSide + "," + thirdSide);
                return false;
            }

            if (!checkTriangleEquality(firstSide, secondSide, thirdSide)) {
                return false;
            } else if (!checkTriangleEquality(secondSide, firstSide, thirdSide)) {
                return false;
            } else {
                return (checkTriangleEquality(thirdSide, firstSide, secondSide));
            }
        }

        static boolean checkTriangleEquality(double verifiedSide, double oneSide, double twoSide) {
            if (verifiedSide >= oneSide + twoSide) {
                log.error("The triangle failed the inequality test. Your values: " + verifiedSide + ","
                        + oneSide + "," + twoSide);
                return false;
            }
            return true;
        }
    }
}
