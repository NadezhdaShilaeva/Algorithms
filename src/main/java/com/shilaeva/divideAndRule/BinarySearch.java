package com.shilaeva.divideAndRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearch {
    private int peerReview(List<Integer> sortedArray) {
        int l = 0;
        int r = sortedArray.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (sortedArray.get(mid) == mid + 1) {
                return mid + 1;
            }

            if (sortedArray.get(mid) < mid + 1) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return -1;
    }

    private int findElementIndex(List<Integer> sortedArray, int element) {
        int l = 0;
        int r = sortedArray.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (sortedArray.get(mid) == element) {
                return mid + 1;
            }

            if (sortedArray.get(mid) < element) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return -1;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i< n; ++i) {
            array.add(scanner.nextInt());
        }

        System.out.println(peerReview(array));

        int m = scanner.nextInt();
        for (int i = 0; i < m; ++i) {
            int number = scanner.nextInt();
            System.out.println(findElementIndex(array, number));
        }
    }

    public static void main(String[] args) {
        new BinarySearch().run();
    }
}
