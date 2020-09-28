package com.tiny.lang.java.interview;

/**
 * @author tiny
 */
public class TreeNode<T> {
    public T value;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
    }
}
