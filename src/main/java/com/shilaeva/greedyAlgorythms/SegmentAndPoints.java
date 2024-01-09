package com.shilaeva.greedyAlgorythms;

import java.util.*;

public class SegmentAndPoints {
    private static class Segment {
        public int startPoint;
        public int endPoint;

        public Segment(int startPoint, int endPoint) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
        }
    }

    private List<Integer> getPoints(List<Segment> segmentsSet) {
        segmentsSet.sort(Comparator.comparingInt(s -> s.endPoint));
        List<Integer> points = new ArrayList<>();

        segmentsSet.forEach(s -> {
            if (points.isEmpty() || points.get(points.size() - 1) < s.startPoint) {
                points.add(s.endPoint);
            }
        });

        return points;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Segment> segmentsSet = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            segmentsSet.add(new Segment(start, end));
        }

        List<Integer> points = getPoints(segmentsSet);
        System.out.println(points.size());
        points.forEach(p -> System.out.print(p + " "));
    }
    public static void main(String[] args) {
        new SegmentAndPoints().run();
    }
}
