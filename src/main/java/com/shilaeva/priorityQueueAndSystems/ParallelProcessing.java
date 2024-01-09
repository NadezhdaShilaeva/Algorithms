package com.shilaeva.priorityQueueAndSystems;

import java.util.Scanner;

public class ParallelProcessing {
    private static class Process implements Comparable<Process> {
        private final int processorNumber;
        private final long finishTime;

        public Process(int processorNumber, long finishTime) {
            this.processorNumber = processorNumber;
            this.finishTime = finishTime;
        }

        @Override
        public int compareTo(Process o) {
            if (this.finishTime == o.finishTime) {
                return Integer.compare(this.processorNumber, o.processorNumber);
            } else {
                return Long.compare(this.finishTime, o.finishTime);
            }
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        MinPriorityQueue<Process> priorityQueue = new MinPriorityQueue<>();
        long currentTime = 0;

        for (int i = 0; i < n; ++i) {
            priorityQueue.insert(new Process(i, currentTime));
        }

        for (int i = 0; i < m; ++i) {
            int time = scanner.nextInt();
            Process oldProcess = priorityQueue.extractMin();
            currentTime = oldProcess.finishTime;
            priorityQueue.insert(new Process(oldProcess.processorNumber, currentTime + time));
            System.out.println(oldProcess.processorNumber + " " + currentTime);
        }
    }

    public static void main(String[] args) {
        new ParallelProcessing().run();
    }
}
