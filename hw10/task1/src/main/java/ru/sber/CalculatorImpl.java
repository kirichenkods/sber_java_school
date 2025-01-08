package ru.sber;

import java.math.BigInteger;

public class CalculatorImpl implements Calculator {
    private final BigInteger ONE = new BigInteger("1");
    @Override
    public BigInteger calc(BigInteger number) {
        if (number.compareTo(ONE) < 0) {
            throw new IllegalArgumentException("отрицательный аргумент!");
        }
        return (number.equals(ONE))
                ? ONE
                : number.multiply(calc(number.subtract(ONE)));
    }
}
