package ru.sber.calculator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.sber.cache.CachedInvocationHandler;

import java.lang.reflect.Proxy;

@AllArgsConstructor
@NoArgsConstructor
public class CalculatorFactory {
    private CalculatorType type;

    public Calculator create() {
        Calculator calculator = new CalculatorImpl();
        if (type == CalculatorType.CACHE) {
            return  (Calculator) Proxy.newProxyInstance(
                    ClassLoader.getSystemClassLoader(),
                    calculator.getClass().getInterfaces(),
                    new CachedInvocationHandler(calculator));
        }

        return calculator;
    }
}
