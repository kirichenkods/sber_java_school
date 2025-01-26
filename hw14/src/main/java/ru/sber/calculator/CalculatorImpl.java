package ru.sber.calculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorImpl implements Calculator {
    public List<Integer> fibonachi(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("число должно быть положительным");
        }
        //для теста
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Integer> fibonacciSequence = new ArrayList<>();
        int n1 = 0;
        int n2 = 1;
        while (n1 <= num) {
            fibonacciSequence.add(n1);
            int next = n1 + n2;
            n1 = n2;
            n2 = next;
        }
        return fibonacciSequence;
    }
}
