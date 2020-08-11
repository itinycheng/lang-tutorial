package com.tiny.lang.java;

import com.tiny.lang.scala.generic.Fruit;
import com.tiny.lang.scala.generic.Medicine;
import com.tiny.lang.scala.generic.Orange;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        //ArrayList<?> objects = new ArrayList<? extends Fruit>();
        test(Collections.singletonList(new Orange()));
        //System.out.println(objects);
        Integer[] integers = test(1, 2);
        System.out.println(Arrays.toString(integers));
    }

    private static <T extends Fruit & Medicine> void test(List<T> list) {
        list.forEach(System.out::println);
    }

    private static <T> T[] test(T t1, T t2) {
        T[] arr = (T[]) Array.newInstance(t1.getClass(), 2);
        arr[0] = t1;
        arr[1] = t2;
        return arr;
    }
}
