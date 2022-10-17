package ru.cft.shift.task2.handler;

import org.slf4j.Logger;
import ru.cft.shift.task2.logger.CustomLoggerFactory;
import ru.cft.shift.task2.reader.CustomFileReader;
import ru.cft.shift.task2.shape.Shape;

import java.util.Optional;

public class CommandLineHandler {
    private final static Logger logger = CustomLoggerFactory.getLogger(CommandLineHandler.class.getName());

    private static final String NEW_LINE = "\n";

    private CommandLineHandler() {
    }

    public static void execute(String[] args) {
        String inputFileName;
        if (args.length == 0) {
            logger.error("Empty parameters. Example: [inputFileName -d logFileName]");
        } else {
            inputFileName = args[0];
            if (args.length == 3) {
                if (args[1].equals("-d")) {
                    enableFileLogger(args[2]);
                }
            }

            Optional<Shape> optionalShape = CustomFileReader.readShapeParametersFromFile(inputFileName);
            optionalShape.ifPresent(shape -> logger.info(NEW_LINE + shape.getContent()));
        }
    }

    private static void enableFileLogger(String logFileName) {
        CustomLoggerFactory.enableFileAppender(logFileName);
        CustomLoggerFactory.addFileAppenderByLoggerName(logger.getName());
    }
}
