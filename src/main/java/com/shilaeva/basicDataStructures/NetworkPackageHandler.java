package com.shilaeva.basicDataStructures;

import java.util.*;

import static java.lang.Math.max;

public class NetworkPackageHandler {
    private static class Package {
        int index;
        int arrival;
        int duration;

        public Package(int index, int arrival, int duration) {
            this.index = index;
            this.arrival = arrival;
            this.duration = duration;
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int bufferSize = scanner.nextInt();
        int n = scanner.nextInt();

        Queue<Package> buffer = new LinkedList<>();
        List<Integer> startTime = new ArrayList<>();
        int currentTime = 0;

        for (int i = 0; i < n; ++i) {
            startTime.add(-1);
            int arrival = scanner.nextInt();
            int duration = scanner.nextInt();

            while (!buffer.isEmpty() && max(currentTime, buffer.peek().arrival) + buffer.peek().duration <= arrival) {
                Package nextPackage = buffer.poll();
                currentTime = max(currentTime, nextPackage.arrival);
                startTime.set(nextPackage.index, currentTime);
                currentTime += nextPackage.duration;
            }

            if (buffer.size() < bufferSize) {
                buffer.add(new Package(i, arrival, duration));
            }
        }

        while (!buffer.isEmpty()) {
            Package nextPackage = buffer.poll();
            currentTime = max(currentTime, nextPackage.arrival);
            startTime.set(nextPackage.index, currentTime);
            currentTime += nextPackage.duration;
        }

        startTime.forEach(System.out::println);
    }

    public static void main(String[] args) {
        new NetworkPackageHandler().run();
    }
}
