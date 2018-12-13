package com.tiny.lang.java.generic.variance;

/**
 * 协变
 *
 * @author tiny.wang
 */
public class BoxVisitor<T, R> {

    /**
     * 若能访问一个装满对象T的盒子,则肯定能访问装满T的子类的盒子
     * 所以Box<T>是协变的
     *
     * @param box 盒子
     */
    public R visit(Box<? extends T> box) {
        return null;
    }

}
