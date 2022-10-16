package ru.cft.shift.task2.reader;

import org.slf4j.Logger;
import ru.cft.shift.task2.logger.CustomLoggerFactory;
import ru.cft.shift.task2.shape.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class CustomFileReader {
    private static final Logger log = CustomLoggerFactory.getLogger(CustomFileReader.class.getName());

    private static final String SEPARATOR = " ";

    private CustomFileReader() {
    }

    public static Optional<Shape> readShapeParametersFromFile(String fileName) {
        String type = null;
        String params = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            type = reader.readLine();
            params = reader.readLine();
            return createShape(type, params);

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            log.error("Wrong parameters: \"" + params + "\"");
            return Optional.empty();

        } catch (FileNotFoundException e) {
            log.error("File \"" + fileName + "\" not found");
            return Optional.empty();

        } catch (IllegalArgumentException e) {
            log.error("Value \"" + type + "\" is not a shape type");
            return Optional.empty();

        } catch (IOException e) {
            log.error("Can't read a file. " + e.getMessage());
            return Optional.empty();
        }
    }

    static Optional<Shape> createShape(String name, String params) {
        if (name == null || params == null) {
            log.error("Shape type or parameters is not specified. Type: " + name + ", parameters: " + params);
            return Optional.empty();
        }

        ShapeType type = ShapeType.valueOf(name);
        ShapeBuilder shapeBuilder;
        switch (type) {
            case CIRCLE -> {
                double radius = Double.parseDouble(params.trim());
                shapeBuilder = new Circle.CircleBuilderImpl(radius);
            }
            case RECTANGLE -> {
                String[] values = params.split(SEPARATOR);
                double largeSide = Double.parseDouble(values[0]);
                double smallSide = Double.parseDouble(values[1]);
                shapeBuilder = new Rectangle.RectangleBuilderImpl(largeSide, smallSide);
            }
            case TRIANGLE -> {
                String[] values = params.split(SEPARATOR);
                double firstSide = Double.parseDouble(values[0]);
                double secondSide = Double.parseDouble(values[1]);
                double thirdSide = Double.parseDouble(values[2]);
                shapeBuilder = new Triangle.TriangleBuilderImpl(firstSide, secondSide, thirdSide);
            }
            default -> {
                return Optional.empty();
            }
        }

        return shapeBuilder.build();
    }
}
