package com.shilaeva.searchTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryTreeCheck {
    private final List<Integer> key = new ArrayList<>();
    private final List<Integer> left = new ArrayList<>();
    private final List<Integer> right = new ArrayList<>();
    private final List<Integer> sequence = new ArrayList<>();
    private boolean isCorrect = true;

    private void inOrderTraversal(int node) {
        if (node == -1) {
            return;
        }

        if (left.get(node) != -1) {
            if (key.get(node) <= key.get(left.get(node))) {
                isCorrect = false;
            }

            inOrderTraversal(left.get(node));
        }

        sequence.add(key.get(node));
        inOrderTraversal(right.get(node));
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; ++i) {
            key.add(scanner.nextInt());
            left.add(scanner.nextInt());
            right.add(scanner.nextInt());
        }

        if (n != 0) {
            inOrderTraversal(0);
        }

        if (!isCorrect) {
            System.out.println("INCORRECT");
            return;
        }

        for (int i = 0; i < sequence.size() - 1; ++i) {
            if (sequence.get(i) > sequence.get(i + 1)) {
                System.out.println("INCORRECT");
                return;
            }
        }

        System.out.println("CORRECT");
    }

    public static void main(String[] args) {
        new BinaryTreeCheck().run();
    }
}