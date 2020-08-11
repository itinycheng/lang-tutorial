package com.tiny.lang.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * stack memory have no relation with heap and off-heap,
 * but where the stack memory really allocated ?
 * link：https://docs.oracle.com/en/java/javase/13/vm/native-memory-tracking.html#GUID-710CAEA1-7C6D-4D80-AB0C-B0958E329407
 *
 * @author tiny
 */
public class StackMemory {

    /**
     *
     * jvm configuration:
     * -Xmx100m -Xms100m -Xss5m -XX:MaxDirectMemorySize=10m -XX:NativeMemoryTracking=detail
     *
     * cmd:
     * jcmd 3379 VM.native_memory scale=MB
     */
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>(10000);
        Thread thread;
        int num = 500;
        while (num-- > 0) {
            thread = new Thread(() -> method(0));
            thread.start();
            list.add(thread);
        }
        list.forEach(item -> {
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void method(long i) {
        int loopSize = 1024 * 16;
        if (i < loopSize) {
            method(++i);
        } else {
            try {
                System.out.println(i);
                Thread.sleep(60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
