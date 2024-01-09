package com.shilaeva.greedyAlgorythms;

import java.util.*;

public class ChangeOfCoins {
    private Map<Integer, Integer> getMinNumberOfCoins(int n, List<Integer> coinsDenominations) {
        coinsDenominations.sort(Comparator.comparingInt(c -> (int) c).reversed());
        Map<Integer, Integer> numberOfCoins = new TreeMap<>();

        final int[] sum = {n};
        coinsDenominations.forEach(denomination -> {
            if (sum[0] >= denomination) {
                numberOfCoins.put(denomination, sum[0] / denomination);
                sum[0] %= denomination;
            } else {
                numberOfCoins.put(denomination, 0);
            }
        });

        return numberOfCoins;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> coinsDenominations = new ArrayList<>(List.of(5, 1, 10, 25));

        getMinNumberOfCoins(n, coinsDenominations)
                .forEach((denomination, number) -> System.out.println(denomination + ": " + number));
    }

    public static void main(String[] args) {
        new ChangeOfCoins().run();
    }
}
