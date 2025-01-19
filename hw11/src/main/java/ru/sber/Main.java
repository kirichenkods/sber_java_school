package ru.sber;

public class Main {
    public static void main(String[] args) {
        ThreadPool fixedThreadPool = new FixedThreadPool(3);
        fixedThreadPool.start();
        fixedThreadPool.execute(() -> System.out.println("work " + Thread.currentThread().getName()));
        fixedThreadPool.execute(() -> System.out.println("work " + Thread.currentThread().getName()));
        fixedThreadPool.execute(() -> System.out.println("work " + Thread.currentThread().getName()));

        ThreadPool scalableThreadPool = new ScalableThreadPool(2, 5);
        scalableThreadPool.start();
        scalableThreadPool.execute(() -> System.out.println("work " + Thread.currentThread().getName()));
        scalableThreadPool.execute(() -> System.out.println("work " + Thread.currentThread().getName()));
        scalableThreadPool.execute(() -> System.out.println("work " + Thread.currentThread().getName()));
        scalableThreadPool.execute(() -> System.out.println("work " + Thread.currentThread().getName()));
    }
}
