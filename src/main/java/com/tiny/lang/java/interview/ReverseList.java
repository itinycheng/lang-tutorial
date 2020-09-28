package com.tiny.lang.java.interview;

/**
 * @author tiny
 */
public class ReverseList {

    public static void main(String[] args) {
        var head = new Node<>("a");
        head.next = new Node<>("b");
        head.next.next = new Node<>("c");

        var newHead = reverse(head);
        System.out.println(newHead);

    }

    public static Node<String> reverse(Node<String> head){
        if (head == null || head.next == null){
            return head;
        }

        var curr = head;
        Node<String> pre = null;
        while (curr != null){
            Node<String> tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }

    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
