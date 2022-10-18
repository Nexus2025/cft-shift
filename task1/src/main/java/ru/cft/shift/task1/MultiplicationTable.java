package ru.cft.shift.task1;

public class MultiplicationTable {
    private final int size;

    private static final String SPACE = " ";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String VERTICAL_LINE = "|";
    private static final String NEW_LINE = "\n";

    public MultiplicationTable(int size){
        this.size = size;
    }

    public void print() {
        int firstColumnLength = String.valueOf(size).length();
        int otherColumnLength = String.valueOf(size * size).length();
        StringBuilder numbers = new StringBuilder();
        StringBuilder separator = new StringBuilder();

        fillSeparator(separator, firstColumnLength, otherColumnLength);
        for (int lineIndex = 0; lineIndex <= size; lineIndex++) {
            fillFirstColumn(numbers, lineIndex, firstColumnLength);
            fillOtherColumns(numbers, lineIndex, otherColumnLength);
            System.out.println(numbers + NEW_LINE + separator);
            numbers.setLength(0);
        }
    }

    private void fillSeparator(StringBuilder separator, int firstColumnLength, int otherColumnLength) {
        if (separator.isEmpty()) {
            separator.append(MINUS.repeat(firstColumnLength));
            for (int columnIndex = 1; columnIndex <= size; columnIndex++) {
                separator.append(PLUS);
                separator.append(MINUS.repeat(otherColumnLength));
            }
        }
    }

    private void fillFirstColumn(StringBuilder numbers, int lineIndex, int firstColumnLength) {
        if (lineIndex == 0) {
            numbers.append(SPACE.repeat(firstColumnLength));
        } else {
            String value = String.valueOf(lineIndex);
            numbers.append(SPACE.repeat(firstColumnLength - value.length()));
            numbers.append(value);
        }
    }

    private void fillOtherColumns(StringBuilder numbers, int lineIndex, int otherColumnLength) {
        for (int columnIndex = 1; columnIndex <= size; columnIndex++) {
            String value = (lineIndex == 0) ? String.valueOf(columnIndex) : String.valueOf(lineIndex * columnIndex);
            numbers.append(VERTICAL_LINE);
            numbers.append(SPACE.repeat(otherColumnLength - value.length()));
            numbers.append(value);
        }
    }
}
