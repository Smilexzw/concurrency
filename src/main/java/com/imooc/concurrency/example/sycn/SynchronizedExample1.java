package com.imooc.concurrency.example.sycn;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuzhangwang
 */
@Slf4j
public class SynchronizedExample1 {

    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 -{}, {}",j , i);
            }
        }
    }

    /**
     * 修饰一个方法
     */
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} , {}",j,  i);
        }
    }


    /**
     * 修饰的静态方法的时候，作用的范围是
     * @param j
     */
    public static synchronized void test3(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test3 - {}, {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test3(1);
        });
        executorService.execute(() -> {
            example2.test3(2);
        });
        executorService.shutdown();
    }
}
