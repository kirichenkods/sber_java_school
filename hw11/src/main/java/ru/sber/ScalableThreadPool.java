package ru.sber;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScalableThreadPool implements ThreadPool {
    private final int minAmountOfThreads;
    private final int maxAmountOfThreads;
    private final AtomicInteger activeThreadCount = new AtomicInteger(0);
    private final Thread[] threads;
    private final BlockingQueue<Runnable> runnableQueue;
    private volatile boolean isRunning = false;

    public ScalableThreadPool(int minAmountOfThreads, int maxAmountOfThreads) {
        this.minAmountOfThreads = minAmountOfThreads;
        this.maxAmountOfThreads = maxAmountOfThreads;
        threads = new Thread[minAmountOfThreads];
        runnableQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void start() {
        if (!isRunning) {
            isRunning = true;
            for (int i = 0; i < minAmountOfThreads; i++) {
                createWorker();
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            try {
                runnableQueue.put(runnable);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // добавляет новый поток, если все заняты
            int threadCount = activeThreadCount.get();
            if (threadCount >= minAmountOfThreads && threadCount < maxAmountOfThreads) {
                createWorker();
            }
        }
    }

    private void createWorker() {
        Worker worker = new Worker(runnableQueue, activeThreadCount);
        worker.start();
        activeThreadCount.incrementAndGet();
    }

    private class Worker extends Thread {
        private final BlockingQueue<Runnable> runnableQueue;
        private final AtomicInteger activeThreadCount;

        private Worker(BlockingQueue<Runnable> runnableQueue, AtomicInteger activeThreadCount) {
            this.runnableQueue = runnableQueue;
            this.activeThreadCount = activeThreadCount;
        }

        @Override
        public void run() {
            while (true) {
                Runnable task;
                try {
                    task = runnableQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                if (activeThreadCount.decrementAndGet() > minAmountOfThreads) {
                    return;
                }
            }
        }
    }
}
