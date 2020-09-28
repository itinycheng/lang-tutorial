package com.tiny.lang.java.interview;

import java.util.Stack;

/**
 * @author tiny
 */
public class MinStack<T extends Comparable<T>> {

    private final Stack<T> actualStack;

    private final Stack<T> minStack;

    private T watermark = null;

    public MinStack() {
        actualStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(T current){
        actualStack.push(current);
        if (watermark == null){
            watermark = current;
        }else if(current.compareTo(watermark) < 0){
            watermark = current;
        }
        minStack.push(watermark);
    }

    public T pop(){
        minStack.pop();
        return actualStack.pop();
    }

    public T top(){
        return actualStack.peek();
    }

    public T getMin(){
        return minStack.peek();
    }

    public static void main(String[] args) {
       var stack = new MinStack<Integer>();
       stack.push(3);
       stack.push(0);
       stack.push(6);

        System.out.println(stack.pop());
        System.out.println(stack.getMin());
        System.out.println(stack.pop());
        System.out.println(stack.getMin());
    }
}
