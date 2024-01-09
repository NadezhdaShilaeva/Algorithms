package com.shilaeva.divideAndRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InversionsNumber {
    private long inversions = 0;

    private List<Integer> mergeSort(List<Integer> array, int l, int r) {
        if (l == r) {
            return List.of(array.get(l));
        }

        int m = (l + r) >> 1;

        return mergeArrays(mergeSort(array, l, m), mergeSort(array, m + 1, r));
    }

    private List<Integer> mergeArrays(List<Integer> array1, List<Integer> array2) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < array1.size() || j < array2.size()) {
            while (i < array1.size() && (j == array2.size() || array1.get(i) <= array2.get(j))) {
                result.add(array1.get(i));
                ++i;
            }

            while (j < array2.size() && (i == array1.size() || array1.get(i) > array2.get(j))) {
                inversions += array1.size() - i;
                result.add(array2.get(j));
                ++j;
            }
        }

        return  result;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            array.add(scanner.nextInt());
        }

        mergeSort(array, 0, n - 1);

        System.out.println(inversions);
    }

    public static void main(String[] args) {
        new InversionsNumber().run();
    }
}
