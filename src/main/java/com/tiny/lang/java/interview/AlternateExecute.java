package com.tiny.lang.java.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tiny
 */
public class AlternateExecute {

    private static int NUM = 0;

    private static final Object OBJ_LOCK = new Object();

    public static void main(String[] args) throws Exception {
        meth1();
        //meth2();
        //meth3();
    }

    private static void meth3() throws Exception {
        final ReentrantLock lock = new ReentrantLock();
        final Condition odd = lock.newCondition();
        final Condition even = lock.newCondition();
        int max = 100;
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                while (NUM < max) {
                    while (NUM % 2 == 0) {
                        even.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ", num = " + ++NUM);
                    odd.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                while (NUM < max) {
                    while (NUM % 2 != 0) {
                        odd.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ", num = " + ++NUM);
                    even.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static void meth2() throws Exception {

        Thread t1 = new Thread(() -> {
            synchronized (OBJ_LOCK) {
                while (NUM < 100) {
                    while (NUM % 2 != 0) {
                        try {
                            OBJ_LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ", num = " + ++NUM);
                    OBJ_LOCK.notify();
                }

            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (OBJ_LOCK) {
                while (NUM < 100) {
                    while (NUM % 2 == 0) {
                        try {
                            OBJ_LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ", num = " + ++NUM);
                    OBJ_LOCK.notify();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

    /**
     * can not keep execution sequence
     * print 10 maybe happens before print 9
     */
    private static void meth1() throws InterruptedException {
        int max = 100;
        Thread t1 = new Thread(() -> {
            while (NUM < max) {
                if (NUM % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ", num = " + ++NUM);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (NUM < max) {
                if (NUM % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + ", num = " + ++NUM);
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
