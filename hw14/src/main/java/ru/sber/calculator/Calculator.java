package ru.sber.calculator;

import ru.sber.cache.Cachable;
import ru.sber.db.PostgresDB;

import java.util.List;

public interface Calculator {
    /**
     * Возвращает последовательность Фибоначчи для переданного числа
     */
    @Cachable(PostgresDB.class)
    List<Integer> fibonachi(int num);
}
