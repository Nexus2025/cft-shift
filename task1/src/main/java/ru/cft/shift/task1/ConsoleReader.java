package ru.cft.shift.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ConsoleReader {
    private ConsoleReader() {
    }

    public static Optional<Integer> readIntegerInRange(int min, int max) {
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter size from " + min + " to " + max + " to build a multiplication table:");
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error: Can't read a value. Some problem with input system");
            return Optional.empty();
        }

        int number;
        try {
            number = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + line + "\" is not a number");
            return Optional.empty();
        }

        if (number < min || number > max) {
            System.out.println("Error: You need to enter value from " + min + " to " + max);
            return Optional.empty();
        }

        return Optional.of(number);
    }
}
