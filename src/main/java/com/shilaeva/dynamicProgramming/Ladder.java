package com.shilaeva.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ladder {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Integer> cost = new ArrayList<>();
        List<Integer> maxSum = new ArrayList<>();
        maxSum.add(0);
        maxSum.add(0);
        for (int i = 2; i < n + 2; ++i) {
            cost.add(scanner.nextInt());
            maxSum.add(Math.max(maxSum.get(i - 1), maxSum.get(i - 2))+ cost.get(i - 2));
        }

        System.out.println(maxSum.get(n + 1));
    }

    public static void main(String[] args) {
        new Ladder().run();
    }
}
