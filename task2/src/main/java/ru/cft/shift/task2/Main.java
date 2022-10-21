package ru.cft.shift.task2;

import org.slf4j.Logger;
import ru.cft.shift.task2.logger.CustomLoggerFactory;
import ru.cft.shift.task2.reader.CustomFileReader;
import ru.cft.shift.task2.shape.Shape;
import ru.cft.shift.task2.shape.ShapeFactory;

import java.util.Collection;
import java.util.Optional;

/**
 * Аргументы для запуска через командную строку:
 * имя_входного_файла -d имя_файла_логов
 * <p>
 * Имя входного файла - обязательный параметр.
 * По умолчанию логи выводятся в консоль.
 * Параметр -d дополнительно включает запись логов в файл с указанным именем.
 */
public class Main {
    private final static Logger log = CustomLoggerFactory.getLogger(Main.class.getName());
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) {
        if (args.length != 0) {
            if (requiredLogToFile(args)) {
                enableFileLogger(args[2]);
            }

            String fileName = readFileName(args);
            Collection<String> lines = CustomFileReader.readLines(fileName);
            if (!lines.isEmpty()) {
                Optional<Shape> optionalShape = ShapeFactory.create(lines);
                optionalShape.ifPresent(shape -> log.info(NEW_LINE + shape.getContent()));
            }
        } else {
            log.error("Empty parameters. Example: [inputFileName -d logFileName]");
        }
    }

    private static String readFileName(String[] args) {
        return args[0];
    }

    private static boolean requiredLogToFile(String[] args) {
        if (args.length == 3) {
            return args[1].equals("-d");
        } else {
            return false;
        }
    }

    private static void enableFileLogger(String logFileName) {
        CustomLoggerFactory.enableFileAppender(logFileName);
        CustomLoggerFactory.addFileAppenderByLoggerName(log.getName());
    }
}
