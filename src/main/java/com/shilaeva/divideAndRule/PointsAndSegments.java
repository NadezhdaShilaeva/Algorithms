package com.shilaeva.divideAndRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class PointsAndSegments {
    private static class PartitionIndexes {
        private int first;
        private int second;
    }

    private int binarySearch(List<Integer> sortedArray, int element, BiFunction<Integer, Integer, Boolean> biFunction) {
        int l = -1; // a[l] <=/< element
        int r = sortedArray.size(); // a[r] >/>= element
        while (l + 1 < r) {
            int mid = (l + r) >> 1;

            if (biFunction.apply(sortedArray.get(mid), element)) {
                l = mid;
            } else {
                r = mid;
            }
        }

        return l + 1;
    }

    private void quickSort(List<Integer> array, int l, int r) {
        if (l >= r) {
            return;
        }

        PartitionIndexes indexes = partition(array, l, r);
        quickSort(array, l, indexes.first - 1);
        quickSort(array, indexes.second + 1, r);
    }

    private PartitionIndexes partition(List<Integer> array, int l, int r) {
        Collections.swap(array, l, (l + r) >> 1);
        PartitionIndexes indexes = new PartitionIndexes();
        indexes.first = l;
        indexes.second = l;
        for (int i = l + 1; i <= r; ++i) {
            if (array.get(i) < array.get(l)) {
                ++indexes.first;
                ++indexes.second;
                Collections.swap(array, i, indexes.first);
                if (indexes.first != indexes.second) {
                    Collections.swap(array, i, indexes.second);
                }
            } else {
                if (array.get(i).equals(array.get(l))) {
                    ++indexes.second;
                    Collections.swap(array, i, indexes.second);
                }
            }
        }

        Collections.swap(array, l, indexes.first);

        return indexes;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            starts.add(scanner.nextInt());
            ends.add(scanner.nextInt());
        }

        quickSort(starts, 0, n - 1);
        quickSort(ends, 0, n - 1);

        for (int i = 0; i < m; ++i) {
            int point = scanner.nextInt();
            System.out.print(binarySearch(starts, point, (elem, pointC) -> elem <= pointC) -
                    binarySearch(ends, point, (elem, pointC) -> elem < pointC) + " ");
        }
    }

    public static void main(String[] args) {
        new PointsAndSegments().run();
    }
}
