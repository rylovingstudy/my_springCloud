package com.atguigu.myrule;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ren
 * @version 1.0
 * @description: TODO
 * @date 2021/11/1 14:47
 */
public class CASTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int count = 10;
        for (; ; ) {
            int current = atomicInteger.get();
            int next = (current + 1) % count;
            if (atomicInteger.compareAndSet(current + 1, next)) {
                //当 current == expected 时，CAS才会执行set操作，更新AtomicInteger；避免current被其他线程修改
                System.out.println(current + " " + next);
                return;
            }
        }
    }
}
