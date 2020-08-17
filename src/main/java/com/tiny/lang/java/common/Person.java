package com.tiny.lang.java.common;

/**
 * @author tiny
 */
public class Person {

    private String name;

    private Age age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    static class Age {

        private int actual;

        private int lookLike;

        public int getActual() {
            return actual;
        }

        public void setActual(int actual) {
            this.actual = actual;
        }

        public int getLookLike() {
            return lookLike;
        }

        public void setLookLike(int lookLike) {
            this.lookLike = lookLike;
        }
    }

}
