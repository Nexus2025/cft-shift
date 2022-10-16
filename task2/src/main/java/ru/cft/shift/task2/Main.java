package ru.cft.shift.task2;

import ru.cft.shift.task2.handler.CommandLineHandler;

/**
 * Аргументы для запуска через командную строку:
 * имя_выходного_файла -d имя_файла_логов
 * <p>
 * Имя выходного файла - обязательный параметр.
 * По умолчанию логи выводятся в консоль.
 * Параметр -d дополнительно включает запись логов в файл с указанным именем.
 */
public class Main {
    public static void main(String[] args) {
        CommandLineHandler.execute(args);
    }
}
