package com.shilaeva.greedyAlgorythms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MyPriorityQueue {
    private static class MaxPriorityQueue {
        List<Integer> array = new ArrayList<>();

        public void insert(int x) {
            array.add(x);
            siftUp(array.size() - 1);
        }

        public int extractMax() {
            int result = array.get(0);
            Collections.swap(array, 0, array.size() - 1);
            array.remove(array.size() - 1);
            siftDown(0);

            return result;
        }

        private void siftDown(int index) {
            if (array.size() - 1 < 2 * index + 1) {
                return;
            }

            if (array.size() - 1 < 2 * index + 2) {
                if (array.get(index) >= array.get(2 * index + 1)) {
                    return;
                }

                Collections.swap(array, index, 2 * index + 1);
                siftDown(2 * index + 1);
                return;
            }

            if (array.get(index) >= array.get(2 * index + 1) && array.get(index) >= array.get(2 * index + 2)) {
                return;
            }

            if (array.get(2 * index + 1) > array.get(2 * index + 2)) {
                Collections.swap(array, index, 2 * index + 1);
                siftDown(2 * index + 1);
            } else {
                Collections.swap(array, index, 2 * index + 2);
                siftDown(2 * index + 2);
            }
        }

        private void siftUp(int index) {
            if (index == 0 || array.get(index) <= array.get((index - 1) / 2)) {
                return;
            }

            Collections.swap(array, index, (index - 1) / 2);
            siftUp((index - 1) / 2);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MaxPriorityQueue priorityQueue = new MaxPriorityQueue();
        for (int i = 0; i < n; ++i) {
            String operation = scanner.next();
            if (operation.equals("Insert")) {
                int x = scanner.nextInt();
                priorityQueue.insert(x);
            } else {
                System.out.println(priorityQueue.extractMax());
            }
        }
    }

    public static void main(String[] args) {
        new MyPriorityQueue().run();
    }
}
