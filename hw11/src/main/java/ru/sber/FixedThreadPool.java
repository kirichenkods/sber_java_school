package ru.sber;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedThreadPool implements ThreadPool {
    private final int amountOfThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> runnableQueue;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    public FixedThreadPool(int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
        threads = new Thread[amountOfThreads];
        runnableQueue = new LinkedBlockingQueue<>();

        for (int i = 0; i < amountOfThreads; i++) {
            threads[i] = new Worker(runnableQueue, isRunning);
        }
    }

    @Override
    public void start() {
        if (!isRunning.getAndSet(true)) {
            for (int i = 0; i < amountOfThreads; i++) {
                threads[i].start();
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            runnableQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static class Worker extends Thread {
        private final BlockingQueue<Runnable> runnableQueue;
        private final AtomicBoolean isRunning;

        private Worker(BlockingQueue<Runnable> runnableQueue, AtomicBoolean isRunning) {
            this.runnableQueue = runnableQueue;
            this.isRunning = isRunning;
        }

        @Override
        public void run() {
            while (isRunning.get()) {
                Runnable task;
                try {
                    task = runnableQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
