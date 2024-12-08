package task6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;

public class PerformanceProxy implements InvocationHandler {
    private final Object delegate;

    public PerformanceProxy(Object delegate) {
        this.delegate = delegate;
    }

    /**
     * обертка над Proxy.newProxyInstance, на вход принимает объект класса и интерфейс этого класса
     */
    public static <T> T create(Object o, Class<T> interfaceObject) {
        return (T) Proxy.newProxyInstance(o.getClass().getClassLoader(),
                new Class<?>[]{interfaceObject},
                new PerformanceProxy(o));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Metric.class)) {
            long timeStart = LocalDateTime.now().getNano();
            Object result = method.invoke(delegate, args);
            long timeEnd = LocalDateTime.now().getNano();
            System.out.println("Время работы метода: " + (timeEnd - timeStart) + " (в наносек)");
            return result;
        }

        return method.invoke(delegate, args);
    }
}
