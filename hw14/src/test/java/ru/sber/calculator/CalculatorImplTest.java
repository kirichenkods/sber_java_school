package ru.sber.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CalculatorImplTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new CalculatorImpl();
    }

    @Test
    void givenPositiveNumber_whenCallingFibonachi_thenReturnCorrectSequence() {
        Assertions.assertEquals(List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144), calculator.fibonachi(220));
        Assertions.assertEquals(List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55), calculator.fibonachi(77));
    }

    @Test
    void givenNegativeNumber_whenCallingFibonachi_thenThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.fibonachi(-1),
                "число должно быть положительным");
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.fibonachi(-1));
    }
}
