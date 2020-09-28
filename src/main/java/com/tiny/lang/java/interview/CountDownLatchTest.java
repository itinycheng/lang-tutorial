package com.tiny.lang.java.interview;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author tiny
 */
public class CountDownLatchTest {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Stream.iterate(0, i -> i + 1)
                .limit(4)
                .forEach(integer -> EXECUTOR_SERVICE.execute(() -> {
                    System.out.println(latch.getCount());
                    latch.countDown();
                }));
        latch.await();
        System.out.println("end latch: " + latch.getCount());
    }
}
