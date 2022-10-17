package ru.cft.shift.task2;

import ru.cft.shift.task2.handler.CommandLineHandler;

/**
 * Аргументы для запуска через командную строку:
 * имя_входного_файла -d имя_файла_логов
 * <p>
 * Имя входного файла - обязательный параметр.
 * По умолчанию логи выводятся в консоль.
 * Параметр -d дополнительно включает запись логов в файл с указанным именем.
 */
public class Main {
    public static void main(String[] args) {
        CommandLineHandler.execute(args);
    }
}
