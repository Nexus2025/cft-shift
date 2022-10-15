package ru.cft.shift.task1;

import java.util.Optional;

public class Main {
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 32;

    public static void main(String[] args) {
        Optional<Integer> optionalSize = ConsoleReader.readIntegerInRange(MIN_SIZE, MAX_SIZE);
        optionalSize.ifPresent(size -> new MultiplicationTable(size).print());
    }
}