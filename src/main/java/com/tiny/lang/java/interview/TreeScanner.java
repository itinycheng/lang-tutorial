package com.tiny.lang.java.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author tiny
 */
public class TreeScanner {

    private static final TreeNode<String> TREE_NODE = Optional.of(new TreeNode<>("A"))
            .map(root -> {
                var leftChild = new TreeNode<>("B");
                var rightChild = new TreeNode<>("C");
                leftChild.left = new TreeNode<>("D");
                leftChild.right = new TreeNode<>("E");
                root.left = leftChild;
                root.right = rightChild;
                return root;
            }).get();

    public static void main(String[] args) {
        var container = new LinkedList<List<TreeNode<String>>>();
        container.add(Collections.singletonList(TREE_NODE));
        List<TreeNode<String>> last;
        while ((last = container.getLast()) != null) {
            var children = new ArrayList<TreeNode<String>>();
            for (TreeNode<String> stringNode : last) {
                if (stringNode.left != null) {
                    children.add(stringNode.left);
                }
                if (stringNode.right != null) {
                    children.add(stringNode.right);
                }
            }
            if (children.isEmpty()) {
                break;
            } else {
                container.add(children);
            }
        }
        System.out.println(last);
    }
}
