package com.imooc.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量（用于控制并发线程的数目）
 * @author xuzhangwang
 */
@Slf4j
public class SemaphoreExample3 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();


        final Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            pool.execute(() -> {
                try {
                   if (semaphore.tryAcquire(3)) {
                       test(threadNum);
                       semaphore.release(); // 释放一个许可
                   }
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {

                }
            });
        }

        log.info("finish");
        pool.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
