package com.imooc.concurrency.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * final修饰基本数据类型，则无法进行修改
 * 修饰引用类型，就无法指向另一个对象
 * @author xuzhangwang
 */
@Slf4j
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}", map.get(1));
    }


    private void test(final int a) {
//        a = 1;
    }
}
