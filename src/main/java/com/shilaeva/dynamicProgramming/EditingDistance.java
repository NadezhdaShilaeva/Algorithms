package com.shilaeva.dynamicProgramming;

import java.util.*;
import java.util.stream.Stream;

public class EditingDistance {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        List<Integer> prevAnswer = new ArrayList<>();
        List<Integer> currAnswer = new ArrayList<>();

        for (int j = 0; j <= str2.length(); ++j) {
            prevAnswer.add(j);
        }

        for (int i = 1; i <= str1.length(); ++i) {
            currAnswer.add(i);
            for (int j = 1; j <= str2.length(); ++j) {
                currAnswer.add(Stream.of(prevAnswer.get(j) + 1, currAnswer.get(j - 1) + 1, prevAnswer.get(j - 1)
                        + (str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1)).min(Comparator.comparingInt(x -> x)).get());
            }

            Collections.copy(prevAnswer, currAnswer);
            currAnswer.clear();
        }

        System.out.println(prevAnswer.get(str2.length()));
    }

    public static void main(String[] args) {
        new EditingDistance().run();
    }
}
