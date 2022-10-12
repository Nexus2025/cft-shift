package ru.cft.shift.task1;

public class Main {
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 32;

    public static void main(String[] args) {
        try {
            System.out.println("Enter size from " + MIN_SIZE + " to " + MAX_SIZE + " to build a multiplication table:");
            int size = ConsoleReader.readIntegerInRange(MIN_SIZE, MAX_SIZE);
            MultiplicationTable table = new MultiplicationTable(size);
            table.print();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}