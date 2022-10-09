package ru.cft.shift.task1;

import ru.cft.shift.task1.exception.InputException;
import ru.cft.shift.task1.exception.InvalidValueException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int minSize = 1;
    private static final int maxSize = 32;

    public static void main(String[] args) {
        try {
            System.out.println("Enter size from 1 to 32 to build a multiplication table:");
            int size = readInteger();
            buildTable(size);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void buildTable(int size) {
        int firstColumnLength = String.valueOf(size).length();
        int otherColumnLength = String.valueOf(size * size).length();

        StringBuilder numbers = new StringBuilder();
        StringBuilder separator = new StringBuilder();
        for (int i = 0; i <= size; i++) {
            //fill first column
            if (i == 0) {
                numbers.append(" ".repeat(firstColumnLength));
            } else {
                String value = String.valueOf(i);
                numbers.append(" ".repeat(firstColumnLength - value.length()));
                numbers.append(value);
            }

            //fill other columns
            for (int j = 1; j <= size; j++) {
                String value = (i == 0) ? String.valueOf(j) : String.valueOf(i * j);
                numbers.append("|");
                numbers.append(" ".repeat(otherColumnLength - value.length()));
                numbers.append(value);
            }

            //fill separator's line
            if (separator.isEmpty()) {
                separator.append("-".repeat(firstColumnLength));
                for (int j = 1; j <= size; j++) {
                    separator.append("+");
                    separator.append("-".repeat(otherColumnLength));
                }
            }

            System.out.println(numbers + "\n" + separator);
            numbers.setLength(0);
        }
    }

    private static int readInteger() throws InputException, InvalidValueException {
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine();
        } catch (IOException e) {
            throw new InputException("Error: Can't read a value. Some problem with input system");
        }

        int number;
        try {
            number = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new InvalidValueException("Error: \"" + line + "\" is not a number");
        }

        if (number < minSize || number > maxSize) {
            throw new InvalidValueException("Error: You need to enter size from 1 to 32");
        }

        return number;
    }
}