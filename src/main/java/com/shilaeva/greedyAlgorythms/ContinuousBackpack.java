package com.shilaeva.greedyAlgorythms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ContinuousBackpack {
    private static class Item {
        public int cost;
        public int weight;

        public Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }
    }

    private double calculateMaxCost(List<Item> items, int capacity) {
        items.sort(Comparator.comparingDouble(item -> (double) item.cost / item.weight));
        double maxCost = 0;

        int i = items.size() - 1;
        while (i>= 0 && Double.compare(capacity, 0) > 0) {
            if (capacity >= items.get(i).weight) {
                maxCost += items.get(i).cost;
                capacity -= items.get(i).weight;
            } else {
                maxCost += (double) items.get(i).cost / items.get(i).weight * capacity;
                capacity = 0;
            }

            i--;
        }

        return maxCost;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            items.add(new Item(scanner.nextInt(), scanner.nextInt()));
        }

        System.out.printf("%.3f", calculateMaxCost(items, capacity));
    }

    public static void main(String[] args) {
        new ContinuousBackpack().run();
    }
}
