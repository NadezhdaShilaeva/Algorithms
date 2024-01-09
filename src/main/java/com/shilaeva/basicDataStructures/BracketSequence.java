package com.shilaeva.basicDataStructures;

import java.util.Scanner;
import java.util.Stack;

public class BracketSequence {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        String sequence = scanner.next();

        Stack<Character> bracketStack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();
        for (int i = 0; i < sequence.length(); ++i) {
            if (sequence.charAt(i) == '(') {
                bracketStack.push('(');
                indexStack.push(i);
            } else if (sequence.charAt(i) == '[') {
                bracketStack.push('[');
                indexStack.push(i);
            } else if (sequence.charAt(i) == '{') {
                bracketStack.push('{');
                indexStack.push(i);
            } else if (sequence.charAt(i) == ')') {
                if (!bracketStack.empty() && bracketStack.peek() == '(') {
                    bracketStack.pop();
                    indexStack.pop();
                } else {
                    System.out.println(i + 1);
                    return;
                }
            } else if (sequence.charAt(i) == ']') {
                if (!bracketStack.empty() && bracketStack.peek() == '[') {
                    bracketStack.pop();
                    indexStack.pop();
                } else {
                    System.out.println(i + 1);
                    return;
                }
            } else if (sequence.charAt(i) == '}') {
                if (!bracketStack.empty() && bracketStack.peek() == '{') {
                    bracketStack.pop();
                    indexStack.pop();
                } else {
                    System.out.println(i + 1);
                    return;
                }
            }
        }

        if (bracketStack.empty()) {
            System.out.println("Success");
        } else {
            System.out.println(indexStack.get(0) + 1);
        }
    }

    public static void main(String[] args) {
        new BracketSequence().run();
    }
}
