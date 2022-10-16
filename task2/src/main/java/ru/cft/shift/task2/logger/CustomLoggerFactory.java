package ru.cft.shift.task2.logger;

import org.apache.log4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomLoggerFactory {
    private static Logger logger = getLogger(CustomLoggerFactory.class.getName());

    private static Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
    private static Appender fileAppender = null;

    private CustomLoggerFactory() {
    }

    public static Logger getLogger(String name) {
        Logger logger = LoggerFactory.getLogger(name);
        if (fileAppender != null) {
            LogManager.getLogger(name).addAppender(fileAppender);
        }

        return logger;
    }

    public static void enableFileAppender(String fileName) {
        try {
            if (fileAppender == null) {
                fileAppender = new FileAppender(layout, fileName);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static void addFileAppenderByLoggerName(String loggerName) {
        LogManager.getLogger(loggerName).addAppender(fileAppender);
    }
}
