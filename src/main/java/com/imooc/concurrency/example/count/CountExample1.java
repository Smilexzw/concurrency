package com.imooc.concurrency.example.count;

import com.imooc.concurrency.annoations.NotThreadSafe;
import com.imooc.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数， 线程不安全的写法
 * @author xuzhangwang
 */
@Slf4j
@NotThreadSafe
public class CountExample1 {
    /** 请求总数 */
    private static int clientTotal = 5000;

    /** 同时并发执行的线程数*/
    private static int threadTotal = 200;

    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            }) ;
        }
        countDownLatch.await();
        log.info("count:{}", count);
        executorService.shutdown();
    }


    public static void add() {
        count++;
    }
}
