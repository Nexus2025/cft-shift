package ru.cft.shift.task1;

import ru.cft.shift.task1.exception.InputException;
import ru.cft.shift.task1.exception.InvalidValueException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {
    private ConsoleReader() {
    }

    public static int readIntegerInRange(int min, int max) throws InputException, InvalidValueException {
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

        if (number < min || number > max) {
            throw new InvalidValueException("Error: You need to enter value from " + min + " to " + max);
        }

        return number;
    }
}
