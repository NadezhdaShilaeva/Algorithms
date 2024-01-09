package com.shilaeva.dynamicProgramming;

import java.util.*;
import java.util.stream.Stream;

public class Calculator {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Integer> minOperationsCount = new ArrayList<>();
        minOperationsCount.add(0);
        minOperationsCount.add(0);
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                if (i % 3 == 0) {
                    minOperationsCount.add(Stream.of(minOperationsCount.get(i / 2), minOperationsCount.get(i / 3),
                            minOperationsCount.get(i - 1)).min(Comparator.comparingInt(x -> x)).get() + 1);
                } else {
                    minOperationsCount.add(Stream.of(minOperationsCount.get(i / 2), minOperationsCount.get(i - 1))
                            .min(Comparator.comparingInt(x -> x)).get() + 1);
                }
            } else {
                if (i % 3 == 0) {
                    minOperationsCount.add(Stream.of(minOperationsCount.get(i / 3), minOperationsCount.get(i - 1))
                            .min(Comparator.comparingInt(x -> x)).get() + 1);
                } else {
                    minOperationsCount.add(minOperationsCount.get(i - 1) + 1);
                }
            }
        }

        int answer = minOperationsCount.get(n);
        System.out.println(answer);

        Deque<Integer> intermediateNumbers = new ArrayDeque<>();
        intermediateNumbers.addFirst(n);
        while (n > 1) {
            if (n % 2 == 0 && minOperationsCount.get(n / 2) == answer - 1) {
                n /= 2;
                intermediateNumbers.addFirst(n);
                --answer;
                continue;
            }

            if (n % 3 == 0 && minOperationsCount.get(n / 3) == answer - 1) {
                n /= 3;
                intermediateNumbers.addFirst(n);
                --answer;
                continue;
            }

            --n;
            intermediateNumbers.addFirst(n);
            --answer;
        }

        intermediateNumbers.forEach(x -> System.out.print(x + " "));
    }

    public static void main(String[] args) {
        new Calculator().run();
    }
}
