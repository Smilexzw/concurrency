package com.imooc.concurrency.example.threadLocal;

/**
 * 使用ThreadLocal加上Filter完成登陆
 * @author xuzhangwang
 */
public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
