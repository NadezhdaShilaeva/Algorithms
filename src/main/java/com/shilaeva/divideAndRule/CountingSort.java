package com.shilaeva.divideAndRule;

import java.util.*;

public class CountingSort {
    private List<Integer> countingSort(List<Integer> array) {
        int[] countingArray = new int[11];
        array.forEach(x -> ++countingArray[x]);

        for (int i = 1; i < 11; ++i) {
            countingArray[i] += countingArray[i - 1];
        }

        Integer[] sortedArray = new Integer[array.size()];
        for (int i = array.size() - 1; i >= 0; --i) {
            sortedArray[countingArray[array.get(i)] - 1] = array.get(i);
            --countingArray[array.get(i)];
        }

        List<Integer> result = new ArrayList<Integer>();
        Collections.addAll(result, sortedArray);
        return result;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            array.add(scanner.nextInt());
        }

        countingSort(array).forEach(x -> System.out.print(x + " "));
    }

    public static void main(String[] args) {
        new CountingSort().run();
    }
}
