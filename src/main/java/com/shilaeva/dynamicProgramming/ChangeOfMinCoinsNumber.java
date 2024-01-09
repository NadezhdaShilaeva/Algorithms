package com.shilaeva.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeOfMinCoinsNumber {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        List<Integer> coinsDenominations = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            coinsDenominations.add(scanner.nextInt());
        }

        List<Integer> minCoinsNumber = new ArrayList<>();
        minCoinsNumber.add(0);
        for (int i = 1; i <= n; ++i) {
            int coinsCount = Integer.MAX_VALUE;
            for(int j = 0; j < k; ++j) {
                if (i >= coinsDenominations.get(j) && coinsCount > minCoinsNumber.get(i - coinsDenominations.get(j))) {
                    coinsCount = minCoinsNumber.get(i - coinsDenominations.get(j));
                }
            }

            minCoinsNumber.add(coinsCount + 1);
        }

        System.out.println(minCoinsNumber.get(n));
    }

    public static void main(String[] args) {
        new ChangeOfMinCoinsNumber().run();
    }
}
