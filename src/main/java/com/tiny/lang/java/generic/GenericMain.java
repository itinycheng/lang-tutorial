package com.tiny.lang.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiny.wang
 */
public class GenericMain {

    public static void main(String[] args) {

        // compile error: fruits = new ArrayList<? super Fruit>()
        // compile error: fruits = new ArrayList<? extends Fruit>()
        // implicit new ArrayList<? extends Fruit>(), so there is no use to declare it to <? extends Fruit>;
        // when creating an obj, its generic type must be explicitly specified.

        var fruits = new ArrayList<Fruit>();
        fruits.add(new Apple());
        fruits.add(new Orange());

        var objects = new ArrayList<>();
        objects.add(new Apple());
        objects.add(new Orange());
        objects.add(new Fruit());
        objects.add(new Vegetable());
        objects.add(new Object());

        var fruitMed = new ArrayList<Orange>();
        fruitMed.add(new Orange());

        test0(fruits);
        test1(fruits);
        test2(objects);

        test4(fruitMed);
        test5(objects);

        // more way to use generics
        test6(fruits);
        test7(objects);
    }

    /**
     * T 在调用该方法时候已经确定
     * 所以不能添加与T类型不同的对象
     * <p>
     * list.add(obj)也就不能使用
     */
    private static void test0(List<? extends Fruit> list) {
        System.out.println("------test0------");
        // compile error: list.add(new Apple())
        list.forEach(Fruit::printName);
    }

    /**
     * same as {@link #test0(List)}
     */
    private static <T extends Fruit> void test1(List<T> list) {
        System.out.println("------test1------");
        // compile error: list.add(new Fruit())
        list.forEach(Fruit::printName);
    }

    /**
     * support add(Apple|Fruit), error will be found when add(Fruit)
     * 猜测可能因为Apple的父类可能不止一个，其致使语法层防止歧义行为而做限制；
     * 另外最顶层的父类是Object，若所有List中的元素当做Object看待，泛型也没什么存在价值；
     * maybe the reason of allow add() is Fruit & subclasses can cast to Fruit;
     * List<? super Fruit> means no upper-bounds because every class extends Object
     */
    private static void test2(List<? super Fruit> list) {
        System.out.println("------test2------");
        list.add(new Apple());
        list.add(new Fruit());
        // compile error: list.add(new Food())
        list.forEach(System.out::println);
    }

    /*// compile error
    public static void test3(List<? extends Fruit & Medicine> list) {
        list.forEach(System.out::println);
    }*/

    /**
     * 多重上界，入参必须满足Fruit & Medicine的上界要求
     * 不支持上界二选一(or运算)，能模拟出来吗？
     * <p>
     * 只有上界有 Multi Bounds，下界没，
     * 所有class的公共的supper是Object，编译器无需推断泛型，
     * 所以用<T super Class>定义一个泛型是不允许的；
     */
    private static <T extends Fruit & Medicine> void test4(List<T> list) {
        System.out.println("------test4------");
        list.forEach(Fruit::printName);
    }

    /**
     * difference with {@link #test2(List)}, list.add() is not allowed here.
     */
    private static <T extends Fruit> void test5(List<? super T> list) {
        System.out.println("------test5------");
        list.forEach(System.out::println);
    }

    /**
     * R is uncertain so no certain class can extends R
     * null is the only value that meets the return requirement.
     */
    private static <T extends Fruit, R extends Medicine> List<? extends R> test6(List<T> list) {
        list.forEach(Fruit::printName);
        // notice, only null meets the return requirement.
        return null;
    }

    /**
     * same as {@link #test5(List)}
     */
    private static <T extends List<? super Fruit>> void test7(T t) {
        t.forEach(System.out::println);
    }

    /**
     * build extends relationship
     */

    interface Medicine {
    }

    interface Food {
    }

    /**
     * banana is Fruit and also a type of medicament
     */
    static class Orange extends Fruit implements Medicine {
        @Override
        public void printName() {
            System.out.println("Orange");
        }
    }

    static class Apple extends Fruit {

        @Override
        public void printName() {
            System.out.println("Apple");
        }
    }

    static class Fruit implements Food {

        public void printName() {
            System.out.println("Fruit");
        }
    }

    static class Vegetable implements Food {

        public void printName() {
            System.out.println("Vegetable");
        }
    }
}
