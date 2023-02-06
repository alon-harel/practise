package com.harel.practise.tdd;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public class Node {
    private int value;
    private Node left;
    private Node right;

    public static Node deserialize(String str) {
        if (str.isEmpty()) {
            return null;
        }

        String leftNodeAsStr = getLeftNodeAsStr(str);
        Node leftNode = Node.deserialize(leftNodeAsStr);
        str = str.replace(leftNodeAsStr, "");

        String rightNodeAsStr = getRightNodeAsStr(str);
        Node rightNode = Node.deserialize(rightNodeAsStr);
        str = str.replace(rightNodeAsStr, "");

        return Node.builder()
            .left(leftNode)
            .value(extractValue(str))
            .right(rightNode)
            .build();
    }

    private static boolean hasRightNode(String str) {
        return str.charAt(str.length() - 1) == '}' &&
            str.charAt(str.length() - 2) == '}';
    }

    private static String getRightNodeAsStr(String str) {
        if (hasRightNode(str)) {
            int positionOfOpeningParenthesis = str.indexOf("{", 1);
            return str.substring(positionOfOpeningParenthesis, str.length() - 1);
        }
        return "";
    }

    private static boolean hasLeftNode(String str) {
        return str.charAt(0) == '{' &&
            str.charAt(1) == '{';
    }

    private static String getLeftNodeAsStr(String str) {
        if (hasLeftNode(str)) {
            int positionOfClosingParenthesis = findLeftNodeClosingParenthesis(str);
            return str.substring(1, positionOfClosingParenthesis);
        }
        return "";
    }

    private static int findLeftNodeClosingParenthesis(String str) {
        int index = 2;
        int openCounter = 1;
        int closeCounter = 0;
        while (closeCounter < openCounter || index == str.length()) {
            if (str.charAt(index) == '{') {
                openCounter++;
            }
            if (str.charAt(index) == '}') {
                closeCounter++;
            }
            index++;
        }
        return index;
    }

    private static int extractValue(String str) {
        int positionOfClosingParenthesis = str.indexOf("}");

        return Integer.parseInt(str.substring(1, positionOfClosingParenthesis));
    }

    public String serialize() {
        StringBuilder builder = new StringBuilder("{");
        if (left != null) {
            builder.append(left.serialize());
        }
        builder.append(value);
        if (right != null) {
            builder.append(right.serialize());
        }
        builder.append("}");
        return builder.toString();
    }


    public static void main(String[] args) {
        System.out.println(Node.builder()
            .value(7)
            .build().serialize());

        System.out.println(
            Node.builder()
                .right(Node.builder()
                    .right(Node.builder().value(4).build())
                    .value(5)
                    .build())
                .value(6)
                .build().serialize());
        System.out.println(
            Node.builder()
                .left(Node.builder()
                    .left(Node.builder()
                        .left(Node.builder()
                            .value(5)
                            .build())
                        .right(Node.builder()
                            .value(9)
                            .build())
                        .value(34)
                        .build())
                    .right(Node.builder().
                        value(78)
                        .build())
                    .value(56)
                    .build())
                .right(Node.builder()
                    .left(Node.builder()
                        .value(5)
                        .build())
                    .right(Node.builder()
                        .value(9)
                        .build())
                    .value(34)
                    .build())
                .value(56)
                .build().serialize()
        );
    }
}
