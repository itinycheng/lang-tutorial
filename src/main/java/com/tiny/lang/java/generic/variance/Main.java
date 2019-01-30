package com.tiny.lang.java.generic.variance;

import java.util.stream.Stream;

/**
 * Java数组是协变的，但由于不是只读，coding不当会导致ArrayStoreException
 *
 * @author tiny.wang
 */
public class Main {
    public static void main(String[] args) {
        Box.Bamboo[] bamboos = new Box.Bamboo[1];
        bamboos[0] = new Box.Bamboo();
        Stream.of(bamboos).forEach(System.out::println);
        Box.Plant[] plants = bamboos;
        plants[0] = new Box.Plant();
        // throw ArrayStoreException at running stage.
        Stream.of(plants).forEach(System.out::println);
    }
}
