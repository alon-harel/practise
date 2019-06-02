package com.harel.practise.trees;

/*
Implement a function to check if a binary tree is a binary search tree.
 */
public class IsBinarySearchTree {

    public static boolean check(BinaryTreeNode root) {
        return checkInRange(Integer.MIN_VALUE, Integer.MAX_VALUE, root);
    }

    private static boolean checkInRange(int minValue, int maxValue, BinaryTreeNode node) {
        if (node == null) {
            return true;
        }
        else if (node.getValue() < minValue || node.getValue() > maxValue) {
            // The node must be Bigger then the min and smaller then max value.
            return false;
        }
        else {
            return checkInRange(minValue, node.getValue(), node.getLeft()) &&
                    checkInRange(node.getValue(), maxValue, node.getRight());
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(8);
        BinaryTreeNode left = root.addLeft(4);
        BinaryTreeNode right = root.addRight(10);
        BinaryTreeNode ll = left.addLeft(2);
        BinaryTreeNode lr = left.addRight(6);
        BinaryTreeNode rl = right.addLeft(9);
        BinaryTreeNode rr = right.addRight(20);

        System.out.println("The tree IS binary search tree: " + check(root));
        System.out.println("---------------------------");

        // Ruin the order:
        ll.addRight(400);
        System.out.println("The tree is NOT binary search tree: " + check(root));

    }
}


