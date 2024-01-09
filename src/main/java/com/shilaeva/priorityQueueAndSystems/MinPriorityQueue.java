package com.shilaeva.priorityQueueAndSystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinPriorityQueue<T extends Comparable<T>> {
    private List<T> array = new ArrayList<>();

    public void insert(T x) {
        array.add(x);
        siftUp(array.size() - 1);
    }

    public T extractMin() {
        T result = array.get(0);
        Collections.swap(array, 0, array.size() - 1);
        array.remove(array.size() - 1);
        siftDown(0);

        return result;
    }

    public int getSize() {
        return array.size();
    }

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
            if (array.get(index).compareTo(array.get(getLeftChild(index))) <= 0) {
                return;
            }

            Collections.swap(array, index, getLeftChild(index));
            siftDown(getLeftChild(index));
            return;
        }

        if (array.get(index).compareTo(array.get(getLeftChild(index))) <= 0 &&
                array.get(index).compareTo(array.get(getRightChild(index))) <= 0) {
            return;
        }

        if (array.get(getLeftChild(index)).compareTo(array.get(getRightChild(index))) < 0) {
            Collections.swap(array, index, getLeftChild(index));
            siftDown(getLeftChild(index));
        } else {
            Collections.swap(array, index, getRightChild(index));
            siftDown(getRightChild(index));
        }
    }

    private void siftUp(int index) {
        if (index == 0 || array.get(index).compareTo(array.get(getParent(index))) >= 0) {
            return;
        }

        Collections.swap(array, index, getParent(index));
        siftUp(getParent(index));
    }
}
