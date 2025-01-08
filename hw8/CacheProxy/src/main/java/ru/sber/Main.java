package ru.sber;

import ru.sber.cacheproxy.CacheProxy;
import ru.sber.services.Service;
import ru.sber.services.ServiceImpl;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = cacheProxy.cache(new ServiceImpl());
        System.out.println(service.work("work"));
        System.out.println(service.run("work", 100.05, new Date()));
    }
}
