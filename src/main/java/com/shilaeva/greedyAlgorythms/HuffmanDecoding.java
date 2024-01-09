package com.shilaeva.greedyAlgorythms;

import java.util.Scanner;

public class HuffmanDecoding {
    private static abstract class Node {
        public String code;

        public abstract void accept(Visitor visitor);
    }

    private static class InternalNode extends Node {
        public Node leftChild;
        public Node rightChild;

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    private static class LeafNode extends Node {
        public char letter;
        public LeafNode(char letter) {
            this.letter = letter;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    private interface Visitor {
        void visit(InternalNode node);
        void visit(LeafNode node);
    }

    private static class BuildingVisitor implements Visitor {
        private final String symbolCode;
        private Integer index;
        private final char symbol;

        public BuildingVisitor(String symbolCode, Integer index, char symbol) {
            this.symbolCode = symbolCode;
            this.index = index;
            this.symbol = symbol;
        }

        @Override
        public void visit(InternalNode node) {
            if (symbolCode.charAt(index) == '0') {
                if (node.leftChild == null) {
                    if (index == symbolCode.length() - 1) {
                        LeafNode newNode = new LeafNode(symbol);
                        node.leftChild = newNode;
                        newNode.code = symbolCode;
                    } else {
                        InternalNode newNode = new InternalNode();
                        node.leftChild = newNode;
                        newNode.code = symbolCode.substring(0, index);
                    }
                }

                ++index;
                node.leftChild.accept(this);
            } else {
                if (node.rightChild == null) {
                    if (index == symbolCode.length() - 1) {
                        LeafNode newNode = new LeafNode(symbol);
                        node.rightChild = newNode;
                        newNode.code = symbolCode;
                    } else {
                        InternalNode newNode = new InternalNode();
                        node.rightChild = newNode;
                        newNode.code = symbolCode.substring(0, index);
                    }
                }

                ++index;
                node.rightChild.accept(this);
            }
        }

        @Override
        public void visit(LeafNode node) {
        }
    }

    private static class DecodingVisitor implements Visitor {
        private final String textCode;
        private Integer index;

        public DecodingVisitor(String textCode, Integer index) {
            this.textCode = textCode;
            this.index = index;
        }

        @Override
        public void visit(InternalNode node) {
            if (textCode.charAt(index) == '0') {
                ++index;
                node.leftChild.accept(this);
            } else {
                ++index;
                node.rightChild.accept(this);
            }
        }

        @Override
        public void visit(LeafNode node) {
            System.out.print(node.letter);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = scanner.nextInt();
        Node root = new InternalNode();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            char symbol = line.charAt(0);
            String code = line.substring(3);

            Visitor visitor = new BuildingVisitor(code, 0, symbol);
            root.accept(visitor);
        }

        String textCode = scanner.nextLine();

        DecodingVisitor visitor = new DecodingVisitor(textCode, 0);
        while (visitor.index < textCode.length()) {
            root.accept(visitor);
        }
    }

    public static void main(String[] args) {
        new HuffmanDecoding().run();
    }
}
