package com.shilaeva;

import java.util.Scanner;

public class FibonacciNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(fibonacciModM(n, m));
    }

    public static int fibonacci(int n) {
        int[] array = new int[n + 1];
        array[0] = 0;
        array[1] = 1;

        for (int i = 2; i <= n; ++i) {
            array[i] = array[i - 1] + array[i - 2];
        }

        return array[n];
    }

    public static int lastFibonacciNumber(int n) {
        int[] array = new int[n + 1];
        array[0] = 0;
        array[1] = 1;

        for (int i = 2; i <= n; ++i) {
            array[i] = (array[i - 1] + array[i - 2]) % 10;
        }

        return array[n];
    }

    public static int fibonacciModM(long n, int m) {
        int[] array = new int[10000000];
        array[0] = 0;
        array[1] = 1;

        for (int i = 2; i <= n; ++i) {
            array[i] = (array[i - 2] + array[i - 1]) % m;
            if (array[i - 1] == 0 && array[i] == 1) {
                return array[(int) (n % (i - 1))];
            }
        }

        return array[(int) n];
    }
}