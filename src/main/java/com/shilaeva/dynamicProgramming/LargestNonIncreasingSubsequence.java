package com.shilaeva.dynamicProgramming;

import java.util.*;

public class LargestNonIncreasingSubsequence {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            sequence.add(scanner.nextInt());
        }

        List<Integer> tempLastIndex = new ArrayList<>();
        List<Integer> prevIndex = new ArrayList<>(n);
        tempLastIndex.add(0);
        prevIndex.add(-1);
        int maxLength = 0;

        for (int i = 1; i < n; ++i) {
            if (sequence.get(i) <= sequence.get(tempLastIndex.get(maxLength))) {
                tempLastIndex.add(i);
                prevIndex.add(tempLastIndex.get(maxLength));
                ++maxLength;
            } else {
                int index = binarySearch(sequence, tempLastIndex, i);
                tempLastIndex.set(index, i);
                prevIndex.add(index == 0 ? -1 : tempLastIndex.get(index - 1));
            }

        }

        System.out.println(maxLength + 1);
        Deque<Integer> indexSequence = new ArrayDeque<>();
        int nextIndex = tempLastIndex.get(maxLength);
        while (nextIndex != -1) {
            indexSequence.addFirst(nextIndex + 1);
            nextIndex = prevIndex.get(nextIndex);
        }

        indexSequence.forEach(i -> System.out.print(i + " "));
    }

    private int binarySearch(List<Integer> sequence, List<Integer> tempLastIndex, int i) {
        int l = -1;
        int r = tempLastIndex.size();
        while (l + 1 < r) {
            int m = (l + r) >> 1;
            if (sequence.get(tempLastIndex.get(m)) < sequence.get(i)) {
                r = m;
            } else {
                l = m;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        new LargestNonIncreasingSubsequence().run();
    }
}
