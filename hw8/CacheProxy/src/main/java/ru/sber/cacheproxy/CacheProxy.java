package ru.sber.cacheproxy;

import java.lang.reflect.Proxy;

public class CacheProxy {

    public <T> T cache(T service) {
        return (T) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new CacheInvocationHandler(service));
    }
}
