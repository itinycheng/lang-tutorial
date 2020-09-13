package com.tiny.lang.java.interview;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SimulationQueue<E> {

    private final Stack<E> inStack;

    private final Stack<E> outStack;

    private final int capacity;

    private int elementCount;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();

    private final Condition notFull = lock.newCondition();

    public SimulationQueue(int capacity) {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
        this.capacity = capacity;
    }

    public void offer(E e) throws Exception {
        lock.lock();
        try {
            while (elementCount >= capacity) {
                notFull.await();
            }
            inStack.push(e);
            elementCount++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E poll() throws Exception {
        lock.lock();
        try {
            while (elementCount <= 0) {
                notEmpty.await();
            }
            E temp = null;
            if (!outStack.isEmpty()) {
                temp = outStack.pop();
            } else if (!inStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
                temp = outStack.pop();
            }
            elementCount--;
            notFull.signal();
            return temp;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return elementCount;
    }

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        SimulationQueue<Integer> queue = new SimulationQueue<>(10);
        ExecutorService consumerThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService producerThreadPool = Executors.newFixedThreadPool(10);

        consumerThreadPool.execute(() -> {
            for (; ; ) {
                try {
                    System.out.println(queue.size() + ", " + queue.poll());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        producerThreadPool.execute(() -> {
            while (count.get() <= 100) {
                try {
                    queue.offer(count.getAndIncrement());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
