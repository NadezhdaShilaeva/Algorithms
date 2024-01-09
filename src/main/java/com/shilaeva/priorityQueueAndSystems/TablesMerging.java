package com.shilaeva.priorityQueueAndSystems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TablesMerging {
    private final List<Integer> tableSize = new ArrayList<>();
    private final List<Integer> symlink = new ArrayList<>();

    private int findRoot(int index) {
        if (symlink.get(index) != index) {
            symlink.set(index, findRoot(symlink.get(index)));
        }

        return symlink.get(index);
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int maxTableSize = 0;
        for (int i = 0; i < n; ++i) {
            tableSize.add(scanner.nextInt());
            symlink.add(i);

            maxTableSize = Math.max(maxTableSize, tableSize.get(i));
        }

        for (int i = 0; i < m; ++i) {
            int destination = scanner.nextInt() - 1;
            int source = scanner.nextInt() - 1;

            int destinationRoot = findRoot(destination);
            int sourceRoot = findRoot(source);

            if (destinationRoot != sourceRoot) {
                tableSize.set(destinationRoot, tableSize.get(destinationRoot) + tableSize.get(sourceRoot));
                tableSize.set(sourceRoot, 0);
                symlink.set(sourceRoot, destinationRoot);

                maxTableSize = Math.max(maxTableSize, tableSize.get(destinationRoot));
            }

            System.out.println(maxTableSize);
        }
    }

    public static void main(String[] args) {
        new TablesMerging().run();
    }
}
