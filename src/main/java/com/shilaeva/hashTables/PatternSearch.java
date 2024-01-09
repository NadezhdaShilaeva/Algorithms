package com.shilaeva.hashTables;

import java.util.Scanner;

public class PatternSearch {
    private static final int p = 1000000007;
    private static final int x = 263;

    private void checkPattern(String pattern, String text, int index) {
        boolean isPattern = true;
        for (int j = 0; j < pattern.length(); ++j) {
            if (pattern.charAt(j) != text.charAt(index + j)) {
                isPattern = false;
                break;
            }
        }

        if (isPattern) {
            System.out.print(index + " ");
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.next();
        String text = scanner.next();
        if (text.length() < pattern.length()) {
            return;
        }

        int patternHash = pattern.charAt(pattern.length() - 1);
        int currentHash = text.charAt(pattern.length() - 1);
        int xPow = 1;
        for (int i = pattern.length() - 2; i >= 0; --i) {
            xPow = (int) ((long) xPow * x % p);
            patternHash = (int) ((patternHash + (long) pattern.charAt(i) * xPow % p) % p);
            currentHash = (int) ((currentHash + (long) text.charAt(i) * xPow % p) % p);
        }

        if (currentHash == patternHash) {
            checkPattern(pattern, text, 0);
        }

        for (int i = pattern.length(); i < text.length(); ++i) {
            currentHash = (int) ((((currentHash - (long) text.charAt(i - pattern.length()) * xPow % p) % p + p) % p
                    * x % p + text.charAt(i)) % p);

            if (currentHash == patternHash) {
                checkPattern(pattern, text, i - pattern.length() + 1);
            }
        }
    }

    public static void main(String[] args) {
        new PatternSearch().run();
    }
}
