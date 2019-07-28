package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annoations.NotRecommend;
import com.imooc.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例在第一次使用时进行创建
 * @author xuzhangwang
 */
@NotRecommend
@ThreadSafe
public class SingletonExample3 {

    // 私有构造函数
    private SingletonExample3() {}

    // 单例对象
    private static SingletonExample3 instance = null;

    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
