package com.harel.practise.trees;

public class BinaryTreeNode {
    private final int value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode addLeft(int value) {
        BinaryTreeNode left = new BinaryTreeNode(value);
        this.left = left;
        return left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public BinaryTreeNode addRight(int value) {
        BinaryTreeNode right = new BinaryTreeNode(value);
        this.right = right;
        return right;
    }
}
