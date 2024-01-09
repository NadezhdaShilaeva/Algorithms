package com.shilaeva.priorityQueueAndSystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HeapBuilding {
    private static class Swap {
        private final int firstIndex;
        private final int secondIndex;

        public Swap(int firstIndex, int secondIndex) {
            this.firstIndex = firstIndex;
            this.secondIndex = secondIndex;
        }
    }

    List<Integer> array = new ArrayList<>();
    List<Swap> answer = new ArrayList<>();

    private int getParent(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChild(int index) {
        return 2 * index + 1;
    }

    private int getRightChild(int index) {
        return 2 * index + 2;
    }

    private void siftDown(int index) {
        if (array.size() - 1 < getLeftChild(index)) {
            return;
        }

        if (array.size() - 1 < getRightChild(index)) {
            if (array.get(index) <= array.get(getLeftChild(index))) {
                return;
            }

            Collections.swap(array, index, getLeftChild(index));
            answer.add(new Swap(index, getLeftChild(index)));
            siftDown(getLeftChild(index));
            return;
        }

        if (array.get(index) <= array.get(getLeftChild(index)) && array.get(index) <= array.get(getRightChild(index))) {
            return;
        }

        if (array.get(getLeftChild(index)) < array.get(getRightChild(index))) {
            Collections.swap(array, index, getLeftChild(index));
            answer.add(new Swap(index, getLeftChild(index)));
            siftDown(getLeftChild(index));
        } else {
            Collections.swap(array, index, getRightChild(index));
            answer.add(new Swap(index, getRightChild(index)));
            siftDown(getRightChild(index));
        }
    }

    private void buildHeap() {
        for (int i = array.size() / 2; i >= 0; --i) {
            siftDown(i);
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; ++i) {
            array.add(scanner.nextInt());
        }

        buildHeap();

        System.out.println(answer.size());
        answer.forEach(swap -> System.out.println(swap.firstIndex + " " + swap.secondIndex));
    }

    public static void main(String[] args) {
        new HeapBuilding().run();
    }
}
