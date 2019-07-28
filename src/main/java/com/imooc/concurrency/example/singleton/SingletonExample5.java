package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annoations.NotThreadSafe;

/**
 *
 * @author xuzhangwang
 */
@NotThreadSafe
public class SingletonExample5 {

    private SingletonExample5() {}

    /*线程不安全
    * 1、memory = allocate() 分配对象的内存空间
    * 2、ctorInstance() 初始化对象
    * 3、instance = memory 设置instance指向刚分配的内存
    * */
    private volatile static SingletonExample5 instance = null;

    public static SingletonExample5 getInstance() {
        if (instance == null) {  // 双重检测机制
            synchronized (SingletonExample5.class) {
                // 同步锁
                instance = new SingletonExample5();
            }
        }
        return instance;
    }

}
