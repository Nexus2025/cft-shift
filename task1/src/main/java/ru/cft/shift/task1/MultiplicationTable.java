package ru.cft.shift.task1;

import ru.cft.shift.task1.exception.InvalidValueException;

public class MultiplicationTable {
    private final int size;
    private final int firstColumnLength;
    private final int otherColumnLength;
    private final StringBuilder numbers;
    private final StringBuilder separator;

    private static final String SPACE = " ";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String VERTICAL_LINE = "|";
    private static final String NEW_LINE = "\n";

    public MultiplicationTable(int size) throws InvalidValueException {
        if (size < 1) {
            throw new InvalidValueException("Error. Size must be greater then 0");
        }

        this.size = size;
        firstColumnLength = String.valueOf(size).length();
        otherColumnLength = String.valueOf(size * size).length();
        numbers = new StringBuilder();
        separator = new StringBuilder();
    }

    public void print() {
        fillSeparator();
        for (int lineIndex = 0; lineIndex <= size; lineIndex++) {
            fillFirstColumn(lineIndex);
            fillOtherColumns(lineIndex);
            System.out.println(numbers + NEW_LINE + separator);
            numbers.setLength(0);
        }
    }

    private void fillSeparator() {
        if (separator.isEmpty()) {
            separator.append(MINUS.repeat(firstColumnLength));
            for (int columnIndex = 1; columnIndex <= size; columnIndex++) {
                separator.append(PLUS);
                separator.append(MINUS.repeat(otherColumnLength));
            }
        }
    }

    private void fillFirstColumn(int lineIndex) {
        if (lineIndex == 0) {
            numbers.append(SPACE.repeat(firstColumnLength));
        } else {
            String value = String.valueOf(lineIndex);
            numbers.append(SPACE.repeat(firstColumnLength - value.length()));
            numbers.append(value);
        }
    }

    private void fillOtherColumns(int lineIndex) {
        for (int columnIndex = 1; columnIndex <= size; columnIndex++) {
            String value = (lineIndex == 0) ? String.valueOf(columnIndex) : String.valueOf(lineIndex * columnIndex);
            numbers.append(VERTICAL_LINE);
            numbers.append(SPACE.repeat(otherColumnLength - value.length()));
            numbers.append(value);
        }
    }
}
