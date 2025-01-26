package ru.sber.db;

import java.util.List;

public interface Source {
    /**
     * Сохраняет последовательность sequence для числа num
     */
    void saveSequence(int num, List<Integer> sequence);

    /**
     * Получить последовательность для числа num
     */
    List<Integer> getSequence(int num);
}
