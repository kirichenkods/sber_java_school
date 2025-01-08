package ru.sber;

import java.math.BigInteger;
import java.util.List;

public class Main {
    private static final String FILE_NAME = "random_numbers.txt"; // Имя файла для записи и чтения чисел
    private static final int NUMBERS_COUNT = 10; // Количество случайных чисел для генерации
    private static final int UPPER_BOUND = 50; // Максимальная верхняя граница для случайных чисел

    public static void main(String[] args) {
        // Генерация списка случайных чисел
        List<Integer> randomNumbers = RandomNumberGenerator.generateNumbers(NUMBERS_COUNT, UPPER_BOUND);
        FileNumIO fileNumIO = new FileNumIO(FILE_NAME);
        // Запись чисел в файл
        fileNumIO.writeNumbersToFile(randomNumbers);
        // Получение чисел из файла
        List<Integer> numbers = fileNumIO.readNumbersFromFile();
        // Калькулятор для вычисления факториалов
        Calculator calculator = new CalculatorImpl();
        // Для каждого числа запуск отдельного потока на вычисление факториала
        // Thread имплементирует Runnable
        numbers.forEach(integer -> {
            Thread thread = new Thread(() -> System.out.println("факториал " + integer + " равен " +
                    calculator.calc(BigInteger.valueOf(integer))));
            thread.start();
        });
    }
}
