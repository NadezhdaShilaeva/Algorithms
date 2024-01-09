package com.shilaeva.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.max;

public class Backpack {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        int capacity = scanner.nextInt();
        int n = scanner.nextInt();
        List<Integer> weights = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            weights.add(scanner.nextInt());
        }

        int[][] maxSumWeight = new int[n + 1][capacity + 1];
        for (int i = 0; i < n;  ++i) {
            maxSumWeight[i][0] = 0;
        }

        for (int i = 0; i < capacity; ++i) {
            maxSumWeight[0][i] = 0;
        }

        for (int i = 1; i <= n; ++i) {
            for (int w = 1; w <= capacity; ++w) {
                if (w >= weights.get(i - 1)) {
                    maxSumWeight[i][w] = max(maxSumWeight[i - 1][w], maxSumWeight[i - 1][w - weights.get(i - 1)] + weights.get(i - 1));
                } else {
                    maxSumWeight[i][w] = maxSumWeight[i - 1][w];
                }
            }
        }

        System.out.println(maxSumWeight[n][capacity]);
    }

    public static void main(String[] args) {
        new Backpack().run();
    }
}
