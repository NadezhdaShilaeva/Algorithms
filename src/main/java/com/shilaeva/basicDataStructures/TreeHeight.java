package com.shilaeva.basicDataStructures;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class TreeHeight {
    private int getHeight(List<List<Integer>> tree, int node) {
        int height = 1;
        for (int child: tree.get(node)) {
            height = Math.max(height, 1 + getHeight(tree, child));
        }

        return height;
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            tree.add(new ArrayList<>());
        }

        int root = -1;
        for (int i = 0; i < n; ++i) {
            int parent = scanner.nextInt();
            if (parent == -1) {
                root = i;
            } else {
                tree.get(parent).add(i);
            }
        }

        System.out.println(getHeight(tree, root));
    }

    public static void main(String[] args) {
        new TreeHeight().run();
    }
}