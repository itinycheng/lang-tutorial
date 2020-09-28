package com.tiny.lang.java.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author tiny
 */
public class MergeTwoSeq {

    static class Node<T> {
        int value;
        Node<T> next;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        // merge list
        List<Integer> listA = Arrays.asList(1, 3, 7, 8);
        List<Integer> listB = Arrays.asList(4, 5, 9, 11, 12);
        printMergedList(listA, listB);

        // merge queue
        Queue<Integer> queueA = new LinkedList<>();
        queueA.add(1);
        queueA.add(4);
        queueA.add(10);
        Queue<Integer> queueB = new LinkedList<>();
        queueB.add(2);
        queueB.add(3);
        queueB.add(5);
        queueB.add(9);
        queueB.add(12);
        printMergedQueue(queueA, queueB);

        // linked list
        Node<Integer> headA = new Node<>(2);
        headA.next = new Node<>(5);
        Node<Integer> headB = new Node<>(1);
        headB.next = new Node<>(6);
        printMergedLink(headA, headB);
    }

    private static void printMergedLink(Node<Integer> headA, Node<Integer> headB) {
        List<Integer> container = new ArrayList<>();
        Node<Integer> tmpHeadA = headA;
        Node<Integer> tmpHeadB = headB;

        while (tmpHeadA != null || tmpHeadB != null) {
            Integer valueA = null;
            Integer valueB = null;
            if (tmpHeadA != null) {
                valueA = tmpHeadA.value;
            }
            if (tmpHeadB != null) {
                valueB = tmpHeadB.value;
            }

            if (valueA == null) {
                container.add(valueB);
                tmpHeadB = tmpHeadB.next;
            } else if (valueB == null) {
                container.add(valueA);
                tmpHeadA = tmpHeadA.next;
            } else {
                if (valueA > valueB) {
                    container.add(valueB);
                    tmpHeadB = tmpHeadB.next;
                } else {
                    container.add(valueA);
                    tmpHeadA = tmpHeadA.next;
                }
            }
        }
        System.out.println(container);
    }

    public static void printMergedQueue(Queue<Integer> queueA, Queue<Integer> queueB) {
        List<Integer> container = new ArrayList<>(queueA.size() + queueB.size());
        Integer valueA = null;
        Integer valueB = null;
        while (!queueA.isEmpty() || !queueB.isEmpty()) {
            if (valueA == null) {
                valueA = queueA.poll();
            }
            if (valueB == null) {
                valueB = queueB.poll();
            }
            if (valueA == null) {
                container.add(valueB);
                valueB = null;
            } else if (valueB == null) {
                container.add(valueA);
                valueA = null;
            } else {
                if (valueA > valueB) {
                    container.add(valueB);
                    valueB = null;
                } else {
                    container.add(valueA);
                    valueA = null;
                }
            }
        }
        System.out.println(container);
    }

    public static void printMergedList(List<Integer> listA, List<Integer> listB) {
        List<Integer> container = new ArrayList<>(listA.size() + listB.size());
        int pointerA = 0, pointerB = 0;
        while (pointerA < listA.size() || pointerB < listB.size()) {
            Integer valueA = null;
            if (pointerA < listA.size()) {
                valueA = listA.get(pointerA);
            }
            Integer valueB = null;
            if (pointerB < listB.size()) {
                valueB = listB.get(pointerB);
            }
            if (valueA == null) {
                container.add(valueB);
                pointerB++;
            } else if (valueB == null) {
                container.add(valueA);
                pointerA++;
            } else {
                if (valueA > valueB) {
                    container.add(valueB);
                    pointerB++;
                } else {
                    container.add(valueA);
                    pointerA++;
                }
            }
        }
        System.out.println(container);
    }
}
