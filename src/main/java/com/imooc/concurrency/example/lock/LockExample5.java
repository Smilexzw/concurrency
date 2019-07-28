package com.imooc.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author xuzhangwang
 */
@Slf4j
public class LockExample5 {

    // 请求总数
    private static int clientTotal = 5000;

    // 同时并发执行的线程数
    private static int threadTotal = 20;

    private static int count = 0;

    private final static StampedLock lock = new StampedLock();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal  ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception {}", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("count {}", count);
        executorService.shutdown();
    }

    /**
     * 需要对该方法进行同步控制
     */
    private static void add() {
        long stample = lock.writeLock();
        try {
            count++;
        } finally {
            lock.unlock(stample);
        }
    }
}
