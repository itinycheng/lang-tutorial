package com.tiny.lang.java.generic.variance;

/**
 * 协变
 *
 * @author tiny.wang
 */
public class Box<T> {

    /**
     * 若有一个Box<Plant>，可以用BoxVisitor<Fuel,_>, BoxVisitor<Plant,_>来消费他，
     * 不能用BoxVisitor<Bamboo,_>来消费，即泛型T逆变；
     * <p>
     * 对于返回值：
     * 默认情况下Java的多态规则本身就契合协变规则：子类可当父类使用
     * 所以此处的<_, ? extends R>与<_, R>在当前情况下并无差异
     *
     * @param visitor visitor
     * @param <R>     R
     * @return value
     */
    public <R> R accept(BoxVisitor<? super T, ? extends R> visitor) {
        return null;
    }

    /**
     * entity classes
     */

    static class Fuel {
    }

    static class Plant extends Fuel {
    }

    static class Bamboo extends Plant {

    }
}
