package com.harel.practise.trees;


/*
Implement a function to check if a binary tree is balanced.
For the purposes of this question, a balanced tree is defined to be a tree such
 that the heights of the two subtrees of any node never differ by more than one.
 */
public class CheckBalanced {

    public static boolean check(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        int heightDiff = Math.abs(getHeight(root.getLeft()) - getHeight(root.getRight()));
        if (heightDiff > 1) {
            return false;
        }
        else {
            // Continue to verify that the sub trees are balanced as well
            // (Imaging a binary tree of this kind:
            // root.left.left.left.left
            // root.right.right.right.left
            // The root has same heights (4) both on its left and right branches, but each of the left
            // and right direct child are not balanced.
            return check(root.getLeft()) && check(root.getRight());
        }
    }

    private static int getHeight(BinaryTreeNode node) {
        if (node == null) {
            return -1;
        }
        return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.addLeft(1).addLeft(1).addLeft(1).addLeft(1);
        root.addRight(1).addRight(1).addRight(1).addRight(1);

        System.out.println("This tree is NOT balanced: " + check(root));
        System.out.println("--------------------------");
        root = new BinaryTreeNode(1);
        BinaryTreeNode left = root.addLeft(1);
        BinaryTreeNode right = root.addRight(1);
        BinaryTreeNode ll = left.addLeft(1);
        BinaryTreeNode lr = left.addRight(1);
        BinaryTreeNode rl = right.addLeft(1);
        BinaryTreeNode rr = right.addRight(1);
        ll.addLeft(1);
        ll.addRight(1);
        lr.addLeft(1);
        lr.addRight(1);
        rl.addLeft(1);
        rl.addRight(1);
        rr.addLeft(1);
        rr.addRight(1);

        System.out.println("This tree IS balanced: " + check(root));
    }

}
