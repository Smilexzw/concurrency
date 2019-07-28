package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annoations.NotThreadSafe;

/**
 *
 * @author xuzhangwang
 */
@NotThreadSafe
public class SingletonExample4 {

    private SingletonExample4() {}

    /*线程不安全
    * 1、memory = allocate() 分配对象的内存空间
    * 2、ctorInstance() 初始化对象
    * 3、instance = memory 设置instance指向刚分配的内存
    * */
    private static SingletonExample4 instance = null;

    public static SingletonExample4 getInstance() {
        if (instance == null) {  // 双重检测机制
            synchronized (SingletonExample4.class) {
                // 同步锁
                instance = new SingletonExample4();
            }
        }
        return instance;
    }

}
