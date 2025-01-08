package ru.sber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {
    /**
     * Генерирует список случайных целых чисел заданной длины с использованием верхней границы.
     * @param numberCount Количество генерируемых чисел.
     * @param upperBound Верхняя граница диапазона для генерации случайных чисел (включительно).
     * @return Список случайно сгенерированных целых чисел.
     */
    public static List<Integer> generateNumbers(int numberCount, int upperBound) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberCount; i++) {
            int randomNumber = random.nextInt(upperBound + 1);
            randomNumbers.add(randomNumber);
        }

        return randomNumbers;
    }
}
