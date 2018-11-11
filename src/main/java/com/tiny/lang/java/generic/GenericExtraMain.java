package com.tiny.lang.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiny.wang
 */
public class GenericExtraMain {

    public static void main(String[] args) {
        var arr0 = test0(1, 2);
        var arr1 = test1(1, 2).toArray(new Integer[0]);
    }

    private static <T> T[] test0(T first, T second) {
        List<T> ts = new ArrayList<>();
        ts.add(first);
        ts.add(second);
        // compile error: ts.toArray(new T[0])
        return null;
    }

    private static <T> List<T> test1(T first, T second) {
        return List.of(first, second);
    }
}
