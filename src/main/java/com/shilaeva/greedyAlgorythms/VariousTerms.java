package com.shilaeva.greedyAlgorythms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VariousTerms {
    private List<Integer> getVariousTerms(int n) {
        List<Integer> terms = new ArrayList<>();
        int sum = 0;
        int i = 1;
        while(sum + i + i + 1 <= n) {
            sum += i;
            terms.add(i);
            ++i;
        }

        terms.add(n - sum);

        return terms;
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Integer> terms = getVariousTerms(n);
        System.out.println(terms.size());
        terms.forEach(term -> System.out.print(term + " "));
    }
    public static void main(String[] args) {
        new VariousTerms().run();
    }
}
