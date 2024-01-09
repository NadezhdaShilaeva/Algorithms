package com.shilaeva.basicDataStructures;

import java.util.Scanner;
import java.util.Stack;

public class StackWithMaxSupport {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Stack<Integer> numberStack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();
        for(int i = 0; i < n; ++i) {
            String operation = scanner.next();
            switch (operation) {
                case "push" -> {
                    int number = scanner.nextInt();
                    numberStack.push(number);
                    maxStack.push(maxStack.empty() ? number : Math.max(maxStack.peek(), number));
                }
                case "pop" -> {
                    numberStack.pop();
                    maxStack.pop();
                }
                case "max" -> System.out.println(maxStack.peek());
            }
        }
    }

    public static void main(String[] args) {
        new StackWithMaxSupport().run();
    }
}
