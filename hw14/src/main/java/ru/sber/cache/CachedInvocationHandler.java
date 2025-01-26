package ru.sber.cache;

import lombok.AllArgsConstructor;
import ru.sber.calculator.Calculator;
import ru.sber.db.Source;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@AllArgsConstructor
public class CachedInvocationHandler implements InvocationHandler {
    private final Calculator calculator;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cachable cachable = method.getAnnotation(Cachable.class);
        //если у класса нет аннотации Cachable, то обычный вызов метода
        if (cachable == null) {
            return invoke(method, args);
        }

        //получим имя класса источника кеша и создадим его объект
        String className = cachable.value().getName();
        Class<?> targetClass = Class.forName(className);
        Constructor<?> constructor = targetClass.getConstructor();
        Source cacheSource = (Source) constructor.newInstance();

        int num = (int) args[0];
        List<Integer> sequence = cacheSource.getSequence(num);
        //если в кеше нет последовательности для полученного числа,
        //то получить её и сохранить в кеш
        if (sequence.isEmpty()) {
            sequence = calculator.fibonachi(num);
            cacheSource.saveSequence(num, sequence);
        }
        return sequence;
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(calculator, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible", e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
