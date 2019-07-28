package com.imooc.concurrency.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xuzhangwang
 */
@Slf4j
public class StringExample2 {

    /** 请求总数 */
    private static int clientTotal = 5000;

    /** 同时并发执行的线程数*/
    private static int threadTotal = 200;

    private static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            }) ;
        }
        countDownLatch.await();
        log.info("count:{}", sb.length());
        executorService.shutdown();
    }


    public static void update() {
        sb.append("1");
    }
}
