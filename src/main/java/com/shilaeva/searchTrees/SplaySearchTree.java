package com.shilaeva.searchTrees;

import java.util.Scanner;

public class SplaySearchTree {
    private static final int m = 1000000001;
    private static long s = 0;

    private static class Node {
        private final int key;
        private long sum;
        private Node parent;
        private Node left;
        private Node right;

        public Node(int key, Node parent, Node left, Node right) {
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
            this.sum = key + (left == null ? 0 : left.sum) + (right == null ? 0 : right.sum);
        }
    }

    private Node splay(Node node) {
        if (node.parent == null) {
            return node;
        }

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

            return node;
        }

        if (node.parent.left == node && node.parent.parent.left == node.parent) {
            Node B = node.right;
            Node C = node.parent.right;
            Node a = node.parent;
            Node b = node.parent.parent;

            if (b.parent != null) {
                if (b.parent.left == b) {
                    b.parent.setLeft(node);
                } else {
                    b.parent.setRight(node);;
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

            return splay(node);
        }

        if (node.parent.right == node && node.parent.parent.right == node.parent) {
            Node B = node.left;
            Node C = node.parent.left;
            Node a = node.parent;
            Node b = node.parent.parent;

            if (b.parent != null) {
                if (b.parent.left == b) {
                    b.parent.setLeft(node);
                } else {
                    b.parent.setRight(node);;
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

            return splay(node);
        }

        if (node.parent.left == node && node.parent.parent.right == node.parent) {
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

            return splay(node);
        }

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

        return splay(node);
    }

    private Node findNode(Node node, int key) {
        if (node.left != null && key < node.key) {
            return findNode(node.left, key);
        } else if (node.right != null && key > node.key) {
            return findNode(node.right, key);
        }

        return node;
    }

    private Node findMaxNode(Node node) {
        if (node.right == null) {
            return node;
        }

        return findMaxNode(node.right);
    }

    private Node searchNode(Node node, int key) {
        if (node == null) {
            return null;
        }

        return splay(findNode(node, key));
    }

    private Node addNode(Node node, int key) {
        if (node == null) {
            return new Node(key, null, null, null);
        }

        Node foundNode = findNode(node, key);
        if (foundNode.key == key) {
            return splay(foundNode);
        } else {
            Node newNode = new Node(key, foundNode, null, null);
            if (key < foundNode.key) {
                foundNode.setLeft(newNode);
            } else {
                foundNode.setRight(newNode);
            }

            return splay(newNode);
        }
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

    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return null;
        }

        Node foundNode = searchNode(node, key);
        Node newRoot = foundNode;
        if (foundNode.key == key) {
            if (foundNode.left != null) {
                foundNode.left.parent = null;
            }

            if (foundNode.right != null) {
                foundNode.right.parent = null;
            }

            newRoot = Merge(foundNode.left, foundNode.right);
        }

        return newRoot;
    }

    private int function(int i) {
        return (int) ((s + i) % m);
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Node root = null;
        for (int i = 0; i < n; ++i) {
            String operation = scanner.next();
            switch (operation) {
                case "+" -> root = addNode(root, function(scanner.nextInt()));
                case "-" -> root = deleteNode(root, function(scanner.nextInt()));
                case "?" -> {
                    int key = function(scanner.nextInt());
                    root = searchNode(root, key);
                    if (root != null && root.key == key) {
                        System.out.println("Found");
                    } else {
                        System.out.println("Not found");
                    }
                }
                case "s" -> {
                    int l = function(scanner.nextInt());
                    int r = function(scanner.nextInt());
                    if (root == null) {
                        s = 0;
                    } else {
                        root = searchNode(root, l);
                        s = root.sum;
                        if (root.left != null) {
                            s = s - root.left.sum;
                        }

                        if (root.key < l) {
                            s = s - root.key;
                        }

                        root = searchNode(root, r);
                        if (root.right != null) {
                            s = s - root.right.sum;
                        }

                        if (root.key > r) {
                            s = s - root.key;
                        }
                    }
                    System.out.println(s);
                }
            }
        }
    }

    public static void main(String[] args) {
        new SplaySearchTree().run();
    }
}
