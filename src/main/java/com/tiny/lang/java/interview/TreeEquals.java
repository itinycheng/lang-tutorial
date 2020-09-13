package com.tiny.lang.java.interview;

/**
 * @author tiny
 */
public class TreeEquals {

    public static void main(String[] args) {
        Node<Integer> one = new Node<>();
        one.value = 1;
        one.left = new Node<>();
        one.right = new Node<>();
        Node<Integer> two = new Node<>();
        two.value = 1;
        two.left = new Node<>();
        two.right = new Node<>();
        System.out.println(one.equals(two));
    }

    static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node() {
        }

        public boolean equals(Node<T> node) {
            return recursive(this, node);
        }

        private boolean recursive(Node<T> one, Node<T> two) {
            if (one == two) {
                return true;
            }

            boolean valueFlag, leftFlag, rightFlag;
            if (one.value == null || two.value == null) {
                valueFlag = one.value == two.value;
            } else {
                valueFlag = one.value.equals(two.value);
            }

            if (!valueFlag){
                return false;
            }

            if (one.left == null || two.left == null) {
                leftFlag = one.left == two.left;
            } else {
                leftFlag = recursive(one.left, two.left);
            }

            if (!leftFlag){
                return false;
            }

            if (one.right == null || two.right == null) {
                rightFlag = one.right == two.right;
            } else {
                rightFlag = recursive(one.right, two.right);
            }

            return rightFlag;
        }
    }

}
