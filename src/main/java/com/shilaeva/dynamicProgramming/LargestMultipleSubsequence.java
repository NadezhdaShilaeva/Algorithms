package com.shilaeva.dynamicProgramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LargestMultipleSubsequence {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            sequence.add(scanner.nextInt());
        }

        List<Integer> answer = new ArrayList<>(n);
        answer.add(1);

        for (int i = 1; i < n; ++i) {
            int maxDivisorsCount = -1;
            for (int j = i - 1; j >= 0; --j) {
                if (sequence.get(i) % sequence.get(j) == 0 && answer.get(j) > maxDivisorsCount) {
                    maxDivisorsCount = answer.get(j);
                }
            }

            if (maxDivisorsCount == -1) {
                answer.add(1);
            } else {
                answer.add(maxDivisorsCount + 1);
            }
        }

        answer.stream().max(Comparator.comparingInt(x -> x)).ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        new LargestMultipleSubsequence().run();
    }
}
