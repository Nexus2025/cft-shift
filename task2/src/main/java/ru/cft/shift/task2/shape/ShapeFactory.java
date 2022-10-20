package ru.cft.shift.task2.shape;

import org.slf4j.Logger;
import ru.cft.shift.task2.logger.CustomLoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ShapeFactory {
    private final static Logger log = CustomLoggerFactory.getLogger(ShapeFactory.class.getName());

    private ShapeFactory() {
    }

    public static Optional<Shape> create(Collection<String> data) {
        if (data == null) {
            log.error("Data is not initialized");
            return Optional.empty();
        }

        if (data.size() != 2) {
            log.error("Wrong data size. Size must be 2");
            return Optional.empty();
        }

        List<String> shapeData = (List<String>) data;
        ShapeBuilder shapeBuilder;
        try {
            ShapeType type = ShapeType.valueOf(shapeData.get(0).toUpperCase());
            String params = shapeData.get(1);
            switch (type) {
                case CIRCLE -> {
                    shapeBuilder = new Circle.CircleBuilderImpl(params);
                }
                case RECTANGLE -> {
                    shapeBuilder = new Rectangle.RectangleBuilderImpl(params);
                }
                case TRIANGLE -> {
                    shapeBuilder = new Triangle.TriangleBuilderImpl(params);
                }
                default -> {
                    return Optional.empty();
                }
            }
            return shapeBuilder.build();

        } catch (IllegalArgumentException e) {
            log.error("Value \"" + shapeData.get(0) + "\" is not a shape type");
            return Optional.empty();
        }
    }
}
