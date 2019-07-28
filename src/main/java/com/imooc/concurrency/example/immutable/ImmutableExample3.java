package com.imooc.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * final修饰基本数据类型，则无法进行修改
 * 修饰引用类型，就无法指向另一个对象
 *
 * @author xuzhangwang
 */
@Slf4j
public class ImmutableExample3 {

    private final static ImmutableList list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);


    public static void main(String[] args) {

    }
}
