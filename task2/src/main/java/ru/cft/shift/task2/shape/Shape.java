package ru.cft.shift.task2.shape;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Shape {
    protected final ShapeType type;
    protected final double area;
    protected final double perimeter;

    protected DecimalFormat decimalFormat;
    protected static final String FORMAT = "#.##";
    protected static final char DECIMAL_SEPARATOR = '.';
    protected static final String NEW_LINE = "\n";

    protected Shape(ShapeType type, double area, double perimeter) {
        this.type = type;
        this.area = area;
        this.perimeter = perimeter;
        this.decimalFormat = new DecimalFormat(FORMAT, getDecimalSeparator());
    }

    public String getContent() {
        return "Тип фигуры: " + type.getName() + NEW_LINE +
                "Площадь: " + decimalFormat.format(area) + " кв. мм" + NEW_LINE +
                "Периметр: " + decimalFormat.format(perimeter) + " мм" + NEW_LINE;
    }

    private DecimalFormatSymbols getDecimalSeparator() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(DECIMAL_SEPARATOR);

        return symbols;
    }

    public ShapeType getType() {
        return type;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }
}
