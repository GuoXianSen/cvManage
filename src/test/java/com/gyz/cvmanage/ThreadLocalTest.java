package com.gyz.cvmanage;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet() {
        // 提供ThreadLocal对象
        ThreadLocal tl = new ThreadLocal();

        new Thread(() -> {
            tl.set("11111111111111");
            System.out.println(Thread.currentThread().getName() + " : " + tl.get());
            System.out.println(Thread.currentThread().getName() + " : " + tl.get());
            System.out.println(Thread.currentThread().getName() + " : " + tl.get());
        }, "蓝色线程").start();
        new Thread(() -> {
            tl.set("22222222222222");
            System.out.println(Thread.currentThread().getName() + " : " + tl.get());
            System.out.println(Thread.currentThread().getName() + " : " + tl.get());
            System.out.println(Thread.currentThread().getName() + " : " + tl.get());
        }, "红色线程").start();

    }
}
