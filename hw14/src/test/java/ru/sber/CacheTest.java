package ru.sber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sber.calculator.Calculator;
import ru.sber.calculator.CalculatorFactory;
import ru.sber.calculator.CalculatorType;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheTest {
    private Calculator calculator;
    private CalculatorFactory factory;

    @BeforeEach
    void setUp() {
        factory = new CalculatorFactory(CalculatorType.CACHE);
        calculator = factory.create();
    }

    @Test
    void givenCacheCalculator_whenReCallFibonachi_thenWorkTimeLessThenOneSecond() {
        // в метод fibonachi добавлен Thread.sleep(2000), чтобы по времени работы
        // можно было понять что результат берется из кеша
        int num1 = 67;
        int num2 = 199;
        int num3 = 999;

        calculator.fibonachi(num1);
        long startTime = System.nanoTime();
        calculator.fibonachi(num1);
        long endTime = System.nanoTime();
        long workTime = endTime - startTime;
        assertTrue(((double) workTime / 1_000_000_000) < 1);

        calculator.fibonachi(num2);
        startTime = System.nanoTime();
        calculator.fibonachi(num2);
        endTime = System.nanoTime();
        workTime = endTime - startTime;
        assertTrue(((double) workTime / 1_000_000_000) < 1);

        calculator.fibonachi(num3);
        startTime = System.nanoTime();
        calculator.fibonachi(num3);
        endTime = System.nanoTime();
        workTime = endTime - startTime;
        assertTrue(((double) workTime / 1_000_000_000) < 1);
    }
}
