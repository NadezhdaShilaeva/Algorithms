package com.shilaeva.searchTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryTreeTraversal {
    List<Integer> key = new ArrayList<>();
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    private void inOrderTraversal(int node) {
        if (node == -1) {
            return;
        }

        inOrderTraversal(left.get(node));
        System.out.print(key.get(node) + " ");
        inOrderTraversal(right.get(node));
    }

    private void preOrderTraversal(int node) {
        if (node == -1) {
            return;
        }

        System.out.print(key.get(node) + " ");
        preOrderTraversal(left.get(node));
        preOrderTraversal(right.get(node));
    }

    private void postOrderTraversal(int node) {
        if (node == -1) {
            return;
        }

        postOrderTraversal(left.get(node));
        postOrderTraversal(right.get(node));
        System.out.print(key.get(node) + " ");
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; ++i) {
            key.add(scanner.nextInt());
            left.add(scanner.nextInt());
            right.add(scanner.nextInt());
        }

        inOrderTraversal(0);
        System.out.println();
        preOrderTraversal(0);
        System.out.println();
        postOrderTraversal(0);
    }

    public static void main(String[] args) {
        new BinaryTreeTraversal().run();
    }
}
