package task5;

import task1.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CachedInvocationHandler implements InvocationHandler {
    private final Map<Object, Object> result = new HashMap<>();
    private final Calculator calculator;

    public CachedInvocationHandler(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) {
            return invoke(method, args);
        }
        if (!result.containsKey(key(method, args))) {
            Object invoke = invoke(method, args);
            result.put(key(method, args), invoke);
        }
        return result.get(key(method, args));
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

    private Object key(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }
}
