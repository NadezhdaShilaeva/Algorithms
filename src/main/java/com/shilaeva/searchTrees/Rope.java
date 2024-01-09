package com.shilaeva.searchTrees;

import java.util.Scanner;

public class Rope {
    private static class Node {
        private final char key;
        private int count;
        private Node parent;
        private Node left;
        private Node right;

        public Node(char key, Node parent, Node left, Node right) {
            this.key = key;
            this.parent = parent;
            this.left = left;
            this.right = right;
            checkSum();
        }

        public void setLeft(Node left) {
            this.left = left;
            if (left != null) {
                left.parent = this;
            }
        }

        public void setRight(Node right) {
            this.right = right;
            if (right != null) {
                right.parent = this;
            }
        }

        public void checkSum() {
            this.count = 1 + (left == null ? 0 : left.count) + (right == null ? 0 : right.count);
        }
    }

    private Node splay(Node node) {
        while (node.parent != null) {
            if (node.parent.parent == null) {
                Node B;
                Node a = node.parent;

                if (node.parent.left == node) {
                    B = node.right;

                    node.setRight(a);
                    a.setLeft(B);
                } else {
                    B = node.left;

                    node.setLeft(a);
                    a.setRight(B);
                }

                node.parent = null;
                a.checkSum();
                node.checkSum();
            } else if (node.parent.left == node && node.parent.parent.left == node.parent) {
                Node B = node.right;
                Node C = node.parent.right;
                Node a = node.parent;
                Node b = node.parent.parent;

                if (b.parent != null) {
                    if (b.parent.left == b) {
                        b.parent.setLeft(node);
                    } else {
                        b.parent.setRight(node);
                        ;
                    }
                } else {
                    node.parent = null;
                }

                node.setRight(a);
                a.setLeft(B);
                a.setRight(b);
                b.setLeft(C);

                b.checkSum();
                a.checkSum();
                node.checkSum();
            } else if (node.parent.right == node && node.parent.parent.right == node.parent) {
                Node B = node.left;
                Node C = node.parent.left;
                Node a = node.parent;
                Node b = node.parent.parent;

                if (b.parent != null) {
                    if (b.parent.left == b) {
                        b.parent.setLeft(node);
                    } else {
                        b.parent.setRight(node);
                        ;
                    }
                } else {
                    node.parent = null;
                }

                node.setLeft(a);
                a.setRight(B);
                a.setLeft(b);
                b.setRight(C);

                b.checkSum();
                a.checkSum();
                node.checkSum();
            } else if (node.parent.left == node && node.parent.parent.right == node.parent) {
                Node B = node.left;
                Node C = node.right;
                Node a = node.parent.parent;
                Node b = node.parent;

                if (a.parent != null) {
                    if (a.parent.left == a) {
                        a.parent.setLeft(node);
                    } else {
                        a.parent.setRight(node);
                    }
                } else {
                    node.parent = null;
                }

                node.setLeft(a);
                node.setRight(b);
                a.setRight(B);
                b.setLeft(C);

                b.checkSum();
                a.checkSum();
                node.checkSum();
            } else {
                Node B = node.right;
                Node C = node.left;
                Node a = node.parent.parent;
                Node b = node.parent;

                if (a.parent != null) {
                    if (a.parent.left == a) {
                        a.parent.setLeft(node);
                    } else {
                        a.parent.setRight(node);
                    }
                } else {
                    node.parent = null;
                }

                node.setRight(a);
                node.setLeft(b);
                a.setLeft(B);
                b.setRight(C);

                b.checkSum();
                a.checkSum();
                node.checkSum();
            }
        }

        return node;
    }

    private Node findNode(Node node, int index) {
        while (true) {
            if (node.left != null && index <= node.left.count) {
                node = node.left;
            } else if (node.right != null && index > (node.left == null ? 0 : node.left.count) + 1) {
                index -= (node.left == null ? 0 : node.left.count) + 1;
                node = node.right;
            } else {
                break;
            }
        }

        return node;
    }

    private Node findMaxNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    private Node searchNode(Node node, int index) {
        if (node == null) {
            return null;
        }

        return splay(findNode(node, index));
    }

    private Node Merge(Node firstNode, Node secondNode) {
        if (firstNode == null) {
            return secondNode;
        }

        Node newRoot = splay(findMaxNode(firstNode));
        newRoot.setRight(secondNode);
        newRoot.checkSum();

        return newRoot;
    }

    private void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);
        System.out.print(node.key);
        inOrderTraversal(node.right);
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        Node root = null;
        for (int i = 0; i < string.length(); i += 2) {
            Node symbol;
            if (string.length() > i + 1) {
                symbol = Merge(new Node(string.charAt(i), null, null, null),
                        new Node(string.charAt(i + 1), null, null, null));
            } else {
                symbol = new Node(string.charAt(i), null, null, null);
            }

            root = Merge(root, symbol);
        }

        inOrderTraversal(root);
        System.out.println();

        int n = scanner.nextInt();

        for (int i = 0; i < n; ++i) {
            int start = scanner.nextInt() + 1;
            int end = scanner.nextInt() + 1;
            int k = scanner.nextInt();

            root = searchNode(root, start);
            Node leftPart = root.left;
            root.left = null;
            if (leftPart != null) {
                leftPart.parent = null;
            }

            root = searchNode(root, end - start + 1);
            Node rightPart = root.right;
            root.right = null;
            if (rightPart != null) {
                rightPart.parent = null;
            }

            Node newTree = Merge(leftPart, rightPart);
            if (k == 0) {
                root = Merge(root, newTree);
            } else {
                newTree = searchNode(newTree, k);
                rightPart = newTree.right;
                newTree.right = null;
                if (rightPart != null) {
                    rightPart.parent = null;
                }

                root = Merge(newTree, root);
                root = Merge(root, rightPart);
            }
        }

        inOrderTraversal(root);
    }

    public static void main(String[] args) {
        new Rope().run();
    }
}