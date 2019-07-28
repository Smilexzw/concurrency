package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * @author xuzhangwang
 */
@ThreadSafe
public class SingletonExample2 {

    private SingletonExample2() {}

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance() {
        return instance;
    }
}
