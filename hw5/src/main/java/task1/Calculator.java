package task1;

import task5.Cache;
import task6.Metric;

public interface Calculator {
    /**
     * Расчет факториала числа.
     *
     * @param number
     */
    @Cache
    @Metric
    int calc(int number);
}
