package com.tiny.lang.java.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tiny.wang
 */
public class GenericExtraMain {

    public static void main(String[] args) {
        var arr0 = test0(1, 2).toArray(new Integer[0]);
        var arr1 = test1(1, 2).toArray(new Integer[0]);
        Integer[] integers = test2(1, 2);
        System.out.println(Arrays.toString(arr0) + '\n'
                + Arrays.toString(arr1) + '\n'
                + integers);
    }

    private static <T> List<T> test0(T first, T second) {
        List<T> ts = new ArrayList<>();
        ts.add(first);
        ts.add(second);
        // compile error: ts.toArray(new T[0])
        return ts;
    }

    private static <T> List<T> test1(T first, T second) {
        return List.of(first, second);
    }

    /**
     * T is uncertain, can't use new, .class, etc. decorate it;
     */
    private static <T> T[] test2(T first, T second) {
        // compile error
        // return List.of(first, second).toArray(T)
        return null;
    }
}
