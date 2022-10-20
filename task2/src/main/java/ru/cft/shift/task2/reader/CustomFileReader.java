package ru.cft.shift.task2.reader;

import org.slf4j.Logger;
import ru.cft.shift.task2.logger.CustomLoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CustomFileReader {
    private static final Logger log = CustomLoggerFactory.getLogger(CustomFileReader.class.getName());

    private CustomFileReader() {
    }

    public static Collection<String> readLines(String fileName) {
        if (fileName == null) {
            log.error("File's name is not initialized");
            return Collections.emptyList();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String type = reader.readLine();
            String params = reader.readLine();
            if (type == null || params == null) {
                log.error("Incorrect file. Must be two lines");
                return Collections.emptyList();
            } else {
                return Arrays.asList(type, params);
            }

        } catch (FileNotFoundException e) {
            log.error("File \"" + fileName + "\" not found");
            return Collections.emptyList();

        } catch (IOException e) {
            log.error("Can't read a file. " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
