package com.shilaeva.priorityQueueAndSystems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutomaticProgramAnalysis {
    private final List<Integer> parent = new ArrayList<>();
    private final List<Integer> rank = new ArrayList<>();

    private int findSetRoot(int index) {
        if (index != parent.get(index)) {
            parent.set(index, findSetRoot(parent.get(index)));
        }

        return parent.get(index);
    }

    private void unionSet(int firstSet, int secondSet) {
        int firstSetRoot = findSetRoot(firstSet);
        int secondSetRoot = findSetRoot(secondSet);
        if (firstSetRoot == secondSetRoot) {
            return;
        }

        if (rank.get(firstSetRoot) > rank.get(secondSetRoot)) {
            parent.set(secondSetRoot, firstSetRoot);
        } else {
            parent.set(firstSetRoot, secondSetRoot);
            if (rank.get(firstSetRoot).equals(rank.get(secondSetRoot))) {
                rank.set(secondSetRoot, rank.get(secondSetRoot) + 1);
            }
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int e = scanner.nextInt();
        int d = scanner.nextInt();

        for (int i = 0; i < n; ++i) {
            parent.add(i);
            rank.add(0);
        }

        for (int i = 0; i < e; ++i) {
            unionSet(scanner.nextInt() - 1, scanner.nextInt() - 1);
        }

        boolean isPossible = true;
        for (int i = 0; i < d; ++i) {
            if (findSetRoot(scanner.nextInt() - 1) == findSetRoot(scanner.nextInt() - 1)) {
                isPossible = false;
            }
        }

        if (isPossible) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    public static void main(String[] args) {
        new AutomaticProgramAnalysis().run();
    }
}
