package com.shilaeva.basicDataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.Math.max;

public class SlidingWindowMax {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            numbers.add(scanner.nextInt());
        }

        int m = scanner.nextInt();

        Stack<Integer> firstStack = new Stack<>();
        Stack<Integer> secondStack = new Stack<>();
        Stack<Integer> maxFirstStack = new Stack<>();
        Stack<Integer> maxSecondStack = new Stack<>();

        for (int i = 0; i < m; ++i) {
            firstStack.push(numbers.get(i));
            maxFirstStack.push(maxFirstStack.empty() ? firstStack.peek() : max(maxFirstStack.peek(), firstStack.peek()));
        }

        System.out.print(maxFirstStack.peek() + " ");

        for (int i = m; i < n; ++i) {
            if (secondStack.empty()) {
                while(!firstStack.empty()) {
                    secondStack.push(firstStack.pop());
                    maxFirstStack.pop();
                    maxSecondStack.push(maxSecondStack.empty() ? secondStack.peek() :
                            max(maxSecondStack.peek(), secondStack.peek()));
                }
            }

            secondStack.pop();
            maxSecondStack.pop();
            firstStack.push(numbers.get(i));
            maxFirstStack.push(maxFirstStack.empty() ? firstStack.peek() : max(maxFirstStack.peek(), firstStack.peek()));

            System.out.print((maxSecondStack.empty() ? maxFirstStack.peek() : max(maxFirstStack.peek(), maxSecondStack.peek())) + " ");
        }
    }

    public static void main(String[] args) {
        new SlidingWindowMax().run();
    }
}
